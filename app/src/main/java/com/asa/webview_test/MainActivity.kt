package com.asa.webview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView.loadUrl("https://shiobaraseminar.jimdofree.com/works/2017%E5%B9%B4%E5%BA%A6%E5%8D%92%E6%A5%AD%E7%94%9F-%E5%8D%92%E6%A5%AD%E8%AB%96%E6%96%87%E4%B8%80%E8%A6%A7/")
    }
}