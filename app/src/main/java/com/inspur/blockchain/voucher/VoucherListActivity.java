package com.inspur.blockchain.voucher;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.model.VoucherListBean;
import com.inspur.blockchain.model.VoucherListItemBean;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichun
 * 凭证列表页
 */
public class VoucherListActivity extends BaseActivity {

    private TextView tvVoucherCount;
    private VoucherListAdapter mAdapter;
    private List<VoucherListItemBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voucher_list;
    }

    @Override
    protected int wrapLayoutId() {
        return R.id.fl_voucher_status;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_voucher_list);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        tvVoucherCount = findViewById(R.id.tv_voucher_count);
        ImageView ivRequestVoucher = findViewById(R.id.iv_request_voucher);
        ivRequestVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RequestVoucherActivity.class);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv_voucher_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new VoucherListAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new VoucherListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v,int position) {
                Intent intent = new Intent(VoucherListActivity.this,VoucherDetailActivity.class);
                intent.putExtra(Keys.DID,mList.get(position).getDid());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        VoucherListViewModel viewModel = new ViewModelProvider(this).get(VoucherListViewModel.class);
        showProgressLoading();
        viewModel.requestData().observe(this, new Observer<VoucherListBean>() {
            @Override
            public void onChanged(VoucherListBean voucherListBean) {
                if(voucherListBean == null || voucherListBean.getList()==null || voucherListBean.getList().size()==0){
                    showEmpty();
                }else{
                    tvVoucherCount.setText("共"+voucherListBean.getTotal()+"个");
                    mAdapter.refreshView(voucherListBean.getList());
                    mList.clear();
                    mList.addAll(voucherListBean.getList());
                    showContent();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList = null;
    }
}
