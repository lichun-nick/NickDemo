package com.inspur.blockchain.voucher.receive;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.UrlConfig;
import com.inspur.lib_base.mmkv.MmkvUtil;

import org.json.JSONObject;

/**
 * @author lichun
 */
public class ScanShowRequestPropertiesViewModel extends ViewModel {

    private ScanShowRequestPropertiesRepository scanShowRequestPropertiesRepository;

    public ScanShowRequestPropertiesViewModel(){
        this.scanShowRequestPropertiesRepository = new ScanShowRequestPropertiesRepository();
    }

    public LiveData<JSONObject> confirmRequest(String vpid, JSONObject properties){
        ArrayMap<String,Object> params = new ArrayMap<>();
        params.put("did", MmkvUtil.getInstance().getString(Keys.ID_CARD_DID,""));
        params.put("domain_path", UrlConfig.RECEIVE_VOUCHER_SETTINGS_PUSH_URL);
        params.put("vp_tmp_id",vpid);
        params.put("disclosure",properties);
        return scanShowRequestPropertiesRepository.confirmRequest(params);
    }
}
