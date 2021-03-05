package com.covidinformation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.covidinformation.R;

public class MapFragment  extends Fragment {

    View view;
    private WebView webview;
    public static MapFragment mapFragment() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_map, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("World Map");

        webview = (WebView)view.findViewById(R.id.articleWebView);
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




        return view;
    }
}
