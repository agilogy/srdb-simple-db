
// *********************************************************************************************************************
// ** DON'T EDIT BY HAND!                                                                                             **
// ** This file is generated                                                                                          **
// ** Use sbt test:run to run CodeGenerator                                                                           **
// *********************************************************************************************************************

package com.agilogy.simpledb

import com.agilogy.srdb.tx.Transaction
import com.agilogy.srdb.types.AtomicDbWriter


trait WithParams[RT]{
  self:Query[RT] =>

  def withParams[PT1](implicit paramType1: AtomicDbWriter[PT1]): Query1[RT, PT1] = new Query1(self,paramType1)
  def withParams[PT1,PT2](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2]): Query2[RT, PT1, PT2] = new Query2(self,paramType1,paramType2)
  def withParams[PT1,PT2,PT3](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3]): Query3[RT, PT1, PT2, PT3] = new Query3(self,paramType1,paramType2,paramType3)
  def withParams[PT1,PT2,PT3,PT4](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4]): Query4[RT, PT1, PT2, PT3, PT4] = new Query4(self,paramType1,paramType2,paramType3,paramType4)
  def withParams[PT1,PT2,PT3,PT4,PT5](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5]): Query5[RT, PT1, PT2, PT3, PT4, PT5] = new Query5(self,paramType1,paramType2,paramType3,paramType4,paramType5)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6]): Query6[RT, PT1, PT2, PT3, PT4, PT5, PT6] = new Query6(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7]): Query7[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7] = new Query7(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8]): Query8[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8] = new Query8(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9]): Query9[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9] = new Query9(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10]): Query10[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10] = new Query10(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11]): Query11[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11] = new Query11(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12]): Query12[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12] = new Query12(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13]): Query13[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13] = new Query13(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14]): Query14[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14] = new Query14(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15]): Query15[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15] = new Query15(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16]): Query16[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15, PT16] = new Query16(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17]): Query17[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15, PT16, PT17] = new Query17(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18]): Query18[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15, PT16, PT17, PT18] = new Query18(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18],paramType19: AtomicDbWriter[PT19]): Query19[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15, PT16, PT17, PT18, PT19] = new Query19(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18],paramType19: AtomicDbWriter[PT19],paramType20: AtomicDbWriter[PT20]): Query20[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15, PT16, PT17, PT18, PT19, PT20] = new Query20(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21](implicit paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18],paramType19: AtomicDbWriter[PT19],paramType20: AtomicDbWriter[PT20],paramType21: AtomicDbWriter[PT21]): Query21[RT, PT1, PT2, PT3, PT4, PT5, PT6, PT7, PT8, PT9, PT10, PT11, PT12, PT13, PT14, PT15, PT16, PT17, PT18, PT19, PT20, PT21] = new Query21(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20,paramType21)
}
case class Query1[RT, PT1](q: Query[RT], paramType1: AtomicDbWriter[PT1]) extends QueryBase[RT](q, paramType1) {

  def apply(arg1: PT1)(implicit tx:Transaction): Seq[RT] = super.execute(arg1)(tx)
  def stream(arg1: PT1): ReadyQueryStream[RT] = super.stream(arg1)
  def map[RT2](f: (RT) => RT2): Query1[RT2, PT1] = this.copy(q = q.map(f))

}

case class Query2[RT, PT1,PT2](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2]) extends QueryBase[RT](q, paramType1,paramType2) {

  def apply(arg1: PT1,arg2: PT2)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2)(tx)
  def stream(arg1: PT1,arg2: PT2): ReadyQueryStream[RT] = super.stream(arg1,arg2)
  def map[RT2](f: (RT) => RT2): Query2[RT2, PT1,PT2] = this.copy(q = q.map(f))

}

case class Query3[RT, PT1,PT2,PT3](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3]) extends QueryBase[RT](q, paramType1,paramType2,paramType3) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3)
  def map[RT2](f: (RT) => RT2): Query3[RT2, PT1,PT2,PT3] = this.copy(q = q.map(f))

}

case class Query4[RT, PT1,PT2,PT3,PT4](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4)
  def map[RT2](f: (RT) => RT2): Query4[RT2, PT1,PT2,PT3,PT4] = this.copy(q = q.map(f))

}

case class Query5[RT, PT1,PT2,PT3,PT4,PT5](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5)
  def map[RT2](f: (RT) => RT2): Query5[RT2, PT1,PT2,PT3,PT4,PT5] = this.copy(q = q.map(f))

}

case class Query6[RT, PT1,PT2,PT3,PT4,PT5,PT6](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6)
  def map[RT2](f: (RT) => RT2): Query6[RT2, PT1,PT2,PT3,PT4,PT5,PT6] = this.copy(q = q.map(f))

}

case class Query7[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7)
  def map[RT2](f: (RT) => RT2): Query7[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7] = this.copy(q = q.map(f))

}

case class Query8[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8)
  def map[RT2](f: (RT) => RT2): Query8[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8] = this.copy(q = q.map(f))

}

case class Query9[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9)
  def map[RT2](f: (RT) => RT2): Query9[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9] = this.copy(q = q.map(f))

}

case class Query10[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10)
  def map[RT2](f: (RT) => RT2): Query10[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10] = this.copy(q = q.map(f))

}

case class Query11[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11)
  def map[RT2](f: (RT) => RT2): Query11[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11] = this.copy(q = q.map(f))

}

case class Query12[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12)
  def map[RT2](f: (RT) => RT2): Query12[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12] = this.copy(q = q.map(f))

}

case class Query13[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13)
  def map[RT2](f: (RT) => RT2): Query13[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13] = this.copy(q = q.map(f))

}

case class Query14[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14)
  def map[RT2](f: (RT) => RT2): Query14[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14] = this.copy(q = q.map(f))

}

case class Query15[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15)
  def map[RT2](f: (RT) => RT2): Query15[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15] = this.copy(q = q.map(f))

}

case class Query16[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16)
  def map[RT2](f: (RT) => RT2): Query16[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16] = this.copy(q = q.map(f))

}

case class Query17[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17)
  def map[RT2](f: (RT) => RT2): Query17[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17] = this.copy(q = q.map(f))

}

case class Query18[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18)
  def map[RT2](f: (RT) => RT2): Query18[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18] = this.copy(q = q.map(f))

}

case class Query19[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18],paramType19: AtomicDbWriter[PT19]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19)
  def map[RT2](f: (RT) => RT2): Query19[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19] = this.copy(q = q.map(f))

}

case class Query20[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18],paramType19: AtomicDbWriter[PT19],paramType20: AtomicDbWriter[PT20]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20)
  def map[RT2](f: (RT) => RT2): Query20[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20] = this.copy(q = q.map(f))

}

case class Query21[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21](q: Query[RT], paramType1: AtomicDbWriter[PT1],paramType2: AtomicDbWriter[PT2],paramType3: AtomicDbWriter[PT3],paramType4: AtomicDbWriter[PT4],paramType5: AtomicDbWriter[PT5],paramType6: AtomicDbWriter[PT6],paramType7: AtomicDbWriter[PT7],paramType8: AtomicDbWriter[PT8],paramType9: AtomicDbWriter[PT9],paramType10: AtomicDbWriter[PT10],paramType11: AtomicDbWriter[PT11],paramType12: AtomicDbWriter[PT12],paramType13: AtomicDbWriter[PT13],paramType14: AtomicDbWriter[PT14],paramType15: AtomicDbWriter[PT15],paramType16: AtomicDbWriter[PT16],paramType17: AtomicDbWriter[PT17],paramType18: AtomicDbWriter[PT18],paramType19: AtomicDbWriter[PT19],paramType20: AtomicDbWriter[PT20],paramType21: AtomicDbWriter[PT21]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20,paramType21) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20,arg21: PT21)(implicit tx:Transaction): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20,arg21)(tx)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20,arg21: PT21): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20,arg21)
  def map[RT2](f: (RT) => RT2): Query21[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21] = this.copy(q = q.map(f))

}
