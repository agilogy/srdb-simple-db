package com.agilogy.simpledb

import scala.language.implicitConversions


package object schema {

  implicit def columnToKeyReader[T](c:Column[T]):(Row => T) = _.get(c)

}
