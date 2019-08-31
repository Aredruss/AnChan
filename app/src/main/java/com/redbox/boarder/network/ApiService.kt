package com.redbox.boarder.network

import com.redbox.boarder.network.pojo.Page
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{board}/{page}.json")
    fun getPage(
        @Path("board") board: String,
        @Path("page") page: Int
    ): Page

    @GET("{board}/thread/{num}.json")
    fun getThread(
        @Path("board") board: String,
        @Path("num") num : Int
    ) : Thread

}