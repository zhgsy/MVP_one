package com.bwie.myapplication.register.model;

import com.bwie.myapplication.network.OkHttp3;

public class RegisterModel implements IRegisterModel{
    @Override
    public void getModelData(String url, String phone, String pass, final IRegCallBack iRegCallBack) {
        OkHttp3.OkHttpPost(url, phone, pass, new OkHttp3.setLogin() {
            @Override
            public void getLogin(String loginData) {
                iRegCallBack.onStatus(loginData);
            }
        });
    }
}
