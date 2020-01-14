package spark

import org.apache.log4j.{Level, LogManager}
import org.apache.spark.SparkConf
import org.apache.spark.sql.types._
import org.apache.spark.sql._


object SchemaEvolutionSparkReadWriteSchema {

  val TweetStruct = StructType(
    Array(
      StructField("target", IntegerType),
      StructField("id", LongType),
      StructField("date", StringType),
      StructField("user", StringType),
      StructField("text", StringType)
    )
  )

  val UserTweetStruct = StructType(
    Array(
      StructField("user", StringType),
      StructField("text", StringType)
    )
  )

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

    val Format = "parquet"
    val tweetFrame = csvToDataFrame("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/src/main/resources/data/tweets.csv", TweetStruct)
    val userTweetFrame = csvToDataFrame("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/src/main/resources/data/user_tweets.csv", UserTweetStruct)
    writeToFile(tweetFrame, Format)
    writeToFile(userTweetFrame, Format)
    readFromFile(s"/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/output_$Format", Format, TweetStruct)
    spark.close()

  }


  //Coz - Using the Avro schema for parquet conversion and writing a parquet file is so convoluted
  def csvToDataFrame(filePath: String, schema: StructType)(implicit spark: SparkSession): DataFrame = {
    import spark.sqlContext.implicits._
    spark
      .read
      .schema(schema)
      .csv(filePath)
      .toDF()
  }

  def writeToConsole(frame: Dataset[TweetSimple])(implicit spark: SparkSession): Unit = {
    frame.show(false)
  }

  def writeToFile(frame: DataFrame, format: String)(implicit spark: SparkSession): Unit = {
    frame
      .write
      .format(format)
      .mode(SaveMode.Append)
      .save(s"output_$format")
  }

  def readFromFile(filePath: String, format: String, schema: StructType)(implicit spark: SparkSession): Unit = {
    spark
      .read
      .schema(schema)
      .format(format)
      .load(filePath)
      .show(false)
  }
}
