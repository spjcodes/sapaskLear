package com.jiayeli.cn.sql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.Properties

object test {

  Logger.getLogger("org").setLevel(Level.INFO)

  def main(args: Array[String]): Unit = {
    //写SparkSQL程序要创建SparkSession，其实是对SparkContext的增强（包装），SparkSession中持有了SparkContext引用
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName(this.getClass.getSimpleName)
      .getOrCreate()

    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","spj+123@Pass")

    //在Driver端读取MySQL，只是为了获取schema信息，没有读取真正的数据
    //spark.read.jdbc方法不会触发Action
    val df: DataFrame = spark.read.jdbc("jdbc:mysql://node01:3306/test?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8", "test", props)

    df.printSchema()

    df.createOrReplaceTempView("t_test")
    import spark.sql
    sql("insert into table t_test values ('4')")
    sql("insert into table t_test values ('2')")

    df.show()


    Thread.sleep(100000000)
  }
}
