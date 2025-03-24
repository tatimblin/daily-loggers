import java.time.LocalDate

object DateLogger extends Logger {
  override def name: String = "date"

  override def generateLogEntry(params: Map[String, String]): String =
    s"${LocalDate.now()},DATE"
}
