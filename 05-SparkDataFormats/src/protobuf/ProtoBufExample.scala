package protobuf

import java.io.File

import com.tweet.proto.TweetProtoOuter.TweetProto

object ProtoBufExample {

  def main(args: Array[String]): Unit = {

    val file = new File("serialized_proto_file")

    //Use the nice fluent API typical with Google APIs
    val tweet = TweetProto
      .newBuilder
      .setTarget(1)
      .setId(123)
      .setDate("Saturday 8th, June")
      .setUser("arunma")
      .setText("Proto tweet")
      .build()

    //Serialize
    ProtoBufSerDe.serialize(tweet, file)

    //Deserialize with exact schema
    println("Deserializing Full Tweet")
    val returnTweet = ProtoBufSerDe.deserializeFullTweet(file)
    println(returnTweet)

    //Deserialize with minimal schema
    println("Deserializing Minimal Tweet")
    val userAndTweet = ProtoBufSerDe.deserializePartWithDifferentSchema(file)
    println(userAndTweet)

  }

}
