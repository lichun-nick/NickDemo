package com.inspur.blockchain.login;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.VerificationCodeType;
import com.inspur.lib_base.mmkv.MmkvUtil;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.net.NetworkManager;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class LoginRepository {

    public LiveData<JSONObject> userLogin(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        mBuilder.url(UrlConfig.PASSWORD_LOGIN_URL).body(params).post().build();
        NetworkManager.getInstance().access(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                mutableLiveData.postValue(jsonObject);
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    MmkvUtil.getInstance().putString(Keys.USER_TOKEN,jsonObject.optString(HttpResponse.RESPONSE_DATA));
                }
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

    public LiveData<JSONObject> requestVerificationCode(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mLiveData = new MutableLiveData<>();
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        mBuilder.url(UrlConfig.REQUEST_VERIFICATION_CODE_URL+ VerificationCodeType.LOGIN).body(params).post().build();
        NetworkManager.getInstance().access(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                mLiveData.postValue(jsonObject);
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                mLiveData.postValue(jsonObject);
            }

            @Override
            public void onError(JSONObject jsonObject) {
                mLiveData.postValue(jsonObject);
            }
        });
        return mLiveData;
    }

}
