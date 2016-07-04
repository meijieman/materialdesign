package com.foo.materialdesign.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:13
 */
public class BaseActivity extends AppCompatActivity {

    private static BaseActivity mActivity;
    private static Toast        mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
    }

    public void skipIntent(Class clz, boolean isFinish) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }


    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
