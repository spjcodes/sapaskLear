import org.apache.spark.sql.SparkSession

import java.sql.DriverManager
import java.util.Properties

object JDBCConnectHiveTest {

  def connect2Hive (): Unit = {


    val url = "jdbc:hive2://node01:10000/bigdata_dev"
    val tbName = "userinfo"
    val propts = new Properties()
    propts.setProperty("username", "root")
    propts.setProperty("password", "root")

    try {
      Class.forName("org.apache.hive.jdbc.HiveDriver")
    } catch {
      case e : Exception => {
        println("驱动加载失败!")
        e.printStackTrace()
      }
    }

    val connection = DriverManager.getConnection(url, propts)
    val result = connection.createStatement().executeQuery("select * from userinfo")
    while (result.next()) {
      println(result.getString("username"))
      println(result.getString("username"))
    }
//    prepareStatement("")




  }


  def main(args: Array[String]): Unit = {
    connect2Hive
  }
}
