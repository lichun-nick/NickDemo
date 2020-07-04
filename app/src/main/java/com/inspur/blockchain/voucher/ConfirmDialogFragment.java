package com.inspur.blockchain.voucher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.lib_base.LiveDataBus;
import com.inspur.lib_base.base.BaseDialogFragment;
import com.inspur.lib_base.util.ToastUtil;

import org.json.JSONObject;


/**
 * @author lichun
 */
public class ConfirmDialogFragment extends BaseDialogFragment {

    private TextView tvTitle;
    private Button btnCancel;
    private Button btnConfirm;

    private DeleteVoucherViewModel deleteVoucherViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteVoucherViewModel = new ViewModelProvider(this).get(DeleteVoucherViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_confirm_dialog;
    }

    @Override
    public void initView(View view) {
        tvTitle = view.findViewById(R.id.tv_confirm_title);
        btnCancel = view.findViewById(R.id.btn_confirm_cancel);
        btnConfirm = view.findViewById(R.id.btn_confirm_sure);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVoucher();
            }
        });

    }

    private void deleteVoucher() {
        assert getArguments() != null;
        showProgressLoading();
        deleteVoucherViewModel.delVoucher(getArguments().getString(Keys.DID)).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject object) {
                hideProgressLoading();
                if(object.optBoolean(HttpResponse.RESPONSE_STATUS)){
                    ToastUtil.show(requireContext(),"凭证已删除！");
                    LiveDataBus.get().with("del_voucher").setValue(true);
                    dismiss();
                }else{
                    ToastUtil.show(requireContext(),object.optString(HttpResponse.RESPONSE_MESSAGE));
                }

            }
        });
    }

}
