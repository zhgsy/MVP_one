package com.bwie.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.myapplication.R;
import com.bwie.myapplication.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    @BindView(R.id.topbar)
    Topbar topbar;
    Unbinder unbinder;
  /*  private LinearLayoutManager linearLayoutManager;*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);
        topbar = view.findViewById(R.id.topbar);
     /*   LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false);
        *//*linearLayoutManager = new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false);*//*
        fragRecy.setLayoutManager(linearLayoutManager);*/
        topbar.setButtonlinsten(new Topbar.leftandrightLinsten() {
            @Override
            public void leftlinseten() {
                Toast.makeText(getActivity(), "点我干啥", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void rightlinseten() {
            }
        });
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
