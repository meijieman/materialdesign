package com.foo.materialdesign.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * @Desc: 具有缓存功能的 Fragment
 * @Author: Major
 * @Since: 2016/8/4 11:15
 */
    /*  1、fragmentactivity 继承自activity，用来解决android3.0 之前没有fragment的api，所以在使用的时候需要导入support包，同时继承fragmentActivity，这样在activity中就能嵌入fragment来实现你想要的布局效果。
        2、当然3.0之后你就可以直接继承自Activity，并且在其中嵌入使用fragment了。
        3、获得Manager的方式也不同
            3.0以下：getSupportFragmentManager()
            3.0以上：getFragmentManager()
    */
//public class SaveStateActivity extends FragmentActivity {
public class SaveStateActivity extends BaseActivity {

    @BindView(R.id.container)
    FrameLayout mContainer;

    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case R.id.radio_button0:
                    return FragmentTest.instantiation(0);
                case R.id.radio_button1:
                    return FragmentTest.instantiation(1);
                case R.id.radio_button2:
                    return FragmentTest.instantiation(2);
                case R.id.radio_button3:
                    return FragmentTest.instantiation(3);
                case R.id.radio_button4:
                    return FragmentTest.instantiation(4);
                default:
                    // cannot be reach
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_state);
        ButterKnife.bind(this);

        //        findViewById(R.id.radio_button0).performClick();
        ((RadioButton) findViewById(R.id.radio_button0)).setChecked(true);
    }

    @OnCheckedChanged({R.id.radio_button0, R.id.radio_button1, R.id.radio_button2, R.id.radio_button3, R.id.radio_button4})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Fragment fragment = (Fragment) mFragmentPagerAdapter.instantiateItem(mContainer, buttonView.getId());
            mFragmentPagerAdapter.setPrimaryItem(mContainer, 0, fragment);
            mFragmentPagerAdapter.finishUpdate(mContainer);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
