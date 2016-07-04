package com.foo.materialdesign.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func1Fragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_func1;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.btn_demo1)
    void onClick(){
        Intent intent = new Intent(getActivity(), Demo1Activity.class);
        startActivity(intent);
    }

    public void method(){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        client.newCall(request);
    }
}
