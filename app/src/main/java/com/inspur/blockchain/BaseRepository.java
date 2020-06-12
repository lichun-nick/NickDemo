package com.inspur.blockchain;


import androidx.collection.ArrayMap;

import com.inspur.lib_base.mmkv.MmkvUtil;
import com.inspur.lib_base.net.NetworkManager;



/**
 * @author lichun
 */
public class BaseRepository {

    protected NetworkManager.Builder defaultHeaders(){
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        ArrayMap<String,String> commonHeaders = new ArrayMap<>();
        commonHeaders.put("Authorization", MmkvUtil.getInstance().getString(Keys.USER_TOKEN,""));
        commonHeaders.put("Content-type","application/json");
        return mBuilder.header(commonHeaders);
    }

    protected NetworkManager.Builder noAuthorizationHeaders(){
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        ArrayMap<String,String> commonHeaders = new ArrayMap<>();
        commonHeaders.put("Content-type","application/json");
        return mBuilder.header(commonHeaders);
    }

}
