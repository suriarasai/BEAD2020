name := "05-SparkDataFormats"

version := "1.0"

scalaVersion := "2.11.11"

val sparkVersion = "2.2.0"


resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

libraryDependencies ++= Seq(

  "org.apache.thrift" % "libthrift" % "0.12.0",
  "org.apache.avro" % "avro" % "1.9.1",
  "org.apache.parquet" % "parquet-avro" % "1.8.2",
  "org.apache.parquet" % "parquet-thrift" % "1.8.2",
  "org.apache.parquet" % "parquet-hadoop" % "1.8.2",
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion
)


  
