package com.bwie.myapplication.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.myapplication.MainActivity;
import com.bwie.myapplication.R;
import com.bwie.myapplication.bean.LoginBean;
import com.bwie.myapplication.fragment.CarFragment;
import com.bwie.myapplication.login.presenter.LoginPresenter;
import com.bwie.myapplication.login.view.ILoginView;
import com.bwie.myapplication.register.RegisterActivity;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private Button loginBtn;
    private EditText loginPwdEt;
    private EditText loginUserEt;
    private LoginPresenter loginPresenter;
    private String pass;
    private String user;
    private TextView viewById;
    private String status;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.login_btn);
        loginPwdEt = findViewById(R.id.login_pwd_et);
        loginUserEt = findViewById(R.id.login_user_et);
        register = findViewById(R.id.reg);


        loginPresenter = new LoginPresenter(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pass = loginPwdEt.getText().toString();
                user = loginUserEt.getText().toString();
                if (!pass.isEmpty()&&!user.isEmpty()){
                    loginPresenter.loginP(user,pass);
                }else{
                    Toast.makeText(LoginActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    @Override
    public void getViewData(final String viewData) {
        new Thread() {
            public void run() {

                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        //更新UI
                        Gson gson=new Gson();
                        LoginBean loginBean = gson.fromJson(viewData, LoginBean.class);
                        status = loginBean.getStatus();
                        if (status.equals("0000")){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        }.start();
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void jumpActivity() {

    }
}
