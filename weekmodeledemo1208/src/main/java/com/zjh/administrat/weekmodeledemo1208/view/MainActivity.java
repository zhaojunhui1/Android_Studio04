package com.zjh.administrat.weekmodeledemo1208.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zjh.administrat.weekmodeledemo1208.R;
import com.zjh.administrat.weekmodeledemo1208.presenter.IPresenterImpl;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {
    private EditText name, pass;
    private Button logino, reg;
    private IPresenterImpl iPresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox jz_pass, zd_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //
        init();   //umeng
        initData(); //登录
        initReg();  //注册
    }
    //跳转注册按钮
    private void initReg() {
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegActivity.class));
            }
        });
    }

    //自动
    private void initData() {
        jz_pass = findViewById(R.id.jz_pass);
        zd_login = findViewById(R.id.zd_login);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean JZ = sharedPreferences.getBoolean("JZ", false);
        String name1 = sharedPreferences.getString("name1", null);
        String pass1 = sharedPreferences.getString("pass1", null);
        if (JZ) {
            name.setText(name1);
            pass.setText(pass1);
            jz_pass.setChecked(true);
        }

        boolean ZD = sharedPreferences.getBoolean("ZD", false);
        if (ZD) {
            startActivity(new Intent(MainActivity.this, DetailsActivity.class));
        }
        logino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = pass.getText().toString();
                if (jz_pass.isChecked()) {
                    editor.putString("name1", username);
                    editor.putString("pass1", password);
                    editor.putBoolean("JZ", true);
                    editor.commit();
                } else {
                }
                if (zd_login.isChecked()) {
                    editor.putBoolean("ZD", true);
                    editor.commit();
                }
                iPresenter.login(username, password);
            }
        });

    }

    //第三方登录
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        findViewById(R.id.qq_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqlogin();
            }
        });
        findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqlogin();
            }
        });
    }
    private void qqlogin(){
        UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //获取名字
                String name = map.get("screen_name");
                // 获取头像
                String img = map.get("profile_image_url");
                //登录完成跳转
                startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    // 初始化View
    private void initView() {
        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        logino = findViewById(R.id.login);
        reg = findViewById(R.id.register);

        iPresenter = new IPresenterImpl(this);

        logino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = pass.getText().toString();

                iPresenter.login(username, password);
            }
        });

    }


    @Override
    public void success(Object data) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }
}
