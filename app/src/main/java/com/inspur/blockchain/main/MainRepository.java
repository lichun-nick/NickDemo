package com.inspur.blockchain.main;


import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.model.MainInfoBean;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.util.GsonUtil;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class MainRepository extends BaseRepository {


    public MainRepository(){}

    public LiveData<MainInfoBean> requestData(){
        final MutableLiveData<MainInfoBean> mLiveData = new MutableLiveData<>();

        defaultHeaders().url(UrlConfig.MAIN_URL).body(null).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    mLiveData.postValue(GsonUtil.transform(jsonObject.optString(HttpResponse.RESPONSE_DATA),MainInfoBean.class));
                }
            }

            @Override
            public void onFailure(JSONObject jsonObject) {

            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        });

        return mLiveData;
    }

}
