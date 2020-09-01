package com.example.android_architecture.view.main;

import com.example.android_architecture.BaseContract;
import com.example.android_architecture.api.model.User;

import java.util.ArrayList;

public interface MainContract {
    interface View extends BaseContract.View {
        void showProgress();

        void hideProgress();

        void showToast(String message);

        // 아이템을 어댑터에 연결
        void setItems(ArrayList<User> items);

        void updateView(User user);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        @Override
        void setView(View view);

        @Override
        void releaseView();

        // API 통신을 통해 데이터를 받아옴
        void loadData();

        // RxEventBus를 연결하여 Like 값을 동기화
        void setRxEvent();
    }
}
