package com.foo.materialdesign.ui;

import android.content.Intent;
import android.view.View;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseFragment;
import com.foo.materialdesign.widget.RotateLayout;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func1Fragment extends BaseFragment {

    @BindView(R.id.rl_func1)
    RotateLayout mLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_func1;
    }

    @Override
    protected void init() {
        mLayout.setAngle(160);
    }


    @OnClick({R.id.btn_demo1, R.id.btn_demo2, R.id.btn_demo3})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1: {
                Intent intent = new Intent(getActivity(), Demo1Activity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_demo2: {
                Intent intent = new Intent(getActivity(), BottomNavigationActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_demo3: {
                Intent intent = new Intent(getActivity(), AppActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void method() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        client.newCall(request);
    }
}
