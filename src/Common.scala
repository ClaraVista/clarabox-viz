import org.apache.spark.SparkContext

/**
 * Created with IntelliJ IDEA.
 * User: coderh
 * Date: 1/13/14
 * Time: 12:55 PM
 */
object Common {
  val sc = new SparkContext("local[2]", "SparkLR", System.getenv("SPARK_HOME"), Seq(), Map[String, String]())
  val awsAccessKeyId = System.getenv("AWS_ACCESS_KEY_ID")
  val awsSecretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY")


  sc.hadoopConfiguration.set("fs.s3.awsAccessKeyId", awsAccessKeyId)
  sc.hadoopConfiguration.set("fs.s3.awsSecretAccessKey", awsSecretAccessKey)
  sc.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", awsAccessKeyId)
  sc.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", awsSecretAccessKey)
}
