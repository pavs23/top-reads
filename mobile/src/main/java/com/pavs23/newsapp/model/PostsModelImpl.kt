package com.pavs23.newsapp.model

import android.os.Build
import android.support.annotation.RequiresApi
import com.pavs23.newsapp.datamodel.NewsApiResponse
import com.pavs23.newsapp.presenter.FeedPresenterImpl
import com.pavs23.newsapp.service.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PostsModelImpl : PostsModel {


    // You can get your own API Key here: https://newsapi.org

    private val API_KEY: String = ""
    private val newsApi: NewsApi

    private lateinit var newsData: NewsApiResponse

    init {
        val newsRetrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        newsApi = newsRetrofit.create(NewsApi::class.java)
    }

    override fun getTopNews(getTopNewsListener: FeedPresenterImpl.getTopNewsListener) {

        var call: Call<NewsApiResponse> = newsApi.getNews(API_KEY, NewsApi.Source.TECHCRUNCH)

        call.enqueue(object : Callback<NewsApiResponse> {

            override fun onResponse(call: Call<NewsApiResponse>?, response: Response<NewsApiResponse>?) {
                if (response?.isSuccessful ?: false && response?.body()?.status.equals("ok")) {
                    getTopNewsListener.onSuccess(response?.body())
                    newsData = response?.body()!!
                } else {
                    getTopNewsListener.onFailure(response?.body()?.message ?: "Sorry :(")
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>?, t: Throwable?) {
                getTopNewsListener.onFailure("Oh no! Couldn't load the feed!")
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getArticle(positon: Int, urlListener: FeedPresenterImpl.getUrlListener) {
        var url = newsData.articles?.get(positon)?.url

        if (url != null && !url.isEmpty()) {
            urlListener.onSuccess(url)
        } else {
            urlListener.onFailure("Sorry, article doesn't exist anymore")
        }
    }

}
