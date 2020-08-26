package com.inspur.blockchain.voucher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.model.VoucherListBean;
import com.inspur.blockchain.model.VoucherListItemBean;
import com.inspur.icity.comp_seal.IDS;
import com.inspur.icity.comp_seal.StartServerListener;
import com.inspur.icity.comp_seal.util.DisplayUtil;
import com.inspur.lib_base.LiveDataBus;
import com.inspur.lib_base.base.BaseStateActivity;
import com.inspur.lib_base.util.ToastUtil;
import com.inspur.lib_base.view.TitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lichun
 * 凭证列表页
 */
public class VoucherListActivity extends BaseStateActivity {

    private static final String TAG = "VoucherListActivity";
    private TextView tvVoucherCount;
    private VoucherListAdapter mAdapter;
    private List<VoucherListItemBean> mList;
    private VoucherListViewModel viewModel;
    private PopupWindow mPopupWindow;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_voucher_list;
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.shape_transparent_divider)));
        recyclerView.addItemDecoration(dividerItemDecoration);
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

        mAdapter.setOnItemMoreClickListener(new VoucherListAdapter.OnItemMoreClickListener() {
            @Override
            public void onMoreClick(View v, int position) {
                showPopupWindow(v,position);
            }
        });


    }

    private void showPopupWindow(View v, final int position) {
        if(mPopupWindow == null){
            mPopupWindow = new PopupWindow(v.getContext());
            mPopupWindow.setContentView(View.inflate(v.getContext(),R.layout.layout_menu_voucher,null));
            mPopupWindow.setHeight(DisplayUtil.dip2px(this,47));
            mPopupWindow.setWidth(DisplayUtil.dip2px(this,100));
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setTouchable(true);
        }

        mPopupWindow.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialogFragment fragment = new ConfirmDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Keys.DID,mList.get(position).getDid());
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(),"confirm");
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.showAsDropDown(v,0,-20);
    }

    @Override
    protected void initData() {
        viewModel = new ViewModelProvider(this).get(VoucherListViewModel.class);
        LiveDataBus.get().with("del_voucher").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof Boolean){
                    if((boolean) o){
                        refreshData();
                    }
                }
            }
        });
        LiveDataBus.get().with("voucher_insert").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof Boolean){
                    if((boolean)o){
                        refreshData();
                    }
                }
            }
        });
        refreshData();
    }

    private void refreshData(){
        showProgressLoading();
        viewModel.requestData().observe(VoucherListActivity.this, new Observer<VoucherListBean>() {
            @Override
            public void onChanged(VoucherListBean voucherListBean) {
                if(voucherListBean == null || voucherListBean.getList()==null || voucherListBean.getList().size()==0){
                    tvVoucherCount.setText("共0个");
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                    showEmpty();
                }else{
                    tvVoucherCount.setText("共"+voucherListBean.getTotal()+"个");
                    mList.clear();
                    mList.addAll(voucherListBean.getList());
                    mAdapter.notifyDataSetChanged();
                    hideProgressLoading();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");

    }

    @Override
    protected int wrapLayoutId() {
        return R.id.fl_voucher_status;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList = null;
        Log.i(TAG, "onDestroy: ");
    }
}
