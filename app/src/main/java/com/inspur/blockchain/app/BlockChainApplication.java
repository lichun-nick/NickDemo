package com.inspur.blockchain.app;

import android.app.Application;

import com.inspur.lib_base.mmkv.MmkvUtil;

/**
 * @author lichun
 */
public class BlockChainApplication extends Application {

    public static BlockChainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MmkvUtil.init(this);
    }

    public static BlockChainApplication getInstance(){
        return instance;
    }
}
