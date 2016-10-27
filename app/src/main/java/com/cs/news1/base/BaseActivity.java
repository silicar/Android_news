package com.cs.news1.base;

import android.os.Bundle;
import android.view.Window;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by Tutu on 2016/10/27.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //初始化数据
        initData(savedInstanceState);
        //初始化视图
        initView(savedInstanceState);
        //初始化监听
        initListener(savedInstanceState);
    }

    protected abstract void initData(Bundle savedInstanceState);
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initListener(Bundle savedInstanceState);
}
