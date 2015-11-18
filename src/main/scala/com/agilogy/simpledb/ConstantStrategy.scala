package com.agilogy.simpledb

import org.joda.time.{DateTimeZone, DateTime}

private[simpledb] trait ConstantStrategy[-T] {
  def isSecure(value: T): Boolean = true

  def constant(typeName: String, value: T): String

  def secureConstant(typeName:String, value:T):Option[String] = if(isSecure(value)) Some(constant(typeName,value)) else None
}

case object AsText extends ConstantStrategy[String] {
  override def constant(typeName: String, value: String): String = s"'$value'"

  private val secureStrings = """[a-zA-Z0-9_% ]{0,20}"""

  override def isSecure(value: String): Boolean = value.matches(secureStrings)
}

case object WithTypeName extends ConstantStrategy[Any] {
  override def constant(typeName: String, value: Any): String =
    s"$typeName '$value'"
}


case object TimestampTZConstantStrategy extends ConstantStrategy[DateTime]{

  override def isSecure(value: DateTime): Boolean = value.isAfter(new DateTime(1990,1,1,0,0,0,DateTimeZone.UTC))

  override def constant(typeName: String, value: DateTime): String = {
    s"$typeName '$value'"
  }

}

case object Literal extends ConstantStrategy[Any] {
  override def constant(typeName: String, value: Any): String = value.toString
}

case class MappedConstantStrategy[T1, T2](cs: ConstantStrategy[T1], f: T2 => T1) extends ConstantStrategy[T2] {
  override def constant(typeName: String, value: T2): String = cs.constant(typeName, f(value))

  override def isSecure(value: T2): Boolean = cs.isSecure(f(value))
}

case object NoConstants extends ConstantStrategy[Any]{

  override def isSecure(value: Any): Boolean = false

  override def constant(typeName: String, value: Any): String = throw new UnsupportedOperationException("This strategy does not support constants!")
}
