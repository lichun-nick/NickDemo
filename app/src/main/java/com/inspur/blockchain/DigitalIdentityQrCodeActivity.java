package com.inspur.blockchain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.view.TitleView;



/**
 * @author lichun
 * 数字身份二维码
 */
public class DigitalIdentityQrCodeActivity extends BaseActivity {

    private TitleView titleView;
    private ImageView ivQR;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_digital_identity_qr_code;
    }

    @Override
    protected void initView() {
        titleView = findViewById(R.id.title_digital_identity);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {
                startActivity(new Intent(DigitalIdentityQrCodeActivity.this,ScanActivity.class));
            }
        });
        ivQR = findViewById(R.id.iv_qr_code);
    }

    @Override
    protected void initData() {
        DigitalIdentityQrCodeViewModel viewModel = new ViewModelProvider(this).get(DigitalIdentityQrCodeViewModel.class);
        viewModel.generateQrCode(this,"nick").observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                if(bitmap != null){
                    ivQR.setImageBitmap(bitmap);
                }
            }
        });
    }


}
