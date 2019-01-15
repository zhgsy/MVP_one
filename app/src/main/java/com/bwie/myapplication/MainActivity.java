package com.bwie.myapplication;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.bwie.myapplication.fragment.CarFragment;
import com.bwie.myapplication.fragment.CircleFragment;
import com.bwie.myapplication.fragment.HomeFragment;
import com.bwie.myapplication.fragment.ListFragment;
import com.bwie.myapplication.fragment.MineFragment;
import com.hjm.bottomtabbar.BottomTabBar;
public class MainActivity extends AppCompatActivity {
    private BottomTabBar tobbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tobbar = findViewById(R.id.fragment);
        tobbar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("商品",R.drawable.ic_butn1,CarFragment.class)
                .addTabItem("羞羞",R.drawable.ic_butn2,CircleFragment.class)
                .addTabItem("主页",R.drawable.ic_butn3,HomeFragment.class)
                .addTabItem("呵呵",R.drawable.ic_butn4,ListFragment.class)
                .addTabItem("ww",R.drawable.ic_butn5,MineFragment.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }


}
