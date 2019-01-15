package com.bwie.myapplication.list.xiang.model;

import com.bwie.myapplication.network.Okhttps;

public class XingModel implements IXingModel{
    @Override
    public void getData(String url, final NetXiangWork netXiangWork) {
        Okhttps.getinstance().doGet(url, new Okhttps.Network() {
            @Override
            public void Success(String data) {
                netXiangWork.Success(data);
            }

            @Override
            public void Faild() {

            }
        });
    }
}
