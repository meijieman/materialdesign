package com.foo.materialdesign.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.foo.materialdesign.R;
import com.foo.materialdesign.base.BaseFragment;
import com.major.base.util.ToastUtil;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2019年4月17日 11:39:32
 */
public class DialogFragment extends BaseFragment {

    Calendar calendar;

    @BindView(R.id.btn_dialog_7)
    Button btn_dialog_7;
    @BindView(R.id.btn_dialog_8)
    Button btn_dialog_8;
    @BindView(R.id.btn_dialog_11)
    Button btn_dialog_11;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dialog;
    }

    @Override
    protected void init() {
        calendar = Calendar.getInstance();

    }

    @OnClick({R.id.btn_dialog_1, R.id.btn_dialog_2, R.id.btn_dialog_3, R.id.btn_dialog_4, R.id.btn_dialog_5,
            R.id.btn_dialog_6, R.id.btn_dialog_7,R.id.btn_dialog_8,R.id.btn_dialog_9,R.id.btn_dialog_10,R.id.btn_dialog_11 })
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                new AlertDialog.Builder(getContext())
                        .setMessage("简单对话框")
                        .setPositiveButton("ok", null)
                        .show();
                break;

            case R.id.btn_dialog_2:
                new AlertDialog.Builder(getContext())
                        .setTitle("简单对话框")
                        .setMessage("message")
                        .setPositiveButton("ok", null)
                        .setNegativeButton("cancel", null)
                        .setNeutralButton("neutral", null)
                        .show();
                break;

            case R.id.btn_dialog_3:
                String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);
                int itemSelected = 0;
                new AlertDialog.Builder(getContext())
                        .setTitle("单选")
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, (dialogInterface, i) -> dialogInterface.dismiss())
                        .setNegativeButton("cancel", null)
                        .show();
                break;

            case R.id.btn_dialog_4:
                String[] multiChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);
                boolean[] checkedItems = {true, false, false, false, false};
                new AlertDialog.Builder(getContext())
                        .setTitle("多选")
                        .setMultiChoiceItems(multiChoiceItems, checkedItems, null)
                        .setPositiveButton("ok", null)
                        .setNegativeButton("cancel", null)
                        .show();
                break;

            case R.id.btn_dialog_5:
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("进度条对话框...");
                progressDialog.show();
                break;

            case R.id.btn_dialog_6:
                final ProgressDialog horizontalProgressDialog = new ProgressDialog(getContext());
                horizontalProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                horizontalProgressDialog.setMessage("main_dialog_progress_title");
                horizontalProgressDialog.setCancelable(false);
                horizontalProgressDialog.setMax(100);
                horizontalProgressDialog.show();

                new Thread(new Runnable() {
                    int progress = 0;

                    @Override
                    public void run() {
                        while (progress <= 100) {
                            horizontalProgressDialog.setProgress(progress);
                            if (progress == 100) {
                                horizontalProgressDialog.dismiss();
                            }
                            try {
                                Thread.sleep(35);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progress++;
                        }
                    }
                }).start();
                break;

            case R.id.btn_dialog_7:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                    btn_dialog_7.setText(date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

            case R.id.btn_dialog_8:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (timePicker, i, i1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, i1);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    btn_dialog_8.setText(time);
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;

            case R.id.btn_dialog_9:
                final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getContext());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
                Button btn_dialog_bottom_sheet_ok = dialogView.findViewById(R.id.btn_dialog_bottom_sheet_ok);
                Button btn_dialog_bottom_sheet_cancel = dialogView.findViewById(R.id.btn_dialog_bottom_sheet_cancel);
                ImageView img_bottom_dialog = dialogView.findViewById(R.id.img_bottom_dialog);
                Glide.with(getContext()).load(R.drawable.bottom_dialog).into(img_bottom_dialog);
                mBottomSheetDialog.setContentView(dialogView);

                btn_dialog_bottom_sheet_ok.setOnClickListener(v -> mBottomSheetDialog.dismiss());
                btn_dialog_bottom_sheet_cancel.setOnClickListener(v -> mBottomSheetDialog.dismiss());
                mBottomSheetDialog.show();
                break;

            case R.id.btn_dialog_10:
                final Dialog fullscreenDialog = new Dialog(getContext(), R.style.DialogFullscreen);
                fullscreenDialog.setContentView(R.layout.dialog_fullscreen);
                ImageView img_full_screen_dialog = fullscreenDialog.findViewById(R.id.img_full_screen_dialog);
                Glide.with(getContext()).load(R.drawable.google_assistant).into(img_full_screen_dialog);
                ImageView img_dialog_fullscreen_close = fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_close);
                img_dialog_fullscreen_close.setOnClickListener(v -> fullscreenDialog.dismiss());
                fullscreenDialog.show();
                break;

            case R.id.btn_dialog_11:
                PopupMenu popupMenu = new PopupMenu(getContext(), btn_dialog_11);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    ToastUtil.showShort("选中 " + itemId);
                    return false;
                });
                popupMenu.show();
                break;

        }
    }
}
