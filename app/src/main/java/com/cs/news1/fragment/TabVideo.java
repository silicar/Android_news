package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.application.MyApplication;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Video;
import com.cs.news1.fragment.videoAdapter.MyVideos;
import com.cs.news1.fragment.videoAdapter.VideoAdapter;
import com.cs.news1.uri.Uri;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabVideo extends BaseFragment{

    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private List<Video.V9LG4E6VRBean> mList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_video,container,false);
        initView(view);
        initData();
        return view;
    }
    private void initData() {
      /*  OkHttpUtils
                .get()
                .url("http://www.mocky.io/v2/58997c7c0f000085021f4ae1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Video video = gson.fromJson(response, Video.class);
                        mList.addAll(video.getV9LG4E6VR());
                        adapterVideoList.notifyDataSetChanged();
                    }
                });*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Uri.VIDEOURI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MyApplication.defalutOkHttpClient())
                .build();
        final MyVideos myVideos = retrofit.create(MyVideos.class);
        Observable<Video> observable = myVideos.getDate();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Video>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Video video) {
                        mList.addAll(video.getV9LG4E6VR());
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoAdapter(getContext(),mList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
//                if (JCVideoPlayerManager.getCurrentJcvdOnFirtFloor() != null) {
//                    JCVideoPlayer videoPlayer = (JCVideoPlayer) JCVideoPlayerManager.getCurrentJcvdOnFirtFloor();
//                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
//                        JCVideoPlayer.releaseAllVideos();
//                    }
//                }
            }
        });
    }
}
