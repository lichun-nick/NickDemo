package com.inspur.lib_base.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;

/**
 * @author lichun
 * 状态展示View
 */
public class StateLayout extends FrameLayout {

    private SparseArrayCompat<View> sparseViews = new SparseArrayCompat<>();

    /**
     *  loading 加载id
     */
    public static final int LAYOUT_LOADING_ID = 1;

    /**
     *  异常id
     */
    public static final int LAYOUT_ERROR_ID = 3;

    /**
     *  网络异常id
     */
    public static final int LAYOUT_NETWORK_ERROR_ID = 4;

    /**
     *  空数据id
     */
    public static final int LAYOUT_EMPTY_DATA_ID = 5;


    private StateLayoutManager layoutManager;


    public StateLayout(@NonNull Context context) {
        super(context);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLayoutManager(StateLayoutManager manager){
        this.layoutManager = manager;
    }


    private void addLayoutId(int layoutId, int id) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId,this,false);
        sparseViews.put(id,view);
        this.removeAllViews();
        addView(view);
    }


    public void showLoading(){
        View view = sparseViews.get(LAYOUT_LOADING_ID);
        if( view != null){
            showThisView(view);
        }else{
            addLayoutId(layoutManager.loadingLayoutResId,LAYOUT_LOADING_ID);
        }
    }

    private void showThisView(View view) {
        this.removeAllViews();
        addView(view);
    }

    private void showViewByType(int type){
        int layoutId = layoutManager.contentLayoutResId;
        if(type == LAYOUT_LOADING_ID){
            layoutId = layoutManager.loadingLayoutResId;
        }else if(type == LAYOUT_EMPTY_DATA_ID){
            layoutId = layoutManager.emptyDataRetryViewId;
        }else if(type == LAYOUT_NETWORK_ERROR_ID){
            layoutId = layoutManager.netWorkErrorRetryViewId;
        }else if(type == LAYOUT_ERROR_ID){
            layoutId = layoutManager.errorRetryViewId;
        }
        View view = sparseViews.get(type);
        if( view != null){
            showThisView(view);
        }else{
            addLayoutId(layoutId,type);
        }
    }


    public void showEmptyData(){
       showViewByType(LAYOUT_EMPTY_DATA_ID);
    }

    public void showEmptyData(int icon,String tip){
        if(icon == 0 && TextUtils.isEmpty(tip)){
            return;
        }
        View parent = sparseViews.get(LAYOUT_EMPTY_DATA_ID);
        if(parent != null){
            View iconView = parent.findViewById(layoutManager.emptyDataIconImageId);
            View tipView = parent.findViewById(layoutManager.emptyDataTextTipId);
            if(iconView instanceof ImageView){
                ((ImageView)iconView).setImageResource(icon);
            }
            if(tipView instanceof TextView){
                ((TextView)tipView).setText(tip);
            }
            showViewByType(LAYOUT_EMPTY_DATA_ID);
        }
    }

    public void showNetWorkError(){
        showViewByType(LAYOUT_NETWORK_ERROR_ID);
    }

    public void showError(){
        showViewByType(LAYOUT_ERROR_ID);
    }
}
