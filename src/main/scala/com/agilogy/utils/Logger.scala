package com.agilogy.utils

import java.util.logging.{Level, Logger => JavaLogger}

class Logger private(val logger: java.util.logging.Logger) {

  def log(level: Level)(msg: => String, t: Throwable = null) {
    if (logger.isLoggable(level)) {
      if (t == null) {
        logger.log(level, msg)
      } else {
        logger.log(level, msg, t)
      }
    }
  }


  def debug(msg: => String, t: Throwable = null) = log(Level.FINE) _

  def info(msg: => String, t: Throwable = null) = log(Level.INFO) _

  def warn(msg: => String, t: Throwable = null) = log(Level.WARNING) _

  def severe(msg: => String, t: Throwable = null) = log(Level.SEVERE) _

  debug("a")

}

trait Loggable {
  val logger: Logger = Logger.getLogger(this)

}

/**
 * Note: implementation taken from scalax.logging API
 */
object Logger {

  private def loggerNameForClass(className: String) =
    if (className endsWith "$") className.substring(0, className.length - 1)
    else className

  def getLogger(logging: AnyRef) = new Logger(JavaLogger.getLogger(loggerNameForClass(logging.getClass.getName)))
}
