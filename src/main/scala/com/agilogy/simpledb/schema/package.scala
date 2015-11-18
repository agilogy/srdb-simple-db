package com.agilogy.simpledb

import com.agilogy.srdb.types.AtomicNamedDbReader

import scala.language.implicitConversions

package object schema {

  implicit def columnToReader[T](c:Column[T]): AtomicNamedDbReader[T] = c.reader

}
