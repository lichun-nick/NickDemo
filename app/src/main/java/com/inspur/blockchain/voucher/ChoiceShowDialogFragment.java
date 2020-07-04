package com.inspur.blockchain.voucher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.DigitalIdentityQrCodeActivity;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.lib_base.base.BaseDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author lichun
 */
public class ChoiceShowDialogFragment extends BaseDialogFragment {

    private AppCompatCheckBox cbTotalProperties;
    private AppCompatCheckBox cbSelectiveDisclosure;
    private AppCompatCheckBox cbShowName;
    private AppCompatCheckBox cbShowBirthday;
    private AppCompatCheckBox cbShowNation;
    private AppCompatCheckBox cbShowLocation;
    /**
     * 凭证详情json,全部
     */
    private JSONObject jsonInfo;
    private JSONObject result;
    private JSONObject tags;

    private String mDID;

    private VoucherDetailViewModel voucherDetailViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        voucherDetailViewModel = new ViewModelProvider(this).get(VoucherDetailViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_choice_show;
    }

    @Override
    public void initView(View view) {
        cbTotalProperties = view.findViewById(R.id.cb_total_properties);
        cbTotalProperties.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbSelectiveDisclosure.setChecked(!isChecked);
                if(isChecked){
                    setCheckBoxStatus(true);
                }
            }
        });
        cbSelectiveDisclosure = view.findViewById(R.id.cb_selective_disclosure);
        cbSelectiveDisclosure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbTotalProperties.setChecked(!isChecked);
            }
        });
        cbShowName = view.findViewById(R.id.cb_show_name);
        cbShowName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTypeCheckBoxStatus();
                constructContent("name",isChecked);
            }
        });
        cbShowBirthday = view.findViewById(R.id.cb_show_birthday);
        cbShowBirthday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTypeCheckBoxStatus();
                constructContent("birthday",isChecked);
            }
        });
        cbShowNation = view.findViewById(R.id.cb_show_nation);
        cbShowNation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTypeCheckBoxStatus();
                constructContent("nation",isChecked);
            }
        });
        cbShowLocation = view.findViewById(R.id.cb_show_address);
        cbShowLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTypeCheckBoxStatus();
                constructContent("location",isChecked);
            }
        });

        Button btnPreview = view.findViewById(R.id.btn_choice_preview);
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoucherPreviewDialogFragment fragment = new VoucherPreviewDialogFragment();
                Bundle bundle = new Bundle();
                JSONObject object = new JSONObject();
                try{
                    object.put("data",result);
                    object.put("tag",tags);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                bundle.putString(Keys.DID,mDID);
                bundle.putString(Keys.USER_VOUCHER_INFO,object.toString());
                fragment.setArguments(bundle);
                fragment.show(getParentFragmentManager(),"voucherPreview");
                dismiss();
            }
        });
        Button btnConfirm = view.findViewById(R.id.btn_choice_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliverData();
            }
        });

        getData();
    }

    /**
     * 组装披露内容
     * @param name
     * @param isChecked
     */
    private void constructContent(String name, boolean isChecked) {
        try{
            String key = "";
            if(TextUtils.equals(name,"name")){
                key = Keys.NAME;
            }else if(TextUtils.equals(name,"birthday")){
                key = Keys.BIRTHDAY;
            }else if(TextUtils.equals(name,"nation")){
                key = Keys.NATION;
            }else if(TextUtils.equals(name,"location")){
                key = Keys.ADDRESS;
            }
            handleMap(key,isChecked);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void handleMap(String key,boolean isChecked) throws JSONException{
        if(result == null){
            result = new JSONObject();
        }
        if(tags == null){
            tags = new JSONObject();
        }
        if(isChecked){
            result.put(key, Objects.requireNonNull(jsonInfo.optJSONObject("data")).optString(key));
            tags.put(key, Objects.requireNonNull(jsonInfo.optJSONObject("tag")).optString(key));
        }else{
            result.remove(key);
            tags.remove(key);
        }
    }

    private void getData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            try {
                mDID = bundle.getString(Keys.DID);
                jsonInfo = new JSONObject(Objects.requireNonNull(bundle.getString(Keys.USER_VOUCHER_INFO)));
                handleMap(Keys.NAME,true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置选择框的状态
     * @param isChecked
     */
    private void setCheckBoxStatus(boolean isChecked){
        cbShowName.setChecked(isChecked);
        cbShowBirthday.setChecked(isChecked);
        cbShowNation.setChecked(isChecked);
        cbShowLocation.setChecked(isChecked);
    }

    private void changeTypeCheckBoxStatus(){
        if(cbShowName.isChecked() && cbShowBirthday.isChecked() && cbShowNation.isChecked() && cbShowLocation.isChecked()){
            cbTotalProperties.setChecked(true);
        }else{
            cbTotalProperties.setChecked(false);
        }
    }

    private void deliverData() {
        showProgressLoading();
        JSONObject object = new JSONObject();
        Iterator<String> it = tags.keys();
        while (it.hasNext()){
            try {
                object.put(tags.getString(it.next()),"1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        voucherDetailViewModel.generateUrlByDID(mDID,object).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject s) {
                hideProgressLoading();
                Intent intent = new Intent(requireContext(), DigitalIdentityQrCodeActivity.class);
                if(s != null){
                    intent.putExtra(Keys.QR_CODE_PATH,s.optString(Keys.QR_CODE_PATH));
                    intent.putExtra(Keys.QR_CODE_AUTH,s.optString(Keys.QR_CODE_AUTH));
                }
                startActivity(intent);
                dismiss();
                if(getActivity() != null){
                    getActivity().finish();
                }
            }
        });
    }


}
