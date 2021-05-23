import java.sql.{Connection, DriverManager}

object JBDCConnectMysqlTest {

  try {
    Class.forName("com.mysql.jdbc.Driver")
  } catch {
    case e: Exception => {
      println("error: 驱动加载失败！！！")
      e.printStackTrace()
    }
  }

  def getMysqlConnection(): Connection = {
    val connection = DriverManager.getConnection("jdbc:mysql://node01:3306/test?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "spj+123@Pass")
    connection
  }

  def main(args: Array[String]): Unit = {
    val connection = this.getMysqlConnection()

    if (connection == null) {
      println("获取连接失败！！！")
      System.exit(-1)
    }

    val statement = connection.prepareStatement("")
    val insertUser: String = "insert into  test.user values(id = 2, name = 'spj', age = '26', address = 'china shanghai' )"
    val result = statement.executeUpdate(insertUser)
    println(result)
    println("..................======================...........................")
    val result1 = statement.executeQuery("select * from test.user");
    while(result1.next()) {
      println(result1.getInt("id"))
      println(result1.getString("name"))
      println(result1.getString("address"))
      println(result1.getInt("age"))
    }
  }



}
