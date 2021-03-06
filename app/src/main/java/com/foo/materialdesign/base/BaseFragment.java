package com.foo.materialdesign.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:13
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    private Unbinder mBind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (BaseActivity) requireActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutRes(), null);
        mBind = ButterKnife.bind(this, view);

        init();
        return view;
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutRes();

    protected abstract void init();

    protected void skipIntent(Class clz, boolean isFinish) {
        mActivity.skipIntent(clz, isFinish);
    }
}
