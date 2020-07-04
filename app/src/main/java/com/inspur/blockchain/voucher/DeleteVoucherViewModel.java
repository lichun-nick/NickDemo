package com.inspur.blockchain.voucher;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.model.VoucherTypeListBean;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class DeleteVoucherViewModel extends ViewModel {

    private DeleteVoucherRepository deleteVoucherRepository;

    public DeleteVoucherViewModel(){
        this.deleteVoucherRepository = new DeleteVoucherRepository();
    }

    public LiveData<JSONObject> delVoucher(String did){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("did",did);
        return deleteVoucherRepository.delVoucher(params);
    }


}
