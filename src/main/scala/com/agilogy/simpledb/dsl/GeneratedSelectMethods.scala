
// *********************************************************************************************************************
// ** DON'T EDIT BY HAND!                                                                                             **
// ** This file is generated                                                                                          **
// ** Use sbt test:run to run CodeGenerator                                                                           **
// *********************************************************************************************************************

package com.agilogy.simpledb

 
package dsl

import com.agilogy.srdb.types.{SimpleDbCursorReader, DbCursorReader}
//TODO: Avoid having to import this by hand
import SimpleDbCursorReader._

trait GeneratedSelectMethods{

  def select[RT](columns: Seq[SelectedElement[_]])(as: DbCursorReader[RT]): DslQuery[RT] = query(columns,as)

  protected def query[RT](columns:Seq[SelectedElement[_]], reads:DbCursorReader[RT]): DslQuery[RT]

  def select[RT,T1](c1: SelectedElement[T1]): DslQuery[T1] = query(Seq(c1),c1.positionalReader)

   def select[RT,T1,T2](c1: SelectedElement[T1],c2: SelectedElement[T2]): DslQuery[(T1,T2)] =
    query(Seq(c1,c2),reader(c1.positionalReader,c2.positionalReader))

  def select[RT,T1,T2,T3](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3]): DslQuery[(T1,T2,T3)] =
    query(Seq(c1,c2,c3),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader))

  def select[RT,T1,T2,T3,T4](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4]): DslQuery[(T1,T2,T3,T4)] =
    query(Seq(c1,c2,c3,c4),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader))

  def select[RT,T1,T2,T3,T4,T5](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5]): DslQuery[(T1,T2,T3,T4,T5)] =
    query(Seq(c1,c2,c3,c4,c5),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6]): DslQuery[(T1,T2,T3,T4,T5,T6)] =
    query(Seq(c1,c2,c3,c4,c5,c6),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7]): DslQuery[(T1,T2,T3,T4,T5,T6,T7)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader,c16.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader,c16.positionalReader,c17.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader,c16.positionalReader,c17.positionalReader,c18.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18],c19: SelectedElement[T19]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader,c16.positionalReader,c17.positionalReader,c18.positionalReader,c19.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18],c19: SelectedElement[T19],c20: SelectedElement[T20]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader,c16.positionalReader,c17.positionalReader,c18.positionalReader,c19.positionalReader,c20.positionalReader))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18],c19: SelectedElement[T19],c20: SelectedElement[T20],c21: SelectedElement[T21]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21),reader(c1.positionalReader,c2.positionalReader,c3.positionalReader,c4.positionalReader,c5.positionalReader,c6.positionalReader,c7.positionalReader,c8.positionalReader,c9.positionalReader,c10.positionalReader,c11.positionalReader,c12.positionalReader,c13.positionalReader,c14.positionalReader,c15.positionalReader,c16.positionalReader,c17.positionalReader,c18.positionalReader,c19.positionalReader,c20.positionalReader,c21.positionalReader))


}
 