package com.inspur.blockchain.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.collection.ArrayMap;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.R;
import com.inspur.lib_base.base.BaseFragment;
import com.inspur.lib_base.util.Md5Util;
import com.inspur.lib_base.util.ToastUtil;
import com.inspur.lib_base.view.CountDownTextView;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONObject;

import java.util.Objects;

/**
 * @author lichun
 * 忘记密码页面
 */
public class ForgetPasswordFragment extends BaseFragment {

    private AppCompatEditText phoneEt;
    private AppCompatEditText codeEt;
    private AppCompatEditText passwordEt;
    private AppCompatEditText passwordConfirmEt;
    private CountDownTextView mCountDownTextView;
    private Button resetBtn;

    private ForgetPasswordViewModel forgetPasswordViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPasswordViewModel = new ViewModelProvider(this).get(ForgetPasswordViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forget_password;
    }

    @Override
    protected void initView(View view) {
        TitleView titleView = view.findViewById(R.id.title_forget_password);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                Navigation.findNavController(v).popBackStack();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        //手机号
        View phoneParentView = view.findViewById(R.id.include_user_phone);
        TextView phoneTv = phoneParentView.findViewById(R.id.tv_item_input_title);
        phoneTv.setText(getResources().getString(R.string.phone_num));
        phoneEt = phoneParentView.findViewById(R.id.et_item_input_content);
        phoneEt.setHint(getResources().getString(R.string.please_input_phone));
        //验证码
        View codeParentView = view.findViewById(R.id.include_get_v_code);
        codeEt = codeParentView.findViewById(R.id.et_item_input_content);
        mCountDownTextView = codeParentView.findViewById(R.id.countdown_tv_get_code);
        mCountDownTextView.setNormalText(getResources().getString(R.string.get_verification_code))
                .setCloseKeepCountDown(true)
                .setCountDownClickable(false)
                .setShowFormatTime(false)
                .setCountDownText("","s")
                .setOnCountDownStartListener(new CountDownTextView.OnCountDownStartListener() {
                    @Override
                    public void onStart() {
                        getVerificationCode();
                    }
                })
                .setOnCountDownFinishListener(new CountDownTextView.OnCountDownFinishListener() {
                    @Override
                    public void onFinish() {
                        mCountDownTextView.setNormalText(getResources().getString(R.string.retry_verification_code));
                    }
                })
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCountDownTextView.startCountDown(60);
                    }
                });
        //密码
        View passwordParentView = view.findViewById(R.id.include_user_password);
        TextView passwordTv = passwordParentView.findViewById(R.id.tv_item_input_title);
        passwordTv.setText(getResources().getString(R.string.password));
        passwordEt = passwordParentView.findViewById(R.id.et_item_input_content);
        passwordEt.setHint(getResources().getString(R.string.please_input_new_password));
        //确认密码
        View passwordConfirmParentView = view.findViewById(R.id.include_confirm_user_password);
        TextView passwordConfirmTv = passwordConfirmParentView.findViewById(R.id.tv_item_input_title);
        passwordConfirmTv.setText(getResources().getString(R.string.confirm_password));
        passwordConfirmEt = passwordConfirmParentView.findViewById(R.id.et_item_input_content);
        passwordConfirmEt.setHint(getResources().getString(R.string.please_confirm_new_password));

        resetBtn = view.findViewById(R.id.btn_reset_password);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0 ){
                    resetBtn.setBackgroundResource(R.drawable.shape_circle_blue_bg);
                    resetBtn.setEnabled(true);
                }else{
                    resetBtn.setBackgroundResource(R.drawable.shape_circle_translucent_blue_bg);
                    resetBtn.setEnabled(false);
                }
            }
        });

    }

    private void getVerificationCode() {
        forgetPasswordViewModel.requestCode(phoneEt.getText().toString().trim()).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {

            }
        });
    }


    @Override
    protected void initData() {


    }

    private void resetPassword() {
        showProgressLoading();
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("phone",phoneEt.getText().toString().trim());
        params.put("phone_code",codeEt.getText().toString().trim());
        params.put("pwd", Md5Util.encrypt32(passwordEt.getText().toString().trim()));
        params.put("confirm_pwd",Md5Util.encrypt32(passwordConfirmEt.getText().toString().trim()));

        forgetPasswordViewModel.resetPassword(params).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {
                hideProgressLoading();
                boolean status = jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS);
                ToastUtil.show(requireContext(),jsonObject.optString(HttpResponse.RESPONSE_MESSAGE));
                if(status){
                    NavController navController = NavHostFragment.findNavController(ForgetPasswordFragment.this);
                    navController.popBackStack();
                    navController.navigate(R.id.fragment_password_login);
                }
            }
        });
    }
}
