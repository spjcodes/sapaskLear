package com.jiayeli.cn.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

import java.io.FileInputStream
import java.net.URI
import java.util.Properties

class GetHdfsClient {

  private var fs: FileSystem = null

  def getFileSystem = {

    val propts = new Properties()
    propts.load(new FileInputStream("application.yml"))
    val uri: String = propts.getProperty("hdfs.uri")
    fs = FileSystem.get(URI.create(uri), new Configuration)

  }

}
