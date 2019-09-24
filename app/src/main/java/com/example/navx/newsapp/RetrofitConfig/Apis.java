package com.example.navx.newsapp.RetrofitConfig;

import com.example.navx.newsapp.newsModel.NewsHeadlineModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apis {

    @GET("top-headlines?country=us&category=business&apiKey=ac500ae75cdf4f769c5a64f5ebd785a1")
    Call<NewsHeadlineModel> NEWS_HEADLINE_MODEL_CALL();

}
