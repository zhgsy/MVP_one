package com.bwie.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bwie.myapplication.bean.XiangBean;
import com.bwie.myapplication.list.xiang.presenter.XingPresenter;
import com.bwie.myapplication.list.xiang.view.IXingView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangqingActivity extends AppCompatActivity implements IXingView {


    @BindView(R.id.web)
    WebView web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        Intent intent = getIntent();
        String extra = intent.getStringExtra("getdata");
        XingPresenter xingPresenter = new XingPresenter(XiangqingActivity.this);
        xingPresenter.GetPresenter(extra);
        ButterKnife.bind(this);

    }

    @Override
    public void getXingView(final String dataView) {


        Toast.makeText(XiangqingActivity.this, dataView, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        XiangBean xiangBean = gson.fromJson(dataView, XiangBean.class);

        XiangBean.ResultBean resultBean = xiangBean.getResult();
        /*  Toast.makeText(XiangqingActivity.this,dataView,Toast.LENGTH_SHORT).show();*/
        web.loadDataWithBaseURL(null, resultBean.getDetails(), "text/html", "utf-8", null);
        web.setWebViewClient(new WebViewClient());
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);


    }
}
