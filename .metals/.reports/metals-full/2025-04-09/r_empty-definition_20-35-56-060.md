error id: `<none>`.
file://<WORKSPACE>/src/main/scala/Logger.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 729
uri: file://<WORKSPACE>/src/main/scala/Logger.scala
text:
```scala
import scala.io.Source
import java.io.{BufferedWriter, PrintWriter, File, FileWriter}

trait Logger {
    def name: String
    def generateLogEntry(params: Map[String, String]): String

  def log(logFilePath: String, retentionCount: Int, params: Map[String, String]): Unit = {
    val file = new File(logFilePath)
    
    createParentDirectory(file)
    createFile(file)

    val lineCount = countLines(logFilePath)
    if (lineCount >= retentionCount) {
      removeOldestEntry(logFilePath)
    }

    val writer = new PrintWriter(new FileWriter(file, true))
    val logEntry = generateLogEntry(params)
    writer.println(logEntry)
    writer.close()
    
    val GREEN = "\u001b[92m"
    val RESET = "\u001b[0m"
    println(s"@@${GREEN}Logged: $logEntry to $logFilePath")
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.