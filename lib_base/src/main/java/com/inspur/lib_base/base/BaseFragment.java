package com.inspur.lib_base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inspur.lib_base.view.LoadingLayout;


/**
 * @author lichun
 */
public abstract class BaseFragment extends Fragment {


    private LoadingLayout mLoadingLayout;

    /**
     * 获取layout id
     * @return id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView(View view);

    /**
     * 初始化获取数据
     */
    protected abstract void initData();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingLayout = LoadingLayout.wrap(container);
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    protected void showEmpty(){
        mLoadingLayout.showEmpty();
    }

    protected void showError(){
        mLoadingLayout.showError();
    }


    protected void showProgressLoading(){
        mLoadingLayout.showLoading();
    }

    protected void hideProgressLoading(){
        mLoadingLayout.showContent();
    }
}
