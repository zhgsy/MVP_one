package com.bwie.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.myapplication.R;
import com.bwie.myapplication.bean.UrlBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MlssListViewAdapter extends BaseAdapter {
    Context context;
    List<UrlBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList;

    public MlssListViewAdapter(Context context, List<UrlBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList) {
        this.context = context;
        this.mlssList = mlssList;
    }

    @Override
    public int getCount() {
        return mlssList.size();
    }

    @Override
    public Object getItem(int position) {
        return mlssList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context,R.layout.mlss_layout,null);

            viewHolder.Vtext1=convertView.findViewById(R.id.mlssText);
            viewHolder.Vtext2=convertView.findViewById(R.id.mlssText2);
            viewHolder.Vimage=convertView.findViewById(R.id.mlssImage);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.Vtext1.setText(mlssList.get(position).getCommodityName());
        viewHolder.Vtext2.setText("$:"+mlssList.get(position).getPrice()+".00");
        ImageLoader.getInstance().displayImage(mlssList.get(position).getMasterPic(),viewHolder.Vimage);
        return convertView;
    }
    class ViewHolder{
        TextView Vtext1,Vtext2;
        ImageView Vimage;
    }
}
