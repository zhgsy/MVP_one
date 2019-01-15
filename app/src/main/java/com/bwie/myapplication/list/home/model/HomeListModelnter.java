package com.bwie.myapplication.list.home.model;
import com.bwie.myapplication.network.OkHttp3;

public class HomeListModelnter implements IHomeListModelnter{
/*    @Override
    public void getData(String url, final HomeModelInter homeModelInter) {
            HttpUtils.getInstance().doGet(url, UrlBean.class, new HttpUtils.NetCallback() {
                @Override
                public void onSuccess(Object o) {
                    homeModelInter.Success(o);
                }

                @Override
                public void Failure(Exception e) {

                }
            });*/

    ModelInterface modelInterface;

    public HomeListModelnter(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
    }

    @Override
    public void getData(final String url) {
        new Runnable() {
            @Override
            public void run() {
                OkHttp3.OkHttpget(url, new OkHttp3.setData() {
                    @Override
                    public void getData(String data) {
                        modelInterface.LoadSuccess(data);
                    }
                });
            }
        }.run();

    }
    //定义传值给P层的接口
    public interface ModelInterface{
        //加载成功
        void LoadSuccess(String data);

        void LoadFaild();
    }
}
