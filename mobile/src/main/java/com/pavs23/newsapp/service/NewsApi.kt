package com.pavs23.newsapp.service

import com.pavs23.newsapp.datamodel.NewsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pavan on 9/7/17.
 */
interface NewsApi {


    /**
     * @param apiKey: your developer API Key
     * @param source: default value is tech-crunch
     * @param sortBy: default sorting key is "top" articles
     *
     * @return
     */

    @GET("/v1/articles")
    fun getNews(@Query("apiKey") apiKey: String
                , @Query("source") source: Source): Call<NewsApiResponse>

    /**
     * Sources of news
     */
    enum class Source(val source: String) {
        TECHRADAR("techradar"),
        TECHCRUNCH("techcrunch"),
        IGN("ign")
    }

    /**
     * Full Sort Criteria:
     *      top	Requests a list of the source's headlines sorted in the order they appear on its homepage.
     *      latest	Requests a list of the source's headlines sorted in chronological order, newest first.
     *      popular Requests a list of the source's current most popular or currently trending headlines.
     *
     */

    enum class SortBy(val sortBy: String) {
        TOP("top"),
        LATEST("latest"),
        POPULAR("popular")
    }

}