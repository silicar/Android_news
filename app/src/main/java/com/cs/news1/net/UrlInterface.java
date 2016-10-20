package com.cs.news1.net;

import com.cs.news1.bean.Bean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tutu on 16-10-20.
 */

public class UrlInterface {

    //gant.io API
    public interface Gank{

        @GET("data/福利/100/1")
        Observable<Bean> getDatePhoto();

    }

}
