package com.bwie.myapplication.utils;

import android.os.Environment;
import android.os.Handler;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {

    private final OkHttpClient client;
    private static volatile HttpUtils instance;
    public Handler handler=new Handler();

    public HttpUtils(){
        File file=new File(Environment.getExternalStorageDirectory(),"cachell");
        //设置读取超时时间
        //设置连接的超时时间
        /* .addInterceptor()*/
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000,TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(3000,TimeUnit.SECONDS)//设置连接的超时时间
               /* .addInterceptor()*/
                .cache(new Cache(file,10*1024))
                .build();
    }
    //单例
    public static  HttpUtils getInstance(){
        if (instance==null){
            synchronized (HttpUtils.class){
                if (null==instance){
                    instance=new HttpUtils();
                }
            }
        }
        return instance;
    }

    public void doGet(String url, final Class clazz, final NetCallback netCallback){
            //创建一个请求对象
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        //创建一个call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            netCallback.Failure(e);
                        }
                    });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallback.onSuccess(o);
                    }
                });
            }
        });
    }
    public void getPost(String url, final Class clazz, Map<String,String> parms, final NetCallback netCallback){
        FormBody.Builder body = new FormBody.Builder();
        //key value
        for (String key:parms.keySet()){
            //value的值
            body.add(key,parms.get(key));
        }
        final Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        //创建一个call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallback.Failure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String requl = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(requl, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallback.onSuccess(o);
                    }
                });

            }
        });

    }

        public interface NetCallback{
        public void onSuccess(Object o);
        public  void Failure(Exception e);
        }
}
