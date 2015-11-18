package com.agilogy.simpledb.dsl

import javax.sql.DataSource

import com.agilogy.simpledb.{ParameterValue, Query, ResultSetReads}
import com.agilogy.simpledb.schema.Column

case class DslQuery[RT] private(ds:DataSource, from: Relation, where: Predicate, select: Seq[SelectedElement[_]], reads: ResultSetReads[RT],
                                groupBy: Seq[Column[_]] = Seq.empty, orderBy: Seq[OrderByCriterion] = Seq.empty,
                                constants:Seq[Constant[_]], isDistinct:Boolean = false)
extends Query[RT]{
  protected val parameters: Seq[Param[_]] = from.parameters ++ where.parameters ++ select.flatMap(_.parameters)


  val sql =
    s"select ${if(isDistinct) "distinct " else ""}${select.map(e => e.sql + " as " + e.alias).mkString(", ")} " +
      s"${from.sql}" +
      (if (where != True) s" where ${where.sql}" else "") +
      (if (groupBy.nonEmpty) s" group by ${groupBy.map(c => c.completeName).mkString(", ")}" else "") +
      (if (orderBy.nonEmpty) s" order by ${orderBy.map(_.sql).mkString(", ")}" else "")

  def map[RT2](f: RT => RT2): DslQuery[RT2] = this.copy(reads = reads.map(f))

  override private[simpledb] val preAssignedParameters: Seq[ParameterValue[_]] = constants.map(_.asParameterValue)

  def distinct: DslQuery[RT] = this.copy(isDistinct = true)

  def andWhere(p:Predicate) = this.copy(where = where and p)
}

object DslQuery {
  private[dsl] def build[RT](ds:DataSource, from: Relation, where: Predicate, select: Seq[SelectedElement[_]], reads: ResultSetReads[RT], groupBy: Seq[Column[_]] = Seq.empty, orderBy: Seq[OrderByCriterion] = Seq.empty): DslQuery[RT] = {
    import ConstantAllocation.{allocateRelationConstants,allocateExpressionConstants,allocateSelectConstants}
    val constantAllocation = for {
      f <- allocateRelationConstants(from)
      w <- allocateExpressionConstants(where)
      s <- allocateSelectConstants(select)
    } yield DslQuery(ds, f, w, s, reads.readUsingAliases, groupBy, orderBy, Seq.empty)

    val (q,ca) = constantAllocation(ConstantAllocationContext.initial)
    q.copy(constants = ca.constants)
  }

}






