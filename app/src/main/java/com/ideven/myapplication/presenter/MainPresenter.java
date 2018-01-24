package com.ideven.myapplication.presenter;

import com.le.base.BasePresenter;
import com.le.base.IBaseView;
import com.le.utils.HttpManager;
import com.le.utils.StringCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/2.
 */

public class MainPresenter extends BasePresenter<IBaseView> {

    public static final int visitBaiDuRespones = 0;
    public static final int visitBaiDuError = 1;
    public static final int loginRes = 2;
    public static final int loginError = 3;

    public void visitBaiDu() {
        HttpManager.get("https://www.baidu.com", null, new StringCallBack() {
            @Override
            public void onResponse(String res) {
                mView.onResponse(visitBaiDuRespones,res);
            }

            @Override
            public void onError(Exception e) {
                mView.onResponse(visitBaiDuError,e);
            }
        });
    }

    public void login(){
        final Map<String,String> parames = new HashMap<>();
        parames.put("login","15800000000");
        parames.put("password","kaitai");
        HttpManager.postForm("https://app6.idevent.cn/account/login", parames, new StringCallBack() {
            @Override
            public void onResponse(String res) {
                mView.onResponse(loginRes,res);
            }

            @Override
            public void onError(Exception e) {
                mView.onResponse(loginError,e);
            }
        });
    }
}
