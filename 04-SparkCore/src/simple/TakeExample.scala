package simple

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object TakeExample {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    val conf = new SparkConf()
           .setAppName("Counting Lines").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val inputWords = List("disappointment", "disturb", "rib", "excessive",
       "high-tech", "debris", "rod", "logical", "ash",
       "socially", "parish", "slavery", "blank", "commodity", 
       "cure", "mineral", "hunger", "dying", "developmental", 
       "faster", "spare", "halfway", "equality", "cemetery", 
       "harassment", "deliberately", "fame", "regret", 
       "striking", "likelihood", "carrot")
    val wordRdd = sc.parallelize(inputWords)
    val words = wordRdd.take(3)
    for (word <- words) println(word)
  }

}