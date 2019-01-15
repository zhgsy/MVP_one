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

public class PzshGridViewAdapter extends BaseAdapter {
    Context context;
    List<UrlBean.ResultBean.PzshBean.CommodityListBeanX> pzshList;
 /*   List<UrlBean.ResultBean.PzshBean> pzshList;*/
    public PzshGridViewAdapter(Context context, List<UrlBean.ResultBean.PzshBean.CommodityListBeanX> pzshList) {
        this.context = context;
        this.pzshList = pzshList;
    }

    @Override
    public int getCount() {
        return pzshList.size();
    }

    @Override
    public Object getItem(int position) {
        return pzshList.get(position);
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
            convertView=View.inflate(context,R.layout.pzsh_layout,null);

            viewHolder.Vtext1=convertView.findViewById(R.id.pzshText1);
            viewHolder.Vtext2=convertView.findViewById(R.id.pzshText2);
            viewHolder.Vimage=convertView.findViewById(R.id.pzshImage);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.Vtext1.setText(pzshList.get(position).getCommodityName());
        viewHolder.Vtext2.setText("$:"+pzshList.get(position).getPrice()+".00");
        ImageLoader.getInstance().displayImage(pzshList.get(position).getMasterPic(),viewHolder.Vimage);
        return convertView;
    }
    class ViewHolder{
        TextView Vtext1,Vtext2;
        ImageView Vimage;
    }
}
