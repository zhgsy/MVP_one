package com.bwie.myapplication.quanz.home.presenter;

import com.bwie.myapplication.bean.QuanBean;
import com.bwie.myapplication.fragment.CircleFragment;
import com.bwie.myapplication.quanz.home.model.HomeModelInter;
import com.bwie.myapplication.quanz.home.model.IHomeModelInter;
import com.bwie.myapplication.utils.Api;

public class HomePresenterInter implements IHomePresenterInter{
    CircleFragment mview;
    private final HomeModelInter homeModelInter;

    public HomePresenterInter(CircleFragment mview) {
        this.mview = mview;
        homeModelInter = new HomeModelInter();
    }

    @Override
    public void getPresenter(int page, int count) {

        homeModelInter.getData(Api.QZSHOW+"?page="+page+"&count="+count, new IHomeModelInter.QuanWork() {

            @Override
            public void Success(String data) {

                mview.getViewData(data);


            }

            @Override
            public void Faild() {

            }

        });
    }
}
