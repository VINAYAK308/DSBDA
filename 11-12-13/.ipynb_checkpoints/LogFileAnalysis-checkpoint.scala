import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object LogFileAnalysis {
  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("Log Analysis")
      .setMaster("local[*]")

    val sc = new SparkContext(conf)

    val logData = sc.textFile("log.txt")

    val logLevels = logData.map { line =>
      if (line.contains("ERROR")) "ERROR"
      else if (line.contains("WARN")) "WARN"
      else if (line.contains("INFO")) "INFO"
      else "OTHER"
    }

    val counts = logLevels
      .map(level => (level, 1))
      .reduceByKey(_ + _)

    counts.collect().foreach(println)

    sc.stop()
  }
}