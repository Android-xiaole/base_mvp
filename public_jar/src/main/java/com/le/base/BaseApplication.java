package com.le.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;
import com.le.utils.MLog;
import com.le.utils.UtilFile;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by sahara on 2017/4/27.
 */

public class BaseApplication extends Application{

    public static BaseApplication baseApplication;
    public static Context appContext;

    public static SharedPreferences sPreferences;//共享参数对象
    public static Resources mResources;//方便获取资源id
    public static String mDeviceToken;//设备唯一ID
    public static String mAppVersion;//app版本号 1.0.1

    public static Handler mHandler;
    public static Toast mToast;
    public static Toast mLongToast;

    public static String mAppName = "you app name";//replace you app name
    public static String mCompany = "you conpany name";//replace you company name
    public static String mSdPath;// sd
    public static String mDownLoadPath;
    public static String mCachePath;
    public static String mErrLogPath;

    @Override
    public void onCreate() {
        super.onCreate();
//        //默认使用的高度是设备的可用高度，也就是不包括状态栏和底部的操作栏的，如果你希望拿设备的物理高度进行百分比化
//        AutoLayoutConifg.getInstance().useDeviceSize();

        Utils.init(this);
        CrashReport.initCrashReport(getApplicationContext(), "you register id", BaseConfig.DEBUG);

        System.gc();
        baseApplication = this;
        appContext = baseApplication.getApplicationContext();

        sPreferences = getSharedPreferences("Flag",MODE_PRIVATE);
        mResources = getResources();
        mDeviceToken = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            mAppVersion = info.versionName + "." + info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            MLog.e("获取应用版本号失败！");
        }

        mHandler = new Handler(getMainLooper());
        mToast = Toast.makeText(appContext, "", Toast.LENGTH_SHORT);
        mLongToast = Toast.makeText(appContext,"",Toast.LENGTH_LONG);
        mSdPath = UtilFile.isSdcardExist();
        mErrLogPath = mSdPath + mCompany + "/" + mAppName
                + "/Log/";
        mDownLoadPath = mSdPath + mCompany + "/" + mAppName
                + "/DownLoad/";
        mCachePath = mSdPath + mCompany + "/" + mAppName
                + "/Cache/";
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * handler.post() 这里为了try-catch
     */
    public static void post(Runnable r) {
        post(r, 0);
    }

    /**
     * handler.post() 这里为了try-catch
     */
    public static void post(Runnable r, int time) {
        try {
            mHandler.postDelayed(r, time);
        } catch (Exception e) {
            e.printStackTrace();
            MLog.e(baseApplication.getClass().getSimpleName()+":post-"+e);
        }
    }

    /**
     * 显示 Toast 放这里是为了在任何地方都可用
     *
     * @param msg
     * 可以为空
     */
    public static void showToast(final Object msg) {
        post(new Runnable() {
            @Override
            public void run() {
                mToast.setText(msg + "");
                mToast.show();
            }
        });
    }

    public static void showLongToast(final Object msg){
        post(new Runnable() {
            @Override
            public void run() {
                mLongToast.setText(msg + "");
                mLongToast.show();
            }
        });
    }
}
