package com.inspur.blockchain.voucher;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.model.VerifyHistoryItemBean;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.util.GsonUtil;

import org.json.JSONObject;
import java.util.List;

/**
 * @author lichun
 */
public class VerifyHistoryListRepository extends BaseRepository {

    public LiveData<List<VerifyHistoryItemBean>> requestHistoryList(ArrayMap<String,String> params){
        final MutableLiveData<List<VerifyHistoryItemBean>> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.VERIFY_HISTORY_LIST_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    JSONObject data = jsonObject.optJSONObject(HttpResponse.RESPONSE_DATA);
                    if(data != null){
                        String validList = data.optString("valid_list");
                        mutableLiveData.postValue(GsonUtil.transformList(validList,VerifyHistoryItemBean.class));
                    }else{
                        mutableLiveData.postValue(null);
                    }
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
