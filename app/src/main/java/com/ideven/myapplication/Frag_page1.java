package com.ideven.myapplication;

import android.widget.TextView;

import com.ideven.myapplication.presenter.MainPresenter;
import com.le.base.BaseFragment;
import com.le.base.IBaseView;

import butterknife.BindView;

/**
 * Created by sahara on 2017/5/2.
 */

public class Frag_page1 extends BaseFragment<IBaseView,MainPresenter> implements IBaseView {

    @BindView(R.id.textView) TextView textView;

    @Override
    protected int setContentViewID() {
        return R.layout.frag_page1;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initDatas() {
        showProgress();
        presenter.visitBaiDu();
    }

    @Override
    protected void configViews() {

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
                dismissProgress();
                textView.setText((String) result);
                break;
            case MainPresenter.visitBaiDuError:
                dismissProgress();
                textView.setText(((Exception)result).getMessage());
                break;
        }
    }
}
