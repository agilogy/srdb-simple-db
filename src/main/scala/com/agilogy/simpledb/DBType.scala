package com.agilogy.simpledb

import java.sql._

import com.agilogy.srdb.types._
import org.joda.time._

import scala.util.control.NonFatal

trait DbReadsOrWrites{

  private[simpledb] type NotNull

  private[simpledb] type Optional = Option[NotNull]

  val typeName: String

}

trait DbReads[T] extends DbReadsOrWrites{

  private[simpledb] def get(rs: ResultSet, name: String): T

  private[simpledb] def get(rs: ResultSet, pos: Int): T

  private[simpledb] def getScalar(rs: ResultSet): T = get(rs, 1)

}

object DbReads{

  private[simpledb] def getOpt[T](dbReads:DbReads[_], columnType:ColumnType[T], optional:Boolean, rs: ResultSet, name: String): Option[T] =  try{
    columnType.get(rs,name)
  } catch {
    case NonFatal(t) => throw new ColumnReadException("??", RowValue(Seq()), name, dbReads, optional, t)
  }

  private[simpledb] def getOpt[T](dbReads:DbReads[_], columnType:ColumnType[T], optional:Boolean, rs: ResultSet, pos: Int): Option[T] =  try{
    columnType.get(rs,pos)
  } catch {
    case NonFatal(t) => throw new ColumnReadException("??", RowValue(Seq()), pos.toString, dbReads, optional, t)
  }

}

trait DbWrites[T] extends DbReadsOrWrites{

  private[simpledb] def set(s: PreparedStatement, position: Int, value: T): Int

  private[simpledb] val constantStrategy:ConstantStrategy[NotNull]

  private[simpledb] def constant(value: T): Option[String]

  private[simpledb] def isSecureConstant(v: T): Boolean = constant(v).nonEmpty

  private[simpledb] def getJdbcPlaceHolderFor(value: Any): String = "?"
}

trait DbType[T] extends DbReads[T] with DbWrites[T]{

  val notNull: NotNullDbType[NotNull]

  val optional: OptionalDbType[NotNull]
}

case class NotNullDbType[T](columnType:ColumnType[T], constantStrategy:ConstantStrategy[T]) extends DbType[T]{

  private[simpledb] override type NotNull = T

  override val typeName: String = columnType.jdbcTypes.head.name

  private[simpledb] override final def get(rs: ResultSet, name: String): T = {
    DbReads.getOpt(this, columnType, optional = false, rs, name).getOrElse(throw new NullColumnReadException("?", RowValue(Seq()), name, this))
  }

  private[simpledb] override final def get(rs: ResultSet, pos: Int): T = {
    DbReads.getOpt(this, columnType, optional = false, rs, pos).getOrElse(throw new NullColumnReadException("?", RowValue(Seq()), "1", this))
  }

  private[simpledb] override def set(s: PreparedStatement, position: Int, value: T): Int = {
    columnType.set(s,position,Some(value))
    1
  }

  private[simpledb] override def constant(value: T): Option[String] = constantStrategy.secureConstant(typeName,value)

  override val notNull: NotNullDbType[NotNull] = this

  override val optional = OptionalDbType(this)

  /** Returns a new dbType that uses the same database type but maps it to a different Scala type T2
    * @param w: A function from the new Scala type T2 to the scala type supported by this DbType
    * @param r: A function from the type supported by this DbType to the new Scala type */
  def map[T2](w: T2 => T)(r: T => T2): NotNullDbType[T2] = {
    new NotNullDbType[T2](columnType.xmap(r,w), MappedConstantStrategy(constantStrategy, w))
  }

  val sqlType: Int = columnType.jdbcTypes.head.code

}

//case class BasicNotNullDbType[T](typeName: String, sqlType: Int, constantStrategy: ConstantStrategy[T])
//                           (writes: (PreparedStatement, Int, T) => Unit)
//                           (readsByName: (ResultSet, String) => T)
//                           (readsByIndex: (ResultSet, Int) => T)
//  extends NotNullDbType[T] {
//
//  private[simpledb] override def set(s: PreparedStatement, position: Int, value: T): Int = {
//    writes(s, position, value)
//    1
//  }
//
//  private[simpledb] def read(rs: ResultSet, name: String): T = readsByName(rs, name)
//
//  private[simpledb] def read(rs: ResultSet, idx: Int): T = readsByIndex(rs, idx)
//
//  override def map[T2](w: T2 => T)(r: T => T2): NotNullDbType[T2] = {
//    new BasicNotNullDbType[T2](typeName, sqlType, MappedConstantStrategy(constantStrategy, w))((s, pos, v) => writes(s, pos, w(v)))((rs, n) => r(readsByName(rs, n)))((rs, i) => r(readsByIndex(rs, i)))
//  }
//}

case class OptionalDbType[T](notNull:NotNullDbType[T]) extends DbType[Option[T]] {

  private[simpledb] val columnType:ColumnType[T] = notNull.columnType

  private[simpledb] override val constantStrategy: ConstantStrategy[T] = notNull.constantStrategy

  private[simpledb] override type NotNull = T

  override val typeName = columnType.jdbcTypes.head.name

  private[simpledb] override def get(rs: ResultSet, name: String): Option[T] = DbReads.getOpt(this, columnType, optional = true, rs, name)

  private[simpledb] override def get(rs: ResultSet, pos: Int): Option[T] = DbReads.getOpt(this, columnType, optional = true, rs, pos)

  private[simpledb] override def set(s: PreparedStatement, position: Int, value: Option[T]):Int = {
    columnType.set(s,position,value)
    1
  }

  override def constant(value: Option[T]): Option[String] = value.map(v => constantStrategy.secureConstant(typeName,v)).getOrElse(Some("null"))

  override val optional: OptionalDbType[NotNull] = this
}

class InClauseValuesDbType[T] private[simpledb](val dbType: NotNullDbType[T]) extends DbWrites[Seq[T]] {

  private[simpledb] override type NotNull = Seq[T]

  override def getJdbcPlaceHolderFor(value: Any): String = {
    val seq = value.asInstanceOf[Seq[T]]
    "(" + seq.map(v => "?").mkString(",") + ")"
  }

  override def set(s: PreparedStatement, position: Int, seq: Seq[T]): Int = {
    var i = 0
    for (v <- seq) {
      dbType.set(s, position + i, v)
      i += 1
    }
    i
  }

  override val constantStrategy: ConstantStrategy[Seq[T]] = new ConstantStrategy[Seq[T]] {

    override def constant(typeName: String, value: Seq[T]): String = {
      val asConstants = value.flatMap(v => dbType.constant(v))
      "(" + asConstants.mkString(", ") + ")"
    }

    override def isSecure(value: Seq[T]): Boolean = value.forall(dbType.isSecureConstant)
  }

  override val typeName: String = "Array"

  override private[simpledb] def constant(value: Seq[T]): Option[String] = constantStrategy.secureConstant(typeName,value)
}

trait DbTypeImplicits {

  def dbType[T:ColumnType](constantStrategy: ConstantStrategy[T] = NoConstants) = NotNullDbType(implicitly[ColumnType[T]],constantStrategy)

  implicit val smallint: NotNullDbType[Short] = dbType(Literal)
  implicit val integer: NotNullDbType[Int] = dbType(Literal)
  implicit val bigint: NotNullDbType[Long] = dbType(Literal)

  implicit val real: NotNullDbType[Float] = dbType(Literal)
  implicit val double: NotNullDbType[Double] = dbType(Literal)
  implicit val numeric: NotNullDbType[BigDecimal] = dbType(Literal)
  implicit val numericInt: NotNullDbType[BigInt] = dbType[BigInt](Literal)

  val text: NotNullDbType[String] = dbType(AsText)
  implicit val textDbType = text


  implicit val dateTimeColumnType: ColumnType[DateTime] = ColumnType.from[DateTime](v => new Timestamp(v.getMillis),
  (s, p, v) => s.setTimestamp(p, new Timestamp(v.getMillis)), { (rs, pos) =>
    val ts = rs.getTimestamp(pos)
    if (ts == null) null
    else new DateTime(ts)
  }, { (rs, name) =>
    val ts = rs.getTimestamp(name)
    if (ts == null) null
    else new DateTime(ts)
  },
  JdbcType.TimestampTZ)
  val timestamp: NotNullDbType[DateTime] = dbType[DateTime](TimestampTZConstantStrategy)
  implicit val timestampDbType = timestamp

  implicit val localDateColumnType = ColumnType.from[LocalDate](v => v.toDate,
  (s, p, v) => s.setObject(p, v.toString, Types.DATE), { (rs, pos) =>
    val d = rs.getDate(pos)
    if (d == null) null
    else LocalDate.fromDateFields(d)
  }, { (rs, name) =>
    val d = rs.getDate(name)
    if (d == null) null
    else LocalDate.fromDateFields(d)
  },
  JdbcType.Date)
  val date: NotNullDbType[LocalDate] = dbType[LocalDate](WithTypeName)
  implicit val dateDbType = date

  implicit val localTimeColumnType = ColumnType.from[LocalTime](v => v.toString,
  (ps, pos, value) => ps.setObject(pos, value.toString, Types.TIME), { (rs, pos) =>
    val t = rs.getTime(pos)
    if (t == null) null
    else new LocalTime(t)
  }, { (rs, name) =>
    val t = rs.getTime(name)
    if (t == null) null
    else new LocalTime(t)
  },
  JdbcType.Time)
  val time: NotNullDbType[LocalTime] = dbType[LocalTime](WithTypeName)
  implicit val timeDbType = time

  //TODO: See http://stackoverflow.com/questions/8727900/how-to-operate-on-postgresql-interval-datatype-using-jdbc-spring-jdbc-not-using
  //  implicit val interval:DbType[Interval] = withSetter[Interval](Types.OTHER)((ps,pos,value) => ps.setObject(pos,value,Types.OTHER))

  implicit val boolean: NotNullDbType[Boolean] = dbType[Boolean](Literal)


  def inClauseValues[T](dbType: NotNullDbType[T]): InClauseValuesDbType[T] = new InClauseValuesDbType[T](dbType)

  @deprecated("Use inClauseValues instead", since = "4.6")
  def seqOf[T](dbType: NotNullDbType[T]): InClauseValuesDbType[T] = inClauseValues(dbType)

  implicit def optionalDbType[T](implicit dbType:NotNullDbType[T]): OptionalDbType[T] = dbType.optional
}

