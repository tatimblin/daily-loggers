import java.time.{LocalTime, ZoneId, ZonedDateTime}

// LOGGER="time" LOG_PATH="logs/times.csv" RETENTION="10" PARAM_TIMEZONE="America/New_York" sbt run

object TimeLogger extends Logger {
  override def name: String = "time"

  override def generateLogEntry(params: Map[String, String]): String = {
    val timezone = params.getOrElse("timezone", "UTC")
    val time = ZonedDateTime.now(ZoneId.of(timezone)).toLocalTime
    s"$time,TIME,$timezone"
  }
}
