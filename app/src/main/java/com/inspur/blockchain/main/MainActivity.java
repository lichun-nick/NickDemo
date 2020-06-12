package com.inspur.blockchain.main;


import android.content.Intent;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.DigitalIdentityQrCodeActivity;
import com.inspur.blockchain.R;
import com.inspur.blockchain.ScanActivity;
import com.inspur.blockchain.Translate;
import com.inspur.blockchain.model.MainInfoBean;
import com.inspur.blockchain.voucher.VoucherListActivity;
import com.inspur.lib_base.base.BaseActivity;

/**
 * @author lichun
 * 主页面
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private TextView tvVoucherCount;
    private TextView tvVerifyCount;
    private LinearLayout llMaxVoucher;
    private TextView tvVoucherMaxDid;
    private TextView tvVoucherMaxCount;
    private TextView tvVerifyDetail;
    private TextView tvVerifyTime;
    private TextView tvVerifyHash;
    private LinearLayout llVerifyRecorder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        findViewById(R.id.fl_home_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ScanActivity.class);
            }
        });
        findViewById(R.id.fl_home_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DigitalIdentityQrCodeActivity.class);
            }
        });
        findViewById(R.id.fl_home_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VoucherListActivity.class);
            }
        });

        tvVerifyCount = findViewById(R.id.tv_item_verify_count);
        tvVoucherCount = findViewById(R.id.tv_item_voucher_count);
        llMaxVoucher = findViewById(R.id.ll_home_verify_max);
        llVerifyRecorder = findViewById(R.id.ll_home_verify_record);

        tvVoucherMaxDid = findViewById(R.id.tv_home_verify_max_did);
        tvVoucherMaxCount = findViewById(R.id.tv_home_verify_did_max_count);
        tvVerifyDetail = findViewById(R.id.tv_home_verify_record_detail);
        tvVerifyTime = findViewById(R.id.tv_home_verify_record_time);
        tvVerifyHash = findViewById(R.id.tv_home_verify_record_hash);

    }

    @Override
    protected void initData() {
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        showProgressLoading();
        mainViewModel.getData().observe(this, new Observer<MainInfoBean>() {
            @Override
            public void onChanged(MainInfoBean mainInfoBean) {
                MainInfoBean.CountBean countBean = mainInfoBean.getCount();
                if(countBean.getDid_counts() <= 0){
                    tvVerifyCount.setText("0");
                    tvVoucherCount.setText("0");
                    llMaxVoucher.setVisibility(View.GONE);
                    llVerifyRecorder.setVisibility(View.GONE);
                }else{
                    tvVoucherCount.setText(String.valueOf(countBean.getDid_counts()));
                    tvVerifyCount.setText(String.valueOf(countBean.getValid_dids()));
                    int validCount = countBean.getValid_dids();
                    if(validCount <= 0){
                        llMaxVoucher.setVisibility(View.GONE);
                        llVerifyRecorder.setVisibility(View.GONE);
                    }else{
                        llMaxVoucher.setVisibility(View.VISIBLE);
                        llVerifyRecorder.setVisibility(View.VISIBLE);
                        MainInfoBean.ValidBean validBean = mainInfoBean.getValid();
                        tvVoucherMaxDid.setText(validBean.getDid());
                        SpannableString ss = new SpannableString("被验证"+validBean.getValid_max()+"次");
                        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(2f);
                        ss.setSpan(relativeSizeSpan,3,ss.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tvVoucherMaxCount.setText(ss);
                        MainInfoBean.RecentBean recentBean = mainInfoBean.getRecent();
                        String props = recentBean.getVerifier_props();
                        Log.i(TAG, "onChanged: " + props);
                        tvVerifyDetail.setText(Translate.getChineseName(recentBean.getNick_name()) +"验证了您的"+ props);
                        tvVerifyTime.setText(recentBean.getTrace_time());
                        tvVerifyHash.setText(recentBean.getTx_hash());
                    }
                }
                hideProgressLoading();
            }
        });

    }


}
