package com.bwie.myapplication.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myapplication.R;
import com.bwie.myapplication.bean.RegisterBean;
import com.bwie.myapplication.login.LoginActivity;
import com.bwie.myapplication.register.presenter.RegisterPresenter;
import com.bwie.myapplication.register.view.IRegisterView;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    private EditText phone;
    private EditText pass;
    private EditText yanzheng;
    private Button regbtn;
    private RegisterPresenter register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phone = findViewById(R.id.reg_shouji);
        pass = findViewById(R.id.reg_password);
        yanzheng = findViewById(R.id.reg_yanzheng);
        regbtn = findViewById(R.id.reg_button);

        register = new RegisterPresenter(this);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = pass.getText().toString();
                String num = phone.getText().toString();
                if (!pwd.isEmpty()&&!num.isEmpty()){
                    register.register(num,pwd);
                }else{
                    Toast.makeText(RegisterActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getViewData(final String viewdata) {
        new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        RegisterBean registerBean = gson.fromJson(viewdata, RegisterBean.class);
                        String status = registerBean.getStatus();
                        if (status.equals("0000")){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }.run();
    }
}
