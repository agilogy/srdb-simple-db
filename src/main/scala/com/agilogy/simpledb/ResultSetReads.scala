//package com.agilogy.simpledb
//
//import java.sql.ResultSet
//
//import com.agilogy.srdb.{CursorReader, types}
//import com.agilogy.srdb.types.DbReader
//
//import scala.annotation.tailrec
//import scala.language.implicitConversions
//
//trait CursorReaderOps[RT] extends CursorReader[RT]{
//  val cr:CursorReader[RT]
//  def map[T2](f: (RT) => T2): CursorReader[T2] = MappedCursorReader(this,f)
//}
//
//case class SimpleCursorReader[RT](rowReader:DbReader[RT]) extends CursorReader[RT]{
//
//  def join[RT2](implicit r2: DbReader[RT2]): GroupLeftJoinReads[RT, RT2] = GroupLeftJoinReads(this, r2)
//
//  def leftJoin[RT2](implicit r2: DbReader[RT2]): GroupLeftJoinReads[RT, RT2] = GroupLeftJoinReads(this, r2)
//
//  def joinOne[RT2](implicit r2: DbReader[RT2]): JoinReads[RT, RT2] = JoinReads(this, r2)
//
//  def leftJoinOne[RT2](implicit r2: DbReader[RT2]): LeftJoinReads[RT, RT2] = LeftJoinReads(this, r2)
//
//  private[simpledb] def readRow(rs: ResultSet): RT = rowReader.get(rs)
//
//  override def readAndNext(rs: ResultSet): RT = {
//    val res = readRow(rs)
//    rs.next()
//    res
//  }
//
//  def map[T2](f: (RT) => T2): CursorReader[T2] = new SimpleCursorReader(rowReader.map(f))
//}
//
//
//trait CursorReaderImplicits{
//  implicit def dbReader2CursorReader[RT](reader:DbReader[RT]): SimpleCursorReader[RT] = SimpleCursorReader(reader)
//}
//
//object ResultSetReads extends CursorReaderImplicits
//
//
//case class JoinReads[T1, T2](left: SimpleCursorReader[T1], right: DbReader[T2]) extends CursorReader[(T1, T2)] {
//
//  override def readAndNext(rs: ResultSet): (T1, T2) = {
//    val leftResult = left.readRow(rs)
//    val rightResult = right.get(rs)
//    leftResult -> rightResult
//  }
//
////  override def readUsingAliases: JoinReads[T1, T2] = this.copy(left.readUsingAliases, right.readUsingAliases)
//}
//
//case class LeftJoinReads[T1, T2](left: SimpleCursorReader[T1], right: DbReader[T2]) extends CursorReader[(T1, Option[T2])] {
//
////  override def readUsingAliases: LeftJoinReads[T1, T2] = this.copy(left.readUsingAliases, right.readUsingAliases)
//
//  override def readAndNext(rs: ResultSet): (T1, Option[T2]) = {
//    val leftResult = left.readRow(rs)
//    val rightResult = try {
//      Some(right.get(rs))
//    }catch{
//      //TODO: Unify exception handling
//      case ncr:types.NullColumnReadException => None
//    }
//    leftResult -> rightResult
//
//
//  }
//
//}
//
//case class GroupLeftJoinReads[T1, T2](groupReads: SimpleCursorReader[T1], right: DbReader[T2]) extends CursorReader[(T1, Seq[T2])] {
//
//  protected[this] def readGroup(rs: ResultSet) = groupReads.readRow(rs)
//
//  def readRow(rs: ResultSet): (T1, Seq[T2]) = groupReads.readRow(rs) -> Seq.empty
//
//  override def readAndNext(rs: ResultSet): (T1, Seq[T2]) = {
//    @tailrec
//    def rec(group: T1, elements: Seq[T2]): (T1, Seq[T2]) = {
//      val currentGroup = readGroup(rs)
//      if (currentGroup == group) {
//        val r = try {
//          Some(right.get(rs))
//        } catch {
//          case ncre: types.NullColumnReadException => None
//        }
//        val currentElements = elements ++ r
//        if (rs.isLast) {
//          group -> currentElements
//        } else {
//          rs.next()
//          rec(currentGroup, currentElements)
//        }
//      } else {
//        group -> elements
//      }
//    }
//
//    require(!rs.isAfterLast)
//    rec(readGroup(rs), Seq.empty)
//  }
//
////  override def readUsingAliases: GroupLeftJoinReads[T1, T2] = GroupLeftJoinReads(groupReads.readUsingAliases, right.readUsingAliases)
//}
//
//case class MappedCursorReader[RT, RT2](reads: CursorReader[RT], f: RT => RT2) extends CursorReader[RT2] {
//
//  override def readAndNext(rs: ResultSet): RT2 = f(reads.readAndNext(rs))
//
////  override def readUsingAliases: ResultSetReads[RT2] = this.copy(reads.readUsingAliases)
//  def map[T2](f: (RT2) => T2): CursorReader[T2] = MappedCursorReader(reads, this.f.andThen(f))
//}