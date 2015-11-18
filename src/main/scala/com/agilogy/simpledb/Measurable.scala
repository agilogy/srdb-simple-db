package com.agilogy.simpledb


import org.joda.time.Duration

import scala.util.DynamicVariable

case class Measures(measures: Seq[Measure]) {

  def :+(measure: Measure) = Measures(measures :+ measure)

  private def sumDurations(durations: Iterable[Duration]): Duration = durations.reduceOption(_ plus _).getOrElse(Duration.ZERO)

  def totalTime: Duration = sumDurations(measures.map(_.timeSpent))

  def distinctObjects = measures.groupBy(_.objectUnderMeasure).mapValues(_.map(_.timeSpent))

  def totalByObject = distinctObjects.mapValues(sumDurations)
}

object Measures {
  def empty = Measures(Seq.empty)
}

case class Measure(objectUnderMeasure: String, timeSpent: Duration)


trait Measurable {

  private val measure = new DynamicVariable[Measures](Measures.empty)

  private val measuring = new DynamicVariable(false)

  def withMeasures[T](f: => T): (T, Measures) = {
    val previousMeasures = measure.value.measures.size
    try {
      measuring.withValue(true) {
        (f, Measures(measure.value.measures.drop(previousMeasures)))
      }
    } finally {
      if (!measuring.value) {
        measure.value = Measures.empty
      }
    }
  }

  private[simpledb] def measure[T](objectUnderMeasure: String)(f: => T): T = {
    val wasMeasuring = measuring.value
    val startTime = if (wasMeasuring) System.currentTimeMillis() else 0
    try {
      f
    } finally {
      if (wasMeasuring) {
        val spentTime = Duration.millis(System.currentTimeMillis() - startTime)
        measure.value = measure.value :+ Measure(objectUnderMeasure, spentTime)
      }
    }

  }

}
