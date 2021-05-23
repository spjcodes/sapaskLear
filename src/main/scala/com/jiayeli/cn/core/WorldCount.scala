package com.jiayeli.cn.core

import com.alibaba.fastjson.{JSON, JSONObject}
import com.jiayeli.cn.module.LogModule
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import java.sql.Timestamp

object WorldCount {

  val json = new JSONObject()

  def main(args: Array[String]): Unit = {
    val sc = SparkContext.getOrCreate(new SparkConf().setMaster("local[2]").setAppName("test"))
    val logs = sc.textFile("hdfs://node01:8020/app.log")
    val logModules: RDD[LogModule] = logs.map(e => {

      try {
        val jsonObj: JSONObject = JSON.parseObject(e)
        val account: String = jsonObj.getString("account")
        val appId: String = jsonObj.getString("appId")
        val appVersion: Double = jsonObj.getDouble("appVersion")
        val carrier: String = jsonObj.getString("carrier")
        val deviceId: String = jsonObj.getString("deviceId")
        val deviceType: String = jsonObj.getString("deviceType")
        val eventId: String = jsonObj.getString("eventId")
        val ip: String = jsonObj.getString("ip")
        val latitude: Double = jsonObj.getDouble("latitude")
        val longitude: Double = jsonObj.getDouble("longitude")
        val netType: String = jsonObj.getString("")
        val osName: String = jsonObj.getString("osName")
        val osVersion: Double = jsonObj.getDouble("osVersion")
        // val properties : Map[String, String]  = jsonObj.get()
        val releaseChannel: String = jsonObj.getString("releaseChannel")
        val resolution: String = jsonObj.getString("resolution")
        val sessionId: String = jsonObj.getString("sessionId")
        val timeStamp: Timestamp = jsonObj.getTimestamp("timeStamp")


      val module: LogModule = new LogModule(
          account
        , appId
        , appVersion
        , carrier
        , deviceId
        , deviceType
        , eventId
        , ip
        , latitude
        , longitude
        , netType
        , osName
        , osVersion
        , properties = null
        , releaseChannel
        , resolution
        ,sessionId = sessionId
        , timeStamp
      )

      module

      } catch {
        case e: Exception => {println("json parse error: "  + e.toString); null }
      }
    })

    val osNamGroup: RDD[(String, Iterable[LogModule])] =  logModules.filter(_ != null).groupBy(_.osName)
    val osNameCounts = osNamGroup.map(g => {
      var counts: Int = 0
      g._2.map(e => {
        counts += 1
      })
      (g._1, counts)
    })
    println("===============================================================================================")

    osNameCounts.foreach(println(_))

    println("===============================================================================================")

    Thread.sleep(Long.MaxValue)

    sc.stop()

  }

}
