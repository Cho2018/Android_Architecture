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

//TODO
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private GithubApi api;
    private CompositeDisposable disposable;

    MainPresenter() {
        this.api = GithubApiProvider.provideGithubApi();
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    // 해제시, 사용중인 통신 중단
    @Override
    public void releaseView() {
        disposable.clear();
    }

    @Override
    public void loadData() {
        disposable.add(api.getUserList(Constant.RANDOM_USER_URL) // REST API 를 통해 정보 요청
                .subscribeOn(Schedulers.io()) // 어떤 스레드에서 작업을 실행할지 정하는 함수
                .observeOn(AndroidSchedulers.mainThread()) // 어떤 스레드에서 작업을 실행할지 정하는 함수
                .doOnSubscribe(__ -> { // 구독할 때 수행할 작업을 구현
                    view.showProgress();
                })
                .doOnTerminate(() -> { // 스트림이 종료될 때 수행할 작업을 구현
                    view.hideProgress();
                })
                .subscribe(userResponse -> {
                    view.setItems((ArrayList<User>)userResponse.userList);
                }, error -> {
                    view.showToast(error.getMessage());
                    Log.e("MyTag", error.getMessage());
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
