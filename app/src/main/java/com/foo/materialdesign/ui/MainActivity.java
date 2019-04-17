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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.foo.materialdesign.R;
import com.foo.materialdesign.adapter.MainTabPageAdapter;
import com.foo.materialdesign.base.BaseActivity;
import com.foo.materialdesign.util.IsExit;
import com.major.base.log.LogUtil;
import com.major.base.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tl)
    TabLayout mTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    // 按返回退出App
    private IsExit exit = new IsExit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();
        setupDrawerContent(mNavigationView);
        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setInlineLabel(true);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        mFloatingActionButton.setOnClickListener(view ->
                Snackbar.make(view, "FAB", Snackbar.LENGTH_SHORT)
                        .setAction("cancel", v -> {
                            //这里的单击事件代表点击消除Action后的响应事件

                        })
                        .show());
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

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
    }

    private void setupViewPager(ViewPager viewPager) {
        MainTabPageAdapter adapter = new MainTabPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new DialogFragment(), "DialogFragment");
        adapter.addFragment(new Func0Fragment(), "功能0");
        adapter.addFragment(new Func1Fragment(), "功能1");
        adapter.addFragment(new Func2Fragment(), "功能2");
        adapter.addFragment(new Func3Fragment(), "功能3");
        adapter.addFragment(new Func4Fragment(), "功能4");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            //                menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();

            ToastUtil.showShort("id " + menuItem.getItemId());

            switch (menuItem.getItemId()) {
                case R.id.nav_home:

                    break;
                case R.id.nav_setting:
                    skipIntent(SettingActivity.class, false);
                    break;
            }
            return true;
        });
        View headerView = navigationView.getHeaderView(0);
        LogUtil.i("headerView " + headerView);
        ImageView head = headerView.findViewById(R.id.imageView_nav_header);

//        navigationView.setItemIconTintList(null);
        headerView.setOnClickListener(v->{ToastUtil.showShort("登录");
            mDrawerLayout.closeDrawers();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                ToastUtil.showShort("action_settings");

                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
            ToastUtil.showShort("喵，再按一次离开^~^");
            exit.doExitInThreeSecond();
        }
    }
}
