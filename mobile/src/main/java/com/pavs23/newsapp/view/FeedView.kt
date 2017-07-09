package com.pavs23.newsapp.view

import com.pavs23.newsapp.datamodel.NewsApiResponse

/**
 * Created by pavan on 8/7/17.
 */

interface FeedView {

    fun showTopNews(topPosts: NewsApiResponse?)

    fun showError(errorMessage : String?)

    fun showProgressBar()

    fun hideProgressBar()
}