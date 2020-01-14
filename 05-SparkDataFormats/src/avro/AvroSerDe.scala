package avro

import java.io.{BufferedOutputStream, File, FileOutputStream}
import java.nio.file.Files

import com.tweet.avro.TweetAvro
import org.apache.avro.Schema
import org.apache.avro.file.{DataFileReader, DataFileWriter, SeekableByteArrayInput}
import org.apache.avro.generic.{GenericDatumReader, GenericRecord}
import org.apache.avro.specific.SpecificDatumWriter

import scala.collection.JavaConversions._

import scala.collection.mutable.ArrayBuffer

object AvroSerDe {

  def serialize(tweets: List[TweetAvro], file: File) = {
    val out = new BufferedOutputStream(new FileOutputStream(file))
    val datumWriter = new SpecificDatumWriter[TweetAvro]()
    val dataFileWriter = new DataFileWriter[TweetAvro](datumWriter)
    dataFileWriter.create(TweetAvro.SCHEMA$, out)
    tweets.foreach(dataFileWriter.append)
    dataFileWriter.close()
    out.flush()
  }

  def deserialize[T](file: File, schema: Schema): Option[T] = {
    val bytes = Files.readAllBytes(file.toPath)
    val in = new SeekableByteArrayInput(bytes)
    val datumReader = new GenericDatumReader[T](schema)
    val dataFileReader = new DataFileReader[T](in, datumReader)

    if (dataFileReader.hasNext) Some(dataFileReader.next()) else None
  }

  def deserializeAsMap(file: File, schema: Schema): List[Map[String, String]] = {
    val datumReader = new GenericDatumReader[GenericRecord](schema)
    val fileReader = new DataFileReader[GenericRecord](file, datumReader)

    fileReader.iterator().map { genericRecord: GenericRecord =>
      schema.getFields.map { field =>
        field.name() -> genericRecord.get(field.name()).toString
      }.toMap
    }.toList
  }
}
