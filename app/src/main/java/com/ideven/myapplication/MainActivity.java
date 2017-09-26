package com.ideven.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.ideven.myapplication.api.Login_res;
import com.ideven.myapplication.api.Test;
import com.ideven.myapplication.presenter.MainPresenter;
import com.le.base.BaseActivity;
import com.le.base.IBaseView;
import com.le.utils.MLog;

import java.util.List;

public class MainActivity extends BaseActivity<IBaseView,MainPresenter> implements IBaseView,View.OnClickListener{

    @Override
    protected int setContentViewID() {
        return R.layout.activity_main;
    }

    private Button button5;
    private EditText editText;
    @Override
    protected void initUI() {
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText);
        String html = "<font color=00ffff>请输入用户名</font>";
        editText.setText(Html.fromHtml(html));
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreateMy(Bundle savedInstanceState) {
        //can use UI to do something
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
                presenter.login();
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
                showDialog((String) result);
                dismissProgress();
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
