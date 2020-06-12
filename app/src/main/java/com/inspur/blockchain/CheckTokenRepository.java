package com.inspur.blockchain;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.net.NetworkManager;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class CheckTokenRepository {

    public LiveData<JSONObject> checkToken(ArrayMap<String,String> headers){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        mBuilder.url(UrlConfig.CHECK_TOKEN).header(headers).get().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                mutableLiveData.postValue(jsonObject);
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                mutableLiveData.postValue(jsonObject);
            }

            @Override
            public void onError(JSONObject jsonObject) {
                mutableLiveData.postValue(jsonObject);
            }
        });
        return mutableLiveData;
    }
}
