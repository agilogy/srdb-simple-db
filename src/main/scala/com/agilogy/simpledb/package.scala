package com.agilogy

import com.agilogy.simpledb.schema.Column
import com.agilogy.srdb.types.{ AtomicNamedDbReader, DbReader, SimpleDbCursorReader, Types }

import scala.language.implicitConversions

package object simpledb extends StatementFactory with QueryFactory with Types {

  implicit def simpleCursorReader[RT](dbReader: DbReader[RT]): SimpleDbCursorReader[RT] = SimpleDbCursorReader.simpleCursorReader(dbReader)
  implicit def columnToReader[T](c: Column[T]): AtomicNamedDbReader[T] = c.reader

}
