package com.ideven.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import com.le.base.BaseActivity;
import com.le.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sahara on 2017/6/20.
 */

public class Act_ListView extends BaseActivity{

    private ListView listView;
    private List<String> list;


    @Override
    protected int setContentViewID() {
        return R.layout.act_listview;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDatas() {
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        list.add("按钮1");
        list.add("按钮2");
        list.add("按钮3");
        list.add("按钮4");
        list.add("按钮5");
    }

    @Override
    protected void configViews() {
        listView.setAdapter(new MyAdapter(this,list));
    }

}
