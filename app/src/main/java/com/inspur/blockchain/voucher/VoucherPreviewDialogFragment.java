package com.inspur.blockchain.voucher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.DigitalIdentityQrCodeActivity;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.lib_base.base.BaseDialogFragment;
import com.inspur.lib_base.view.LoadingLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author lichun
 */
public class VoucherPreviewDialogFragment extends BaseDialogFragment {

    private LoadingLayout mLoadingLayout;
    private LinearLayout llContent;
    private JSONObject jsonInfo;
    private String mDID;

    private VoucherDetailViewModel voucherDetailViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        voucherDetailViewModel = new ViewModelProvider(this).get(VoucherDetailViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_voucher_preview;
    }

    @Override
    public void initView(View view) {

        llContent = view.findViewById(R.id.ll_preview_property_detail);

        Button btnCancel = view.findViewById(R.id.btn_preview_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button btnConfirm = view.findViewById(R.id.btn_preview_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliverData();
            }
        });

        mLoadingLayout = LoadingLayout.wrap(view.findViewById(R.id.loading_view));
        mLoadingLayout.setBackgroundResource(R.drawable.shape_circle_translucent_black_bg);
        mLoadingLayout.setLoading(R.layout._loading_layout_small_loading);
        mLoadingLayout.showContent();
        /**
         * 获取数据
         */
        getData();
    }

    private void deliverData() {
        showLoading();
        JSONObject object = new JSONObject();
        JSONObject tags = jsonInfo.optJSONObject("tag");
        Iterator<String> it = Objects.requireNonNull(tags).keys();
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
                hideLoading();
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

    private void getData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            try {
                mDID = bundle.getString(Keys.DID);
                jsonInfo = new JSONObject(Objects.requireNonNull(bundle.getString(Keys.USER_VOUCHER_INFO)));
                JSONObject result = jsonInfo.optJSONObject("data");
                Iterator<String> it = Objects.requireNonNull(result).keys();
                while (it.hasNext()){
                    String key = it.next();
                    addContent(key,result.optString(key));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void addContent(String field, String val) {
        View child = LayoutInflater.from(llContent.getContext()).inflate(R.layout.list_item_voucher_detail,llContent,false);
        TextView tvTitle = child.findViewById(R.id.tv_voucher_detail_item_title);
        TextView tvContent = child.findViewById(R.id.tv_voucher_detail_item_content);
        tvContent.setGravity(Gravity.RIGHT);
        tvTitle.setText(field);
        tvContent.setText(val);
        llContent.addView(child);
    }

    protected void showLoading(){
        mLoadingLayout.showLoading();
    }

    protected void hideLoading(){
        mLoadingLayout.showContent();
    }

}
