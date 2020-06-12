package com.inspur.blockchain.voucher;


import android.util.Log;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.UrlConfig;

import org.json.JSONObject;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * @author lichun
 */
public class VoucherDetailViewModel extends ViewModel {

    private VoucherDetailRepository voucherDetailRepository;

    public VoucherDetailViewModel(){
        this.voucherDetailRepository = new VoucherDetailRepository();
    }

    public LiveData<JSONObject> requestData(String did){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("did",did);
        return voucherDetailRepository.requestData(params);
    }

    public LiveData<JSONObject> generateUrlByDID(String did,JSONObject object){
        ArrayMap<String,Object> params = new ArrayMap<>();
        params.put("did",did);
        params.put("disclosure",object);
        Log.i("generateUrlByDID", "generateUrlByDID: " + object.toString());
        params.put("domain_path", UrlConfig.GET_DID_URL_BY_VERIFIER);
        return voucherDetailRepository.generateQRString(params);

    }
}
