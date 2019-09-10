package com.redbox.anchan.network

import com.redbox.anchan.network.pojo.BoardList
import com.redbox.anchan.network.pojo.ThreadList
import com.redbox.anchan.network.pojo.PostList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{board}/{page}.json")
    fun getPage(
        @Path("board") board: String,
        @Path("page") page: Int
    ): Single<ThreadList>

    @GET("{board}/thread/{num}.json")
    fun getThread(
        @Path("board") board: String,
        @Path("num") num: Int
    ): Single<PostList>

    @GET("/boards.json")
    fun getBoards() : Single<BoardList>

}