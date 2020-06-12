package com.inspur.blockchain.voucher;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author lichun
 * 凭证详情页
 */
public class VoucherDetailActivity extends BaseActivity {

    private LinearLayout voucherDetailParent;
    private JSONObject mVoucherDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voucher_detail;
    }

    @Override
    protected int wrapLayoutId() {
        return R.id.cl_voucher_detail_parent;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_voucher_detail);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        voucherDetailParent = findViewById(R.id.ll_voucher_detail);
        Button btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        });
    }

    private void showChoiceDialog() {
        ChoiceShowDialogFragment fragment = new ChoiceShowDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Keys.DID, Objects.requireNonNull(mVoucherDetail.optJSONObject("data")).optString(Keys.VOUCHER_DID));
        bundle.putString(Keys.USER_VOUCHER_INFO,mVoucherDetail.toString());
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(),"choiceShow");
    }

    @Override
    protected void initData() {
        VoucherDetailViewModel voucherDetailViewModel = new ViewModelProvider(this).get(VoucherDetailViewModel.class);
        String did = getIntent().getStringExtra(Keys.DID);
        if(!TextUtils.isEmpty(did)){
            showProgressLoading();
            voucherDetailViewModel.requestData(did).observe(this, new Observer<JSONObject>() {
                @Override
                public void onChanged(JSONObject jsonObject) {
                    if(jsonObject != null){
                        mVoucherDetail = jsonObject;
                        jsonObject = jsonObject.optJSONObject("data");
                        Iterator<String> it = Objects.requireNonNull(jsonObject).keys();
                        while (it.hasNext()){
                            String key = it.next();
                            addContent(key,jsonObject.optString(key));
                        }
                    }
                    hideProgressLoading();
                }
            });
        }

    }

    /**
     * 添加凭证信息到页面展示
     * @param field
     * @param val
     */
    private void addContent(String field, String val) {
        View child = LayoutInflater.from(voucherDetailParent.getContext()).inflate(R.layout.list_item_voucher_detail,voucherDetailParent,false);
        TextView tvTitle = child.findViewById(R.id.tv_voucher_detail_item_title);
        TextView tvContent = child.findViewById(R.id.tv_voucher_detail_item_content);
        tvTitle.setText(field);
        tvContent.setText(val);
        voucherDetailParent.addView(child);
    }
}
