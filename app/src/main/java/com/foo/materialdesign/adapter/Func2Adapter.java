package com.foo.materialdesign.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foo.materialdesign.R;
import com.foo.materialdesign.vo.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/8 9:21
 */
public class Func2Adapter extends RecyclerView.Adapter<ViewHolder> {

    private Activity mActivity;
    private List<Item> mData;

    public Func2Adapter(Activity ac){
        mActivity = ac;
    }

    public void setData(List<Item> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mActivity, R.layout.item_func2, null);

        return new Func2Holder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Func2Holder vh = (Func2Holder)holder;
        vh.tv.setText(mData.get(position).name);

        vh.iv.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class Func2Holder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_item_func2)
        TextView tv;
        @Bind(R.id.iv_item_func2)
        ImageView iv;


        public Func2Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            ButterKnife.bind(itemView);
        }
    }
}
