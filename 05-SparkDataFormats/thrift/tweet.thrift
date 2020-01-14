namespace java com.tweet.thrift

struct TweetThrift {
    1:  required i64 target;
    2:  required i64 id;
    3:  required string date;
    4:  required string user;
    9:  optional string text;
}

struct UserTweetThrift {
    9:  optional string text;
    4:  required string user;
}
