package parquet

import java.io.File
import java.nio.file.Files

import com.tweet.avro.TweetAvroOpt
import com.tweet.thrift.TweetThrift
import org.apache.avro.Schema
import org.apache.avro.file.{DataFileReader, SeekableByteArrayInput}
import org.apache.avro.generic.{GenericDatumReader, GenericRecord}
import org.apache.avro.specific.SpecificRecord
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.parquet.avro.{AvroParquetReader, AvroParquetWriter, AvroReadSupport}
import org.apache.parquet.hadoop.ParquetReader
import org.apache.parquet.hadoop.api.ReadSupport
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.thrift.ThriftParquetWriter

import scala.collection.JavaConversions._


object ParquetSerDe {
  def serializeWithAvroSchema[T <: SpecificRecord](lt: List[T], schema: Schema, file: File): Unit = {
    val dataFileWriter = AvroParquetWriter
      .builder[T](new Path(file.getAbsolutePath))
      .withSchema(schema)
      .withConf(new Configuration())
      .withCompressionCodec(CompressionCodecName.UNCOMPRESSED)
      .build()

    lt.foreach(dataFileWriter.write)
    dataFileWriter.close()
  }

  def serializeWithThriftSchema(lt: List[TweetThrift], file: File): Unit = {
    val dataFileWriter = new ThriftParquetWriter[TweetThrift](new Path(file.getAbsolutePath),
      classOf[TweetThrift],
      CompressionCodecName.UNCOMPRESSED)
    lt.foreach(dataFileWriter.write)
    dataFileWriter.close()
  }


  def deserializeWithAvroSchema[T <: SpecificRecord](file: File, readSchema: Schema): Unit = {
    val readSupport = new AvroReadSupport[T]()
    val conf = new Configuration()
    AvroReadSupport.setAvroReadSchema(conf, readSchema)
    AvroReadSupport.setRequestedProjection(conf, readSchema)

    val reader =
      AvroParquetReader
        .builder[GenericRecord](new Path(file.getAbsolutePath))
        .withConf(conf)
        .build()

    /*    val reader =
          ParquetReader
            .builder(readSupport, new Path(file.getAbsolutePath))
            .withConf(conf)
            .build()*/

    Iterator.continually(reader.read()).takeWhile(_ != null).foreach(println)
  }
}
