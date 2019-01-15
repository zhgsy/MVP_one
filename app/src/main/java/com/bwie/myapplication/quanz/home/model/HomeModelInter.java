package com.bwie.myapplication.quanz.home.model;

import android.util.Log;

import com.bwie.myapplication.bean.QuanBean;
import com.bwie.myapplication.network.Okhttps;
import com.bwie.myapplication.utils.HttpUtils;

public class HomeModelInter implements IHomeModelInter{


    @Override
    public void getData(String url, final QuanWork quanWork) {
        Okhttps.getinstance().doGet(url, new Okhttps.Network() {
            @Override
            public void Success(String data) {
                quanWork.Success(data);
            }

            @Override
            public void Faild() {

            }
        });
    }
}
