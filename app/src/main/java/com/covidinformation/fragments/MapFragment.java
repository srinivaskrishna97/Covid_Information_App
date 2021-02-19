package com.covidinformation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.covidinformation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {
    private WebView webview;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment mapFragment() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        return fragment;
    }


//    public static MapFragment newInstance(String param1, String param2) {
//        MapFragment fragment = new MapFragment();
//        Bundle args = new Bundle();
////        args.putString(ARG_PARAM1, param1);
////        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {


        webview = webview.findViewById(R.id.articleWebView_F);
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

// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}