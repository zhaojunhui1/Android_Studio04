package com.zjh.administrat.zhaojunhui1206.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

    public interface CallBack<T>{
        void OnSuccess(T t);
    }

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
