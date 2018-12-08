package com.zjh.administrat.zhaojunhui1206.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.zjh.administrat.zhaojunhui1206.utils.HttpUtil;
import com.zjh.administrat.zhaojunhui1206.view.IView;

public class IModelImpl implements IModel{

    public <T> T getRequest(String urlStr, Class clazz){
        return (T) new Gson().fromJson(HttpUtil.getRequest(urlStr), clazz);
    }

    @Override
    public void showData(final String urlStr, final Class clazz, final HttpUtil.CallBack callBack) {
            new AsyncTask<String, Void, Object>(){
                @Override
                protected Object doInBackground(String... strings) {
                    return getRequest(urlStr, clazz);
                }

                @Override
                protected void onPostExecute(Object o) {
                    callBack.OnSuccess(o);
                }
            }.execute(urlStr);
        }

}
