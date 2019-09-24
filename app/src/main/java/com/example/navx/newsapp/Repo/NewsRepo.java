package com.example.navx.newsapp.Repo;

import androidx.lifecycle.MutableLiveData;

import com.example.navx.newsapp.RetrofitConfig.Retro;
import com.example.navx.newsapp.newsModel.NewsHeadlineModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {
    Retro retro;

    static NewsRepo newsRepo;
    public static NewsRepo getInstance(){
        if (newsRepo == null){
            newsRepo = new NewsRepo();
        }
        return newsRepo;
    }

    public NewsRepo(){
        retro=new Retro();
    }
    public MutableLiveData<NewsHeadlineModel> getnewsHeadLines(){
        final MutableLiveData<NewsHeadlineModel> newsData = new MutableLiveData<>();
        //calll api
        retro.getApi().NEWS_HEADLINE_MODEL_CALL().enqueue(new Callback<NewsHeadlineModel>() {
            @Override
            public void onResponse(Call<NewsHeadlineModel> call, Response<NewsHeadlineModel> response) {
                if (response.isSuccessful()){
                    //store data
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsHeadlineModel> call, Throwable t) {
       newsData.setValue(null);
            }
        });
        return newsData;
    }


}
