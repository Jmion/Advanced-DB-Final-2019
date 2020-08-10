import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object MyClass {
  def solution(): RDD[(String, String)] = {
    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()

    val sc = spark.sparkContext


    val employee = sc.parallelize(List(
      ((1000, 1200), ("Mary", 1000)),
      ((1200, 1400), ("Jon", 1200)),
      ((800, 1000), ("Jane", 800)),
      ((1400, 1600), ("Bob", 1400))
    ))

    val t = employee.groupByKey()

    t.collect.foreach(println)

    t.cartesian(t).filter{case (((min1, _),_), ((_,max2),_)) =>
      min1 <= max2}.flatMap { case (((_, _), e1), ((_, _), e2)) =>
      for ((e1name, e1salary) <- e1;
           (e2name, e2salary) <- e2
           if e1salary < e2salary) yield {(e1name, e2name)}
    }
  }

}
