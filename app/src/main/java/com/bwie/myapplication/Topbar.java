package com.bwie.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Topbar extends RelativeLayout {

    @BindView(R.id.leftbutn)
    Button leftbutn;
    @BindView(R.id.titlet)
    EditText titlet;
    @BindView(R.id.rightbutn)
    Button rightbutn;
    leftandrightLinsten buttonlinsten;
    private Unbinder bind;

    public Topbar(Context context) {
        super(context);
    }
    public void setButtonlinsten(leftandrightLinsten buttonlinsten){
        this.buttonlinsten=buttonlinsten;

    }
    public interface leftandrightLinsten{
        public void leftlinseten();
        public void rightlinseten();
    }
    public void setLeftbutn(boolean flag){
            if (flag){
                leftbutn.setVisibility(View.VISIBLE);
            }else{
                leftbutn.setVisibility(View.GONE);
            }
    }
    public void setRightbutn(boolean flag){
        if (flag){
            rightbutn.setVisibility(View.VISIBLE);
        }else{
            rightbutn.setVisibility(View.GONE);
        }
    }
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_topbar, this);
        bind = ButterKnife.bind(this);
        leftbutn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    buttonlinsten.leftlinseten();

            }
        });
        rightbutn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    buttonlinsten.rightlinseten();

            }
        });
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.zhang_topbar);
        int leftBackGroud = typedArray.getResourceId(R.styleable.zhang_topbar_zhangleftBackGrouod, 0);
        int rightBackGroud = typedArray.getResourceId(R.styleable.zhang_topbar_zhangrightBackGroud, 0);
        String titletext = typedArray.getString(R.styleable.zhang_topbar_titleText);
        float titleSize = typedArray.getDimension(R.styleable.zhang_topbar_titleSize, 0);
        int titleColor = typedArray.getColor(R.styleable.zhang_topbar_titleColor, 0x38ad5a);
        typedArray.recycle();
        leftbutn.setBackgroundResource(leftBackGroud);
        rightbutn.setBackgroundResource(rightBackGroud);
        titlet.setText(titletext);
        titlet.setTextColor(titleColor);
        titlet.setTextSize(titleSize);

    }

    public Topbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Topbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
