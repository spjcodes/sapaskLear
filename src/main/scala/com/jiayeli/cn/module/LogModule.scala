package com.jiayeli.cn.module

import java.sql.Timestamp


case class LogModule(

   val account    	 : String
  ,val appId         : String
  ,val appVersion    : Double
  ,val carrier       : String
  ,val deviceId      : String
  ,val deviceType    : String
  ,val eventId       : String
  ,val ip            : String
  ,val latitude      : Double
  ,val longitude     : Double
  ,val netType       : String
  ,val osName        : String
  ,val osVersion     : Double
  ,val properties    : Map[String, String]
  ,val releaseChannel: String
  ,val resolution    : String
  ,val sessionId     : String
  ,val timeStamp     : Timestamp

  ) { }
