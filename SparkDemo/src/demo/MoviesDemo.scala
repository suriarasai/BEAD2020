package demo

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql._
import org.apache.spark.sql.functions._


object MoviesDemo extends App {

  val confy = new SparkConf().setAppName("VideoData").setMaster("local[2]")
  val contexty = new SparkContext(confy)
  val sparky = SparkSession.builder().appName("Test").config("x", "y").getOrCreate()
  import sparky.implicits._

  val sqlContext = new SQLContext(contexty)

  val dfMovies = sqlContext.load("jdbc", Map("url" -> "jdbc:mysql://localhost/VideoData?user=root&password=cloudera", "dbtable" -> "Movies"))

  dfMovies.show(400, false)

}