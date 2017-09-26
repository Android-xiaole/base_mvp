package com.ideven.myapplication;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.le.base.BaseActivity;
import com.le.base.BasePresenter;

/**
 * Created by sahara on 2017/5/2.
 */

public class Act_Home extends BaseActivity implements View.OnClickListener{

    @Override
    protected int setContentViewID() {
        return R.layout.act_home;
    }

    @Override
    protected void initUI() {
        frags.add(new Frag_page1());
        frags.add(new Frag_page2());
        frags.add(new Frag_page3());
        loadFragment(0);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreateMy(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                loadFragment(0);
            break;

            case R.id.button2:
                loadFragment(1);
                break;
            case R.id.button3:
                loadFragment(2);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
