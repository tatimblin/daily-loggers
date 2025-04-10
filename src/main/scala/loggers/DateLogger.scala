import java.time.LocalDate

// LOGGER="date" LOG_PATH="logs/dates.csv" RETENTION="5" sbt run

object DateLogger extends Logger {
  override def name: String = "date"

  override def generateLogEntry(params: Map[String, String]): String =
    s"${LocalDate.now()},DATE"
}
