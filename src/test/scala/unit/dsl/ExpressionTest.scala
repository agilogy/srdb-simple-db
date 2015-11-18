package unit.dsl

import javax.sql.DataSource

import com.agilogy.simpledb.dsl.Predicate
import org.postgresql.jdbc2.optional.SimpleDataSource
import org.scalatest.FlatSpec

import com.agilogy.simpledb._
import com.agilogy.simpledb.schema._
import com.agilogy.simpledb.dsl._
class ExpressionTest extends FlatSpec{

  private val mockDs:DataSource = new SimpleDataSource

  private val db = Database(mockDs)

  behavior of "columns"
  
  case class People(alias:String) extends Table{
    val id = notNull[Long]("id")
    val inactive = notNull[Boolean]("inactive")
    val deleted = notNull[Boolean]("deleted")
    val age = notNull[Int]("age")
    override val tableName: String = "people"
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }
  
  private val p = People("p")
  
  they should "be valid expressions" in {
    db.from(p).where(p.inactive)
    val e:Predicate = p.inactive and p.deleted
    val e2:Predicate = p.inactive or true
    val e3:Predicate = p.inactive ==== true
    val e4:Predicate = p.inactive nullSafeEq true
    val e5:Predicate = p.inactive < true
    val e6:Predicate = p.inactive <= true
    val e7:Predicate = p.inactive > true
    val e8:Predicate = p.inactive >= true
    val e9:Predicate = p.inactive in Seq(true, false)
    val e11:Predicate = p.inactive.isNull
  }

  // TODO review these tests
  behavior of "seqs of expressions"
  
  ignore should "be a valid expression" in {
//        val e10:Predicate = p.inactive in Seq(const(true), p.deleted)
  }
  
  behavior of "sum aggregate function"
  
  it should "have an optional dbType" in {
    val res:Query0[Option[Int]] = db.from(p).select(sum(p.age).as("ages")).withoutParams

  } 
  

}
