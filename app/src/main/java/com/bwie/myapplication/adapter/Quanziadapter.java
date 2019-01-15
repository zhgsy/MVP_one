package com.bwie.myapplication.adapter;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.myapplication.R;
import com.bwie.myapplication.bean.QuanBean;

import java.util.List;

public class Quanziadapter extends RecyclerView.Adapter<Quanziadapter.ViewHode> {
    List<QuanBean.ResultBean> list;
    Context context;

    public Quanziadapter(List<QuanBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHode onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ViewHode viewHode = new ViewHode(LayoutInflater.from(context).inflate(R.layout.qunzi_list, viewGroup, false));
        return viewHode;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHode viewHode, int i) {
        /* viewHode.textname.setText(list.get(i).getId());*/
        /*   viewHode.tetv.setText(list.get(i).getId());*/
        viewHode.tetime.setText(list.get(i).getCreateTime() + "");
        viewHode.textt.setText(list.get(i).getContent());
        Glide.with(context).load(list.get(i).getImage()).into(viewHode.image);
        viewHode.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick();
            }
        });
        Glide.with(context).load(list.get(i).getHeadPic()).into(viewHode.imagev);
        Glide.with(context).load(list.get(i).getHeadPic()).into(viewHode.imageview);
       /* Glide.with(this)
                .load(URL)
                .apply(options)
                .into(mImageView);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHode extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView tetv;
        private final TextView tetime;
        private final TextView textt;
        private final ImageView imageview;
        private final ImageView imagev;

        public ViewHode(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.im_qunzi);
            tetv = itemView.findViewById(R.id.te_name);
            tetime = itemView.findViewById(R.id.te_time);
            textt = itemView.findViewById(R.id.quz_tex);
            imageview = itemView.findViewById(R.id.quzi_ima);
            imagev = itemView.findViewById(R.id.quz_im);
        }
    }

    private MOnItemClickListener mClickListener;

    //创建接口
    public interface MOnItemClickListener {
        void onItemClick();
    }

    //实现接口
    public void setOnItemClickListener(MOnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

/*    *//**
     * 自定义圆形裁剪.
     *//*
    private void customerOptions() {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.image_error)
                .fallback(R.drawable.fallback_nodata)
               *//* .transform(new )*//*
                .transform(new CircleTransform(this));


    }*/
}