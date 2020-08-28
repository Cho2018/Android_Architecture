package com.example.android_architecture.view.main;

import com.example.android_architecture.BaseContract;

public class MainContract {
    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<View> {

    }
}
