package com.inspur.lib_base.view;

import android.content.Context;
import android.view.ViewGroup;

/**
 * @author lichun
 * 状态页面管理器
 */
public final class StateLayoutManager {

      Context context = null;
      int netWorkErrorRetryViewId = 0;
      int emptyDataRetryViewId = 0;
      int errorRetryViewId = 0;
      int loadingLayoutResId = 0;
      int contentLayoutResId = 0;
      int retryViewId = 0;
      int emptyDataIconImageId = 0;
      int emptyDataTextTipId = 0;
      int errorIconImageId = 0;
      int errorTextTipId = 0;
      int backgroundColor = 0;

    private final StateLayout stateLayout;

    public StateLayoutManager(Builder builder){
        this.contentLayoutResId = builder.contentLayoutResId;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.backgroundColor = builder.backgroundColor;
        this.emptyDataIconImageId = builder.emptyDataIconImageId;
        this.emptyDataTextTipId = builder.emptyDataTextTipId;

        stateLayout = new StateLayout(builder.context);
        stateLayout.setBackgroundColor(this.backgroundColor);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        stateLayout.setLayoutParams(layoutParams);

        /**
         * 关联
         */
        stateLayout.setLayoutManager(this);
    }

    public StateLayout getStateLayout(){
        return stateLayout;
    }

    public void showLoading(){
        stateLayout.showLoading();
    }

    public void showContent(){
        stateLayout.showContent();
    }

    public void showNetWorkError(){
        stateLayout.showNetWorkError();
    }

    public void showError(){
        stateLayout.showError();
    }

    public void showEmptyData(){
        stateLayout.showEmptyData();
    }

    public static final class Builder{
        private  Context context = null;
        private  int netWorkErrorRetryViewId = 0;
        private  int emptyDataRetryViewId = 0;
        private  int errorRetryViewId = 0;
        private  int loadingLayoutResId = 0;
        private  int contentLayoutResId = 0;
        private  int retryViewId = 0;
        private  int emptyDataIconImageId = 0;
        private  int emptyDataTextTipId = 0;
        private  int errorIconImageId = 0;
        private  int errorTextTipId = 0;

        private  int backgroundColor;

        public Builder(Context context){
            this.context = context;
        }

        public Builder loadingView(int loadingLayoutResId){
            this.loadingLayoutResId = loadingLayoutResId;
            return this;
        }

        public Builder emptyView(int emptyDataRetryViewId){
            this.emptyDataRetryViewId = emptyDataRetryViewId;
            return this;
        }

        public Builder errorView(int errorRetryViewId){
            this.errorRetryViewId = errorRetryViewId;
            return this;
        }

        public Builder netErrorView(int netWorkErrorRetryViewId){
            this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
            return this;
        }

        public Builder contentView(int contentLayoutResId){
            this.contentLayoutResId = contentLayoutResId;
            return this;
        }


        public Builder backgroundColor(int backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder emptyDataIconImageId(int emptyDataIconImageId) {
            this.emptyDataIconImageId = emptyDataIconImageId;
            return this;
        }

        public Builder emptyDataTextTipId(int emptyDataTextTipId) {
            this.emptyDataTextTipId = emptyDataTextTipId;
            return this;
        }


        public StateLayoutManager build(){
            return new StateLayoutManager(this);
        }
    }

}
