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

public class RxxpGridViewAdapter extends BaseAdapter {
    Context context;
    List<UrlBean.ResultBean.RxxpBean.CommodityListBean> list;

    public RxxpGridViewAdapter(Context context, List<UrlBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView=View.inflate(context,R.layout.rxxp_layout,null);
            viewHolder.Vtext1=convertView.findViewById(R.id.rxxpText1);
            viewHolder.Vtext2=convertView.findViewById(R.id.rxxpText2);
            viewHolder.Vimage=convertView.findViewById(R.id.rxxpImage);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.Vtext1.setText(list.get(position).getCommodityName());
        viewHolder.Vtext2.setText("$:"+list.get(position).getPrice()+".00");
        ImageLoader.getInstance().displayImage(list.get(position).getMasterPic(),viewHolder.Vimage);
        return convertView;
    }

    class ViewHolder{
        TextView Vtext1,Vtext2;
        ImageView Vimage;
    }

    public interface NetInter{
        public void getSuccess();
        public void Faild();
    }
}
