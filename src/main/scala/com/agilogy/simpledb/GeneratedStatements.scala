
// *********************************************************************************************************************
// ** DON'T EDIT BY HAND!                                                                                             **
// ** This file is generated                                                                                          **
// ** Use sbt test:run to run CodeGenerator                                                                           **
// *********************************************************************************************************************

package com.agilogy.simpledb

 

trait StatementWithParams[RT]{
  val self:RawStatement[RT]

  def withParams[PT1](paramType1: DbWrites[PT1]) = new Statement1(self,paramType1)
  def withParams[PT1,PT2](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2]) = new Statement2(self,paramType1,paramType2)
  def withParams[PT1,PT2,PT3](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3]) = new Statement3(self,paramType1,paramType2,paramType3)
  def withParams[PT1,PT2,PT3,PT4](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4]) = new Statement4(self,paramType1,paramType2,paramType3,paramType4)
  def withParams[PT1,PT2,PT3,PT4,PT5](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5]) = new Statement5(self,paramType1,paramType2,paramType3,paramType4,paramType5)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6]) = new Statement6(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7]) = new Statement7(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8]) = new Statement8(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9]) = new Statement9(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10]) = new Statement10(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11]) = new Statement11(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12]) = new Statement12(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13]) = new Statement13(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14]) = new Statement14(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15]) = new Statement15(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16]) = new Statement16(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17]) = new Statement17(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18]) = new Statement18(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19]) = new Statement19(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20]) = new Statement20(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20)
  def withParams[PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21](paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20],paramType21: DbWrites[PT21]) = new Statement21(self,paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20,paramType21)
}
      
class Statement1[RT,PT1](stmt:RawStatement[RT], paramType1: DbWrites[PT1])
  extends StatementByPositionBase[RT](stmt, paramType1) {

  def apply(arg1: PT1)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1))

}

class Statement2[RT,PT1,PT2](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2) {

  def apply(arg1: PT1,arg2: PT2)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2))

}

class Statement3[RT,PT1,PT2,PT3](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3))

}

class Statement4[RT,PT1,PT2,PT3,PT4](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4))

}

class Statement5[RT,PT1,PT2,PT3,PT4,PT5](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5))

}

class Statement6[RT,PT1,PT2,PT3,PT4,PT5,PT6](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6))

}

class Statement7[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7))

}

class Statement8[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8))

}

class Statement9[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9))

}

class Statement10[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10))

}

class Statement11[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11))

}

class Statement12[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12))

}

class Statement13[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13))

}

class Statement14[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14))

}

class Statement15[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15))

}

class Statement16[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15),PositionalArgument(arg16,paramType16))

}

class Statement17[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15),PositionalArgument(arg16,paramType16),PositionalArgument(arg17,paramType17))

}

class Statement18[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15),PositionalArgument(arg16,paramType16),PositionalArgument(arg17,paramType17),PositionalArgument(arg18,paramType18))

}

class Statement19[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15),PositionalArgument(arg16,paramType16),PositionalArgument(arg17,paramType17),PositionalArgument(arg18,paramType18),PositionalArgument(arg19,paramType19))

}

class Statement20[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15),PositionalArgument(arg16,paramType16),PositionalArgument(arg17,paramType17),PositionalArgument(arg18,paramType18),PositionalArgument(arg19,paramType19),PositionalArgument(arg20,paramType20))

}

class Statement21[RT,PT1,PT2,PT3,PT4,PT5,PT6,PT7,PT8,PT9,PT10,PT11,PT12,PT13,PT14,PT15,PT16,PT17,PT18,PT19,PT20,PT21](stmt:RawStatement[RT], paramType1: DbWrites[PT1],paramType2: DbWrites[PT2],paramType3: DbWrites[PT3],paramType4: DbWrites[PT4],paramType5: DbWrites[PT5],paramType6: DbWrites[PT6],paramType7: DbWrites[PT7],paramType8: DbWrites[PT8],paramType9: DbWrites[PT9],paramType10: DbWrites[PT10],paramType11: DbWrites[PT11],paramType12: DbWrites[PT12],paramType13: DbWrites[PT13],paramType14: DbWrites[PT14],paramType15: DbWrites[PT15],paramType16: DbWrites[PT16],paramType17: DbWrites[PT17],paramType18: DbWrites[PT18],paramType19: DbWrites[PT19],paramType20: DbWrites[PT20],paramType21: DbWrites[PT21])
  extends StatementByPositionBase[RT](stmt, paramType1,paramType2,paramType3,paramType4,paramType5,paramType6,paramType7,paramType8,paramType9,paramType10,paramType11,paramType12,paramType13,paramType14,paramType15,paramType16,paramType17,paramType18,paramType19,paramType20,paramType21) {

  def apply(arg1: PT1,arg2: PT2,arg3: PT3,arg4: PT4,arg5: PT5,arg6: PT6,arg7: PT7,arg8: PT8,arg9: PT9,arg10: PT10,arg11: PT11,arg12: PT12,arg13: PT13,arg14: PT14,arg15: PT15,arg16: PT16,arg17: PT17,arg18: PT18,arg19: PT19,arg20: PT20,arg21: PT21)(implicit txConfig: TransactionConfig): RT = stmt.apply(PositionalArgument(arg1,paramType1),PositionalArgument(arg2,paramType2),PositionalArgument(arg3,paramType3),PositionalArgument(arg4,paramType4),PositionalArgument(arg5,paramType5),PositionalArgument(arg6,paramType6),PositionalArgument(arg7,paramType7),PositionalArgument(arg8,paramType8),PositionalArgument(arg9,paramType9),PositionalArgument(arg10,paramType10),PositionalArgument(arg11,paramType11),PositionalArgument(arg12,paramType12),PositionalArgument(arg13,paramType13),PositionalArgument(arg14,paramType14),PositionalArgument(arg15,paramType15),PositionalArgument(arg16,paramType16),PositionalArgument(arg17,paramType17),PositionalArgument(arg18,paramType18),PositionalArgument(arg19,paramType19),PositionalArgument(arg20,paramType20),PositionalArgument(arg21,paramType21))

}
