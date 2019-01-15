package com.bwie.myapplication.network;

import android.net.Network;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttps {
    private final OkHttpClient client;
    private static volatile Okhttps instance;
    private Handler handler=new Handler();
    private FormBody.Builder body;

    private Interceptor getAppInterceptor(){
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Log.e("++++++++++", "intercept: 拦截前" );
                Response response = chain.proceed(request);
                Log.e("++++++++++", "intercept: 拦截后" );
                return response;

            }
        };
        return interceptor;
    }
    public static Okhttps getinstance(){
        if (instance==null){
            synchronized (Okhttps.class){
                instance=new Okhttps();
            }
        }
        return instance;
    }
    public Okhttps(){
        File file=new File(Environment.getExternalStorageDirectory(),"cachell");

        client = new OkHttpClient().newBuilder()
                .readTimeout(3000,TimeUnit.SECONDS)
                .connectTimeout(3000,TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file,2*1024))
                .build();
    }
    public void doGet(String url, final Network network){
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        network.Success(result);
                    }
                });
            }
        });
    }
    public void doPost(String url, Map<String,String> parms, final Network network){
        body = new FormBody.Builder();
        for (String key:parms.keySet()){
            body.add(key,parms.get(key));
        }
        Request request=new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        network.Success(result);
                    }
                });
            }
        });
    }


    public  interface Network{
        void Success(String data);
        void Faild();
    }


}
