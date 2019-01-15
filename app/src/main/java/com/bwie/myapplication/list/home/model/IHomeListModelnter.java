package com.bwie.myapplication.list.home.model;

public interface IHomeListModelnter {
    //获取数据的接口
    void getData(String url);
    public interface HomeModelInter{
        public void Success(Object o);
        public void Faild();
    }

}
