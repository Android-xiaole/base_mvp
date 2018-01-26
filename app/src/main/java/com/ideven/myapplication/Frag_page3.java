package com.ideven.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.le.base.BaseFragment;
import com.le.base.BasePresenter;

/**
 * Created by sahara on 2017/5/3.
 */

public class Frag_page3 extends BaseFragment{

    @Override
    protected int setContentViewID() {
        return R.layout.frag_page3;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void configViews() {

    }

    @Override
    protected boolean onBackPressed() {
        ((Act_Home)getActivity()).showLongToast(this.getClass().getSimpleName()+"的onBackPressed()被调用了");
        return super.onBackPressed();
//        return true;
    }
}
