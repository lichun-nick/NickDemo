package com.inspur.blockchain;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;


import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * @author lichun
 * 扫描二维码页面
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    private static final int REQUEST_CAMERA = 1111;
    private static final String TAG = "ScanActivity";
    private ZBarView zBarView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
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
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            zBarView.startCamera();
            zBarView.startSpotAndShowRect();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA){
            if(permissions.length == 1 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                zBarView.startCamera();
                zBarView.startSpotAndShowRect();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

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
        Log.i(TAG, "onScanQRCodeSuccess: " + result);
        Intent intent = new Intent(this,ScanShowResultActivity.class);
        intent.putExtra(Keys.QR_CODE_RESULT,result);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
