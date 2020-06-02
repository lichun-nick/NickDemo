package com.inspur.blockchain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;


import com.inspur.lib_base.BaseActivity;
import com.inspur.lib_base.ToastUtil;
import com.inspur.lib_base.view.TitleView;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;


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
        createEnglishQRCode();
    }

    private void createEnglishQRCode() {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("nick", BGAQRCodeUtil.dp2px(DigitalIdentityQrCodeActivity.this, 196), Color.parseColor("#319DFD"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ivQR.setImageBitmap(bitmap);
                } else {
                    ToastUtil.show(DigitalIdentityQrCodeActivity.this, "生成英文二维码失败");
                }
            }
        }.execute();
    }
}
