package com.cs.news1.fragment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.News;
import com.cs.news1.fragment.fm_adapter.CookAdapter.CookAdater;
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

public class TabCook extends BaseFragment {
    private static final String TAG="TAG";
    private RecyclerView mRecyclerView;
    private List<News.TngouBean> mData=new ArrayList<>();

    private CookAdater madapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_cook,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rl_life);
        AppRetrofit.getRetrofit().create(UrlInterface.Tngou.class)
                .getDate(1, 20, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(News bean) {
                        mData.addAll(bean.getTngou());

                    }
                });

        madapter=new CookAdater(getContext(),mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(madapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override//绘制之前可以自定义颜色
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
               // c.drawColor(Color.GRAY);
            }

            @Override//绘制前景
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override//绘制行间距
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,3,0,0);
            }
        });
      /*  madapter.setOnChildClickListenner(new CookAdater.OnChildClickListenner() {
            @Override
            public void onChildClick(RecyclerView parent, View view, int position, List<News.TngouBean> list) {
                Toast.makeText(getContext(), "你点击了第"+position+"个", Toast.LENGTH_SHORT).show();

            }
        });*/
        return view;
    }

}
