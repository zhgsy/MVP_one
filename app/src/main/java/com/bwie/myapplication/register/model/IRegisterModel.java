package com.bwie.myapplication.register.model;

public interface IRegisterModel {
    void getModelData(String url,String phone,String pass,IRegCallBack iRegCallBack);

    interface IRegCallBack{
        void onStatus(String data);
        void onFaild();
    }
}
