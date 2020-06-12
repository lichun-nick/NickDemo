package com.inspur.blockchain.voucher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.model.VoucherListBean;
import com.inspur.blockchain.model.VoucherListItemBean;

import java.util.List;

/**
 * @author lichun
 */
public class VoucherListViewModel extends ViewModel {

    private VoucherListRepository voucherListRepository;

    public VoucherListViewModel(){
        this.voucherListRepository = new VoucherListRepository();
    }

    public LiveData<VoucherListBean> requestData(){
        return voucherListRepository.requestData();
    }
}
