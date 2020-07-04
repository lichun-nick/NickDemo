package com.inspur.blockchain.voucher;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.model.VerifyHistoryItemBean;
import com.inspur.blockchain.model.VoucherTypeListBean;

import org.json.JSONObject;

import java.util.List;

/**
 * @author lichun
 */
public class VerifyHistoryListViewModel extends ViewModel {

    private VerifyHistoryListRepository verifyHistoryListRepository;

    public VerifyHistoryListViewModel(){
        this.verifyHistoryListRepository = new VerifyHistoryListRepository();
    }

    public LiveData<List<VerifyHistoryItemBean>> requestHistoryList(ArrayMap<String,String> params){
        return verifyHistoryListRepository.requestHistoryList(params);
    }

}
