package com.cs.news1.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 网络请求
 * Created by tutu on 16-10-20.
 */

public class AppRetrofit {
    private static String BASE_URL = "http://www.tngou.net/";
    private static String GANK_URL = "http://gank.io/api/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())    //显示Log
            .connectTimeout(1, TimeUnit.MINUTES)    //连接超时
            .readTimeout(2, TimeUnit.MINUTES)       //读取超时
            .writeTimeout(1, TimeUnit.MINUTES)      //写入超时
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    private static Retrofit retrofitGank = new Retrofit.Builder()
            .baseUrl(GANK_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public static Retrofit getRetrofitGank() {
        return retrofitGank;
    }

    public static Retrofit getRetrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
