package com.le.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.le.utils.MyUtils;

import butterknife.ButterKnife;

/**
 * Created by sahara on 2017/4/27.
 */

public abstract class BaseFragment<E,T extends BasePresenter<E>> extends BackHandledFragment implements IBaseUI{

    private BaseUI baseUI;
    protected View mView;
    protected T presenter;

    protected abstract int setContentViewID();
    protected abstract T getPresenter();
    protected abstract void initDatas();
    protected abstract void configViews();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseUI = new BaseUI(getActivity());
        presenter = getPresenter();
        try {
            presenter.attach((E) this);
        } catch (Exception e) {
            LogUtils.e(e);
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null){
            int r = setContentViewID();
            if (r == 0){
                throw new RuntimeException("can not find "+this.getClass().getSimpleName()+" layout resource id");
            }
            mView = inflater.inflate(r,container,false);
        }else{
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup!=null){
                viewGroup.removeView(mView);
            }
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initDatas();
        configViews();
    }

    @Override
    protected boolean onBackPressed() {
        return false;//默认finish当前界面 true就是不finish
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseUI.REQUEST_CODE&&resultCode == BaseUI.RESULT_CODE){
            BaseApplication.post(new Runnable() {
                @Override
                public void run() {
                    MyUtils.backgroundAlpha(getActivity(), (float) 1.0);
                }
            },200);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void toActivity(Class<?> clas) {
        baseUI.toActivity(clas);
    }

    @Override
    public void showToast(String msg) {
        baseUI.showToast(msg);
    }

    @Override
    public void showLongToast(String msg) {
        baseUI.showLongToast(msg);
    }

    @Override
    public void showProgress() {
        baseUI.showProgress();
    }

    @Override
    public void showProgress(String msg) {
        baseUI.showProgress(msg);
    }

    @Override
    public void showProgress(boolean cancel) {
        baseUI.showProgress(cancel);
    }

    @Override
    public void showProgress(String msg, boolean cancel) {
        baseUI.showProgress(msg,cancel);
    }

    @Override
    public void dismissProgress() {
        baseUI.dismissProgress();
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
