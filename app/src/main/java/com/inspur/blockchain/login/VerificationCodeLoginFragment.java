package com.inspur.blockchain.login;

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
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.R;
import com.inspur.lib_base.BaseFragment;
import com.inspur.lib_base.ToastUtil;
import com.inspur.lib_base.view.CountDownTextView;

import org.json.JSONObject;

/**
 * @author lichun
 * 验证码登录页面
 */
public class VerificationCodeLoginFragment extends BaseFragment {

    private CountDownTextView countDownTextView;
    private AppCompatEditText phoneEditText;
    private AppCompatEditText codeEditText;
    private Button login;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verification_code_login;
    }

    @Override
    protected void initView(View view) {
        TextView changePage = view.findViewById(R.id.btn_switch_page);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragment_verification_code_login_to_fragment_password_login);
            }
        });
        phoneEditText = view.findViewById(R.id.et_input_phone);
        codeEditText = view.findViewById(R.id.et_input_code);
        countDownTextView = view.findViewById(R.id.countdown_tv_get_code);
        countDownTextView.setNormalText(getResources().getString(R.string.get_verification_code))
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
                        countDownTextView.setNormalText(getResources().getString(R.string.retry_verification_code));
                    }
                })
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countDownTextView.startCountDown(60);
                    }
                });

        login = view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationLogin();
            }
        });
        Button register = view.findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(VerificationCodeLoginFragment.this).navigate(R.id.fragment_register_user);
            }
        });

        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0 ){
                    login.setBackgroundResource(R.drawable.shape_circle_white_bg);
                    login.setEnabled(true);
                }else{
                    login.setBackgroundResource(R.drawable.shape_circle_translucent_white_bg);
                    login.setEnabled(false);
                }
            }
        });
    }


    @Override
    protected void initData() {

    }

    /**
     * 获取
     */
    private void getVerificationCode() {
        if(phoneEditText.getText() != null){
            String phone = phoneEditText.getText().toString().trim();
            ArrayMap<String,String> params = new ArrayMap<>();
            params.put("phone",phone);
            loginViewModel.requestCode(params).observe(this, new Observer<JSONObject>() {
                @Override
                public void onChanged(JSONObject jsonObject) {

                }
            });
        }
    }

    private void verificationLogin() {
        showProgressLoading();
        loginViewModel.userLogin(LoginViewModel.VERIFICATION_CODE_LOGIN_TYPE,phoneEditText.getText().toString().trim(),codeEditText.getText().toString().trim()).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {
                hideProgressLoading();
                boolean status = jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS);
                if(status){
                    NavHostFragment.findNavController(VerificationCodeLoginFragment.this).navigate(R.id.activity_main);
                    if(getActivity() != null){
                        getActivity().finish();
                    }
                }else{
                    ToastUtil.show(getContext(),jsonObject.optString(HttpResponse.RESPONSE_MESSAGE));
                }
            }
        });

    }
}
