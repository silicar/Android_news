package com.cs.news1.net;

import com.cs.news1.entry.Bean;
import com.cs.news1.entry.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tutu on 16-10-20.
 */

public class UrlInterface {

    //gank.io API
    public interface Gank{

        @GET("data/福利/100/1")
        Observable<Bean> getDataPhoto();

        @GET("data/Android/10/24")
        Observable<Bean> getDataAndroid();

        @GET("data/iOS/10/24")
        Observable<Bean> getDataIOS();

    }

    //聚合数据
    public interface Tngou{
        //@GET("/api/top/list")
        //Call<News> getDate(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);

        @GET("api/top/list")
        Observable<News> getDate(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);
    }

}
