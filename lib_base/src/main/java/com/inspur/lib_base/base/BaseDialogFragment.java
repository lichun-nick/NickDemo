package com.inspur.lib_base.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.inspur.lib_base.view.LoadingDialog;


/**
 * @author lichun
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private LoadingDialog loadingDialog;
    /**
     * 获取view layout
     * @return
     */
    public abstract int getLayoutId();

    public abstract void initView(View view);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setCancelable(true);
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
        if(loadingDialog != null && !loadingDialog.isCancelable()){
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

}
