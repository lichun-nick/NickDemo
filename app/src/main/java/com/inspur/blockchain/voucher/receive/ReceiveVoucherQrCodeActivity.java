package com.inspur.blockchain.voucher.receive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.DigitalIdentityQrCodeViewModel;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.ScanActivity;
import com.inspur.blockchain.ScanShowVoucherDetailActivity;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.util.ToastUtil;
import com.inspur.lib_base.view.TitleView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author lichun
 * 接收二维码页面
 */
public class ReceiveVoucherQrCodeActivity extends BaseActivity {

    private ImageView ivqr;
    private String vpId;
    private ReceiveVoucherQrCodeViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_voucher_qr_code;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        TitleView titleView = findViewById(R.id.title_receive_qr_code);
        titleView.setDelegate(new TitleView.Delegate() {
            @Override
            public void onClickLeft(View v) {
                finish();
            }

            @Override
            public void onClickRight(View v) {
                startActivity(new Intent(ReceiveVoucherQrCodeActivity.this, ScanActivity.class));
            }
        });
        ivqr = findViewById(R.id.iv_qr_code);
    }

    @Override
    protected void initData() {
        String msg = getIntent().getStringExtra(Keys.QR_CODE_PATH);
        try {
            JSONObject object = new JSONObject(msg);
            vpId = object.getString("vp_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel = new ViewModelProvider(this).get(ReceiveVoucherQrCodeViewModel.class);
        viewModel.generateQrCode(this,msg).observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                if(bitmap != null){
                    ivqr.setImageBitmap(bitmap);
                    viewModel.loopQuery("nick",vpId).observe(ReceiveVoucherQrCodeActivity.this, new Observer<JSONObject>() {
                        @Override
                        public void onChanged(JSONObject object) {
                            if(object.optBoolean(HttpResponse.RESPONSE_STATUS)){
                                Intent intent = new Intent(ReceiveVoucherQrCodeActivity.this, ScanShowVoucherDetailActivity.class);
                                intent.putExtra(Keys.QR_CODE_RESULT,object.optString(HttpResponse.RESPONSE_DATA));
                                startActivity(intent);
                                finish();
                            }else{
                                //ToastUtil.show(ReceiveVoucherQrCodeActivity.this,object.optString(HttpResponse.RESPONSE_MESSAGE));
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.shutDownSchedule();
    }
}
