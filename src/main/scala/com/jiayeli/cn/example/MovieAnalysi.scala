package com.jiayeli.cn.example

import com.jiayeli.cn.module.movie.MovieModule
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.util.LegacySimpleTimestampFormatter
import org.apache.spark.{SparkConf, SparkContext}

import java.io.{File, PrintWriter}
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 电影评分分析
 */
object MovieAnalysis {

  val path = "src/main/resources/data/movie/"

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("电影评分分析程序")
    conf.setMaster("local[*]")

    val sc = SparkContext.getOrCreate(conf)
    sc.setLogLevel("warn")
    //1256|Designated Mourner, The (1997)|23-May-1997||http://us.imdb.com/M/title-exact?Designated%20Mourner%2C%20The%20%281997%29|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0
    val movieLines: RDD[String] = sc.textFile(path + "movie.info")
    //1256 Designated Mourner, The (1997) 23-May-1997 |http://us.imdb.com/M/title-exact?De
    val moviesInfo: RDD[Array[String]] = movieLines.map(_.split('|'))

    val errorDataFileName: String = "errorOut/errorDatas_" +
      new  SimpleDateFormat("yyyMMddHHmmss").format(new Date()) + ".data"
    val erroDatas = new File(path +  errorDataFileName)
    if (!erroDatas.exists())
      erroDatas.createNewFile()


    var movies: RDD[MovieModule] = null
    //判断是否有脏数据，有的话保存到指定文件中
    movies = moviesInfo
      .mapPartitions(p => {
       val errorDataSWrite = new PrintWriter(erroDatas)
        try {
          p.map(e => {
            var movie: MovieModule = null
             if (e.length == 24) {
                movie = new MovieModule(
                  e(0),
                  e(1),
                  e(2),
                  e(3),
                  e(4),
                  e(5),
                  e(6),
                  e(7),
                  e(8),
                  e(9),
                  e(10),
                  e(11),
                  e(12),
                  e(13),
                  e(14),
                  e(15),
                  e(16),
                  e(17),
                  e(18),
                  e(19),
                  e(20),
                  e(21),
                  e(22),
                  e(23)
              )
              movie
             } else {
                errorDataSWrite.write(e.toString)
                movie
              }
            movie
          }).filter(_ != null)
        } catch {
          case e: Exception => {
            e.printStackTrace()
            null
          }
        } finally {
          errorDataSWrite.flush()
          errorDataSWrite.close()
        }
      })

    movies.take(10).foreach(println(_))
    Thread.sleep(Long.MaxValue)
    sc.stop()

  }


}
