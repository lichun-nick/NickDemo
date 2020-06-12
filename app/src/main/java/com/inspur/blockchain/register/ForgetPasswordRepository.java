package com.inspur.blockchain.register;

import android.util.Log;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.VerificationCodeType;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.net.NetworkManager;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class ForgetPasswordRepository {

    public static final String TAG = "ForgetPwdRepository";

    public LiveData<JSONObject> resetPassword(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mLiveData = new MutableLiveData<>();
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        mBuilder.url(UrlConfig.RESET_PASSWORD_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
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

    public LiveData<JSONObject> requestVerificationCode(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mLiveData = new MutableLiveData<>();
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        mBuilder.url(UrlConfig.REQUEST_VERIFICATION_CODE_URL+ VerificationCodeType.RESET_PASSWORD).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.i(TAG, "onSuccess: " + jsonObject.toString());
                mLiveData.postValue(jsonObject);
            }

            @Override
            public void onFailure(JSONObject jsonObject) {
                Log.i(TAG, "onFailure: " + jsonObject.toString());
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
