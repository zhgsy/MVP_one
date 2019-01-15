package com.bwie.myapplication.login.model;

import com.bwie.myapplication.network.MyTask;
import com.bwie.myapplication.network.MyTaskOne;
import com.bwie.myapplication.network.OkHttp3;

public class LoginModel implements ILoginModel{


    @Override
    public void login(final String url, final String name, final String pwd, final ILoginCallBack loginCallBack) {
        new Runnable() {
            @Override
            public void run() {
                OkHttp3.OkHttpPost(url, name, pwd, new OkHttp3.setLogin() {
                    @Override
                    public void getLogin(String loginData) {
                        loginCallBack.onStatus(loginData);
                    }
                });
            }
        }.run();
    }

}
