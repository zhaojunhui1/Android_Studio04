package com.zjh.administrat.weekmodeledemo1208.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

   public interface CallBack<T>{
       void OnSuccess(T data);
   }
   @SuppressLint("StaticFieldLeak")
   public static void getRequest(final String urlStr, final Class clazz, final CallBack callBack){
       new AsyncTask<String, Void, Object>(){
           @Override
           protected Object doInBackground(String... strings) {
               return getRequest(strings[0], clazz);
           }

           @Override
           protected void onPostExecute(Object o) {
               callBack.OnSuccess(o);
           }
       }.execute(urlStr);
   }

   public static <T> T getRequest(String urlStr, Class calzz){
       return (T) new Gson().fromJson(getRequest(urlStr), calzz);
   }

    //网络请求
    public static String getRequest(String urlStr){
        String result = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            if (urlConnection.getResponseCode() == 200){
                result = stream2String(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String stream2String(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (String temp = bufferedReader.readLine(); temp != null; temp = bufferedReader.readLine()){
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }
}
