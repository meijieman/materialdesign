package com.foo.materialdesign.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();
    public static final String CRASH_FILENAME = "crashFile.log";

    private static CrashHandler mInstance = new CrashHandler();

    private String                   mPath;
    private Context                  mContext;
    private UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> mInfo = new HashMap<>();

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void init(Context context, String filePath) {
        mContext = context;
        mPath = filePath;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        String deviceType = android.os.Build.MODEL;
        String sdk = Build.VERSION.SDK_INT + "";
        String release = Build.VERSION.RELEASE;
        mInfo.put("Device Type", deviceType);
        mInfo.put("SDK Version", sdk);
        mInfo.put("android System", release);
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (info != null) {
                mInfo.put("App Version", info.versionName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (handleException(ex)) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        File dir;
        if (TextUtils.isEmpty(mPath)) {
            File root = Environment.getExternalStorageDirectory();
            dir = new File(root, mContext.getPackageName());
        } else {
            dir = new File(mPath);
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }

        StringBuilder builder = new StringBuilder();
        File crashFile = new File(dir, CRASH_FILENAME);
        Log.e(TAG, "handleException: path " + crashFile.getAbsolutePath());
        try {
            if (!crashFile.exists()) {
                crashFile.createNewFile();
            } else {
                // 读取已存在的文本
                InputStream in = new FileInputStream(crashFile);
                byte[] buff = new byte[256];
                int len;
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while ((len = in.read(buff)) != -1) {
                    os.write(buff, 0, len);
                }
                os.flush();
                String oldEx = new String(os.toByteArray());
                builder.append(oldEx).append("\n-----------------------\n");
                in.close();
            }

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            String exception = writer.toString();
            StringBuilder builder2 = new StringBuilder();
            for (Map.Entry<String, String> entry : mInfo.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.append(key).append(":").append(value).append("\n");
                builder2.append(key).append(":").append(value).append("\n");
            }
            Date date = new Date();
            builder.append("Date").append(":").append(date).append("\n");
            builder2.append("Date").append(":").append(date).append("\n");
            builder.append(exception);
            builder2.append(exception);
            OutputStream ous = new FileOutputStream(crashFile);
            ous.write(builder.toString().getBytes());
            ous.flush();
            ous.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
