package com.jiayeli.cn.module.movie

case class RatingModule(
                         userId: String // 用户id
                         , itemId: String // 电影id
                         , rating: String // 评分
                         , timestamp: String // 时间戳
                       )
