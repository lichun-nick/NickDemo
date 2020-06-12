package com.inspur.blockchain;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.lib_base.net.NetCallback;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class ScanResultShowRepository extends BaseRepository{

    public LiveData<JSONObject> requestData(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.SCAN_CODE_GET_DATA).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                mutableLiveData.postValue(jsonObject);
            }

            @Override
            public void onFailure(JSONObject jsonObject) {

            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        });
        return mutableLiveData;
    }

}
