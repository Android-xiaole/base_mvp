package com.le.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

/**
 * Created by sahara on 2017/4/27.
 */

public abstract class BaseFragment<E,T extends BasePresenter<E>> extends BackHandledFragment{

    protected View mView;
    protected T presenter;

    protected abstract int setContentViewID();
    protected abstract T getPresenter();
    protected abstract void onCreateMy(Bundle savedInstanceState);
    protected abstract void onCreateViewMy(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        try {
            presenter.attach((E) this);
        } catch (Exception e) {
            LogUtils.e(e);
            e.printStackTrace();
        }
        onCreateMy(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null){
            int r = setContentViewID();
            if (r == 0){
                throw new RuntimeException("can not find "+this.getClass().getSimpleName()+" layout resource id");
            }
            mView = inflater.inflate(r,null);
            onCreateViewMy(inflater,container,savedInstanceState);
        }else{
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup!=null){
                viewGroup.removeView(mView);
            }
        }
        return mView;
    }

    @Override
    protected boolean onBackPressed() {
        return false;//默认finish当前界面 true就是不finish
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            presenter.dettach();
        } catch (Exception e) {
            LogUtils.e(e);
            e.printStackTrace();
        }
    }
}
