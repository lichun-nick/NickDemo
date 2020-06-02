package com.inspur.blockchain.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.inspur.blockchain.DigitalIdentityQrCodeActivity;
import com.inspur.blockchain.R;
import com.inspur.blockchain.ScanActivity;
import com.inspur.lib_base.BaseActivity;

/**
 * @author lichun
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.fl_home_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScanActivity.class));
            }
        });
        findViewById(R.id.fl_home_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DigitalIdentityQrCodeActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }


}
