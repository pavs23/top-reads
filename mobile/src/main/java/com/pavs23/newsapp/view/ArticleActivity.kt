package com.pavs23.newsapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView

/**
 * Created by pavan on 22/8/17.
 */
class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webview = WebView(this)
        setContentView(webview)
        val url: String = intent.getStringExtra("url")
        webview.loadUrl(url)
    }

}
