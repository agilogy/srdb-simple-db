package com.agilogy.simpledb.dsl

import com.agilogy.simpledb._
import com.agilogy.simpledb.schema.{ Table, Column }
import com.agilogy.srdb.types._

import scala.language.implicitConversions

trait Expression[T] {

  def sql: String

  def ====(v: Expression[T]): Predicate = EqualsPredicate[T](this, v)

  def nullSafeEq(v: Expression[T]): Predicate = NullSafeEqualsPredicate[T](this, v)

  def <(v: Expression[T]): Predicate = ComparisonPredicate(this, v, LT)

  def <=(v: Expression[T]): Predicate = ComparisonPredicate(this, v, LTE)

  def >(v: Expression[T]): Predicate = ComparisonPredicate(this, v, GT)

  def >=(v: Expression[T]): Predicate = ComparisonPredicate(this, v, GTE)

  def in(v: Seq[T])(implicit writer: AtomicNotNullDbWriter[T], cs: ConstantStrategy[T]): Predicate =
    InPredicate(this, CommonConstant(v)(InClauseValuesDbWriter[T], InClauseValuesConstantStrategy[T]))

  def in(v: Expression[T]*): Predicate = InPredicate(this, new Expression[Seq[T]] {

    override def sql: String = v.map(_.sql).mkString("(", ",", ")")

    override private[simpledb] val parameters: Seq[Param[_]] = v.foldLeft[Seq[Param[_]]](Seq.empty)(_ ++ _.parameters)
  })

  /**
   * Creates a predicate that is true if and only if this expression is in the argument represented by this parameter
   * You'll need to annotate the type parameter of the Param or use inParam instead
   */
  def in(p: Param[Seq[T]]): Predicate = InPredicate(this, p)

  def inParam(pos: Int): Predicate = InPredicate(this, Param[Seq[T]](pos))

  def isNull: Predicate = IsNullPredicate(this)

  def as(alias: String)(implicit r: AtomicPositionalDbReader[T]): SelectedExpression[T] = SelectedExpression(this, alias)

  private[simpledb] val parameters: Seq[Param[_]]

  private[simpledb] val allocateConstants: ConstantAllocation[Expression[T]] = ConstantAllocation.empty(this)
}

trait BaseExpression[T] extends Expression[T]

trait Constant[T] extends Expression[T] {
  val v: T
  val writer: AtomicDbWriter[T]
  val constantStrategy: ConstantStrategy[T]
  val allocatedAs: Option[Int]
  private[simpledb] def asParameterValue = Parameter[T]("c" + allocatedAs.get)(writer).set(v)
  private[simpledb] def assignIndex(i: Int): Constant[T]
  // The sql may be computed for an expression whose constants have not been yet allocated
  // In such a case, we return '?' instead of the constant in the sql
  override lazy val sql: String = allocatedAs.map(i => ":c" + i).getOrElse(constantStrategy.secureConstant(v).getOrElse("?"))
  override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty
}

case class CommonConstant[T](v: T, allocatedAs: Option[Int] = None)(implicit val writer: AtomicDbWriter[T], val constantStrategy: ConstantStrategy[T]) extends Constant[T] {
  private[simpledb] def assignIndex(i: Int): CommonConstant[T] = this.copy(allocatedAs = Some(i))
  private[simpledb] override val allocateConstants: ConstantAllocation[CommonConstant[T]] = {
    if (constantStrategy.isSecure(v)) ConstantAllocation.empty(this)
    else ConstantAllocation.newConstant(this).asInstanceOf[ConstantAllocation[CommonConstant[T]]]
  }
}

trait StringExpression extends Expression[String] {
  def like(e2: Expression[String]): LikePredicate = LikePredicate(this, e2)
}

case class StringConstant(v: String, allocatedAs: Option[Int] = None)(implicit val writer: AtomicDbWriter[String], val constantStrategy: ConstantStrategy[String]) extends StringExpression with Constant[String] with Expression[String] {
  private[simpledb] override val allocateConstants: ConstantAllocation[StringConstant] = {
    if (constantStrategy.isSecure(v)) ConstantAllocation.empty(this)
    else ConstantAllocation.newConstant(this).asInstanceOf[ConstantAllocation[StringConstant]]
  }

  override private[simpledb] def assignIndex(i: Int): Constant[String] = this.copy(allocatedAs = Some(i))(writer, constantStrategy)
}

case class Param[T](pos: Int) extends Expression[T] {
  override lazy val sql: String = ":" + pos
  override private[simpledb] val parameters: Seq[Param[_]] = Seq(this)
}

sealed case class ConstantPredicate(v: Boolean) extends Predicate {
  override lazy val sql: String = v.toString
  override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty
}

object True extends ConstantPredicate(true)

object False extends ConstantPredicate(false)

case class OrPredicate(ps: Seq[Predicate]) extends Predicate {
  override lazy val sql: String = "(" + ps.map(_.sql).mkString(" or ") + ")"

  def or(p2: Predicate): OrPredicate = OrPredicate(ps :+ p2)

  override private[simpledb] val parameters: Seq[Param[_]] = ps.flatMap(_.parameters)

  private[simpledb] override val allocateConstants: ConstantAllocation[OrPredicate] =
    ConstantAllocation.allocateAll(ps)(_.allocateConstants).map(psa => OrPredicate(psa))
}

case class AndPredicate(ps: Seq[Predicate]) extends Predicate {
  override lazy val sql: String = "(" + ps.map(_.sql).mkString(" and ") + ")"

  def and(p2: Predicate): AndPredicate = AndPredicate(ps :+ p2)

  override private[simpledb] val parameters: Seq[Param[_]] = ps.flatMap(_.parameters)

  private[simpledb] override val allocateConstants: ConstantAllocation[AndPredicate] =
    ConstantAllocation.allocateAll(ps)(_.allocateConstants).map(psa => AndPredicate(psa))
}

case class NotPredicate(p: Predicate) extends Predicate {
  override lazy val sql: String = s"(not ${p.sql})"
  override private[simpledb] val parameters: Seq[Param[_]] = p.parameters
  private[simpledb] override val allocateConstants: ConstantAllocation[NotPredicate] =
    p.allocateConstants.map(p => NotPredicate(p))
}

trait BinaryFunction[T1, T2, RT] extends BaseExpression[RT] {
  val v1: Expression[T1]
  val v2: Expression[T2]

  override private[simpledb] val parameters: Seq[Param[_]] = v1.parameters ++ v2.parameters

  type SBF <: BinaryFunction[T1, T2, RT]
  def copy(v1: Expression[T1], v2: Expression[T2]): SBF
  private[simpledb] override val allocateConstants: ConstantAllocation[SBF] = for {
    v1a <- v1.allocateConstants
    v2a <- v2.allocateConstants
  } yield copy(v1a, v2a)
}

case class NotNullEqualsPredicate[T](v1: Expression[T], v2: Expression[T]) extends Predicate with BinaryFunction[T, T, Boolean] {
  override lazy val sql: String = s"(${v1.sql} = ${v2.sql})"

  override type SBF = NotNullEqualsPredicate[T]
  override def copy(v1: Expression[T], v2: Expression[T]): SBF = NotNullEqualsPredicate(v1, v2)
}

case class EqualsPredicate[T](v1: Expression[T], v2: Expression[T]) extends Predicate with BinaryFunction[T, T, Boolean] {
  override lazy val sql: String = s"(${v1.sql} = ${v2.sql})"
  override type SBF = EqualsPredicate[T]
  override def copy(v1: Expression[T], v2: Expression[T]): SBF = EqualsPredicate(v1, v2)
}

case class NullSafeEqualsPredicate[T](v1: Expression[T], v2: Expression[T]) extends Predicate with BinaryFunction[T, T, Boolean] {
  override lazy val sql: String = s"(${v1.sql} is not distinct from ${v2.sql})"
  override type SBF = NullSafeEqualsPredicate[T]
  override def copy(v1: Expression[T], v2: Expression[T]): SBF = NullSafeEqualsPredicate(v1, v2)
}

sealed class ComparisonOperator(val op: String) {
  override def toString: String = op
}

case object GT extends ComparisonOperator(">")

case object GTE extends ComparisonOperator(">=")

case object LT extends ComparisonOperator("<")

case object LTE extends ComparisonOperator("<=")

case class ComparisonPredicate[T](v1: Expression[T], v2: Expression[T], op: ComparisonOperator) extends Predicate with BinaryFunction[T, T, Boolean] {
  override lazy val sql: String = s"(${v1.sql} $op ${v2.sql})"

  override type SBF = ComparisonPredicate[T]
  override def copy(v1: Expression[T], v2: Expression[T]): SBF = ComparisonPredicate(v1, v2, op)
}

case class LikePredicate(v1: Expression[String], v2: Expression[String]) extends Predicate with BinaryFunction[String, String, Boolean] {
  override lazy val sql: String = s"(${v1.sql} like ${v2.sql})"

  override type SBF = LikePredicate
  override def copy(v1: Expression[String], v2: Expression[String]): SBF = LikePredicate(v1, v2)

}

case class InPredicate[T](v1: Expression[T], v2: Expression[Seq[T]]) extends Predicate with BinaryFunction[T, Seq[T], Boolean] {
  override lazy val sql: String = s"${v1.sql} in ${v2.sql}"

  override type SBF = InPredicate[T]
  override def copy(v1: Expression[T], v2: Expression[Seq[T]]): SBF = InPredicate(v1, v2)
}

case class IsNullPredicate[T](v: Expression[T]) extends Predicate {
  override lazy val sql: String = s"${v.sql} is null"
  override private[simpledb] val parameters: Seq[Param[_]] = v.parameters
  private[simpledb] override val allocateConstants: ConstantAllocation[IsNullPredicate[T]] =
    v.allocateConstants.map(va => IsNullPredicate(va))
}

case class CoalesceExpression[T](args: Seq[Expression[Option[T]]]) extends Expression[Option[T]] {
  override lazy val sql: String = s"coalesce(${args.map(_.sql).mkString(",")})"
  override private[simpledb] val parameters: Seq[Param[_]] = args.flatMap(_.parameters)
  override private[simpledb] val allocateConstants: ConstantAllocation[CoalesceExpression[T]] =
    ConstantAllocation.allocateAll(args)(_.allocateConstants).map(argsa => CoalesceExpression(argsa))
}

case class CoalesceNotNullExpression[T](args: Seq[Expression[Option[T]]], defaultValue: Expression[T]) extends Expression[T] {
  override lazy val sql: String = s"coalesce(${args.map(_.sql).mkString(",")},${defaultValue.sql.trim})"
  override private[simpledb] val parameters: Seq[Param[_]] = args.flatMap(_.parameters)
  override private[simpledb] val allocateConstants: ConstantAllocation[CoalesceNotNullExpression[T]] =
    ConstantAllocation.allocateAll(args)(_.allocateConstants).flatMap(argsa =>
      defaultValue.allocateConstants.map(defaultValue => CoalesceNotNullExpression(argsa, defaultValue)))
}

class NullExpression[T] extends Expression[T] {
  override val sql: String = "null"
  override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty
}

trait ColumnExpression[T] extends Expression[T] {
  val c: Column[T]
  override lazy val sql: String = c.completeName

  def canEqual(other: Any): Boolean = other.isInstanceOf[ColumnExpression[_]]

  override def equals(other: Any): Boolean = other match {
    case that: ColumnExpression[_] => (that canEqual this) && c == that.c
    case _ => false
  }

  override def hashCode(): Int = {
    c.hashCode()
  }

  override def toString: String = s"ColumnExpression($c)"

  def :=(e: Expression[T]): ColumnAssignment[T] = ColumnAssignment(c, e)

  override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty
}

trait LowPriorityConstantStrategy {
  //  implicit val noConstants: ConstantStrategy[Any] = NoConstants
}

trait ExpressionSyntax extends LowPriorityConstantStrategy {

  type Predicate = Expression[Boolean]

  implicit class StringColumnExpression(val c: Column[String]) extends StringExpression with ColumnExpression[String]

  implicit class BooleanColumnExpression(val c: Column[Boolean]) extends Predicate with ColumnExpression[Boolean]

  implicit class IntColumnExpression(val c: Column[Int]) extends Expression[Int] with ColumnExpression[Int]

  implicit class LongColumnExpression(val c: Column[Long]) extends Expression[Long] with ColumnExpression[Long]

  implicit class GenericColumnExpression[T](val c: Column[T]) extends ColumnExpression[T]

  implicit class PredicateOps(val e: Expression[Boolean]) {

    def or(p2: Predicate): OrPredicate = OrPredicate(Seq(e, p2))

    def and(p2: Predicate): AndPredicate = AndPredicate(Seq(e, p2))

  }

  case class BasicColumnExpression[T](c: Column[T]) extends ColumnExpression[T]

  case class AggregateExpression[T, T2](aggregateFunction: String, c: Column[T]) extends Expression[T2] {
    override lazy val sql: String = aggregateFunction + "(" + c.completeName + ")"
    override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty
  }

  implicit def const(b: Boolean): Predicate = if (b) True else False

  implicit def const(s: String)(implicit writer: AtomicDbWriter[String], constantStrategy: ConstantStrategy[String]): StringConstant = StringConstant(s)(writer, constantStrategy)

  //TODO: And Option[String]?
  implicit def const[T](v: Option[T])(implicit writer: AtomicOptionalDbWriter[T], constantStrategy: ConstantStrategy[T]): Constant[Option[T]] = CommonConstant(v)

  implicit def const[T](v: T)(implicit writer: AtomicNotNullDbWriter[T], constantStrategy: ConstantStrategy[T]): Constant[T] = CommonConstant(v)

  def not(p: Predicate): NotPredicate = NotPredicate(p)

  def param[T](pos: Int): Param[T] = Param[T](pos)

  /**
   * Builds a coalesce expression resulting in an optional result.
   * Calls MUST explicitly annotatate the call with the type of `T` as in `coalesce[Int](p.headOf, 23)`
   */
  def coalesce[T](args: Expression[Option[T]]*): CoalesceExpression[T] = CoalesceExpression(args)

  /**
   * Builds a coalesce expression resulting in an optional result.
   * Calls MUST explicitly annotatate the call with the type of `T` as in `coalesce[Int](p.headOf, 23)`
   */
  def coalesce[T](arg1: Expression[Option[T]], defaultValue: Expression[Option[T]]): CoalesceExpression[T] = CoalesceExpression(Seq(arg1, defaultValue))

  /**
   * Builds a coalesce expression resulting in a not null result.
   * Calls MUST explicitly annotatate the call with the type of `T` as in `coalesce[Int](p.headOf, 23)`
   */
  def coalesce[T](args: Seq[Expression[Option[T]]], defaultValue: Expression[T]): CoalesceNotNullExpression[T] = CoalesceNotNullExpression(args, defaultValue)

  /**
   * Builds a coalesce expression resulting in a not null result.
   * Calls MUST explicitly annotatate the call with the type of `T` as in `coalesce[Int](p.headOf, 23)`
   */
  def coalesce[T](arg1: Expression[Option[T]], defaultValue: Expression[T]): CoalesceNotNullExpression[T] =
    CoalesceNotNullExpression(Seq(arg1), defaultValue)

  /**
   * Builds a coalesce expression resulting in a not null result.
   * Calls MUST explicitly annotatate the call with the type of `T` as in `coalesce[Int](p.headOf, 23)`
   */
  def coalesce[T](arg1: Expression[Option[T]], arg2: Expression[Option[T]], defaultValue: Expression[T]): CoalesceNotNullExpression[T] =
    CoalesceNotNullExpression(Seq(arg1, arg2), defaultValue)

  def Null[T]: NullExpression[T] = new NullExpression[T]

  def sum[T](c: Column[T]): AggregateExpression[T, c.positionalReader.Optional] = AggregateExpression("sum", c)

  def max[T](c: Column[T]): AggregateExpression[T, c.positionalReader.Optional] = AggregateExpression("max", c)

  def min[T](c: Column[T]): AggregateExpression[T, c.positionalReader.Optional] = AggregateExpression("min", c)

  def every(c: Column[Boolean]): AggregateExpression[Boolean, c.positionalReader.Optional] = AggregateExpression("every", c)

  def boolAnd(c: Column[Boolean]): AggregateExpression[Boolean, Boolean] = AggregateExpression("bool_and", c)

  def boolOr(c: Column[Boolean]): AggregateExpression[Boolean, Boolean] = AggregateExpression("bool_or", c)

  def stringAgg(c: Column[String]): AggregateExpression[String, String] = AggregateExpression("string_agg", c)

  def count[T](c: Column[T]): AggregateExpression[T, Int] = AggregateExpression("count", c)

  def aggregateFunction[I, O](name: String): (Column[I]) => AggregateExpression[I, O] = {
    c => AggregateExpression[I, O](name, c)
  }

  case class NotNullExpression[T](arg: Expression[Option[T]]) extends BaseExpression[T] {

    override def sql: String = arg.sql

    override private[simpledb] val parameters: Seq[Param[_]] = arg.parameters

    private[simpledb] override val allocateConstants: ConstantAllocation[NotNullExpression[T]] = for {
      res <- arg.allocateConstants
    } yield copy(res)
  }

  def notNull[T](arg: Expression[Option[T]]): Expression[T] = NotNullExpression[T](arg)

  //  implicit def selectedColumns(c:Seq[Column[_]]):Seq[SelectedColumn[_]] = c.map(c => SelectedColumn(c))

  implicit def table2Relation(t: Table): JoinRelation = Relation.table2Relation(t)

  implicit val stringConstantStrategy: ConstantStrategy[String] = AsText
  implicit val byteConstantStrategy = new Literal[Byte]
  implicit val shortConstantStrategy = new Literal[Short]
  implicit val intConstantStrategy: ConstantStrategy[Int] = new Literal[Int]
  implicit val longConstantStrategy: ConstantStrategy[Long] = new Literal[Long]
  implicit val floatConstantStrategy = new Literal[Float]
  implicit val doubleConstantStrategy = new Literal[Double]
  implicit val booleanConstantStrategy: ConstantStrategy[Boolean] = new Literal[Boolean]
  implicit val bdConstantStrategy: ConstantStrategy[BigDecimal] = NoConstants
  implicit val biConstantStrategy: ConstantStrategy[BigInt] = NoConstants

}

object ExpressionSyntax extends ExpressionSyntax