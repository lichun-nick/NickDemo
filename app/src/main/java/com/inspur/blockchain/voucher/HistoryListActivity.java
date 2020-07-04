package com.inspur.blockchain.voucher;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.model.VerifyHistoryItemBean;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.StateLayoutManager;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lichun
 * 验证记录列表
 */
public class HistoryListActivity extends BaseActivity {

    private StateLayoutManager stateLayoutManager;
    private HistoryListAdapter mAdapter;
    private List<VerifyHistoryItemBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_list;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_history_list);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv_history_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,RecyclerView.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.shape_transparent_narrow_divider)));
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter = new HistoryListAdapter(this,mList = new ArrayList<>());
        mAdapter.setOnItemClickListener(new HistoryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(HistoryListActivity.this,VerifyRecordDetailActivity.class);
                intent.putExtra(Keys.DID,mList.get(position).getDid());
                intent.putExtra(Keys.VERIFY_TIME,mList.get(position).getTrace_time());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(mAdapter);

        stateLayoutManager = new StateLayoutManager.Builder(this).emptyView(R.layout._loading_layout_empty).build();

    }

    @Override
    protected void initData() {
        VerifyHistoryListViewModel verifyHistoryListViewModel = new ViewModelProvider(this).get(VerifyHistoryListViewModel.class);
        verifyHistoryListViewModel.requestHistoryList(new ArrayMap<String, String>()).observe(this, new Observer<List<VerifyHistoryItemBean>>() {
            @Override
            public void onChanged(List<VerifyHistoryItemBean> verifyHistoryItemBeans) {
                mList.clear();
                if(verifyHistoryItemBeans != null && verifyHistoryItemBeans.size() > 0){
                    mList.addAll(verifyHistoryItemBeans);
                    mAdapter.notifyDataSetChanged();
                }else{
                    stateLayoutManager.showEmptyData();
                }
            }
        });

    }
}