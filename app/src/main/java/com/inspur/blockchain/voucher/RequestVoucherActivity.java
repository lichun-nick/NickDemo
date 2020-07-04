package com.inspur.blockchain.voucher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.collection.ArrayMap;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.model.VoucherTypeListBean;
import com.inspur.blockchain.recognize.FaceRecognizeActivity;
import com.inspur.icity.comp_seal.IDS;
import com.inspur.icity.comp_seal.IDataSeal;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.mmkv.MmkvUtil;
import com.inspur.lib_base.view.FlexibleCheckBox;
import com.inspur.lib_base.view.TitleView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author lichun
 * 申请凭证页面
 */
public class RequestVoucherActivity extends BaseActivity {

    private FlexibleCheckBox cbIdCard;
    private FlexibleCheckBox cbDriveCard;
    private AppCompatEditText etName;
    private TextView tvSex;
    private TextView tvBirthday;
    private AppCompatEditText etNation;
    private AppCompatEditText etLocation;
    private AppCompatEditText etIdNum;
    private AppCompatEditText etDescription;
    private Button btnRequest;
    private TimePickerView timePickerView;
    private OptionsPickerView<String> mOptionsPickView;

    private String cptId;
    private String mBirthday;
    private String mSex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_request_voucher;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_request_voucher);
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

        View nameParent = findViewById(R.id.include_user_name);
        TextView nameTip = nameParent.findViewById(R.id.tv_item_input_title);
        nameTip.setText(getResources().getString(R.string.name));
        etName = nameParent.findViewById(R.id.et_item_input_content);
        etName.setHint(getResources().getString(R.string.please_input_your_name));

        View sexParent = findViewById(R.id.include_user_sex);
        TextView sexTip = sexParent.findViewById(R.id.tv_item_input_title);
        sexTip.setText(getResources().getString(R.string.sex));
        tvSex = sexParent.findViewById(R.id.et_item_input_content);
        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOptionsPickView == null){
                    mOptionsPickView = new OptionsPickerBuilder(RequestVoucherActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            tvSex.setText(options1==0?"男":"女");
                        }
                    }).build();
                    List<String> data = new ArrayList<>();
                    data.add("男");
                    data.add("女");
                    mOptionsPickView.setPicker(data);
                }
                mOptionsPickView.show();
                hideSoftInput(RequestVoucherActivity.this);

            }
        });

        View birthdayParent = findViewById(R.id.include_user_birthday);
        TextView birthdayTip = birthdayParent.findViewById(R.id.tv_item_input_title);
        birthdayTip.setText(getResources().getString(R.string.birthday));
        tvBirthday = birthdayParent.findViewById(R.id.et_item_input_content);
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePickerView == null){
                    timePickerView = new TimePickerBuilder(RequestVoucherActivity.this, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                            tvBirthday.setText(sdf.format(date));
                        }
                    }).build();
                }
                timePickerView.show();
                hideSoftInput(RequestVoucherActivity.this);
            }
        });

        View nationParent = findViewById(R.id.include_user_nation);
        TextView nationTip = nationParent.findViewById(R.id.tv_item_input_title);
        nationTip.setText(getResources().getString(R.string.nation));
        etNation = nationParent.findViewById(R.id.et_item_input_content);
        etNation.setHint(getResources().getString(R.string.please_input_your_nation));

        View locationParent = findViewById(R.id.include_user_location);
        TextView locationTip = locationParent.findViewById(R.id.tv_item_input_title);
        locationTip.setText(getResources().getString(R.string.location));
        etLocation = locationParent.findViewById(R.id.et_item_input_content);
        etLocation.setHint(getResources().getString(R.string.please_input_your_location));

        View idNumParent = findViewById(R.id.include_user_id_card_num);
        TextView idNumTip = idNumParent.findViewById(R.id.tv_item_input_title);
        idNumTip.setText(getResources().getString(R.string.id_card_num));
        etIdNum = idNumParent.findViewById(R.id.et_item_input_content);
        etIdNum.setHint(getResources().getString(R.string.please_input_your_id_number));

        View voucherDescriptionParent = findViewById(R.id.include_user_voucher_description);
        TextView descriptionTip = voucherDescriptionParent.findViewById(R.id.tv_item_input_title);
        descriptionTip.setText(getResources().getString(R.string.voucher_description));
        etDescription = voucherDescriptionParent.findViewById(R.id.et_item_input_content);
        etDescription.setHint(getResources().getString(R.string.please_input_voucher_description));

        btnRequest = findViewById(R.id.btn_request);
        btnRequest.setEnabled(false);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestVoucher();
            }
        });

    }

    private void requestVoucher() {
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("cpt_id",cptId);
        params.put("name",etName.getText().toString().trim());
        params.put("sex",tvSex.getText().toString().trim());
        params.put("dob",tvBirthday.getText().toString().trim());
        params.put("nation",etNation.getText().toString().trim());
        params.put("addr",etLocation.getText().toString().trim());
        params.put("id_card_no",etIdNum.getText().toString().trim());
        params.put("desc",etDescription.getText().toString().trim());

        Bundle bundle = new Bundle();
        for (String key:params.keySet()) {
            bundle.putString(key,params.get(key));
        }
        Intent intent = new Intent(RequestVoucherActivity.this,FaceRecognizeActivity.class);
        intent.putExtra(Keys.VOUCHER_INFO,bundle);
        startActivity(intent);
//        IDS.getInstance(this).openSealWithWay("hahah","blockchain",IDataSeal.FACE_VERIFY,null);
        finish();
    }

    @Override
    protected void initData() {
        RequestVoucherViewModel requestVoucherViewModel = new ViewModelProvider(this).get(RequestVoucherViewModel.class);
        requestVoucherViewModel.queryVoucherTypes().observe(this, new Observer<VoucherTypeListBean>() {
            @Override
            public void onChanged(VoucherTypeListBean voucherTypeListBean) {
                cptId = voucherTypeListBean.getIssuer_list().get(0).getCPT_ID();
                Log.i("voucherTypes", "onChanged: " + cptId);
                MmkvUtil.getInstance().putString(Keys.ID_CARD_CPT_ID,cptId);
                btnRequest.setBackgroundResource(R.drawable.shape_circle_blue_bg);
                btnRequest.setEnabled(true);
            }
        });
    }
}
