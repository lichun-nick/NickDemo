package com.inspur.blockchain;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.lib_base.mmkv.MmkvUtil;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class ScanResultShowViewModel extends ViewModel {

    private ScanResultShowRepository scanResultShowRepository;

    public ScanResultShowViewModel(){
        this.scanResultShowRepository = new ScanResultShowRepository();
    }

    public LiveData<JSONObject> requestData(String path){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("url_path",path);
        params.put("verifier_did","did:ibid:A01E55A5D2037A2C2590EF5F3416F0BE");
        return scanResultShowRepository.requestData(params);
    }

}
