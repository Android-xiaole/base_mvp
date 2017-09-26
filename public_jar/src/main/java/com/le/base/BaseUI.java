package com.le.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.le.R;
import com.le.utils.MyUtils;

/**
 * Created by sahara on 2017/4/27.
 */

public class BaseUI implements IBaseUI{

    public Context ctx;
    public static final int REQUEST_CODE = 99;
    public static final int RESULT_CODE = 100;

    public BaseUI(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void toActivity(Class<?> clas) {
        ctx.startActivity(new Intent(ctx,clas));
    }

    @Override
    public void showToast(String msg) {
        BaseApplication.showToast(msg);
    }

    @Override
    public void showLongToast(String msg) {
        BaseApplication.showLongToast(msg);
    }

    @Override
    public void showProgress() {
        MyUtils.backgroundAlpha((Activity) ctx, (float) 0.5);
        Intent intent = new Intent(ctx,ProgressActivity.class);
//        ctx.startActivity(intent);
        ((Activity) ctx).startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void showProgress(String msg) {
        MyUtils.backgroundAlpha((Activity) ctx, (float) 0.5);
        Intent intent = new Intent(ctx,ProgressActivity.class);
        intent.putExtra("msg",msg);
//        ctx.startActivity(intent);
        ((Activity) ctx).startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void showProgress(boolean cancel) {
        MyUtils.backgroundAlpha((Activity) ctx, (float) 0.5);
        Intent intent = new Intent(ctx,ProgressActivity.class);
        intent.putExtra("cancel",cancel);
//        ctx.startActivity(intent);
        ((Activity) ctx).startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void showProgress(String msg,boolean cancel) {
        MyUtils.backgroundAlpha((Activity) ctx, (float) 0.5);
        Intent intent = new Intent(ctx,ProgressActivity.class);
        intent.putExtra("msg",msg);
        intent.putExtra("cancel",cancel);
//        ctx.startActivity(intent);
        ((Activity) ctx).startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void dismissProgress() {
        if (ProgressActivity.instance!=null){
            ProgressActivity.instance.finish();
            ProgressActivity.instance.overridePendingTransition(R.anim.scale_out,R.anim.scale_out);
            ProgressActivity.instance = null;
            System.gc();
        }
        BaseApplication.post(new Runnable() {
            @Override
            public void run() {
                MyUtils.backgroundAlpha((Activity) ctx, (float) 1.0);
            }
        },200);
    }
}
