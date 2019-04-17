package com.foo.materialdesign.ui;

import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseFragment;

import butterknife.BindView;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/6/4 9:37
 */
public class Func0Fragment extends BaseFragment {

    @BindView(R.id.cv_func0)
    CardView mCardView;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_func0;
    }

    @Override
    protected void init() {
        mCardView.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 16);
                    upAnim.setDuration(150);
                    upAnim.setInterpolator(new DecelerateInterpolator());
                    upAnim.start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                    downAnim.setDuration(150);
                    downAnim.setInterpolator(new AccelerateInterpolator());
                    downAnim.start();
                    break;
            }
            return true;

        });
    }
}
