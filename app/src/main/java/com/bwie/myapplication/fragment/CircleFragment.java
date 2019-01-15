package com.bwie.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bwie.myapplication.R;
import com.bwie.myapplication.ZhanshiActivity;
import com.bwie.myapplication.adapter.Quanziadapter;
import com.bwie.myapplication.bean.QuanBean;
import com.bwie.myapplication.network.MyTaskOne;
import com.bwie.myapplication.quanz.home.presenter.HomePresenterInter;
import com.bwie.myapplication.quanz.home.view.IHomeView;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CircleFragment extends Fragment implements IHomeView {

    private HomePresenterInter homePresenterInter;
    private LinearLayoutManager linearLayoutManager;
    private XRecyclerView xrecyv;
    private LinearLayoutManager xlinearLayoutManager;
/*     private RecyclerView recy;*/
    private int count=1;
    private int page=8;
    private Quanziadapter quanziadapter;
    private List<QuanBean.ResultBean> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_circle,container,false);
        xrecyv = view.findViewById(R.id.xrecy);
        linearLayoutManager = new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false);
        xrecyv.setLoadingMoreEnabled(true);
        xrecyv.setPullRefreshEnabled(true);
        homePresenterInter = new HomePresenterInter(CircleFragment.this);
        homePresenterInter.getPresenter(page,count);
        xrecyv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        count=5;
                        homePresenterInter.getPresenter(page,count);
                        quanziadapter.notifyDataSetChanged();
                        xrecyv.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        count++;
                        homePresenterInter.getPresenter(page,count);
                        quanziadapter.notifyDataSetChanged();
                        xrecyv.loadMoreComplete();
                    }
                },2000);
            }
        });
        /*  recy = view.findViewById(R.id.qu_recy);*/
        xrecyv.setLayoutManager(linearLayoutManager);


        return view;
    }


    @Override
    public void getViewData( final String dataview) {
        Gson gson=new Gson();
        QuanBean bean = gson.fromJson(dataview, QuanBean.class);
        list = bean.getResult();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("ss","ss:"+dataview);
                quanziadapter = new Quanziadapter(list,getActivity());
                quanziadapter.setOnItemClickListener(new Quanziadapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        Intent intent=new Intent(getActivity(),ZhanshiActivity.class);
                        startActivity(intent);
                    }
                });
                xrecyv.setAdapter(quanziadapter);
            }
        });


    }
}
