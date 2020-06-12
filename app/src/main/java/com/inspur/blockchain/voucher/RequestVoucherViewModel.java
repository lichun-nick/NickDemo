package com.inspur.blockchain.voucher;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.model.VoucherTypeListBean;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class RequestVoucherViewModel extends ViewModel {

    private RequestVoucherRepository requestVoucherRepository;

    public RequestVoucherViewModel(){
        this.requestVoucherRepository = new RequestVoucherRepository();
    }

    public LiveData<JSONObject> requestVoucher(ArrayMap<String,String> params){
        return requestVoucherRepository.requestVoucher(params);
    }

    public LiveData<VoucherTypeListBean> queryVoucherTypes(){
        return requestVoucherRepository.queryVoucherTypes();
    }
}
