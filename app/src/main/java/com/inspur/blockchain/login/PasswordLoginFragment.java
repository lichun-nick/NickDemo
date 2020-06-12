package com.inspur.blockchain.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.R;
import com.inspur.lib_base.base.BaseFragment;
import com.inspur.lib_base.util.ToastUtil;

import org.json.JSONObject;


/**
 * @author lichun
 * 密码登录页面
 */
public class PasswordLoginFragment extends BaseFragment {

    private static final String TAG = "PasswordLoginFragment";

    private AppCompatEditText nameEditText;
    private AppCompatEditText passwordEditText;
    private ImageView clearPwdIv;
    private ImageView changeVisibleIv;
    private Button login;
    private boolean passwordVisible;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_password_login;
    }

    @Override
    protected void initView(View view) {
        TextView changePage = view.findViewById(R.id.btn_switch_page);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragment_password_login_to_fragment_verification_code_login);
            }
        });
        TextView forgetPassword = view.findViewById(R.id.btn_forget_password);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragment_password_login_to_fragment_forget_password);
            }
        });
        clearPwdIv = view.findViewById(R.id.iv_clear_password);
        clearPwdIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordEditText.setText("");
            }
        });
        changeVisibleIv = view.findViewById(R.id.iv_change_visible);
        changeVisibleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordVisible) {
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                passwordVisible = !passwordVisible;
            }
        });

        login = view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordLogin();
            }
        });
        Button register = view.findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PasswordLoginFragment.this).navigate(R.id.fragment_register_user);
            }
        });
        nameEditText = view.findViewById(R.id.et_input_name);
        passwordEditText = view.findViewById(R.id.et_input_password);
        nameEditText.addTextChangedListener(new TextWatcher() {
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

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0){
                    clearPwdIv.setVisibility(View.VISIBLE);
                    changeVisibleIv.setVisibility(View.VISIBLE);
                }else{
                    clearPwdIv.setVisibility(View.INVISIBLE);
                    changeVisibleIv.setVisibility(View.INVISIBLE);
                }

            }
        });



    }

    private void passwordLogin() {
        showProgressLoading();
        loginViewModel.userLogin(LoginViewModel.PASSWORD_LOGIN_TYPE,nameEditText.getText().toString().trim(),passwordEditText.getText().toString().trim()).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {

                boolean status = jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS);
                if(status){
                    NavHostFragment.findNavController(PasswordLoginFragment.this).navigate(R.id.activity_main);
                    if(getActivity() != null){
                        getActivity().finish();
                    }
                }else{
                    ToastUtil.show(requireContext(),jsonObject.optString(HttpResponse.RESPONSE_MESSAGE));
                }
                hideProgressLoading();
            }
        });

    }

    @Override
    protected void initData() {

    }
}
