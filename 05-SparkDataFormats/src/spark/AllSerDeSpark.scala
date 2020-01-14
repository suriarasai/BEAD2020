package spark

import org.apache.log4j.{Level, LogManager}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}

case class TweetParquet(target: Long, id: Long, date: String, user: String, text: String)

object AllSerDeSpark {

  implicit val encoder = Encoders.product[TweetParquet]
  val tweet = TweetParquet(1, 123, "Saturday 8th, June", "arunma", "Parquet Tweet")

  def main(args: Array[String]): Unit = {
    implicit val spark = SparkSession
      .builder()
      .config(new SparkConf())
      .appName("Parquet Writer and Reader")
      .master("local[*]")
      .getOrCreate()

    LogManager.getRootLogger.setLevel(Level.WARN)
    spark.sparkContext.setLogLevel("ERROR")

    val frame = convertToDataframe(tweet)
    writeToConsole(frame)
    writeToFile(frame, "csv")
    spark.close()

  }


  //Coz - Using the Avro schema for parquet conversion and writing a parquet file is so convoluted
  def convertToDataframe(tweet: TweetParquet)(implicit spark: SparkSession): Dataset[TweetParquet] = {
    import spark.sqlContext.implicits._
    spark.sparkContext.parallelize(List(tweet)).toDS()
  }

  def writeToConsole(frame: Dataset[TweetParquet])(implicit spark: SparkSession): Unit = {
    frame.show(false)
  }

  def writeToFile(frame: Dataset[TweetParquet], format: String)(implicit spark: SparkSession): Unit = {
    frame
      .repartition(1)
      .write
      .format(format)
      .save(s"output_${format}")
  }
}
