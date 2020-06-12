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
public class RegisterUserRepository {

    public static final String TAG = "RegisterUserRepository";

    public LiveData<JSONObject> registerUser(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mLiveData = new MutableLiveData<>();
        NetworkManager.Builder mBuilder = new NetworkManager.Builder();
        mBuilder.url(UrlConfig.REGISTER_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
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
        mBuilder.url(UrlConfig.REQUEST_VERIFICATION_CODE_URL+ VerificationCodeType.REGISTER).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
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
