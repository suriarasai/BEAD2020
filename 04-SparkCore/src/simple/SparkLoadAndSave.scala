package simple

import org.apache.hadoop.io.{ Text, IntWritable }
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

// Loading JSON file
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature

import java.io.{ StringWriter, StringReader }
import au.com.bytecode.opencsv.{ CSVWriter, CSVReader }
import scala.collection.JavaConverters._
import collection.JavaConverters._
import org.apache.log4j.Level
import org.apache.log4j.Logger

object SparkLoadAndSave {
    Logger.getLogger("org").setLevel(Level.OFF)
   
  case class Person(name: String, age: Int)

  case class Stocks(name: String, totalPrice: Long)

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("CLoadingSavingData").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val mapper = new ObjectMapper()
   
    
    // Loading JSON File
    val jsonInput = sc.textFile("/home/cloudera/git/BEAD2020/04-SparkCore/data/people.json")
    val result1 = jsonInput.flatMap(record => {
      try {
        Some(mapper.readValue(record, classOf[Person]))
      } catch {
        case e: Exception => None
      }
    })
    result1.filter(person => person.age > 15).map(mapper.writeValueAsString(_)).
      saveAsTextFile("/home/cloudera/git/BEAD2020/04-SparkCore/data/outputFile")

    // Loading CSV

    val input1 = sc.textFile("/home/cloudera/git/BEAD2020/04-SparkCore/data/stocks.csv")
    val result2 = input1.flatMap { line =>
      val reader = new CSVReader(new StringReader(line))
      reader.readAll().asScala.toList.map(x => Stocks(x(0), x(5).toLong))
    }


  }

}