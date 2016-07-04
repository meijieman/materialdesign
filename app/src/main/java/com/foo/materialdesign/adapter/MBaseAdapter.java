package com.foo.materialdesign.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/4/4 13:56
 */
public abstract class MBaseAdapter<T> extends BaseAdapter {

    protected Activity pContext;
    protected List<T>  pData;

    public MBaseAdapter(Activity ac){
        pContext = ac;
    }

    public MBaseAdapter(Activity pContext, List<T> pData) {
        this.pContext = pContext;
        this.pData = pData;
    }

    public void setData(List<T> data) {
        pData = data;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> data){
        if (pData == null) {
            pData = new ArrayList<>();
        }
        pData.addAll(data);
        notifyDataSetChanged();
    }
    public void addData(T data){
        if (pData == null) {
            pData = new ArrayList<>();
        }
        pData.add(data);
        notifyDataSetChanged();
    }

    public T removeData(int position) {
        if (pData == null) {
            return null;
        }
        T remove = pData.remove(position);
        notifyDataSetChanged();
        return remove;
    }

    @Override
    public int getCount() {
        return pData == null ? 0 : pData.size();
    }

    @Override
    public T getItem(int position) {
        return pData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
