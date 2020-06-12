package com.inspur.blockchain.welcome;

import android.content.Intent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.CheckTokenViewModel;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.R;
import com.inspur.blockchain.login.LoginActivity;
import com.inspur.blockchain.main.MainActivity;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.CountDownTextView;

import org.json.JSONObject;

/**
 * @author lichun
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    private boolean main;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        CountDownTextView countDownTextView = findViewById(R.id.countdown_tv);
        countDownTextView.setNormalText("")
                .setCloseKeepCountDown(true)
                .setCountDownClickable(true)
                .setShowFormatTime(false)
                .setOnCountDownFinishListener(new CountDownTextView.OnCountDownFinishListener() {
                    @Override
                    public void onFinish() {
                        nextPage(main? MainActivity.class: LoginActivity.class);
                    }
                });
        countDownTextView.startCountDown(3);
    }

    @Override
    protected void initData() {
        CheckTokenViewModel checkTokenViewModel = new ViewModelProvider(this).get(CheckTokenViewModel.class);
        checkTokenViewModel.checkToken().observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {
                main = jsonObject.optBoolean(HttpResponse.RESPONSE_STATUS);
            }
        });
    }

    private void nextPage(Class clazz){
        startActivity(new Intent(this,clazz));
        finish();
    }
}
