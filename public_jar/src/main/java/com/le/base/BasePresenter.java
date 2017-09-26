package com.le.base;

/**
 * Created by admin on 2017/9/2.
 */

public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView){this.mView = mView;}

    public void dettach(){
        if (mView!=null){
            mView = null;
        }
    }
}
