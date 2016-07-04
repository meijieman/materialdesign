package com.foo.materialdesign.ui;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.foo.materialdesign.R;
import com.foo.materialdesign.adapter.Func2Adapter;
import com.foo.materialdesign.base.BaseFragment;
import com.foo.materialdesign.vo.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func2Fragment extends BaseFragment {

    @Bind(R.id.rv_func2)
    RecyclerView mRecyclerView;

    @Bind(R.id.srl_func2)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_func2;
    }

    @Override
    protected void init() {
        final Func2Adapter adapter = new Func2Adapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        adapter.setData(getData());
        // 设置刷新图标的背景
        //        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor((getResources().getColor(R.color.colorPrimary)));
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Item> list = getData();
                        SystemClock.sleep(4000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setData(list);
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public List<Item> getData() {
        List<Item> mData = new ArrayList<>();
        Item item = new Item();
        item.name= new Date().toString();
        mData.add(item);

        return mData;
    }
}
