package com.inspur.blockchain.voucher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.model.VoucherListBean;
import com.inspur.blockchain.model.VoucherListItemBean;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.util.GsonUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * @author lichun
 */
public class VoucherListRepository extends BaseRepository {

    public LiveData<VoucherListBean> requestData(){
        final MutableLiveData<VoucherListBean> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.VOUCHER_LIST_URL).body(null).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    JSONObject data = jsonObject.optJSONObject(HttpResponse.RESPONSE_DATA);
                    if(data != null){
                        VoucherListBean bean = GsonUtil.transform(data.toString(),VoucherListBean.class);
                        mutableLiveData.postValue(bean);
                    }else{
                        mutableLiveData.postValue(null);
                    }
                }else{
                    mutableLiveData.postValue(null);
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
