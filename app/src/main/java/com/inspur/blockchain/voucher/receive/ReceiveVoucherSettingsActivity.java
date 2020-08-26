package com.inspur.blockchain.voucher.receive;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.Translate;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.FlexibleCheckBox;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONObject;

import java.util.LinkedList;

/**
 * @author lichun
 * 接收凭证属性设置页面
 */
public class ReceiveVoucherSettingsActivity extends BaseActivity {

    private static final String TAG = "ReceiveVoucherSettings";
    private FlexibleCheckBox cbIdCard;
    private FlexibleCheckBox cbDriveCard;
    private FlexibleCheckBox cbTotalProperty;
    private FlexibleCheckBox cbSelectProperty;
    private FlexibleCheckBox cbName;
    private FlexibleCheckBox cbBirthday;
    private FlexibleCheckBox cbNation;
    private FlexibleCheckBox cbAddress;

    private String propertyShow="";
    private ReceiveVoucherSettingsViewModel receiveVoucherSettingsViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_voucher_settings;
    }

    @Override
    protected void initStateView() {

    }


    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_set_receive_voucher_properties);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        cbIdCard = findViewById(R.id.cb_voucher_id_card);
        cbDriveCard = findViewById(R.id.cb_voucher_drive_card);
        cbTotalProperty = findViewById(R.id.cb_voucher_total_types);
        cbTotalProperty.setOnCheckListener(new FlexibleCheckBox.OnCheckListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                cbSelectProperty.setChecked(!isChecked);
            }
        });

        cbSelectProperty = findViewById(R.id.cb_voucher_selected_properties);
        cbSelectProperty.setOnCheckListener(new FlexibleCheckBox.OnCheckListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                cbTotalProperty.setChecked(!isChecked);
            }
        });
        cbName = findViewById(R.id.cb_user_name);
        cbName.setOnCheckListener(new FlexibleCheckBox.OnCheckListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                changeDataByChecked(cbName.getText(),isChecked);
            }
        });
        cbBirthday = findViewById(R.id.cb_user_birthday);
        cbBirthday.setOnCheckListener(new FlexibleCheckBox.OnCheckListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                changeDataByChecked(cbBirthday.getText(),isChecked);
            }
        });

        cbNation = findViewById(R.id.cb_user_nation);
        cbNation.setOnCheckListener(new FlexibleCheckBox.OnCheckListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                changeDataByChecked(cbNation.getText(),isChecked);
            }
        });

        cbAddress = findViewById(R.id.cb_user_location);
        cbAddress.setOnCheckListener(new FlexibleCheckBox.OnCheckListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                changeDataByChecked(cbAddress.getText(),isChecked);
            }
        });

        Button confirm = findViewById(R.id.btn_request);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProperty();
            }
        });

    }

    private void setProperty() {
        showProgressLoading();
        receiveVoucherSettingsViewModel.requestSettings("身份证",propertyShow.substring(0,propertyShow.length()-1)).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject object) {
                hideProgressLoading();
                if(object.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    JSONObject data = object.optJSONObject(HttpResponse.RESPONSE_DATA);
                    if(data != null){
                        Intent intent = new Intent(ReceiveVoucherSettingsActivity.this,ReceiveVoucherQrCodeActivity.class);
                        intent.putExtra(Keys.QR_CODE_PATH,data.optString("code_string"));
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        receiveVoucherSettingsViewModel = new ViewModelProvider(this).get(ReceiveVoucherSettingsViewModel.class);

        propertyShow += (Translate.getEnglishName(cbName.getText())+",");
        propertyShow += (Translate.getEnglishName(cbBirthday.getText())+",");
        propertyShow += (Translate.getEnglishName(cbNation.getText())+",");
        propertyShow += (Translate.getEnglishName(cbAddress.getText())+",");
    }

    private void setChecked(boolean isChecked){
        cbName.setChecked(isChecked);
        cbBirthday.setChecked(isChecked);
        cbNation.setChecked(isChecked);
        cbAddress.setChecked(isChecked);
    }

    private void changeTypeCheckBoxStatus(){
        if(cbName.isChecked() && cbBirthday.isChecked() && cbNation.isChecked() && cbAddress.isChecked()){
            cbTotalProperty.setChecked(true);
            cbSelectProperty.setChecked(false);
        }else{
            cbTotalProperty.setChecked(false);
            cbSelectProperty.setChecked(true);
        }
    }

    private void changeDataByChecked(String name,boolean isChecked){
        String flag = Translate.getEnglishName(name);
        try{
            if(isChecked){
                propertyShow += (flag+",");
            }else{
                propertyShow = propertyShow.replace(flag+",","");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        changeTypeCheckBoxStatus();
    }



}
