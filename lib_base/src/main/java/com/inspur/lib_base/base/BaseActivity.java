package com.inspur.lib_base.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.inspur.lib_base.view.LoadingLayout;

/**
 * @author lichun
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LoadingLayout mLoadingLayout;
    private FragmentManager mFragmentManager;

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
        if(wrapLayoutId() == 0){
            mLoadingLayout = LoadingLayout.wrap(this);
        }else{
            mLoadingLayout = LoadingLayout.wrap(findViewById(wrapLayoutId()));
        }
        initView();
        initData();
    }


    protected void showEmpty(){
        mLoadingLayout.showEmpty();
    }

    protected void showContent(){
        mLoadingLayout.showContent();
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

}
