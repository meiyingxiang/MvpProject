package com.mvp.project.view;


import com.mvp.project.base.view.BaseView;
import com.mvp.project.bean.JokeBean;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokeView extends BaseView {

    interface IJokeView extends BaseView {
        void getJokeData(JokeBean jokeBean);

        void onFailData(String msg);
    }


    interface IJokeModel extends BaseView {
        @GET("getJoke?page=1&count=2&type=video")
        Observable<JokeBean> getJokeData();
    }

}
