package com.agilogy.simpledb.dsl

import org.joda.time.{ DateTime, DateTimeZone }

trait ConstantStrategy[-T] {
  def isSecure(value: T): Boolean = true

  def constant(value: T): String

  def secureConstant(value: T): Option[String] = if (isSecure(value)) Some(constant(value)) else None

  def map[T2](f: T2 => T): ConstantStrategy[T2] = MappedConstantStrategy(this, f)
}

object ConstantStrategy {
  implicit def optionalConstantStrategy[T: ConstantStrategy]: ConstantStrategy[Option[T]] = new ConstantStrategy[Option[T]] {

    private val cs = implicitly[ConstantStrategy[T]]

    override def isSecure(value: Option[T]): Boolean = value match {
      case None => true
      case Some(v) => cs.isSecure(v)
    }

    override def constant(value: Option[T]): String = value match {
      case None => "null"
      case Some(v) => cs.constant(v)
    }
  }
}

case object AsText extends ConstantStrategy[String] {
  override def constant(value: String): String = s"'$value'"

  private val secureStrings = """[a-zA-Z0-9_% ]{0,20}"""

  override def isSecure(value: String): Boolean = value.matches(secureStrings)
}

//case object WithTypeName extends ConstantStrategy[Any] {
//  override def constant(typeName: String, value: Any): String =
//    s"$typeName '$value'"
//}

//case object TimestampTZConstantStrategy extends ConstantStrategy[DateTime]{
//
//  override def isSecure(value: DateTime): Boolean = value.isAfter(new DateTime(1990,1,1,0,0,0,DateTimeZone.UTC))
//
//  override def constant(value: DateTime): String = {
//    s"timestamptz '$value'"
//  }
//
//}

class Literal[T] extends ConstantStrategy[T] {
  override def constant(value: T): String = value.toString
}

case class MappedConstantStrategy[T1, T2](cs: ConstantStrategy[T1], f: T2 => T1) extends ConstantStrategy[T2] {
  override def constant(value: T2): String = cs.constant(f(value))

  override def isSecure(value: T2): Boolean = cs.isSecure(f(value))
}

case object NoConstants extends ConstantStrategy[Any] {

  override def isSecure(value: Any): Boolean = false

  override def constant(value: Any): String = throw new UnsupportedOperationException(s"This strategy does not support constants! (for $value)")
}
