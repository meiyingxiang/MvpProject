package com.mvp.project.presenter;

import com.mvp.project.base.presenter.BasePresenter;
import com.mvp.project.bean.JokeBean;
import com.mvp.project.model.JokeModel;
import com.mvp.project.view.JokeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.mvp.project.base.view.BaseView.BASE_URL;

public class JokePresenter extends BasePresenter<JokeView.IJokeView> {

    private JokeModel jokeModel;
    private CompositeDisposable mCompositeDisposable;

    public JokePresenter() {
        jokeModel = new JokeModel();
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getJokeData() {
        jokeModel.getJokeData(BASE_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JokeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
                            mCompositeDisposable.add(d);
                        }
                    }

                    @Override
                    public void onNext(JokeBean jokeBean) {
                        if (getView() != null) {
                            getView().getJokeData(jokeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().onFailData(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
