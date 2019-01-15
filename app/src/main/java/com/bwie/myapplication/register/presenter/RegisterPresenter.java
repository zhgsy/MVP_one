package com.bwie.myapplication.register.presenter;

import com.bwie.myapplication.utils.Api;
import com.bwie.myapplication.register.RegisterActivity;
import com.bwie.myapplication.register.model.IRegisterModel;
import com.bwie.myapplication.register.model.RegisterModel;

public class RegisterPresenter implements IRegisterPresenter{
    RegisterActivity reg;
    private final RegisterModel registerModel;

    public RegisterPresenter(RegisterActivity registerActivity) {
        this.reg=registerActivity;
        registerModel = new RegisterModel();
    }

    @Override
    public void register(String phone, String pass) {
        registerModel.getModelData(Api.REGISTER, phone, pass, new IRegisterModel.IRegCallBack() {
            @Override
            public void onStatus(String data) {
                reg.getViewData(data);
            }

            @Override
            public void onFaild() {

            }
        });
    }
}
