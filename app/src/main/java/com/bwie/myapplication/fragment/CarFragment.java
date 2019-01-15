package com.bwie.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.bwie.myapplication.R;
import com.bwie.myapplication.XiangqingActivity;
import com.bwie.myapplication.adapter.MlssListViewAdapter;
import com.bwie.myapplication.adapter.PzshGridViewAdapter;
import com.bwie.myapplication.adapter.RxxpGridViewAdapter;
import com.bwie.myapplication.bean.UrlBean;
import com.bwie.myapplication.list.home.presenter.HomeListPresenter;
import com.bwie.myapplication.list.home.view.IHomeListView;
import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;


public class CarFragment extends Fragment implements IHomeListView {
    private HomeListPresenter homeListPresenter;
    private FlyBanner flybanner;
    private ListView listview;
    private GridView gridView;
    private GridView gridView2;
    private UrlBean urlBean;
    private List<UrlBean.ResultBean.RxxpBean.CommodityListBean> list;
    private List<UrlBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList;
    private List<UrlBean.ResultBean.PzshBean.CommodityListBeanX> pzshList;
    private FragmentManager manager;
    private  static  Handler handler=new Handler();
   /* private UrlBean.ResultBean.PzshBean pzshList;*/
    /*    private List<UrlBean.ResultBean.PzshBean> pzshList;*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_car,container,false);
        homeListPresenter = new HomeListPresenter(CarFragment.this);
        homeListPresenter.getModelDate();
        initView(view);
        return view;
    }
    private void initView(View view) {
        flybanner = view.findViewById(R.id.flyBanner);
        listview = view.findViewById(R.id.listView);
        gridView = view.findViewById(R.id.gridView);
        gridView2 = view.findViewById(R.id.gridView2);
        List<String> list = new ArrayList<>();
        list.add("http://172.17.8.100/images/small/banner/cj.png");
        list.add("http://172.17.8.100/images/small/banner/hzp.png");
        list.add("http://172.17.8.100/images/small/banner/lyq.png");
        list.add("http://172.17.8.100/images/small/banner/px.png");
        list.add("http://172.17.8.100/images/small/banner/wy.png");
        flybanner.setImagesUrl(list);
    }

    @Override
    public void getViewData(final String mViewData) {

        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                urlBean = gson.fromJson(mViewData, UrlBean.class);
                list = urlBean.getResult().getRxxp().get(0).getCommodityList();
                mlssList = urlBean.getResult().getMlss().get(0).getCommodityList();
                pzshList = urlBean.getResult().getPzsh().get(0).getCommodityList();
                //适配器
                gridView.setAdapter(new RxxpGridViewAdapter(getActivity(), list));
                listview.setAdapter(new MlssListViewAdapter(getActivity(),mlssList));

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                gridView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                       /* String url = urlBean.getResult().getRxxp().get(0).getId();*/
                        String id1 = urlBean.getResult().getRxxp().get(0).getCommodityList().get(0).getCommodityId();
                        Intent intent=new Intent(getActivity(),XiangqingActivity.class);
                        intent.putExtra("getdata",id1);
                        startActivity(intent);
                        return true;
                    }
                });
                gridView2.setAdapter(new PzshGridViewAdapter(getActivity(),pzshList));

            }
        });
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                urlBean = gson.fromJson(mViewData, UrlBean.class);
                list = urlBean.getResult().getRxxp().get(0).getCommodityList();
                mlssList = urlBean.getResult().getMlss().get(0).getCommodityList();
                pzshList = urlBean.getResult().getPzsh().get(0).getCommodityList();
                //适配器
                gridView.setAdapter(new RxxpGridViewAdapter(getActivity(), list));
                listview.setAdapter(new MlssListViewAdapter(getActivity(),mlssList));
                gridView2.setAdapter(new PzshGridViewAdapter(getActivity(),pzshList));
            }
        }).start();*/

    }

   /* @Override
    public void getViewData(Object mViewData) {
        UrlBean urlBean= (UrlBean) mViewData;
        pzshList = urlBean.getResult().getPzsh().get(0);
        gridView.setAdapter(new RxxpGridViewAdapter(getActivity(), list));
        listview.setAdapter(new MlssListViewAdapter(getActivity(),mlssList));
        gridView2.setAdapter(new PzshGridViewAdapter(getActivity(), pzshList));
    }*/
}
