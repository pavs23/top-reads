package com.pavs23.newsapp.presenter

import android.view.View
import com.pavs23.newsapp.view.FeedView

/**
 * Created by pavan on 6/7/17.
 */
interface FeedPresenter {

    fun onBind(feedView: FeedView)

    fun onLoad()

    fun onItemClicked(position: Int)
}