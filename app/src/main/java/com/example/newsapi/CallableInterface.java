package com.example.newsapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallableInterface {
    //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=02e9e45a987e434ea9ce461ed700dcfd
    //https://jsonformatter.curiousconcept.com/#
    //https://jsonformatter.org/json-to-java

    String BASE_URL = "https://newsapi.org";

    @GET("/v2/top-headlines?country=us&category=business&apiKey=02e9e45a987e434ea9ce461ed700dcfd")
    Call<NewsModel> getNews();

}
