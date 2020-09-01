package com.example.android_architecture.view.detail;

import com.example.android_architecture.api.model.User;
import com.example.android_architecture.rxEventBus.RxEvent;

public class DetailPresenter implements DetailContract.Presenter {
    DetailContract.View view;

    @Override
    public void setView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
    }

    @Override
    public void clickEvent(User user) {
        user.likeCnt++;

        // view 상태에 변화를 줌
        view.setText(user.getLikeCnt());

        // RxEventBus를 호출하여 MainActivity에 변화가 생겼음을 알림.
        RxEvent.getInstance().sendEvent(user);
    }
}
