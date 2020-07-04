package com.inspur.blockchain.voucher.receive;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.UrlConfig;
import com.inspur.lib_base.mmkv.MmkvUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author lichun
 */
public class ReceiveVoucherSettingsViewModel extends ViewModel {

    private ReceiveVoucherSettingsRepository receiveVoucherSettingsRepository;

    public ReceiveVoucherSettingsViewModel(){
        this.receiveVoucherSettingsRepository = new ReceiveVoucherSettingsRepository();
    }

    public LiveData<JSONObject> requestSettings(String type, String properties){
        ArrayMap<String,Object> params = new ArrayMap<>();
        params.put(Keys.CPT_ID, MmkvUtil.getInstance().getString(Keys.ID_CARD_CPT_ID,""));
        params.put("domain_path", UrlConfig.RECEIVE_VOUCHER_SETTINGS_PUSH_URL);
        params.put("purpose","接收凭证");
        params.put("valid_props",properties);
        params.put("cpt_name",type);
        params.put("valider",MmkvUtil.getInstance().getString(Keys.NICK_NAME,"jack"));
        return receiveVoucherSettingsRepository.requestSettings(params);
    }
}
