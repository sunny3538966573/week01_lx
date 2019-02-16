package com.bw.week01_lx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initview();
        initData();
    }

    protected abstract int getContentView();

    protected abstract void initview();

    protected abstract void initData();
}
