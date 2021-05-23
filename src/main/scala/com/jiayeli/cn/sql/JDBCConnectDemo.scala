package com.jiayeli.cn.sql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.impl.Log4jLoggerFactory

import java.util.Properties

object JDBCConnectDemo {
  System.setProperty("HADOOP_USER_NAME", "root")

  Logger.getLogger("org").setLevel(Level.ERROR)

  def connct2JDBC(): Unit = {
    val url = "jdbc:mysql://node01:3306/test?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8"
    val tb = "test"
    val properties = new Properties()
    properties.setProperty("user", "root")
    properties.setProperty("password", "spj+123@Pass")

    val spkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()

    val mysqlFM: DataFrame = spkSession.read.jdbc(url, tb, properties)

    mysqlFM.printSchema()

    spkSession.close()
  }

  def main(args: Array[String]): Unit = {
    this.connct2JDBC()
  }




}
