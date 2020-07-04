package com.inspur.blockchain.login;

import android.text.TextUtils;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.lib_base.util.Md5Util;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class LoginViewModel extends ViewModel {

    private LoginRepository mLoginRepository;
    public static final String PASSWORD_LOGIN_TYPE = "01";
    public static final String VERIFICATION_CODE_LOGIN_TYPE = "02";

    public LoginViewModel(){
        this.mLoginRepository = new LoginRepository();
    }

    public LiveData<JSONObject> userLogin(String type,String phone,String code){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("phone",phone);
        if(TextUtils.equals(type,PASSWORD_LOGIN_TYPE)){
            params.put("pwd", Md5Util.encrypt32(code));
        }else if(TextUtils.equals(type,VERIFICATION_CODE_LOGIN_TYPE)){
            params.put("phone_code",code);
        }
        params.put("auth_type",type);
        return mLoginRepository.userLogin(params);
    }

    public LiveData<JSONObject> requestCode(ArrayMap<String,String> params){
        return mLoginRepository.requestVerificationCode(params);
    }
}
