package com.inspur.blockchain.voucher;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.Translate;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author lichun
 * 凭证验证记录详情页
 */
public class VerifyRecordDetailActivity extends BaseActivity {

    private LinearLayout voucherDetailParent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_record_detail;
    }

    @Override
    protected int wrapLayoutId() {
        return R.id.cl_voucher_detail_parent;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_verify_record_detail);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        voucherDetailParent = findViewById(R.id.ll_verify_record_detail);

    }


    @Override
    protected void initData() {
        VerifyRecordDetailViewModel verifyRecordDetailViewModel = new ViewModelProvider(this).get(VerifyRecordDetailViewModel.class);
        String did = getIntent().getStringExtra(Keys.DID);
        String time = getIntent().getStringExtra(Keys.VERIFY_TIME);
        if(!TextUtils.isEmpty(did)){
            showProgressLoading();

            verifyRecordDetailViewModel.requestRecordDetail(did,time).observe(this, new Observer<JSONObject>() {
                @Override
                public void onChanged(JSONObject jsonObject) {
                    if(jsonObject != null){
                        Iterator<String> it = jsonObject.keys();
                        while (it.hasNext()){
                            String key = it.next();
                            String value = jsonObject.optString(key);
                            if(TextUtils.equals(key,"trace_time")){
                                addContent("上链时间",value,true);
                            }else if(TextUtils.equals(key,"nick_name")){
                                addContent("验证方",value,true);
                            }else if(TextUtils.equals(key,"tx_hash")){
                                value = value.substring(0,4)+"****"+value.substring(value.length()-4);
                                addContent("交易哈希",value.toUpperCase(),true);
                            }else if(TextUtils.equals(key,"verifier_props")){
                                value = value.substring(1,key.length()-1);
                                String[] properties = value.split(",");
                                StringBuilder content= new StringBuilder();
                                for (String item:properties) {
                                    content.append(Translate.getChineseName(item));
                                    content.append(",");
                                }
                                addContent("验证属性",content.substring(0,content.length()-1),true);
                            }else if(TextUtils.equals(key,"valid_flag")){
                                addContent("验证结果",TextUtils.equals("1",value)?"成功":"失败",false);
                            }

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
    private void addContent(String field, String val,boolean addLine) {
        View child = LayoutInflater.from(voucherDetailParent.getContext()).inflate(R.layout.list_item_verify_record_detail,voucherDetailParent,false);
        TextView tvTitle = child.findViewById(R.id.tv_record_detail_item_title);
        TextView tvContent = child.findViewById(R.id.tv_record_detail_item_content);
        tvTitle.setText(field);
        tvContent.setText(val);
        voucherDetailParent.addView(child);
        if(addLine){
            View line = new View(voucherDetailParent.getContext());
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,4);
            mParams.leftMargin = 16;
            mParams.rightMargin = 16;
            line.setLayoutParams(mParams);
            line.setBackgroundResource(R.drawable.shape_line);
            voucherDetailParent.addView(line);
        }else{
            tvContent.setTextColor(Color.parseColor("#2EC469"));
        }
    }
}
