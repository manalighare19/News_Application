/*
 ************************ Assignment #HOMEWORK 05 *******************************************
 *********************** File Name- WebActivity.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */

package com.example.manalighare.hw05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.example.manalighare.hw05.NewsActivity.SELECTED_NEWS;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getSupportActionBar().hide();

        webView = (WebView)findViewById(R.id.webview);
        news = (News) getIntent().getExtras().getSerializable(SELECTED_NEWS);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(news.url);


    }
}
