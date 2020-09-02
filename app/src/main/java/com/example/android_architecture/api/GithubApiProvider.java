package com.example.android_architecture.api;

import androidx.annotation.NonNull;

import com.example.android_architecture.constant.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class GithubApiProvider {
    public static GithubApi provideGithubApi() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL) // 통신할 서버의 주소 입력
                .client(provideOkHttpClient(provideLoggingInterceptor())) // 네트워크 요청 로그 표시
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()) // 받은 응답을 Observable 형태로 변환
                .addConverterFactory(GsonConverterFactory.create()) // 서버에서 json 형식으로 데이터를 보내고 이를 파싱해서 받아옴
                .build()
                .create(GithubApi.class);
    }

    // 네트워크 통신에 사용할 클라이언트 객체 생성
    private static OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.addInterceptor(interceptor); // 이 클라이언트 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 함
        return b.build();
    }

    // 네트워크 요청/응답을 로그에 표시하는 Interceptor 객체 생성
    private static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
