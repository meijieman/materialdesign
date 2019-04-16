package com.foo.materialdesign.ui;

import android.content.Intent;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseFragment;
import com.foo.materialdesign.widget.RotateLayout;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func1Fragment extends BaseFragment {

    @Bind(R.id.rl_func1)
    RotateLayout mLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_func1;
    }

    @Override
    protected void init() {
        mLayout.setAngle(160);
    }


    @OnClick(R.id.btn_demo1)
    void onClick() {
        Intent intent = new Intent(getActivity(), Demo1Activity.class);
        startActivity(intent);
    }

    public void method() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        client.newCall(request);
    }
}
