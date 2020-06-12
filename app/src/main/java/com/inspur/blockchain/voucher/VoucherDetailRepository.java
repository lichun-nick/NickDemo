package com.inspur.blockchain.voucher;

import android.text.TextUtils;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.UrlConfig;
import com.inspur.blockchain.model.VoucherDetailBean;
import com.inspur.blockchain.model.VoucherListItemBean;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.util.GsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author lichun
 */
public class VoucherDetailRepository extends BaseRepository {

    public LiveData<JSONObject> requestData(ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.VOUCHER_DETAIL_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    JSONObject data = jsonObject.optJSONObject(HttpResponse.RESPONSE_DATA);
                    JSONObject result = new JSONObject();
                    JSONObject tags = new JSONObject();
                    if(data != null){
                        JSONArray jsonArray = data.optJSONArray("fieldList");
                        if(jsonArray != null){
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.optJSONObject(i);
                                try {
                                    result.put("凭证DID",object.optString("DID"));
                                    tags.put("凭证DID","did");
                                    String key = object.optString("FIELD");
                                    if(TextUtils.equals(key,"ADDR")){
                                        result.put("地址",object.optString("VAL"));
                                        tags.put("地址",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"ID_CARD_NO")){
                                        result.put("身份证号",object.optString("VAL"));
                                        tags.put("身份证号",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"NAME")){
                                        result.put("姓名",object.optString("VAL"));
                                        tags.put("姓名",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"NATION")){
                                        result.put("民族",object.optString("VAL"));
                                        tags.put("民族",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"SEX")){
                                        result.put("性别",object.optString("VAL"));
                                        tags.put("性别",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"DOB")){
                                        result.put("出生日期",object.optString("VAL"));
                                        tags.put("出生日期",key.toLowerCase());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        JSONObject jobject = data.optJSONObject("credentialMap");
                        if(jobject != null){
                            Iterator<String> it = jobject.keys();
                            while (it.hasNext()){
                                String key = it.next();
                                try {
                                    if(TextUtils.equals(key,"ISSUER_DESC")){
                                        result.put("签发人",jobject.optString(key));
                                        tags.put("签发人",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"IS_AUTH")){
                                        result.put("是否权威","是");
                                        tags.put("是否权威",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"ISSUANCE_DATE")){
                                        result.put("发布日期",jobject.optString(key));
                                        tags.put("发布日期",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"EXPIRATION_DATE")){
                                        result.put("有效期至",jobject.optString(key));
                                        tags.put("有效期至",key.toLowerCase());
                                    }else if(TextUtils.equals(key,"DID_DESC")){
                                        result.put("凭证描述",jobject.optString(key));
                                        tags.put("凭证描述",key.toLowerCase());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        JSONObject json = new JSONObject();
                        try{
                            json.put("tag",tags);
                            json.put("data",result);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        mutableLiveData.postValue(json);
                    }
                }
            }

            @Override
            public void onFailure(JSONObject jsonObject) {

            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        });
        return mutableLiveData;
    }

    public LiveData<JSONObject> generateQRString(ArrayMap<String,Object> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        defaultHeaders().url(UrlConfig.GENERATE_URL_BY_DID).mixedBody(params).post().build().requestJson(new NetCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    JSONObject data = jsonObject.optJSONObject(HttpResponse.RESPONSE_DATA);
                    JSONObject result = new JSONObject();
                    if (data != null) {
                        try{
                            result.put(Keys.QR_CODE_PATH,data.optString("path"));
                            result.put(Keys.QR_CODE_AUTH, Objects.requireNonNull(data.optJSONObject("authority")).optString("did_desc"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        mutableLiveData.postValue(result);
                    }
                }
            }

            @Override
            public void onFailure(JSONObject jsonObject) {

            }

            @Override
            public void onError(JSONObject jsonObject) {

            }
        });
        return mutableLiveData;
    }


}
