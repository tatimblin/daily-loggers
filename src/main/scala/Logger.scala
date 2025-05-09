import scala.io.Source
import java.io.{BufferedWriter, PrintWriter, File, FileWriter}

object Logger {
  val COLOR_ENTER = "\u001b[93m"
  val COLOR_RESET = "\u001b[0m"
}

trait Logger {
    def name: String
    def generateLogEntry(params: Map[String, String]): String

  def log(logFilePath: String, retentionCount: Int, params: Map[String, String]): Unit = {
    val file = new File(logFilePath)
    
    createParentDirectory(file)
    createFile(file)

    trimLogFileToRetention(file, retentionCount)

    val writer = new PrintWriter(new FileWriter(file, true))
    val logEntry = generateLogEntry(params)
    writer.println(logEntry)
    writer.close()
    
    println(s"${Logger.COLOR_ENTER}Logged: $logEntry to $logFilePath${Logger.COLOR_RESET}")
  }

  private def removeOldestEntry(logFilePath: String): Unit = {
    val file = new File(logFilePath)
    if (!file.exists() || file.length() == 0) return

    val source = Source.fromFile(file)
    val lines = source.getLines().toList
    source.close()
    
    if (lines.isEmpty) return

    val writer = new PrintWriter(new FileWriter(file))
    lines.tail.foreach(line => writer.println(line))
    writer.close()
  }

  private def trimLogFileToRetention(file: File, retentionCount: Int): Unit = {
    if (!file.exists()) return

    val lines = scala.io.Source.fromFile(file).getLines().toList
    if (lines.length >= retentionCount) {
      val trimmed = lines.takeRight(retentionCount - 1)
      val writer = new PrintWriter(file)
      trimmed.foreach(writer.println)
      writer.close()
    }
  }

  private def countLines(filePath: String): Int = {
    val file = new File(filePath)
    if (!file.exists()) return 0
    
    val source = Source.fromFile(file)
    val count = source.getLines().size
    source.close()
    count
  }

  private def createParentDirectory(file: File): Unit = {
    val parentDir = file.getParentFile
    if (parentDir != null && !parentDir.exists()) {
      parentDir.mkdirs()
    }
  }

  private def createFile(file: File): Unit = {
    if (!file.exists()) {
      file.createNewFile()
    }
  }
}
