package com.inspur.blockchain.voucher;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.model.VoucherTypeListBean;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.util.GsonUtil;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class DeleteVoucherRepository extends BaseRepository {

    public LiveData<JSONObject> delVoucher(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.DELETE_VOUCHER_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
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

            }
        });
        return mutableLiveData;
    }

    public LiveData<VoucherTypeListBean> queryVoucherTypes(){
        final MutableLiveData<VoucherTypeListBean> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.QUERY_VOUCHER_TYPES_URL).get().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    mutableLiveData.postValue(GsonUtil.transform(jsonObject.optString(HttpResponse.RESPONSE_DATA), VoucherTypeListBean.class));
                }
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
