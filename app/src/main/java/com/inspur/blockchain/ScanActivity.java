package com.inspur.blockchain;

import android.view.View;

import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * @author lichun
 * 扫描二维码页面
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    private ZBarView zBarView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_scan_code);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {

            }
        });
        zBarView = findViewById(R.id.zbarview_scan);
        zBarView.setDelegate(this);
    }

    @Override
    protected void initData() {
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        zBarView.startCamera();
        zBarView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        zBarView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zBarView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
