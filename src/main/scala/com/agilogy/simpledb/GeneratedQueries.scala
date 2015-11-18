
// *********************************************************************************************************************
// ** DON'T EDIT BY HAND!                                                                                             **
// ** This file is generated                                                                                          **
// ** Use sbt test:run to run CodeGenerator                                                                           **
// *********************************************************************************************************************

package com.agilogy.simpledb

 

trait WithParams[RT]{
  self:Query[RT] =>

  def withParams[PT1](paramType1: DbWrites[PT1]) = new Query1(self,paramType1)
  def withParams[PT1,PT2](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2]) = new Query2(self,paramType1,paramType2)
  def withParams[PT1,PT2,PT3](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3]) = new Query3(self,paramType1,paramType2,paramType3)
  def withParams[PT1,PT2,PT3,PT4](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4]) = new Query4(self,paramType1,paramType2,paramType3,paramType4)
  def withParams[PT1,PT2,PT3,PT4,PT5](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5]) = new Query5(self,paramType1,paramType2,paramType3,paramType4,paramType5)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6]) = new Query6(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7]) = new Query7(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8]) = new Query8(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9]) = new Query9(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10]) = new Query10(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11]) = new Query11(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12]) = new Query12(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13]) = new Query13(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14]) = new Query14(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15]) = new Query15(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16]) = new Query16(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17]) = new Query17(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18]) = new Query18(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19]) = new Query19(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20]) = new Query20(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20],paramType21: DbWrites[PT21]) = new Query21(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20,paramType21)
}
case class Query1[RT, PT1](q: Query[RT], paramType1: DbWrites[PT1]) extends QueryBase[RT](q, paramType1) {

  def apply(arg1: PT1)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1)(txConfig)
  def stream(arg1: PT1): ReadyQueryStream[RT] = super.stream(arg1)
  def map[RT2](f: (RT) => RT2): Query1[RT2, PT1] = this.copy(q = q.map(f))

}

case class Query2[RT, PT1,PT2](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2]) extends QueryBase[RT](q, paramType1,paramType2) {

  def apply(arg1: PT1,arg2: PT2)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2)(txConfig)
  def stream(arg1: PT1,arg2: PT2): ReadyQueryStream[RT] = super.stream(arg1,arg2)
  def map[RT2](f: (RT) => RT2): Query2[RT2, PT1,PT2] = this.copy(q = q.map(f))

}

case class Query3[RT, PT1,PT2,PT3](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3]) extends QueryBase[RT](q, paramType1,paramType2,paramType3) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3)
  def map[RT2](f: (RT) => RT2): Query3[RT2, PT1,PT2,PT3] = this.copy(q = q.map(f))

}

case class Query4[RT, PT1,PT2,PT3,PT4](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4)
  def map[RT2](f: (RT) => RT2): Query4[RT2, PT1,PT2,PT3,PT4] = this.copy(q = q.map(f))

}

case class Query5[RT, PT1,PT2,PT3,PT4,PT5](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5)
  def map[RT2](f: (RT) => RT2): Query5[RT2, PT1,PT2,PT3,PT4,PT5] = this.copy(q = q.map(f))

}

case class Query6[RT, PT1,PT2,PT3,PT4,PT5,PT6](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6)
  def map[RT2](f: (RT) => RT2): Query6[RT2, PT1,PT2,PT3,PT4,PT5,PT6] = this.copy(q = q.map(f))

}

case class Query7[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7)
  def map[RT2](f: (RT) => RT2): Query7[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7] = this.copy(q = q.map(f))

}

case class Query8[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8)
  def map[RT2](f: (RT) => RT2): Query8[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8] = this.copy(q = q.map(f))

}

case class Query9[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9)
  def map[RT2](f: (RT) => RT2): Query9[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9] = this.copy(q = q.map(f))

}

case class Query10[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10)
  def map[RT2](f: (RT) => RT2): Query10[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10] = this.copy(q = q.map(f))

}

case class Query11[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11)
  def map[RT2](f: (RT) => RT2): Query11[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11] = this.copy(q = q.map(f))

}

case class Query12[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12)
  def map[RT2](f: (RT) => RT2): Query12[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12] = this.copy(q = q.map(f))

}

case class Query13[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13)
  def map[RT2](f: (RT) => RT2): Query13[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13] = this.copy(q = q.map(f))

}

case class Query14[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14)
  def map[RT2](f: (RT) => RT2): Query14[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14] = this.copy(q = q.map(f))

}

case class Query15[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15)
  def map[RT2](f: (RT) => RT2): Query15[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15] = this.copy(q = q.map(f))

}

case class Query16[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16)
  def map[RT2](f: (RT) => RT2): Query16[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16] = this.copy(q = q.map(f))

}

case class Query17[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17)
  def map[RT2](f: (RT) => RT2): Query17[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17] = this.copy(q = q.map(f))

}

case class Query18[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18)
  def map[RT2](f: (RT) => RT2): Query18[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18] = this.copy(q = q.map(f))

}

case class Query19[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19)
  def map[RT2](f: (RT) => RT2): Query19[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19] = this.copy(q = q.map(f))

}

case class Query20[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20)
  def map[RT2](f: (RT) => RT2): Query20[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20] = this.copy(q = q.map(f))

}

case class Query21[RT, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21](q: Query[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20],paramType21: DbWrites[PT21]) extends QueryBase[RT](q, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20,paramType21) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20,arg21: PT21)(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20,arg21)(txConfig)
  def stream(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20,arg21: PT21): ReadyQueryStream[RT] = super.stream(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,arg17,arg18,arg19,arg20,arg21)
  def map[RT2](f: (RT) => RT2): Query21[RT2, PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21] = this.copy(q = q.map(f))

}
