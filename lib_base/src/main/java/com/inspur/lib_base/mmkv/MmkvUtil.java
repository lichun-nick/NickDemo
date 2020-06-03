package com.inspur.lib_base.mmkv;

import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * @author lichun
 */
public class MmkvUtil {

    private static MmkvUtil instance;
    private MMKV mmkv;

    private MmkvUtil(){
        mmkv = MMKV.defaultMMKV(MMKV.SINGLE_PROCESS_MODE,"chainblock");
    }

    /**
     * 确保初始化
     * @param context
     */
    public static void init(Context context){
        MMKV.initialize(context.getApplicationContext());
    }

    public static MmkvUtil getInstance(){
        if(instance == null){
            synchronized (MmkvUtil.class){
                if(instance == null){
                    instance = new MmkvUtil();
                }
            }
        }
        return instance;
    }

    public MMKV getMmkv(){
        return mmkv;
    }

    public boolean putString(String key,String value){
        return mmkv.encode(key,value);
    }

    public String getString(String key,String defaultValue){
        return mmkv.decodeString(key,defaultValue);
    }

    public boolean putInt(String key,int value){
        return mmkv.encode(key,value);
    }

    public int getInt(String key,int defaultValue){
        return mmkv.decodeInt(key,defaultValue);
    }

    public boolean putBoolean(String key,boolean value){
        return mmkv.encode(key,value);
    }

    public boolean getBoolean(String key,boolean defaultValue){
        return mmkv.decodeBool(key,defaultValue);
    }



}
