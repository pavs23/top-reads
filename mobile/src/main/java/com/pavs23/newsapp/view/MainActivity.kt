package com.pavs23.newsapp.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.pavs23.newsapp.R
import com.pavs23.newsapp.datamodel.NewsApiResponse
import com.pavs23.newsapp.presenter.FeedPresenterImpl
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

class MainActivity : AppCompatActivity(), FeedView {

    val presenter: FeedPresenterImpl = FeedPresenterImpl()

    lateinit var listView: ListView
    lateinit var progressDialog: ProgressDialog
    private val button: Button by lazy { findViewById<Button>(R.id.image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.news_feed)

        progressDialog = ProgressDialog(this)

        with(progressDialog) {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setMessage("Loading some tech news")
        }

        presenter.onBind(this)
        presenter.onLoad()
    }

    override fun showTopNews(topPosts: NewsApiResponse?) {
        listView.adapter = SimpleListAdapter(topPosts?.articles!!, this)
        listView.setOnItemClickListener { a, b, c, d -> Log.d("Tag", "Hello") }
        listView.setOnItemClickListener { adapterView, a, i, l ->
            presenter.onItemClicked(i)
        }
    }

    override fun showArticle(url: String) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
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
