package basic.featureext

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.feature.IDF
import basic.Util

object TfIdfSample{
  def main(args: Array[String]) {
    //TODO replace with path specific to your machine
    val file = "/home/cloudera/git/BEAD-SEP19/W07-SparkML/data/words.txt"
    val spConfig = (new SparkConf).setMaster("local").setAppName("SparkApp")
    val sc = new SparkContext(spConfig)
    val documents: RDD[Seq[String]] = sc.textFile(file).map(_.split(" ").toSeq)
    print("Documents Size:" + documents.count)
    val hashingTF = new HashingTF()
    val tf = hashingTF.transform(documents)
    for(tf_ <- tf) {
      println(s"$tf_")
    }
    tf.cache()
    val idf = new IDF().fit(tf)
    val tfidf = idf.transform(tf)
    println("tfidf size : " + tfidf.count)
    for(tfidf_ <- tfidf) {
      println(s"$tfidf_")
    }
  }
}