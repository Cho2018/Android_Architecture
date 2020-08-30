package com.example.android_architecture.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android_architecture.BaseActivity;
import com.example.android_architecture.R;
import com.example.android_architecture.api.model.User;

import java.util.ArrayList;

import butterknife.BindView;

//TODO
public class MainActivity extends BaseActivity
        implements MainContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_view)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RANDOM USER");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setItems(ArrayList<User> items) {

    }

    @Override
    public void updateView(User user) {

    }
}
