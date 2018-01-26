package com.ideven.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ideven.myapplication.api.Login_res;
import com.ideven.myapplication.api.MyAilas;
import com.ideven.myapplication.api.Test;
import com.ideven.myapplication.presenter.MainPresenter;
import com.le.base.BaseActivity;
import com.le.base.IBaseView;
import com.le.net.HttpManagerWithCookie;
import com.le.net.IHttpHandler;
import com.le.utils.HttpManager;
import com.le.utils.MLog;
import com.le.utils.StringCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IBaseView,MainPresenter> implements IBaseView,View.OnClickListener{

    @Override
    protected int setContentViewID() {
        return R.layout.activity_main;
    }

    @BindView(R.id.button5) Button button5;
    @BindView(R.id.editText) EditText editText;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void configViews() {
        button5.setOnClickListener(this);
        String html = "请输入<font color='red'><big>用户名</big></font>";
        editText.setText(Html.fromHtml(html));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                showProgress("haha",false);
                MyApplication.post(new Runnable() {
                    @Override
                    public void run() {
                      dismissProgress();
                    }
                },3000);
                break;
            case R.id.button2:
                showProgress();
                break;
            case R.id.button3:
                toActivity(Act_Home.class);
                break;
            case R.id.button4:
                toActivity(Act_ListView.class);
                break;
            case R.id.button5:
                showProgress();
                presenter.visitBaiDu();
                break;
            case R.id.button6:
//                presenter.login();
                loginTest();
                break;
            case R.id.button7:
                String jsonArray = "[{\"circleComment\":\"~!@#$%^&*()_+\"}]";
                List<Test> tests = JSON.parseArray(jsonArray, Test.class);
                for (Test test:tests) {
                    MLog.e(test.toString());
                    showDialog("解析结果："+test.toString());
                }
                break;
        }
    }

    private void loginTest(){
        String token = "5942E5563E1CB8644EB2AAD0FD6B4213";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("token",token);
        HttpManager.getInstance().setHeaders(headers).postJson("http://192.168.8.130:8080/sealfinance-api/user/updateDevice",new MyAilas("13"), new StringCallBack() {
            @Override
            public void onResponse(String res) {
                LogUtils.e(res);
            }

            @Override
            public void onError(Exception e) {
                LogUtils.e(e+"");
            }
        });
    }

    private void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("返回结果：")
                .setMessage(msg)
                .create().show();
    }

    @Override
    public void onResponse(int flag, Object result) {
        LogUtils.e(result);
        switch (flag){
            case MainPresenter.visitBaiDuRespones:
                dismissProgress();
//                showDialog((String) result);
//                showDialog("加载完成");
                ToastUtils.showLong((String)result);
                break;
            case MainPresenter.visitBaiDuError:
                showDialog(((Exception)result).getMessage());
                dismissProgress();
                break;
            case MainPresenter.loginRes:
                Login_res login_res = JSON.parseObject((String) result, Login_res.class);
                LogUtils.e(login_res.toString());
                showDialog(login_res.toString());
                break;
            case MainPresenter.loginError:
                showDialog(((Exception)result).getMessage());
                break;
        }
    }
}
