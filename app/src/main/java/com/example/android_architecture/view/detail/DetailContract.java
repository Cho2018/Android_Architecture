package com.example.android_architecture.view.detail;

import com.example.android_architecture.BaseContract;
import com.example.android_architecture.api.model.User;

public interface DetailContract {
    interface View extends BaseContract.View {
        void setText(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        @Override
        void setView(DetailContract.View view);

        @Override
        void releaseView();

        void clickEvent(User user);
    }
}
