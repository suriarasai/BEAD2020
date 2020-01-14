package protobuf

import java.io.{BufferedOutputStream, File, FileOutputStream}
import java.nio.file.Files

import com.tweet.proto.TweetProtoOuter.TweetProto
import com.tweet.proto.TweetProtoOuter.UserTweetProto

object ProtoBufSerDe {

  def serialize (tweet: TweetProto, file: File): Unit ={
    val byteArray = tweet.toByteArray
    val bos = new BufferedOutputStream(new FileOutputStream(file))
    bos.write(byteArray)
    bos.flush()
  }

  def deserializeFullTweet(file:File): TweetProto ={
    val byteArray = Files.readAllBytes(file.toPath)
    TweetProto.parseFrom(byteArray)
  }

  def deserializePartWithDifferentSchema(file:File): UserTweetProto ={
    val byteArray = Files.readAllBytes(file.toPath)
    UserTweetProto.parseFrom(byteArray)
  }
}
