package com.inspur.lib_base.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.inspur.lib_base.view.LoadingDialog;


/**
 * @author lichun
 */
public abstract class BaseActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private LoadingDialog loadingDialog;

    /**
     * 获取layout id
     * @return id
     */
    protected abstract int getLayoutId();

    protected abstract int wrapLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化获取数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }


    protected void showFragment(int parentId, Fragment fragment){
        if(mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment frag = mFragmentManager.findFragmentByTag(fragment.getTag());
        if(frag == null){
            mFragmentManager.beginTransaction().add(parentId,fragment).commit();
        }else{
            mFragmentManager.beginTransaction().show(fragment).commit();
        }
    }

    protected void showFragment(Fragment fragment,String tag){
        if(mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment frag = mFragmentManager.findFragmentByTag(tag);
        if(frag == null){
            mFragmentManager.beginTransaction().add(fragment,tag).commit();
        }else{
            mFragmentManager.beginTransaction().show(fragment).commit();
        }
    }

    protected void hideFragment(Fragment fragment){
        if(mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }
        Fragment frag = mFragmentManager.findFragmentByTag(fragment.getTag());
        if(frag != null){
            mFragmentManager.beginTransaction().hide(fragment).commit();
        }

    }

    protected void startActivity(Class activityClass){
        Intent intent = new Intent(this,activityClass);
        startActivity(intent);
    }

    protected void startActivityWithFinish(Class activityClass){
        Intent intent = new Intent(this,activityClass);
        startActivity(intent);
        finish();
    }

    protected void toggleSoftInput(Context context){
        InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void hideSoftInput(Activity context){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(context.getCurrentFocus() != null){
            imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showProgressLoading(){
        if(loadingDialog == null){
            loadingDialog = LoadingDialog.getInstance("加载中...");
        }
        if(loadingDialog.isAdded()){
            loadingDialog.dismiss();
        }
        loadingDialog.show(getSupportFragmentManager(),"loading");
    }

    protected void hideProgressLoading(){
        if(loadingDialog != null && !loadingDialog.isCancelable()){
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    protected void showEmpty(){}

}
