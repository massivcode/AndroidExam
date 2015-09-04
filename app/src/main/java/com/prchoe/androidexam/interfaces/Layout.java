package com.prchoe.androidexam.interfaces;

import android.view.View;

/**
 * Created by massivCode on 2015-09-04.
 */
public interface Layout extends View.OnClickListener {

    void initView();

    void initListener();

    @Override
    void onClick(View v);
}
