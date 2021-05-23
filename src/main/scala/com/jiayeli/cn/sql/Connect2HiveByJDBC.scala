package com.jiayeli.cn.sql

import org.apache.spark.sql.SparkSession
import java.util.Properties

object Connect2HiveByJDBC {

  System.setProperty("HADOOP_USER_NAME", "root")



  def connect2Hive: Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("connect2HiveByJDBC")
      //开启hive支持
      .enableHiveSupport()
      .getOrCreate()

    val hurl = "jdbc:hive2://node01:10000"
    val tbName = "test"
    val propts = new Properties()
    propts.setProperty("username", "root")
    propts.setProperty("password", "root")
    propts.setProperty("driver", "")

    val df = spark.read.jdbc(hurl, tbName, propts)
    df.show()
    df.printSchema()



    spark.stop()

  }

  def main(args: Array[String]): Unit = {
    this.connect2Hive
  }

}
