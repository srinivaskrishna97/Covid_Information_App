package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.covidinformation.R;

public class MapActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        webview = (WebView)findViewById(R.id.articleWebView);
        String url="https://www.healthmap.org/covid-19/";
        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webview.loadUrl("javascript:(function() {var a =  document.getElementsByClassName('mobile-header');for (index = 0; index < a.length; ++index) { a[index].style.display='none';}})()");
                webview.loadUrl("javascript:(function() {var a =  document.getElementsByClassName('mapboxgl-ctrl-logo');for (index = 0; index < a.length; ++index) { a[index].style.display='none';}})()");
                webview.loadUrl("javascript:(function() {var a =  document.getElementsByClassName('credit');for (index = 0; index < a.length; ++index) { a[index].style.display='none';}})()");
                webview.loadUrl("javascript:(function() {var a =  document.getElementsByClassName('mapboxgl-ctrl-bottom-right');for (index = 0; index < a.length; ++index) { a[index].style.display='none';}})()");
            }

        });

        webview.loadUrl(url);


    }
}