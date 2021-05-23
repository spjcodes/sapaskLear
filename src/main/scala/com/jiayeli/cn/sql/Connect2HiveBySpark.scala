package com.jiayeli.cn.sql

import org.apache.spark.sql.SparkSession

object Connect2HiveBySpark {

  def connect2Hive(): Unit = {

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("spark2hiveOpt")
      .enableHiveSupport()
      .getOrCreate()

    import spark.sql
    sql("show databases").show()
   /* sql("drop table if exists hetu_dws.userinfo")
    sql(
      """
        |
        |create table if not exists hetu_dws.userinfo (
        |username       string
        |,name          string
        |,com2          string
        |,com3          string
        |,com4          string
        |,com5          string
        |,comcode       string
        |,comlevel      string
        |,type          string
        |,channel       string
        |,channellevel  string
        |,gtzx          string
        |,crqd          string
        |)
        |
        |""".stripMargin)
      .show()*/
  }

  def main(args: Array[String]): Unit = {
    connect2Hive()
  }

}
