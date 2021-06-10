package com.example.bankweb

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        webViewSetup()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup() {

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true


        if (activeNetwork!=null && activeNetwork.isConnectedOrConnecting){
            webView.webViewClient = WebViewClient()
            webView.apply {
                Relative_internet.setVisibility(View.GONE)
                webView.setVisibility(View.VISIBLE)
                loadUrl("https://www.platinumsef.com")
                settings.javaScriptEnabled = true
                settings.safeBrowsingEnabled = true
            }
        }
        else{
            Relative_internet.setVisibility(View.VISIBLE)
            webView.setVisibility(View.GONE)
        }


    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        else super.onBackPressed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ReconnectPage(view: View) {

        webViewSetup()
    }
}