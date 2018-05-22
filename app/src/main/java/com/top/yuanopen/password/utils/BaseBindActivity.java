package com.top.yuanopen.password.utils;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by yuanopen on 2018/3/28/028.
 */

public abstract class BaseBindActivity<VB extends ViewDataBinding> extends Activity {

    protected VB mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView=getLayoutInflater().inflate(this.getLayoutId(),null,false);
        super.setContentView(rootView);

        initView();
    }

    protected abstract void initView();

    public abstract int getLayoutId() ;
}
