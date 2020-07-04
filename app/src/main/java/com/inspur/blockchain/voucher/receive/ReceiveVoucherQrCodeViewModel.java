package com.inspur.blockchain.voucher.receive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inspur.blockchain.R;
import com.inspur.blockchain.app.BlockChainApplication;
import com.inspur.lib_base.threadpool.ThreadPoolManager;

import org.json.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * @author lichun
 */
public class ReceiveVoucherQrCodeViewModel extends ViewModel {

    private ReceiveVoucherQrCodeRepository receiveVoucherQrCodeRepository;

    public ReceiveVoucherQrCodeViewModel(){
        this.receiveVoucherQrCodeRepository = new ReceiveVoucherQrCodeRepository();
    }

    public LiveData<Bitmap> generateQrCode(final Context context,final String msg){
        final MutableLiveData<Bitmap> mutableLiveData = new MutableLiveData<>();
        Callable<Bitmap> callable = new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return QRCodeEncoder.syncEncodeQRCode(msg, BGAQRCodeUtil.dp2px(BlockChainApplication.getInstance(), 196), Color.parseColor("#ff0000"), BitmapFactory.decodeResource(context.getResources(),R.mipmap.icon_white_label));
            }
        };
        try {
            mutableLiveData.postValue(ThreadPoolManager.execute(callable).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mutableLiveData;
    }

    public LiveData<JSONObject> loopQuery(String did,String vpId){
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("verifier_did",did);
        params.put("stub_id",vpId);
        return receiveVoucherQrCodeRepository.loopQuery(params);
    }

    public void shutDownSchedule(){
        receiveVoucherQrCodeRepository.shutDownSchedule();
    }

}
