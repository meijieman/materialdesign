package com.foo.materialdesign.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foo.materialdesign.R;

import java.util.Date;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/8/4 11:21
 */
public class FragmentTest extends Fragment {

    public static FragmentTest instantiation(int position) {
        FragmentTest fragment = new FragmentTest();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("--------- onCreateView");
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text1 = (TextView)view.findViewById(R.id.text1);
        text1.setText("Fragment " + getArguments().getInt("position", 1)+ "\n" + new Date().toLocaleString());
    }

    // 没有下面的代码会出现切换tab的时候重影现象
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
