package com.foo.materialdesign.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseActivity;
import com.foo.materialdesign.adapter.MainTabPageAdapter;
import com.foo.materialdesign.utils.IsExit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar              mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout         mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView       mNavigationView;
    @Bind(R.id.viewpager)
    ViewPager            mViewPager;
    @Bind(R.id.tl)
    TabLayout            mTabLayout;
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupDrawerContent(mNavigationView);
        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    //                    EventBus.getDefault().postSticky("Reselected");
                }
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FAB",Snackbar.LENGTH_SHORT)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件

                            }
                        })
                        .show();
            }
        });
    }

    private void setupToolbar() {
        //        mToolbar.setTitleTextColor(Color.parseColor("#ffff00"));
        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setHomeButtonEnabled(true); // 设置返回键可用
        ab.setDisplayHomeAsUpEnabled(true); // 创建返回键，并实现打开关/闭监听
        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainTabPageAdapter adapter = new MainTabPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Func1Fragment(), "行程单");
        adapter.addFragment(new Func2Fragment(), "打赏");
        adapter.addFragment(new Func2Fragment(), "个人中心");
        adapter.addFragment(new Func2Fragment(), "朋友圈");
        viewPager.setAdapter(adapter);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                showToast("id " + menuItem.getItemId());

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        break;
                    case R.id.nav_setting:
                        skipIntent(SettingActivity.class, false);
                        break;
                }
                return true;
            }
        });
    }

    // 按返回退出App
    private IsExit exit = new IsExit();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            pressAgainExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void pressAgainExit() {
        if (exit.isExit()) {
            finish();
        } else {
            showToast("喵，再按一次离开^~^");
            exit.doExitInThreeSecond();
        }
    }
}
