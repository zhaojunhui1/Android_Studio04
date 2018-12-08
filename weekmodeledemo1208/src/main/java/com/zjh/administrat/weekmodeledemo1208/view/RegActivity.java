package com.zjh.administrat.weekmodeledemo1208.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.zjh.administrat.weekmodeledemo1208.R;

public class RegActivity extends AppCompatActivity {

    private EditText name, pass;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        next = findViewById(R.id.next);
        initName();
        initPass();
    }
    //账号
    private void initName() {
        name = findViewById(R.id.name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //按钮可以点击 改变背景色
                next.setEnabled((s.length() == 11));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //密码
    private void initPass() {
        pass = findViewById(R.id.pass);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //按钮可以点击 改变背景色
                next.setEnabled((s.length() == 6));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
