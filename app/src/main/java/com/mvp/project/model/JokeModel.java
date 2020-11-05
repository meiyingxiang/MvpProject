package com.mvp.project.model;

import com.mvp.project.bean.JokeBean;
import com.mvp.project.net.retrofit.RetrofitRequestUtilsFansClub;

import io.reactivex.Observable;

public class JokeModel {
    public Observable<JokeBean> getJokeData(String url){
        return RetrofitRequestUtilsFansClub.getApiServiceFansClub(url);
    }

}
