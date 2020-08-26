package com.inspur.lib_base.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.inspur.lib_base.R;
import com.inspur.lib_base.view.LoadingDialog;
import com.inspur.lib_base.view.StateLayoutManager;


/**
 * @author lichun
 */
public abstract class BaseStateActivity extends BaseActivity {

    private StateLayoutManager stateLayoutManager;

    protected abstract int wrapLayoutId();

    @Override
    protected void initStateView() {
        stateLayoutManager = new StateLayoutManager.Builder(this)
                .emptyView(R.layout._loading_layout_empty)
                .errorView(R.layout._loading_layout_error)
                .loadingView(R.layout._loading_layout_loading)
                .build();
        ((ViewGroup)findViewById(wrapLayoutId())).addView(stateLayoutManager.getStateLayout());
    }



    @Override
    protected void showProgressLoading() {
        stateLayoutManager.showLoading();
    }

    @Override
    protected void hideProgressLoading(){
        stateLayoutManager.dismiss();
    }


    protected void showEmpty() {
        stateLayoutManager.showEmptyData();
    }

    protected void showError(){
        stateLayoutManager.showError();
    }

    protected void showNetWorkError(){
        stateLayoutManager.showNetWorkError();
    }
}
