# simpleDb

**simpleDb** is a simple to use, imperative JDBC layer for Scala. It simply provides:

- A thin transaction demarcation layer to handle transactions in your call stack
- A thin layer to write and execute statements and queries without manually dealing  with JDBC
- A simple SQL Scala DSL so that your queries and query fragments compose and typecheck

## Usage and Alternatives

**simpleDb** was created long ago for internal use at [Agilogy](www.agilogy.com) and its already in use in production in several of our projects. We are therefore maintaining it for internal use, but not promoting its usage. 

Since its creation, several open source, more mature alternatives have appeared. Although you can freely use **simpleDb**, you probably will prefer one of those.

In particular, we like [doobie](https://github.com/tpolecat/doobie), an elegant jdbc library for a more civilized age. 

## Quick Start

To use **simpleDb** add the following dependency to your build.sbt:

```scala
libraryDependencies += "com.agilogy" %% "simple-db" % "0.3.1.1"
```

The supported Scala versions are, currently, 2.10, 2.11 and 2.12.

## SimpleDb entry points

SimpleDb offers 2 basic classes whose instances can be injected wherever they are needed:
	- Database: Represents the database, and allows to demarcate transaction boundaries and to create and execute queries and statements
	- TransactionController: Allows to demarcate transaction boundaries. Its motivation is to allow such demarcation to be used in layers wich are not execpted to use any other database feature.

# Imports

SimpleDb has been designed with simplicity in mind. That's true for the imports, as well, and we try harder to avoid confusion to the end user. For that reason, almost all needed classes, singleton objects and helpers can be imported with just a couple of imports:

	import com.agilogy.simpledb.SimpleDb._
	import Syntax._

For those classes that want to just use transaction demarcation (like may be the case of the service layer), it is preferrable to just import the classes needed, usually like this:

	import com.agilogy.simpledb.SimpleDb.{TransactionConfig, TransactionController}


# Transaction demarcation

Transaction configuration in SimpleDb is represented by 2 classes:
	- TransactionConfig: Reprensents the transactional context
	- Transaction: Represents an actual transaction

Instances of both classes can be passed to a method, usually as a separate parameter list. Such methods are classified into 2 categories:

	1) Methods that require a transaction: They should only be invoked when a transaction is in place, and will be executed within that transaction
	2) Methods that support transactions: They may be invoked when a transaction is in place. If no transaction is in place, we expect them to create a new transaction and execute all the method as a new transaction

SimpleDb tries to represent this distinction at compile time. To do so:

	1) Methods that require a transaction can be declared and used like this:

    def someMethod1(param1:ParamType1, ...)(tx:Transaction)
    // Using an existing transaction tx:
    someMethod1(...)(tx)
    
    2) Methods that support a transaction can be declared and used like this:
    
    def someMethod2(param1:ParamType1, ...)(txConfig:TransactionConfig)
    // Using an existing transaction tx:
    someMethod2(...)(tx)
    // To execute it with a new transaction:
    someMethod2(...)(NewTransaction)


## Programming methods that support transactions

Methods that support transactions should:

	1) Declare a TransactionConfig parameter

	2) Demarcate a transaction with Database.inTransaction or TransactionController.inTransaction

	3) Never invoke methods that execute transactions without passing the transaction from the "inTransaction" block

Example:

    def someMethod1(param1: ParamType1, ...)(txConfig: TransactionConfig): ResultType = {
    	db.inTransaction{
    		tx =>
    			someOtherMethod(...)(tx)
    			someYetAnotherMethod(...)(tx)
    			resultingExpression
    	}(txConfig)
    }

Note that Database.inTransaction and TransactionController.inTransaction both return the value of the expression inside the transactional block.

## Programming methods that require a transaction

Methods that require a transaction need not to declare the transaction boundaries as they will always be executed in the context of an existing transaction:

    def someMethod2(param1:ParamType1, ...)(tx:Transaction): ResultType = {
    	someOtherMethod(...)(tx)
    	someYetAnotherMethod(...)(tx)
    	resultingExpression
    }

## Transaction demarcation and implicit parameters

Parameters of both type TransactionConfig and Transaction can be declared as implicit, so that transaction demarcation is more fluent. But take into account that making the transaction demarcation "invisible" may be error prone. If you like this approach, we strongly suggest to avoid combining it with default values for those implicit parameters.

With implicit transactions, the example of someMethod1 would be something like this:

    def someMethod1(param1:ParamType1, ...)(implicit txConfig:TransactionConfig): ResultType = {
    	db.inTransaction{
    		implicit tx =>
    			someOtherMethod(...)
    			someYetAnotherMethod(...)
    			resultingExpression
    	}
    }

# Creating queries and statements

SimpleDb was designed to keep the creation of queries and statements separated from their usage. The idea is that you create the queries and statements at initialization time, so that some errors can be thrown when the system is initialized, which allows to detect some errors even when the query or statement has not yet been executed. In order to have these benefits, the created queries and statements must be kept as vals so that they can be used later.

There are 2 ways to create a query or statement:
	- Using plain SQL strings
	- Using the SimpleDb DSL for the SQL

The DSL is documented below, in a different section of the documentation.

## Creating statements using plain SQL strings

Statements can be created with the Database.createStatement method.

    val deleteClients = db.createStatement("delete from clients")

In the case of *insert* statements, sometimes we want to recover the generated key after the insertion. In such cases, there is a different version of Database.createStatement that expects a second argument telling the framework how to read the key:

    val insertClient = db.createStatement("insert into greetings(msg) values('hi')", keyReader)

For documentation about the keyReader, see below. 

## Type safe parameters for queries and statements

Both Database.createQuery and Database.createStatement return an object (RawQuery and RawStatement) that can be executed.

//TODO

## Known limitations

- Obviously, you can **not** create the query or statement if the query or statement to execute are dynamic, in the sense that the exact query or statement you want to execute depend on the actual arguments of the request to the system.

- Some basic queries or statements are easier to use with the predefined operations in Database (like select, update, delete, insert, insertAndGetKey, insertIfNotFound, etc)


