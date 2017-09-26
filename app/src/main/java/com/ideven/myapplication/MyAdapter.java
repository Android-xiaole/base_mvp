package com.ideven.myapplication;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

import com.le.base.AbsBaseAdapter;

import java.util.List;

/**
 * Created by sahara on 2017/6/20.
 */

public class MyAdapter extends AbsBaseAdapter<String,MyAdapter.myHolder>{

    public MyAdapter(Context ctx, List<String> list) {
        super(ctx, list);
    }

    @Override
    protected myHolder getHolder() {
        return new myHolder(R.layout.item);
    }

    @Override
    protected void getView(myHolder t, String item, int position, ViewGroup parent) {
        t.button.setText(item);
    }

    class myHolder extends AbsBaseAdapter.Holder{
        private Button button;

        public myHolder(int r) {
            super(r);
            button = (Button) mView.findViewById(R.id.button);
        }
    }
}
