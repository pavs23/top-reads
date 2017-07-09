package com.pavs23.newsapp.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import com.pavs23.newsapp.R
import com.pavs23.newsapp.datamodel.NewsApiResponse
import com.pavs23.newsapp.presenter.FeedPresenterImpl

class MainActivity : AppCompatActivity(), FeedView {

    val presenter: FeedPresenterImpl = FeedPresenterImpl()

    lateinit var listView: ListView
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        listView = findViewById<ListView>(R.id.news_feed)

        progressDialog = ProgressDialog(this)

        with(progressDialog) {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setMessage("Loading some tech news")
        }
    }

    override fun showTopNews(topPosts: NewsApiResponse?) {
        listView.adapter = SimpleListAdapter(topPosts?.articles!!, this)
    }

    override fun onResume() {
        super.onResume()

        presenter.onBind(this)
        presenter.onLoad()
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressDialog.show()
    }

    override fun hideProgressBar() {
        progressDialog.hide()
    }

}
