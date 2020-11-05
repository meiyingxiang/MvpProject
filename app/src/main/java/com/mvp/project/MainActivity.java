package com.mvp.project;


import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mvp.project.base.BaseActivity;
import com.mvp.project.bean.JokeBean;
import com.mvp.project.presenter.JokePresenter;
import com.mvp.project.view.JokeView;

import java.util.List;

public class MainActivity extends BaseActivity<JokeView.IJokeView, JokePresenter> implements JokeView.IJokeView {
    private LinearLayout listMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listMode = findViewById(R.id.listMode);

        getPresenter().getJokeData();
    }

    @Override
    public JokePresenter createPresenter() {
        return new JokePresenter();
    }

    @Override
    public JokeView.IJokeView createView() {
        return this;
    }


    @Override
    public void getJokeData(JokeBean jokeBean) {
        Log.e("Frank", "getJokeData: jokeBean=" + jokeBean.toString());
        if (jokeBean!=null){
            List<JokeBean.ResultBean> result = jokeBean.getResult();
            if (result!=null&&result.size()>0){
                for (int i = 0; i < result.size(); i++) {
                    JokeBean.ResultBean resultBean = result.get(i);
                    if (resultBean!=null){
                        String thumbnail = resultBean.getThumbnail();
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        Glide.with(MainActivity.this)
                                .load(thumbnail)
                                .into(imageView);
                        listMode.addView(imageView);
                    }
                }
            }
        }
    }

    @Override
    public void onFailData(String msg) {
        Log.e("Frank", "onFailData: msg=" + msg);
    }
}
