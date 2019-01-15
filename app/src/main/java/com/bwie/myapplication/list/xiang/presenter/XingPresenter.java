package com.bwie.myapplication.list.xiang.presenter;

import com.bwie.myapplication.XiangqingActivity;
import com.bwie.myapplication.list.xiang.model.IXingModel;
import com.bwie.myapplication.list.xiang.model.XingModel;
import com.bwie.myapplication.utils.Api;

public class XingPresenter implements IXingPresenter{

    XiangqingActivity mview;
    private final XingModel xingModel;

    public XingPresenter(XiangqingActivity mview) {
        this.mview = mview;
        xingModel = new XingModel();
    }
    @Override
    public void GetPresenter(String cid) {
        xingModel.getData(Api.XQING + cid, new IXingModel.NetXiangWork() {
            @Override
            public void Success(String data) {
                mview.getXingView(data);
            }

            @Override
            public void Faild() {

            }
        });
    }
}
