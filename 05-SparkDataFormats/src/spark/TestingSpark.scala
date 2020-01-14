package spark

import org.apache.log4j.{Level, LogManager}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}

case class TweetSimple(target: Long, id: Long, date: String, user: String, text: String)

object TestingSpark {

  implicit val encoder = Encoders.product[TweetParquet]
  val tweets = List(
    TweetSimple(1, 123, "Saturday 8th, June", "nus1", "Simple Tweet"),
    TweetSimple(2, 234, "Sunday 9th, June", "nus2", "Not so simple Tweet")
  )


  def main(args: Array[String]): Unit = {
    implicit val spark = SparkSession
      .builder()
      .config(new SparkConf())
      .appName("Parquet Writer and Reader")
      .master("local[*]")
      .getOrCreate()

    LogManager.getRootLogger.setLevel(Level.WARN)
    spark.sparkContext.setLogLevel("ERROR")

    val frame = convertToDataframe(tweets)
    writeToConsole(frame)
    writeToFile(frame, "parquet")
    //readFromFile("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/output_avro")
    spark.close()

  }


  //Coz - Using the Avro schema for parquet conversion and writing a parquet file is so convoluted
  def convertToDataframe(tweets: List[TweetSimple])(implicit spark: SparkSession): Dataset[TweetSimple] = {
    import spark.sqlContext.implicits._
    spark.sparkContext.parallelize(tweets).toDS()
  }

  def writeToConsole(frame: Dataset[TweetSimple])(implicit spark: SparkSession): Unit = {
    frame.show(false)
  }

  def writeToFile(frame: Dataset[TweetSimple], format: String)(implicit spark: SparkSession): Unit = {
    frame
      .write
      .option("compression", "snappy")
      .format("parquet")
      .save(s"output_${format}")
  }

  def readFromFile(filePath: String)(implicit spark: SparkSession): Unit = {
    spark
      .read
      .format("parquet")
      .load(filePath)
      .show(false)
  }
}
