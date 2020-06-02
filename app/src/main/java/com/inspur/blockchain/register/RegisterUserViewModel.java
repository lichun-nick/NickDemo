package com.inspur.blockchain.register;


import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;


/**
 * @author lichun
 */
public class RegisterUserViewModel extends ViewModel {

    private RegisterUserRepository registerUserRepository;

    public RegisterUserViewModel(){
        this.registerUserRepository = new RegisterUserRepository();
    }

    public LiveData<JSONObject> registerUser(ArrayMap<String,String> params){
        return registerUserRepository.registerUser(params);
    }

    public LiveData<JSONObject> requestCode(String phone){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("phone",phone);
        return registerUserRepository.requestVerificationCode(params);
    }
}
