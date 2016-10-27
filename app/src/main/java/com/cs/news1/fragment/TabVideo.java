package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Bean;
import com.cs.news1.fragment.fm_adapter.VideoAdater.VideoAdapter;
import com.cs.news1.net.AppRetrofit;
import com.cs.news1.net.UrlInterface;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabVideo extends BaseFragment{
    private RecyclerView mRecyclerView;
    private List<Bean.ResultsBean> mData=new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_video,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rl_video);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mVideoAdapter=new VideoAdapter(getContext(),mData);
        mRecyclerView.setAdapter(mVideoAdapter);
        final String url = "http://gank.io/api/data/福利/100/1";
        AppRetrofit.getRetrofitGank()
                .create(UrlInterface.Gank.class)
                .getDataPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        mData.addAll(bean.getResults());

                    }
                });

//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.d("TAT", response);
//                        Gson gson = new Gson();
//                        Bean bean = gson.fromJson(response, Bean.class);
//                        mData.addAll(bean.getResults());
//                        mVideoAdapter.notifyDataSetChanged();
//                    }
//                });
        return view;
    }
}
