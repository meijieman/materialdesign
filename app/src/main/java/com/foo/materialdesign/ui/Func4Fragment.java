package com.foo.materialdesign.ui;

import android.view.View;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseFragment;

import butterknife.OnClick;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func4Fragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_func4;
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.btn_func4_click)
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_func4_click:
                skipIntent(SaveStateActivity.class, false);
                break;
        }
    }
}
