package com.inspur.lib_base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inspur.lib_base.view.LoadingDialog;


/**
 * @author lichun
 */
public abstract class BaseFragment extends Fragment {

    private LoadingDialog loadingDialog;

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
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }



    protected void showProgressLoading(){
        if(loadingDialog == null){
            loadingDialog = LoadingDialog.getInstance("加载中...");
        }
        if(loadingDialog.isAdded()){
            loadingDialog.dismiss();
        }
        loadingDialog.show(getChildFragmentManager(),"loading");
    }

    protected void hideProgressLoading(){
        if(loadingDialog != null){
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

}
