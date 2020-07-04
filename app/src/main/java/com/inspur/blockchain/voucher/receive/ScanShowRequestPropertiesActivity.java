package com.inspur.blockchain.voucher.receive;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.ScanShowVoucherDetailViewModel;
import com.inspur.blockchain.Translate;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.util.ToastUtil;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author lichun
 * 扫描二维码结果展示页
 */
public class ScanShowRequestPropertiesActivity extends BaseActivity {

    private TextView tvWho;
    private TextView tvType;
    private TextView tvProperties;

    private String vpId;
    private JSONObject disclosure;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_show_request_properties;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_scan_show_result);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        tvWho = findViewById(R.id.tv_scan_show_who_request_properties);
        View typeParent = findViewById(R.id.include_request_voucher_type);
        TextView tvTitleType = typeParent.findViewById(R.id.tv_request_voucher_detail_item_title);
        tvTitleType.setText(getResources().getString(R.string.voucher_type));
        tvType = typeParent.findViewById(R.id.tv_request_voucher_detail_item_content);
        View propertiesParent = findViewById(R.id.include_request_voucher_properties);
        TextView tvTitleProperty = propertiesParent.findViewById(R.id.tv_request_voucher_detail_item_title);
        tvTitleProperty.setText(getResources().getString(R.string.verify_property));
        tvProperties = propertiesParent.findViewById(R.id.tv_request_voucher_detail_item_content);
        Button btnConfirm = findViewById(R.id.btn_scan_request_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informServer();
            }
        });
    }

    private void informServer() {
        ScanShowRequestPropertiesViewModel scanShowRequestPropertiesViewModel = new ViewModelProvider(this).get(ScanShowRequestPropertiesViewModel.class);
        scanShowRequestPropertiesViewModel.confirmRequest(vpId,disclosure).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject object) {
                if(object.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    ToastUtil.show(ScanShowRequestPropertiesActivity.this,"同意查看");
                    finish();
                }else{
                    ToastUtil.show(ScanShowRequestPropertiesActivity.this,object.optString(HttpResponse.RESPONSE_MESSAGE));
                }
            }
        });
    }

    @Override
    protected void initData() {
        ScanShowRequestPropertiesViewModel scanShowRequestPropertiesViewModel = new ViewModelProvider(this).get(ScanShowRequestPropertiesViewModel.class);
        String qrData = getIntent().getStringExtra(Keys.QR_CODE_RESULT);
        if(!TextUtils.isEmpty(qrData)){
            try {
                JSONObject object = new JSONObject(qrData);
                tvWho.setText(object.getString("valider") + "请求验证您的凭证");
                tvType.setText(object.getString("cpt_name"));
                String selectedProperties = object.getString("valid_props");
                String[] properties = selectedProperties.split(",");
                String result = "";
                if(disclosure == null){
                    disclosure = new JSONObject();
                }
                for (String property:properties) {
                    result += Translate.getChineseName(property);
                    result += ";";
                    disclosure.put(property,"1");
                }

                tvProperties.setText(result.substring(0,result.length()-1));
                vpId = object.getString("vp_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
