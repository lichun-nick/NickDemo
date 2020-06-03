package com.inspur.blockchain.register;


import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;


/**
 * @author lichun
 */
public class ForgetPasswordViewModel extends ViewModel {

    private ForgetPasswordRepository forgetPasswordRepository;

    public ForgetPasswordViewModel(){
        this.forgetPasswordRepository = new ForgetPasswordRepository();
    }

    public LiveData<JSONObject> resetPassword(ArrayMap<String,String> params){
        return forgetPasswordRepository.resetPassword(params);
    }

    public LiveData<JSONObject> requestCode(String phone){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("phone",phone);
        return forgetPasswordRepository.requestVerificationCode(params);
    }
}
