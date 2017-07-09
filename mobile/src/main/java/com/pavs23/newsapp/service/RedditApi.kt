package com.pavs23.newsapp.service

import com.pavs23.newsapp.datamodel.RedditNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RedditApi {

    @GET("/top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: Int): Call<RedditNewsResponse>

}