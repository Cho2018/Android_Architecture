package com.example.android_architecture.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("results") public List<User> userList;
}
