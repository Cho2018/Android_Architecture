package com.example.android_architecture;

public class BaseContract {
    public interface View {
    }

    public interface Presenter<T> {
        void setView(T view);

        void releaseView();
    }
}
