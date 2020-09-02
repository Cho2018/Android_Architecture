package com.example.android_architecture.view.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android_architecture.BaseActivity;
import com.example.android_architecture.R;
import com.example.android_architecture.adapter.MainAdapter;
import com.example.android_architecture.api.model.User;
import com.example.android_architecture.view.detail.DetailActivity;

import java.util.ArrayList;

import butterknife.BindView;

//TODO
public class MainActivity extends BaseActivity
        implements MainContract.View, MainAdapter.OnItemClickListener {
    private MainAdapter adapter = new MainAdapter();
    private MainPresenter presenter = new MainPresenter();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_view)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RANDOM USER");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        presenter.setView(this);

        presenter.loadData();

        presenter.setRxEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.releaseView();
    }

    @Override
    public void onClick(User user) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_USER, user);
        startActivity(intent);
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
        adapter.setItems(items);
    }

    @Override
    public void updateView(User user) {
        adapter.updateView(user);
    }
}
