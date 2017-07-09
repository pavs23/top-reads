package com.pavs23.newsapp.presenter


import com.pavs23.newsapp.datamodel.NewsApiResponse
import com.pavs23.newsapp.model.PostsModel
import com.pavs23.newsapp.model.PostsModelImpl
import com.pavs23.newsapp.view.FeedView

class FeedPresenterImpl : FeedPresenter {


    private lateinit var feedView: FeedView
    private lateinit var postsModel: PostsModel

    interface getTopNewsListener {

        fun onSuccess(newsApiResponse: NewsApiResponse?)

        fun onFailure(errorMessage: String?)
    }

    override fun onBind(view: FeedView) {
        feedView = view
        postsModel = PostsModelImpl()
    }


    override fun onLoad() {

        feedView.showProgressBar()

        postsModel.getTopNews(object : getTopNewsListener {

            override fun onSuccess(redditNewsResponse: NewsApiResponse?) {
                feedView.hideProgressBar()
                feedView.showTopNews(redditNewsResponse!!)
            }

            override fun onFailure(errorMessage: String?) {
                feedView.hideProgressBar()
                feedView.showError(errorMessage)
            }
        })

    }

}