package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Web;
import com.cs.news1.fragment.fm_adapter.WebAdapter.BaseRetrofit;
import com.cs.news1.fragment.fm_adapter.WebAdapter.MnAPIService;
import com.cs.news1.fragment.fm_adapter.WebAdapter.WebAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenshuai on 2016/11/7.
 */

public class TabWeb extends BaseFragment implements View.OnClickListener {
    private Button fm_btn;
    private Button fm_clear;
    private RecyclerView rl_web;
    private WebAdapter adapter;
    private List<Web.ResultBean.DataBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_tabweb, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        fm_btn = (Button) view.findViewById(R.id.fm_btn);
        fm_clear = (Button) view.findViewById(R.id.fm_clear);
        rl_web = (RecyclerView) view.findViewById(R.id.rl_web);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rl_web.setLayoutManager(layoutManager);
        adapter = new WebAdapter(getContext(), list);
        rl_web.setAdapter(adapter);
        fm_btn.setOnClickListener(this);
        fm_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fm_btn:
                MnAPIService mnAPIService = BaseRetrofit.getMnAPIService();
                Call<Web> call = mnAPIService.getData("top", "ce18f1786cf2e609acbc076a4b6a2df5");
                call.enqueue(new Callback<Web>() {
                    @Override
                    public void onResponse(Call<Web> call, Response<Web> response) {
                        Web body = response.body();
                        if (response.isSuccessful()) {
                            if (body!=null) {
                                list.addAll(body.getResult().getData());
                                adapter.notifyDataSetChanged();
                            }else {

                            }

                        }else {
                            Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Web> call, Throwable t) {
                        t.printStackTrace();

                    }
                });

                break;
            case R.id.fm_clear:
                adapter.clearCache();
                break;
        }
    }
}
