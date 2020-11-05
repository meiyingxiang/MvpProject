package com.mvp.project.net.retrofit;

import com.mvp.project.bean.JokeBean;
import com.mvp.project.view.JokeView;

import io.reactivex.Observable;

public class RetrofitRequestUtilsFansClub {

    public static Observable<JokeBean> getApiServiceFansClub(String url){
        JokeView.IJokeModel jokeModel = ApiClient.getRetrofit(url).create(JokeView.IJokeModel.class);
        return jokeModel.getJokeData();
    }
}
