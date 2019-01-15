package com.bwie.myapplication.login.presenter;

import com.bwie.myapplication.utils.Api;
import com.bwie.myapplication.login.LoginActivity;
import com.bwie.myapplication.login.model.ILoginModel;
import com.bwie.myapplication.login.model.LoginModel;

public class LoginPresenter implements ILoginPresenter{

    LoginActivity loginActivity;
    private final LoginModel loginModel;

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity=loginActivity;
        loginModel = new LoginModel();
    }

    @Override
    public void loginP(String name, String pswd) {
        loginModel.login(Api.LOGIN, name, pswd, new ILoginModel.ILoginCallBack() {
            @Override
            public void onStatus(String data) {
                loginActivity.getViewData(data);

            }

            @Override
            public void onFailed() {

            }
        });
    }
}
