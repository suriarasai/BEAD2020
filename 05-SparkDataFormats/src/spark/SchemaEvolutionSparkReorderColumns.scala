package spark

import org.apache.log4j.{Level, LogManager}
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.catalyst.util.CompressionCodecs
import org.apache.spark.sql.types._


object SchemaEvolutionSparkReorderColumns {

  val TweetStruct = StructType(
    Array(
      StructField("target", IntegerType),
      StructField("id", LongType),
      StructField("date", StringType),
      StructField("user", StringType),
      StructField("text", StringType)
    )
  )

  val TweetStructReordered = StructType(
    Array(
      StructField("id", LongType),
      StructField("target", IntegerType),
      StructField("date", StringType),
      StructField("user", StringType),
      StructField("tweet", StringType)
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

    val Format = "csv"
    val tweetFrame = csvToDataFrame("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/src/main/resources/data/tweets.csv", TweetStruct)
    val userTweetFrame = csvToDataFrame("/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/src/main/resources/data/tweets_reordered.csv", TweetStructReordered)
    //writeToFile(tweetFrame, Format)
    //writeToFile(userTweetFrame, Format)
    readFromFile(s"/Users/arunma/IdeaProjects/OSS/DataFormats-Scala/output_$Format", Format, TweetStruct)
    spark.close()

  }


  //Coz - Using the Avro schema for parquet conversion and writing a parquet file is so convoluted
  def csvToDataFrame(filePath: String, schema: StructType)(implicit spark: SparkSession): DataFrame = {
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
      //.option("compression", "snappy")
      .format(format)
      .mode(SaveMode.Append)
      .save(s"output_$format")
  }

  def readFromFile(filePath: String, format: String, schema: StructType)(implicit spark: SparkSession): Unit = {
    val df = spark
      .read
      .schema(schema)
      //.option("mergeSchema", true)
      //.option("inferSchema", true)
      .format(format)
      .load(filePath)
      //.show(false)
    df.show(false)

    df.printSchema
  }
}
