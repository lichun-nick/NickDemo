package com.inspur.blockchain;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.lib_base.mmkv.MmkvUtil;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class CheckTokenViewModel extends ViewModel {

    private CheckTokenRepository checkTokenRepository;

    public CheckTokenViewModel(){
        this.checkTokenRepository = new CheckTokenRepository();
    }

    public LiveData<JSONObject> checkToken(){
        String token = MmkvUtil.getInstance().getString(Keys.USER_TOKEN,"");
        ArrayMap<String,String> headers = new ArrayMap<>();
        headers.put("Content-type","application/json");
        headers.put("Authorization",token);
        return checkTokenRepository.checkToken(headers);
    }

}
