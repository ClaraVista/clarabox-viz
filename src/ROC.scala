import breeze.plot._

import org.apache.spark.SparkContext

/**
 * Created with IntelliJ IDEA.
 * User: coderh
 * Date: 1/13/14
 * Time: 12:29 PM
 */
object ROC {

  def d2Plot(x: Array[Double], y: Array[Double]): Unit = {
    val f = Figure()
    val p = f.subplot(0)

    // figure settings
    p.xlim = (0, 1)
    p.ylim = (0, 1)
    p.xlabel = "% total population"
    p.ylabel = "% target population"


    // draw data
    p += plot(x, y, '-', "red")
    p += plot(Array(0, 1), Array(0, 1), '-', "blue")
    p
  }

  def run(): Unit = {
    val s3BucketName = "claravista.output/"
    val s3outputLocation = "liftTest/"
    val learningCurveData = Common.sc.textFile("s3n://" + s3BucketName + s3outputLocation)
      .map(_.split(";"))
      .map {
      case Array(x, y) => (x.toDouble, y.toDouble)
    } .collect

    val (xs, ys) = learningCurveData.unzip
    d2Plot(xs.toArray, ys.toArray)
  }

  def main(args: Array[String]) {
    run
  }
}
