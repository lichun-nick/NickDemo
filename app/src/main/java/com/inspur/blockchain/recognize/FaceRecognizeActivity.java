package com.inspur.blockchain.recognize;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.Keys;
import com.inspur.blockchain.R;
import com.inspur.blockchain.voucher.RequestVoucherViewModel;
import com.inspur.icity.comp_seal.util.StatusBarUtil;
import com.inspur.lib_base.LiveDataBus;
import com.inspur.lib_base.base.BaseActivity;
import com.inspur.lib_base.util.ToastUtil;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.size.SizeSelector;
import com.otaliastudios.cameraview.size.SizeSelectors;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

/**
 * @author lichun
 */
public class FaceRecognizeActivity extends BaseActivity {

    private static final String TAG = "FaceRecognizeActivity";
    private RelativeLayout rootView;
    private CameraView camera;
    private CountDownTimer countDownTimer;
    private CountDownLatch countDownLatch = new CountDownLatch(2);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_face_recognize;
    }

    @Override
    protected int wrapLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        rootView = findViewById(R.id.rl_face_detect);
        camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);
        camera.addCameraListener(new CameraActionListener());
        camera.setPreviewStreamSize(SizeSelectors.biggest());
    }

    @Override
    protected void initData() {
        RequestVoucherViewModel requestVoucherViewModel = new ViewModelProvider(this).get(RequestVoucherViewModel.class);
        countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                countDownLatch.countDown();
                Log.i(TAG, "onFinish: " + countDownLatch.getCount());
                if(countDownLatch.getCount() == 0){
                    finish();
                }
            }
        };
        Bundle bundle = getIntent().getBundleExtra(Keys.VOUCHER_INFO);
        ArrayMap<String,String> params = new ArrayMap<>();
        if(bundle != null){
            for (String key:bundle.keySet()) {
                params.put(key,bundle.getString(key));
            }
        }
        requestVoucherViewModel.requestVoucher(params).observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(JSONObject jsonObject) {
                LiveDataBus.get().with("voucher_insert").setValue(true);
                ToastUtil.show(getApplicationContext(),jsonObject.optString(HttpResponse.RESPONSE_MESSAGE));
                countDownLatch.countDown();
                if(countDownLatch.getCount() == 0){
                    finish();
                }
                Log.i(TAG, "onChanged: " + countDownLatch.getCount());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            int width = rootView.getMeasuredWidth();
            int height = rootView.getMeasuredHeight();
            RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) camera.getLayoutParams();

            mParams.width = width * 2/3;
            mParams.height = width * 2/3;
            mParams.topMargin = height * 2/5 - width/3 ;

            Log.i(TAG, "initView: "+ height + ":::" + width + "::::" + StatusBarUtil.getStatusBarHeight(this));
            camera.setLayoutParams(mParams);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        camera.destroy();
        super.onDestroy();
        countDownTimer.cancel();
        countDownTimer = null;
    }

    private static class CameraActionListener extends CameraListener{

        @Override
        public void onCameraOpened(@NonNull CameraOptions options) {
            super.onCameraOpened(options);
        }


    }
}
