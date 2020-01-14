package spark

import org.apache.log4j.{Level, LogManager}
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._


object NestedSchemaJson {

  implicit val encoder = Encoders.product[TweetParquet]

  def main(args: Array[String]): Unit = {
    implicit val spark = SparkSession
      .builder()
      .config(new SparkConf())
      .appName("Schema evolution")
      .master("local[*]")
      .getOrCreate()

    LogManager.getRootLogger.setLevel(Level.WARN)
    spark.sparkContext.setLogLevel("ERROR")

    val Format = "json"
    //val tweetFrame = jsonToDataFrame("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/src/main/resources/data/multiline.json")
    val tweetFrame = jsonToDataFrame("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/src/main/resources/data/tweets_single.json")
    writeToFile(tweetFrame, Format)
    readFromFile(s"/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/output_$Format", Format)
    spark.close()

  }


  //Coz - Using the Avro schema for parquet conversion and writing a parquet file is so convoluted
  def jsonToDataFrame(filePath: String)(implicit spark: SparkSession): DataFrame = {
    import spark.sqlContext.implicits._
    val df = spark
      .read
      .option("multiline", "true")
      .option("mode", "PERMISSIVE")
      .json(filePath)

    df
  }

  def writeToConsole(frame: Dataset[TweetSimple])(implicit spark: SparkSession): Unit = {
    frame.show(false)
  }

  def writeToFile(frame: DataFrame, format: String)(implicit spark: SparkSession): Unit = {
    /*frame
      .write
      .format(format)
      .mode(SaveMode.Append)
      .save(s"output_$format")*/

    frame
      .write
      .format(format)
      .mode(SaveMode.Overwrite)
      .save(s"output_$format")
  }

  def readFromFile(filePath: String, format: String)(implicit spark: SparkSession): Unit = {
    val df = spark
      .read
      .format(format)
      .load(filePath)

    df.printSchema
    df.show(false)

    df.select(col("extended_tweet")).show(false)
    df.select(col("extended_tweet")).printSchema()

    //Show all Urls in the entity
    df.select(col("text"), col("extended_tweet.entities.hashtags")).show(false)

    //Flatten the dataframe
    df.select(col("text"), explode(col("extended_tweet.entities.hashtags.text")) as "hashtags").show(false)

    //Show the first url in the array
    df.select(col("extended_tweet.entities.hashtags").getItem(0)).show(false)


  }
}
