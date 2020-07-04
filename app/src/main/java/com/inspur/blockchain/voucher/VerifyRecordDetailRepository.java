package com.inspur.blockchain.voucher;

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
public class VerifyRecordDetailRepository extends BaseRepository {

    public LiveData<JSONObject> requestDetail(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.GET_VERIFY_HISTORY_DETAIL_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    mutableLiveData.postValue(jsonObject.optJSONObject(HttpResponse.RESPONSE_DATA));
                }

            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                mutableLiveData.postValue(null);
            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        });
        return mutableLiveData;
    }


}
