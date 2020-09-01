package com.example.android_architecture.view.detail;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_architecture.BaseActivity;
import com.example.android_architecture.R;
import com.example.android_architecture.api.model.User;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends BaseActivity implements DetailContract.View {
    DetailPresenter presenter;

    @BindView(R.id.iv_detail_profile)
    CircleImageView ivDetailProfile;

    @BindView(R.id.tv_detail_like_cnt)
    TextView tvDetailLIkeCnt;

    public final static String KEY_USER = "key_user";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new DetailPresenter();
        presenter.setView(this);

        getUserFromIntent();
    }

    private void getUserFromIntent() {
        user = (User) getIntent().getSerializableExtra(KEY_USER);
        setTitle(user.getFullName());

        // 받아온 User 객체로부터 View 초기화
        initView(user);
    }

    private void initView(User user) {
        tvDetailLIkeCnt.setText(user.getLikeCnt());

        Glide.with(this)
                .load(user.picture.large)
                .into(ivDetailProfile);
    }

    @Override
    public void setText(String text) {
        tvDetailLIkeCnt.setText(text);
    }

    @OnClick(R.id.btn_detail_like) void onClick() {
        presenter.clickEvent(user);
    }
}
