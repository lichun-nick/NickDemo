package com.inspur.blockchain;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.lib_base.LiveDataBus;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author lichun
 * 扫描二维码结果展示页
 */
public class ScanShowVoucherDetailActivity extends BaseActivity {

    private LinearLayout llParent;
    private int index;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_show_result;
    }

    @Override
    protected void initStateView() {

    }


    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_scan_show_result);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                LiveDataBus.get().with("refresh_main").setValue(true);
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        llParent = findViewById(R.id.ll_scan_result_show);
    }

    @Override
    protected void initData() {
        ScanShowVoucherDetailViewModel scanResultShowViewModel = new ViewModelProvider(this).get(ScanShowVoucherDetailViewModel.class);
        String qrData = getIntent().getStringExtra(Keys.QR_CODE_RESULT);
        if(!TextUtils.isEmpty(qrData)){
            if(qrData.startsWith("http") || qrData.startsWith("https")){
                showProgressLoading();
                scanResultShowViewModel.requestData(qrData).observe(this, new Observer<JSONObject>() {
                    @Override
                    public void onChanged(JSONObject jsonObject) {
                        if(jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS)){
                            JSONObject result = jsonObject.optJSONObject(HttpResponse.RESPONSE_DATA);
                            generateView(result);
                        }
                        hideProgressLoading();
                    }
                });
            }else{
                JSONObject result = null;
                try {
                    result = new JSONObject(qrData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                generateView(result);

            }
        }

    }

    private void generateView(JSONObject result){
        if(result != null){
            if(result.optBoolean("result")){
                JSONObject didTraceObject = result.optJSONObject("did_trace");
                if(didTraceObject != null){
                    JSONObject object = didTraceObject.optJSONObject("verifier_props");
                    Iterator<String> it = Objects.requireNonNull(object).keys();
                    while (it.hasNext()){
                        String key = it.next();
                        addContent(Translate.getChineseName(key),object.optString(key));
                    }
                    addContent("交易哈希",didTraceObject.optString("tx_hash"));
                    addContent("签发人",didTraceObject.optString("issuer_name"));
                    addContent("持有人",didTraceObject.optString("holder_name"));
                }
            }
        }
    }

    /**
     * 添加凭证信息到页面展示
     * @param field
     * @param val
     */
    private void addContent(String field, String val) {
        View child = LayoutInflater.from(llParent.getContext()).inflate(R.layout.list_item_scan_show_result,llParent,false);
        TextView tvTitle = child.findViewById(R.id.tv_voucher_detail_item_title);
        TextView tvContent = child.findViewById(R.id.tv_voucher_detail_item_content);
        tvTitle.setText(field);
        tvContent.setText(val);
        llParent.addView(child,index++);
    }
}
