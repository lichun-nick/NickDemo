package com.inspur.blockchain.main;


import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.ScanActivity;
import com.inspur.blockchain.Translate;
import com.inspur.blockchain.model.MainInfoBean;
import com.inspur.blockchain.model.VoucherTypeListBean;
import com.inspur.blockchain.voucher.HistoryListActivity;
import com.inspur.blockchain.voucher.RequestVoucherViewModel;
import com.inspur.blockchain.voucher.VerifyRecordDetailActivity;
import com.inspur.blockchain.voucher.VoucherDetailActivity;
import com.inspur.blockchain.voucher.receive.ReceiveVoucherSettingsActivity;
import com.inspur.blockchain.voucher.VoucherListActivity;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.mmkv.MmkvUtil;
import com.inspur.lib_base.view.StateLayoutManager;

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
    private LinearLayout llMainContent;

    private StateLayoutManager stateLayoutManager;

    private String mVerifyMaxDid;
    private String mRecentDid;
    private String mRecentTime;

    private MainViewModel mainViewModel;

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
                startActivity(ReceiveVoucherSettingsActivity.class);
            }
        });
        findViewById(R.id.fl_home_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VoucherListActivity.class);
            }
        });
        findViewById(R.id.view_voucher_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VoucherListActivity.class);
            }
        });
        findViewById(R.id.view_verify_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HistoryListActivity.class);
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

        llMainContent = findViewById(R.id.ll_main_show);

        findViewById(R.id.tv_see_verify_max_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VoucherDetailActivity.class);
                intent.putExtra(Keys.DID,mVerifyMaxDid);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_see_verify_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VerifyRecordDetailActivity.class);
                intent.putExtra(Keys.DID,mRecentDid);
                intent.putExtra(Keys.VERIFY_TIME,mRecentTime);
                startActivity(intent);
            }
        });

        StateLayoutManager.Builder stateBuilder = new StateLayoutManager.Builder(this);
        stateBuilder.emptyView(R.layout._loading_layout_empty);
        stateBuilder.loadingView(R.layout._loading_layout_loading);
        stateLayoutManager = stateBuilder.build();
        llMainContent.addView(stateLayoutManager.getStateLayout());
    }

    @Override
    protected void initData() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        RequestVoucherViewModel requestVoucherViewModel = new ViewModelProvider(this).get(RequestVoucherViewModel.class);
        requestVoucherViewModel.queryVoucherTypes().observe(this, new Observer<VoucherTypeListBean>() {
            @Override
            public void onChanged(VoucherTypeListBean voucherTypeListBean) {
                MmkvUtil.getInstance().putString(Keys.ID_CARD_CPT_ID,voucherTypeListBean.getIssuer_list().get(0).getCPT_ID());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                    stateLayoutManager.showEmptyData();
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
                        tvVoucherMaxDid.setText(mVerifyMaxDid = validBean.getDid());
                        SpannableString ss = new SpannableString("被验证"+validBean.getValid_max()+"次");
                        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(2f);
                        ss.setSpan(relativeSizeSpan,3,ss.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tvVoucherMaxCount.setText(ss);
                        MainInfoBean.RecentBean recentBean = mainInfoBean.getRecent();
                        mRecentDid = recentBean.getDid();
                        mRecentTime = recentBean.getTrace_time();
                        String props = recentBean.getVerifier_props();
                        if(props.contains("{")){
                            props = props.replace("{","");
                        }
                        if(props.contains("}")){
                            props = props.replace("}","");
                        }
                        String[] prop = props.split(",");
                        StringBuilder sb = new StringBuilder();
                        for(String item:prop){
                            sb.append(Translate.getChineseName(item)).append(",");
                        }
                        Log.i(TAG, "onChanged: " + sb.toString());
                        tvVerifyDetail.setText(recentBean.getNick_name() +"验证了您的"+ sb.toString().substring(0,sb.length()-2));
                        tvVerifyTime.setText(recentBean.getTrace_time());
                        tvVerifyHash.setText(recentBean.getTx_hash());
                    }
                }
                hideProgressLoading();

            }
        });
    }
}
