package com.bwie.myapplication.login.model;

public interface ILoginModel {

    public void login(String url,String name,String pwd,ILoginCallBack loginCallBack);

    interface ILoginCallBack{
        public void onStatus(String data);
        public void onFailed();
    }
}
