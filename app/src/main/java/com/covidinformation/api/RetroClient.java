package com.covidinformation.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {


    //Retrofit is type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services. ... Retrofit automatically serialises the JSON response

    private static final String ROOT_URL = "http://covidinformation.live/";
    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
