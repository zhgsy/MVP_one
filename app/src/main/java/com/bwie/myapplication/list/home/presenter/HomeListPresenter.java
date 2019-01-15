package com.bwie.myapplication.list.home.presenter;

import com.bwie.myapplication.fragment.CarFragment;
import com.bwie.myapplication.utils.Api;
import com.bwie.myapplication.list.home.model.HomeListModelnter;

public class HomeListPresenter implements IHomeListPresenter,HomeListModelnter.ModelInterface{

   /*
    private final HomeListModelnter homeListModelnter;

    public HomeListPresenter(CarFragment mview) {
        this.mview = mview;
        homeListModelnter = new HomeListModelnter();
    }*/

  /*  @Override
    public void getModelDate() {
        homeListModelnter.getData(Api.SHOPLIST, new IHomeListModelnter.HomeModelInter() {
            @Override
            public void Success(Object o) {
                mview.getViewData(o);
            }

            @Override
            public void Faild() {

            }
        });
    }*/

    //因为MainActivity实现了V层接口，所以可以直接可以用

    private HomeListModelnter homeListModelnter;
    CarFragment mview;
    //实现接口
    public HomeListPresenter(CarFragment mview) {

        this.mview = mview;
        //M层强引用
        homeListModelnter=new HomeListModelnter(this);
    }

    @Override
    public void getModelDate() {
        //获取路径
        homeListModelnter.getData(Api.SHOPLIST);
    }

    @Override
    public void LoadSuccess(String data) {
        //给V层的赋值
        mview.getViewData(data);
    }

    @Override
    public void LoadFaild() {
        mview.getViewData("失败");
    }
}
