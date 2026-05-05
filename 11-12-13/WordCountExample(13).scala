import org.apache.spark.sql.SparkSession

object WordCountExample {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Word Count Example")
      .master("local[*]")
      .getOrCreate()

    val input = spark.read.textFile("input.txt")

    val wordCount = input
      .flatMap(line => line.split(" "))
      .map(word => word.trim)
      .filter(word => word.nonEmpty)
      .groupBy("value")
      .count()

    wordCount.show()

    spark.stop()
  }
}




