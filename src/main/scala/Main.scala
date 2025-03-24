object Main {
  def main(args: Array[String]): Unit = {
    val logger = sys.env.get("LOGGER")

    val loggers = List(DateLogger, TimeLogger)
    val loggerToRun = loggers.find(_.name == logger.getOrElse(""))

    loggerToRun match {
      case Some(logger) =>
        val filePath = sys.env.getOrElse("LOG_PATH", s"logs/${logger.name}_log.csv")
        val retentionCount = sys.env.getOrElse("RETENTION", "30").toInt

        val params = sys.env
          .filterKeys(_.startsWith("PARAM_"))
          .map { case (key, value) =>
            val paramName = key.stripPrefix("PARAM_").toLowerCase
            paramName -> value
          }
          .toMap

        logger.log(filePath, retentionCount, params)

      case None =>
        println("No valid logger specified.")
    }
  }
}
