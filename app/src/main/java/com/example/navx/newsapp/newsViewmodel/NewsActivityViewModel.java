package com.example.navx.newsapp.newsViewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navx.newsapp.Repo.NewsRepo;
import com.example.navx.newsapp.newsModel.NewsHeadlineModel;

public class NewsActivityViewModel extends ViewModel {
    //create MutableLiveData object of model class or data
    private MutableLiveData<NewsHeadlineModel> mutableLiveData;
    private NewsRepo newsRepository;
    //init
    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        //connect repo for  getting data
        newsRepository = NewsRepo.getInstance();
        mutableLiveData = newsRepository.getnewsHeadLines();
    }
//give live data
    public LiveData<NewsHeadlineModel> getNewsRepository() {
        return mutableLiveData;
    }


    }
