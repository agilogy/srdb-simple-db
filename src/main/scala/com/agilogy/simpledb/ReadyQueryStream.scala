package com.agilogy.simpledb

import com.agilogy.srdb.LimitedFetchSize
import com.agilogy.srdb.tx.{NewTransaction, TransactionController}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait QueryStream[RT] { self =>
  def toSeq: Seq[RT] = {
    val lb = ListBuffer.empty[RT]
    foreach(lb.append(_))
    lb.toSeq
  }

  def foreach(f: (RT) => Unit): Unit

  def map[RT2](f: (RT) => RT2): QueryStream[RT2] = new MappedQueryStream[RT, RT2](this, f)

  def ++(i: Iterable[RT]): QueryStream[RT] = new ConcatenatedQueryStream(Seq.empty, this, i)

  def ++:(i: Iterable[RT]): QueryStream[RT] = new ConcatenatedQueryStream[RT](i, this, Seq.empty)

  def ++(qs2: QueryStream[RT]): QueryStream[RT] = new QueryStreams[RT](Seq(this, qs2))

  def filter(f: (RT) => Boolean): QueryStream[RT] = new FilteredQueryStream[RT](this, f)

  def collect[RT2](pf: PartialFunction[RT, RT2]): QueryStream[RT2] = this.filter(pf.isDefinedAt).map(pf)

  def foldLeft[Z](z: Z)(f:(RT, Z)=> Z): Z = {
    var acc = z
    foreach(r => acc = f(r,acc))
    acc
  }

  def zipWithIndex = new QueryStream[(RT, Int)] {
    override def foreach(f: ((RT, Int)) => Unit): Unit = {
      var i = 0
      self.foreach{rt =>
        f((rt,i))
        i+=1
      }
    }
  }

  def grouped(n: Int) = new QueryStream[Seq[RT]] {
    override def foreach(f: (Seq[RT]) => Unit) = {
      var seq = mutable.ListBuffer.empty[RT]
      self.foreach { rt =>
        seq += rt
        if (seq.length == n) {
          f(seq)
          seq = mutable.ListBuffer.empty[RT]
        }
      }
      if (seq.nonEmpty) {
        f(seq)
      }
    }
  }
}

case class ReadyQueryStream[RT] private[simpledb] (q: ReadyQuery[RT])(implicit txController: TransactionController) extends QueryStream[RT] {
  override def foreach(f: (RT) => Unit): Unit = txController.inTransaction {
    implicit tx =>
      q.foreach(f)(tx, LimitedFetchSize(100)) // MAGIC NUMBER!!!!!!
  }(NewTransaction)
}

class EmptyQueryStream[RT] extends QueryStream[RT] {
  override def foreach(f: (RT) => Unit): Unit = {}
}

class ConcatenatedQueryStream[RT](before: Iterable[RT], qs: QueryStream[RT], after: Iterable[RT]) extends QueryStream[RT] {
  override def foreach(f: (RT) => Unit): Unit = {
    before.foreach(f)
    qs.foreach(f)
    after.foreach(f)
  }

  override def ++(i: Iterable[RT]): QueryStream[RT] = new ConcatenatedQueryStream(before, qs, after ++ i)

  override def ++:(i: Iterable[RT]): QueryStream[RT] = new ConcatenatedQueryStream(before ++ i, qs, after)
}

class QueryStreams[RT](qss: Seq[QueryStream[RT]]) extends QueryStream[RT] {

  override def foreach(f: (RT) => Unit): Unit = qss.foreach(_.foreach(f))

  override def ++(qs2: QueryStream[RT]): QueryStream[RT] = new QueryStreams(qss :+ qs2)
}

object QueryStream {
  def empty[RT]: QueryStream[RT] = new EmptyQueryStream[RT]
}

class MappedQueryStream[RT1, RT2](stream: QueryStream[RT1], mapFunction: (RT1) => RT2) extends QueryStream[RT2] {
  override def foreach(f: (RT2) => Unit): Unit = stream.foreach(r => f(mapFunction(r)))
}

class FilteredQueryStream[RT](stream: QueryStream[RT], filter: (RT) => Boolean) extends QueryStream[RT] {
  override def foreach(f: (RT) => Unit): Unit = stream.foreach(r => if (filter(r)) f(r))
}