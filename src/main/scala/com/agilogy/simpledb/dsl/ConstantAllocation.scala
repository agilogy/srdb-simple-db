package com.agilogy.simpledb.dsl

private[dsl] case class ConstantAllocationContext(nextConstantIndex: Int, constants: Seq[Constant[_]])

private[dsl] object ConstantAllocationContext {
  def initial: ConstantAllocationContext = ConstantAllocationContext(0, Seq.empty)
  def apply(alreadyAllocatedConstants:Seq[Constant[_]]):ConstantAllocationContext = ConstantAllocationContext(alreadyAllocatedConstants.size,alreadyAllocatedConstants)
}

private[simpledb] case class ConstantAllocation[+T](f: ConstantAllocationContext => (T, ConstantAllocationContext)) {
  def apply(cac: ConstantAllocationContext): (T, ConstantAllocationContext) = f(cac)

  def map[T2](mf: T => T2): ConstantAllocation[T2] = ConstantAllocation {
    ctx =>
      val (resV, resCtx) = f(ctx)
      (mf(resV), resCtx)
  }

  def flatMap[T2](mf: T => ConstantAllocation[T2]) = ConstantAllocation {
    ctx =>
      val (resV, resCtx) = f(ctx)
      val ca2 = mf(resV)
      ca2(resCtx)
  }
}

private[simpledb] object ConstantAllocation {

  def newConstant[T](c: Constant[T]) = ConstantAllocation {
    ctx =>
      val res = c.assignIndex(ctx.nextConstantIndex)
      (res, ConstantAllocationContext(ctx.nextConstantIndex + 1, ctx.constants :+ res))
  }

  def empty[T](v: T) = ConstantAllocation(ctx => (v, ctx))

  def allocateAll[T](seq: Seq[T])(implicit af: T => ConstantAllocation[T]): ConstantAllocation[Seq[T]] =
    seq.foldLeft(ConstantAllocation.empty(Seq.empty[T])) {
      case (acc, se) =>
        acc.flatMap(ss => af(se).map(sea => ss :+ sea))
    }

  def allocateSelectConstants[T <: SelectedElement[_]](s: Seq[T]):ConstantAllocation[Seq[T]] = allocateAll(s)(_.allocateConstants.asInstanceOf[ConstantAllocation[T]])
  
  def allocateRelationConstants[T <: Relation](r: T): ConstantAllocation[T] = r.allocateConstants.asInstanceOf[ConstantAllocation[T]]

  def allocateExpressionConstants[T <: Expression[_]](e: T): ConstantAllocation[T] = e.allocateConstants.asInstanceOf[ConstantAllocation[T]]
}
