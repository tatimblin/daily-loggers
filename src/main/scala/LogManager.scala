object LogManager {
  def run(loggers: List[Logger], config: Map[String, (String, Int, Map[String, String])]): Unit = {
    loggers.foreach { logger =>
      config.get(logger.name).foreach { case (filePath, retentionCount, params) =>
        logger.log(filePath, retentionCount, params)
      }
    }
  }
}
