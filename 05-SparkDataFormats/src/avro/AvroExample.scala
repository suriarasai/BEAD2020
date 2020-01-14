package avro

import java.io.File

import com.tweet.avro.UserTweetAvro
import com.tweet.avro.TweetAvro

object AvroExample {

  def main(args: Array[String]): Unit = {

    val file = new File("serialized_avro_file.avro")
    //Serialize
    val tweet1 = TweetAvro
      .newBuilder
      .setTarget(1)
      .setId(123)
      .setDate("Saturday 8th, June")
      .setUser("alpha1")
      .setText("Avro tweet1")
      .build()

    val tweet2 = TweetAvro
      .newBuilder
      .setTarget(2)
      .setId(234)
      .setDate("Sunday 9th, June")
      .setUser("alpha2")
      .setText("Avro tweet2")
      .build()

    AvroSerDe.serialize(List(tweet1, tweet2), file)

    //Deserialize with exact schema
    println("Deserializing Full Tweet")
    val returnTweet = AvroSerDe.deserialize(file, TweetAvro.getClassSchema)
    println(returnTweet)

    //Deserialize with minimal schema
    println("Deserializing Minimal Tweet")
    val userAndTweet = AvroSerDe.deserialize(file, UserTweetAvro.getClassSchema)
    println(userAndTweet)


    println("Deserializing as Map")
    val userAttsMap = AvroSerDe.deserializeAsMap(file, UserTweetAvro.getClassSchema)

    println ("-" * 50)
    userAttsMap.foreach { each =>
      println (each.mkString("\n"))
      println ("-" * 50)
    }

  }

}
