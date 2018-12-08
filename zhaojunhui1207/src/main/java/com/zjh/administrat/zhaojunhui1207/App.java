package com.zjh.administrat.zhaojunhui1207;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App extends Application implements Thread.UncaughtExceptionHandler {

    Thread.UncaughtExceptionHandler mHandler = Thread.getDefaultUncaughtExceptionHandler();

    @Override
    public void uncaughtException(Thread t, Throwable e) {
          try {
              saveSD(e);
              Log.i("TAG", e.getLocalizedMessage());
          }catch (Exception ex){
              ex.printStackTrace();
          }

    }

    private void saveSD(Throwable e) throws Exception{
        if (!Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            return;
        }

        //获取手机信息
        PackageManager pm = getPackageManager();
        PackageInfo info = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);

        //手机版本
        String versionName = info.versionName;
        int versionCode = info.versionCode;
        int sdkInt = Build.VERSION.SDK_INT;

        //android版本号
        String release = Build.VERSION.RELEASE;
        //型号
        String model = Build.MODEL;
        //制造商
        String manufacturer = Build.MANUFACTURER;
        //sd卡
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());

        File file = new File(path, "Exception");
        file.mkdirs();
         //放入sd
        File file1 = new File(file.getAbsolutePath(), "Exception"+time+".txt");
        if (!file1.exists()){
            file1.createNewFile();
        }

        String data = "手机版本"+versionName+",android版本号"+release+"型号"+model+"制造商"+manufacturer+",时间"+time;

        Log.i("TAG", data);

        byte[] buffer = data.trim().getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(buffer, 0, buffer.length);
        fileOutputStream.flush();
        fileOutputStream.close();

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }



}
