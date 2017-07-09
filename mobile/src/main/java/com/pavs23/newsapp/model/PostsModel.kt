package com.pavs23.newsapp.model

import com.pavs23.newsapp.presenter.FeedPresenterImpl

/**
 * Created by pavan on 9/7/17.
 */
interface PostsModel {

    fun getTopNews(getTopNewsListener: FeedPresenterImpl.getTopNewsListener)

}