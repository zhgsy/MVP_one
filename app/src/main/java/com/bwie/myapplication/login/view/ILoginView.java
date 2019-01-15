package com.bwie.myapplication.login.view;

public interface ILoginView {
    void getViewData (String viewData);
    //Toast形式提示
    public void showMsg(String msg);
    //加载提示框
    public void   showLoading();
    //隐藏加载提示框
    public void hiddenLoading();
    //页面跳转
    public void jumpActivity();

}
