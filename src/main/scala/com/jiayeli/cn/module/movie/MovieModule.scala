package com.jiayeli.cn.module.movie

case class MovieModule(
                        movieId: String, // 电影id
                        movieTitle: String, // 电影名
                        releaseDate: String, // 发行时间
                        videoReleaseDate: String, // 视频发布时间
                        IMDbURL: String, // 电影url
                        unknown: String, // 未知
                        Action: String, // 动作
                        Adventure: String, // 冒险
                        Animation: String, // 动画
                        Childrens: String, // 儿童
                        Comedy: String, // 喜剧
                        Crime: String, // 犯罪
                        Documentary: String, // 纪录片
                        Drama: String, // 剧情
                        Fantasy: String, // 幻想
                        FilmNoir: String, // 黑色电影
                        Horror: String, // 恐怖
                        Musical: String, // 音乐
                        Mystery: String, // 神秘
                        Romance: String, // 爱情
                        SciFi: String, // 科幻
                        Thriller: String, // 惊悚
                        War: String, // 战争
                        Western: String // 西部

                      )
