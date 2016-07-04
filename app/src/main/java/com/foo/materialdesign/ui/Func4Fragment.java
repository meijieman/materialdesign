package com.foo.materialdesign.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foo.materialdesign.R;
import com.foo.materialdesign.adapter.Func2Adapter;
import com.foo.materialdesign.base.BaseFragment;
import com.foo.materialdesign.vo.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func4Fragment extends BaseFragment {

    @Bind(R.id.rv_func2)
    RecyclerView mRecyclerView;

    @Bind(R.id.srl_func2)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void init() {

    }
}
