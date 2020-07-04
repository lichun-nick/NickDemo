package com.inspur.blockchain.voucher;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.model.VerifyHistoryItemBean;

import org.json.JSONObject;

import java.util.List;

/**
 * @author lichun
 */
public class VerifyRecordDetailViewModel extends ViewModel {

    private VerifyRecordDetailRepository verifyRecordDetailRepository;

    public VerifyRecordDetailViewModel(){
        this.verifyRecordDetailRepository = new VerifyRecordDetailRepository();
    }

    public LiveData<JSONObject> requestRecordDetail(String did,String time){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("did",did);
        params.put("trace_time",time);
        return verifyRecordDetailRepository.requestDetail(params);
    }

}
