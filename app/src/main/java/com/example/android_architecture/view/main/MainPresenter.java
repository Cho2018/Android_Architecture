package com.example.android_architecture.view.main;

import android.util.Log;

import com.example.android_architecture.api.GithubApi;
import com.example.android_architecture.api.GithubApiProvider;
import com.example.android_architecture.api.model.User;
import com.example.android_architecture.constant.Constant;
import com.example.android_architecture.rxEventBus.RxEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {
    private GithubApi api;
    private MainContract.View view;
    private CompositeDisposable disposable;

    MainPresenter() {
        this.api = GithubApiProvider.provideGithubApi();
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        disposable.clear();
    }

    @Override
    public void loadData() {
        disposable.add(api.getUserList(Constant.RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> {
                    view.showProgress();
                })
                .doOnTerminate(() -> {
                    view.hideProgress();
                })
                .subscribe(userResponse -> {
                    view.setItems((ArrayList<User>)userResponse.userList);
                }, error -> {
                    view.showToast(error.getMessage());
                })
        );
    }

    @Override
    public void setRxEvent() {
        disposable.add(
                RxEvent.getInstance()
                        .getObservable()
                        .subscribe(
                                object -> {
                                    if (object instanceof User) {
                                        view.updateView((User) object);
                                    }
                                },
                                // 아래 코드는 생략 가능
                                error -> {
                                    Log.d("MyTag", "onError");
                                },
                                () -> {
                                    Log.d("MyTag", "onCompleted");
                                }
                        )
        );
    }
}
