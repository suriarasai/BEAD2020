package thrift

import java.io.File

import com.tweet.thrift.{TweetThrift, UserTweetThrift}

object ThriftExample {

  def main(args: Array[String]): Unit = {

    val file = new File ("serialized_thrift_file.t")
    //Serialize
    val tweet = new TweetThrift(1, 123, "Saturday 8th, June", "arunma")
    val user = new UserTweetThrift("arunma2")
    tweet.setText("First tweet")
    ThriftSerDe.serialize(tweet, user, file)

    //Deserialize with exact schema
   /* println("Deserializing Full Tweet")
    val returnTweet = ThriftSerDe.deserializeFullTweet(file)
    println (returnTweet)*/

    //Deserialize with minimal schema
    println("Deserializing Minimal Tweet")
    val userAndTweet = ThriftSerDe.deserializePartWithDifferentSchema(file)
    println (userAndTweet)

  }

}
