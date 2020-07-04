package com.inspur.lib_base.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.DialogFragment;

import com.inspur.lib_base.R;

/**
 * @author lichun
 */
public class LoadingDialog extends DialogFragment {

    private static final String TIP_TEXT = "tip";

    public static LoadingDialog getInstance(String text) {
        Bundle bundle = new Bundle();
        LoadingDialog dialogFragment = new LoadingDialog();
        bundle.putString(TIP_TEXT, text);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireContext(), R.style.LoadingDialog);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_base_loading_dialog, null);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(false);
        TextView tipView = (TextView) v.findViewById(R.id.tv_tips);
        String tip = getArguments().getString(TIP_TEXT);
        if (!TextUtils.isEmpty(tip)){
            tipView.setText(tip);
        }
        return dialog;
    }
}
