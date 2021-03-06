package com.foo.materialdesign.ui;

import android.animation.Animator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foo.materialdesign.R;
import com.foo.materialdesign.bean.AppInfo;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final int SCALE_DELAY = 30;

    @BindView(R.id.row_container)
    LinearLayout mRowContainer;

    @BindView(R.id.container)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private AppInfo mAppInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Handle Back Navigation :D
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Fab Button
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_normal);
//        floatingActionButton.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_file_upload).color(Color.WHITE).actionBar());
        floatingActionButton.setOnClickListener(v -> {

        });

        //getWindow().getEnterTransition().removeListener(this);

        for (int i = 1; i < mRowContainer.getChildCount(); i++) {
            View rowView = mRowContainer.getChildAt(i);
            rowView.animate().setStartDelay(100 + i * SCALE_DELAY).scaleX(1).scaleY(1);
        }

        ComponentName componentName = null;

        if (savedInstanceState != null) {
            componentName = savedInstanceState.getParcelable("appInfo");
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            componentName = (ComponentName) getIntent().getExtras().get("appInfo");
        }

        if (componentName != null) {
            Intent intent = new Intent();
            intent.setComponent(componentName);
            ResolveInfo app = getPackageManager().resolveActivity(intent, 0);
            mAppInfo = new AppInfo(this, app);
        }

        if (mAppInfo != null) {
            //toolbar.setLogo(mAppInfo.getIcon());
            toolbar.setTitle(mAppInfo.getName());

            View view = mRowContainer.findViewById(R.id.row_name);
            fillRow(view, "Application Name", mAppInfo.getName());
            ((ImageView) view.findViewById(R.id.appIcon)).setImageDrawable(mAppInfo.getIcon());

            view = mRowContainer.findViewById(R.id.row_package_name);
            fillRow(view, "Package Name", mAppInfo.getPackageName());

            view = mRowContainer.findViewById(R.id.row_activity);
            fillRow(view, "Activity", mAppInfo.getActivityName());

            view = mRowContainer.findViewById(R.id.row_component_info);
            fillRow(view, "ComponentInfo", mAppInfo.getComponentInfo());

            view = mRowContainer.findViewById(R.id.row_version);
            fillRow(view, "Version", mAppInfo.getVersionName() + " (" + mAppInfo.getVersionCode() + ")");

            view = mRowContainer.findViewById(R.id.row_moments);
            fillRow(view, "Moments", "First installed: " + new Date(mAppInfo.getFirstInstallTime()) + "\nLast updated: " + new Date(mAppInfo.getLastUpdateTime()));
        }
    }

    public void fillRow(View view, final String title, final String description) {
        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);

        TextView descriptionView = view.findViewById(R.id.description);
        descriptionView.setText(description);

        view.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("AppInfo", description);
            clipboard.setPrimaryClip(clip);

            Snackbar.make(mCoordinatorLayout, "Copied " + title, Snackbar.LENGTH_SHORT).show();
        });
    }

    /**
     * animate the views if we close the activity
     */
    @Override
    public void onBackPressed() {
        for (int i = mRowContainer.getChildCount() - 1; i > 0; i--) {
            View rowView = mRowContainer.getChildAt(i);
            ViewPropertyAnimator propertyAnimator = rowView.animate()
                    .setStartDelay((mRowContainer.getChildCount() - 1 - i) * SCALE_DELAY).scaleX(0).scaleY(0);

            propertyAnimator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
        }
    }
}
