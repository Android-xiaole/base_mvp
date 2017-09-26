package com.ideven.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ideven.myapplication.presenter.MainPresenter;
import com.le.base.BaseFragment;
import com.le.base.IBaseView;

/**
 * Created by sahara on 2017/5/2.
 */

public class Frag_page1 extends BaseFragment<IBaseView,MainPresenter> implements IBaseView {

    private TextView textView;

    @Override
    protected int setContentViewID() {
        return R.layout.frag_page1;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreateMy(Bundle savedInstanceState) {
        presenter.visitBaiDu();
    }

    @Override
    protected void onCreateViewMy(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textView = (TextView) mView.findViewById(R.id.textView);
    }

    @Override
    protected boolean onBackPressed() {
        ((Act_Home)getActivity()).showToast(this.getClass().getSimpleName()+"的onBackPressed()被调用了");
        return true;
    }

    @Override
    public void onResponse(int flag, Object result) {
        switch (flag){
            case MainPresenter.visitBaiDuRespones:
                textView.setText((CharSequence) result);
                break;
            case MainPresenter.loginError:
                textView.setText(((Exception)result).getMessage());
                break;
        }
    }
}
