package com.example.codingchallengenycschools.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.codingchallengenycschools.R
import kotlinx.android.synthetic.main.activity_school_web.*

class SchoolWebActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_web)
        this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
    }

    override fun onResume() {
        super.onResume()

        val schoolUrl = intent.getStringExtra("schoolUrl")

        webview.apply {
            webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progress_bar.visibility = View.GONE
                }
            }
        }

        //Assist in performance
        webview.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        webview.loadUrl(schoolUrl)
    }

    override fun finish() {
        super.finish()
        this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
    }


}
