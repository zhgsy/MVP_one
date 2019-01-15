package com.bwie.myapplication.list.xiang.model;

public interface IXingModel {
    public void getData(String url,NetXiangWork netXiangWork);
    public interface NetXiangWork{
        public void Success(String data);
        public void Faild();
    }

}
