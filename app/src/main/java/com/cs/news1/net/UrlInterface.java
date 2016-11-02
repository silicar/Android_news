package com.cs.news1.net;

import com.cs.news1.entry.Bean;
import com.cs.news1.entry.News;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tutu on 16-10-20.
 */

public interface UrlInterface {

    //gank.io API
    interface Gank{

        @GET("data/福利/{month}/{day}")
        Observable<Bean> getDataPhoto(@Path("month") String month, @Path("day") String day);

        @GET("data/Android/{month}/{day}")
        Observable<Bean> getDataAndroid(@Path("month") String month, @Path("day") String day);

        @GET("data/iOS/{month}/{day}")
        Observable<Bean> getDataIOS(@Path("month") String month, @Path("day") String day);

    }

    //聚合数据
    interface Tngou{
        //@GET("/api/top/list")
        //Call<News> getDate(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);

        @GET("api/top/list")
        Observable<News> getDate(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);
    }

}
