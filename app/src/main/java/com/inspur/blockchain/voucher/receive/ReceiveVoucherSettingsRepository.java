package com.inspur.blockchain.voucher.receive;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.UrlConfig;
import com.inspur.lib_base.net.NetCallback;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class ReceiveVoucherSettingsRepository extends BaseRepository {

    public LiveData<JSONObject> requestSettings(ArrayMap<String,Object> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.RECEIVE_VOUCHER_SETTINGS_URL).mixedBody(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject object) {
                mutableLiveData.postValue(object);
            }

            @Override
            public void onFailure(JSONObject object) {

            }

            @Override
            public void onError(JSONObject object) {

            }
        });
        return mutableLiveData;
    }
}
