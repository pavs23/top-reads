package com.pavs23.newsapp.datamodel

/**
 * Created by pavan on 9/7/17.
 */

class NewsApiResponse(
        var status: String?,
        var source: String?,
        var sortBy: String?,
        var message: String?,
        var articles: List<NewsApiArticle>?) : Response()


class NewsApiArticle(
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String
)