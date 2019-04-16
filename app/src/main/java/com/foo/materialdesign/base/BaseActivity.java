package com.foo.materialdesign.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:13
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void skipIntent(Class clz, boolean isFinish) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }
}
