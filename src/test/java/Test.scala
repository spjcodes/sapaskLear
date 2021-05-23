object Test {

  def main(args: Array[String]): Unit = {
    val str = "1257|Designated Mourner, The (1997)|23-May-1997||http://us.imdb.com/M/title-exact?Designated%20Mourner%2C%20The%20%281997%29|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0"
    val strings: Array[String] = str.split('|')
    strings.map(println(_))
  }

}
