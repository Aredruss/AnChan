package com.redbox.anchan.network

import com.redbox.anchan.network.pojo.Page
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{board}/{page}.json")
    fun getPage(
        @Path("board") board: String,
        @Path("page") page: Int
    ): Single<Page>

    @GET("{board}/thread/{num}.json")
    fun getThread(
        @Path("board") board: String,
        @Path("num") num: Int
    ): Observable<Thread>

}