package org.liju.examples

import org.apache.spark.{SparkConf, SparkContext}

/*

*/
object WordCount {

  def main(args: Array[String]): Unit = {
    val inFile = "hdfs://localhost:9000/tmp/temp.txt"
    val outFile = "hdfs://localhost:9000/tmp/wordcount"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val file = sc.textFile(inFile)
    val words = file.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    counts.saveAsTextFile(outFile)
  }
}
