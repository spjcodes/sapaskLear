import java.text.SimpleDateFormat
import java.util.Date

object TimeTransformtTest {


  def main(args: Array[String]): Unit = {
    println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
  }

}
