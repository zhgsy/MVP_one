package com.bwie.myapplication.network;


import android.os.AsyncTask;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class MyTaskOne<T> extends AsyncTask<T,T,String> {
    String mPath;
    String username;
    String pwd;
    private TaskListeners taskListener ;
    View mView;
    //获取接口
    public MyTaskOne setTaskListener(TaskListeners taskListener){
        this.taskListener = taskListener ;
        return this ;
    }
    public MyTaskOne(String mPath, String username,String pwd) {
        this.mPath=mPath;
        this.username=username;
        this.pwd=pwd;
    }
    @Override
    protected String doInBackground(T... ts) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("phone", username);
        param.put("pwd", pwd);
        return HttpUrlUtils.post(mPath,param);
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            //实现接口内面方法
            taskListener.result(s);
        }
    }
    //定义接口
    public interface TaskListeners{
        void result(String t);
    }


}
