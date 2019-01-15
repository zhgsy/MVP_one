package com.bwie.myapplication.quanz.home.model;

public interface IHomeModelInter {

    public  void  getData(String url,QuanWork quanWork);
    public interface QuanWork{
        public void Success(String data);
        public void Faild();
    }
}
