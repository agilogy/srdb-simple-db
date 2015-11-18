
// *********************************************************************************************************************
// ** DON'T EDIT BY HAND!                                                                                             **
// ** This file is generated                                                                                          **
// ** Use sbt test:run to run CodeGenerator                                                                           **
// *********************************************************************************************************************

package com.agilogy.simpledb

 
package dsl

import com.agilogy.simpledb.schema.Column

trait GeneratedSelectMethods{

  def select[RT](columns: Seq[SelectedElement[_]])(as: ResultSetReads[RT]): DslQuery[RT] = query(columns,as)

  protected def query[RT](columns:Seq[SelectedElement[_]], reads:ResultSetReads[RT]): DslQuery[RT]

  def select[RT,T1](c1: SelectedElement[T1]): DslQuery[T1] = query(Seq(c1),reader(row => row.get(c1)))

   def select[RT,T1,T2](c1: SelectedElement[T1],c2: SelectedElement[T2]): DslQuery[(T1,T2)] =
    query(Seq(c1,c2),reader(row => (row.get(c1),row.get(c2))))

  def select[RT,T1,T2,T3](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3]): DslQuery[(T1,T2,T3)] =
    query(Seq(c1,c2,c3),reader(row => (row.get(c1),row.get(c2),row.get(c3))))

  def select[RT,T1,T2,T3,T4](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4]): DslQuery[(T1,T2,T3,T4)] =
    query(Seq(c1,c2,c3,c4),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4))))

  def select[RT,T1,T2,T3,T4,T5](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5]): DslQuery[(T1,T2,T3,T4,T5)] =
    query(Seq(c1,c2,c3,c4,c5),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5))))

  def select[RT,T1,T2,T3,T4,T5,T6](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6]): DslQuery[(T1,T2,T3,T4,T5,T6)] =
    query(Seq(c1,c2,c3,c4,c5,c6),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7]): DslQuery[(T1,T2,T3,T4,T5,T6,T7)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15),row.get(c16))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15),row.get(c16),row.get(c17))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15),row.get(c16),row.get(c17),row.get(c18))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18],c19: SelectedElement[T19]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15),row.get(c16),row.get(c17),row.get(c18),row.get(c19))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18],c19: SelectedElement[T19],c20: SelectedElement[T20]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15),row.get(c16),row.get(c17),row.get(c18),row.get(c19),row.get(c20))))

  def select[RT,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21](c1: SelectedElement[T1],c2: SelectedElement[T2],c3: SelectedElement[T3],c4: SelectedElement[T4],c5: SelectedElement[T5],c6: SelectedElement[T6],c7: SelectedElement[T7],c8: SelectedElement[T8],c9: SelectedElement[T9],c10: SelectedElement[T10],c11: SelectedElement[T11],c12: SelectedElement[T12],c13: SelectedElement[T13],c14: SelectedElement[T14],c15: SelectedElement[T15],c16: SelectedElement[T16],c17: SelectedElement[T17],c18: SelectedElement[T18],c19: SelectedElement[T19],c20: SelectedElement[T20],c21: SelectedElement[T21]): DslQuery[(T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21)] =
    query(Seq(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21),reader(row => (row.get(c1),row.get(c2),row.get(c3),row.get(c4),row.get(c5),row.get(c6),row.get(c7),row.get(c8),row.get(c9),row.get(c10),row.get(c11),row.get(c12),row.get(c13),row.get(c14),row.get(c15),row.get(c16),row.get(c17),row.get(c18),row.get(c19),row.get(c20),row.get(c21))))


}
 