package com.cs.news1.fragment.videoAdapter;

import com.cs.news1.entry.Video;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chenshuai on 2017/2/7.
 */

public interface MyVideos {

    //http://www.mocky.io/v2/58997c7c0f000085021f4ae1
    @GET("v2/58997c7c0f000085021f4ae1")
    Observable<Video> getDate();

}
