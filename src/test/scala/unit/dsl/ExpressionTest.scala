package unit.dsl

import javax.sql.DataSource

import com.agilogy.simpledb.dsl.Predicate
import org.postgresql.jdbc2.optional.SimpleDataSource
import org.scalatest.FlatSpec

import com.agilogy.simpledb._
import com.agilogy.simpledb.schema._
import com.agilogy.simpledb.dsl._

class ExpressionTest extends FlatSpec{

  private val mockDs:DataSource = new SimpleDataSource

  behavior of "columns"
  
  case class People(alias:String) extends Table{
    val id = notNull[Long]("id")
    val inactive = notNull[Boolean]("inactive")
    val deleted = notNull[Boolean]("deleted")
    val age = notNull[Int]("age")
    override val tableName: String = "people"
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }
  
  private val p = People("p")
  
  they should "be valid expressions" in {
    from(p).where(p.inactive)
    val e:Predicate = p.inactive and p.deleted
    val e2:Predicate = p.inactive or true
    val e3:Predicate = p.inactive ==== true
    val e4:Predicate = p.inactive nullSafeEq true
    val e5:Predicate = p.inactive < true
    val e6:Predicate = p.inactive <= true
    val e7:Predicate = p.inactive > true
    val e8:Predicate = p.inactive >= true
    val e9:Predicate = p.inactive in (true, false)
    val e11:Predicate = p.inactive.isNull
  }

  // TODO review these tests
  behavior of "in expressions"

  it should "accept predicates" in {
    val res: Predicate = p.inactive in (true, p.deleted)
    assert(res.sql === "p.inactive in (true,p.deleted)")
  }

  it should "accept constants as arguments" in {
    val res = p.age in (17,18,19)
    assert(res.sql === "p.age in (17,18,19)")
    val res2 = p.age in Seq(17, 18, 19)
    assert(res2.sql === res.sql)
  }

  it should "accept parameters as expressions" in {
    val res = p.age in(param(0),param(1))
    assert(res.sql === "p.age in (:0,:1)")
  }

  it should "accept a single parameter for all the expression" in {
    val res = p.age in param[Seq[Int]](0)
  }

  behavior of "sum aggregate function"

  ignore should "return an option of the summed type" in {
    val res:Query0[Option[Int]] = from(p).select(sum(p.age).as("ages")).withoutParams

  }


}
