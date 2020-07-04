package com.inspur.blockchain.voucher.receive;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.inspur.blockchain.BaseRepository;
import com.inspur.blockchain.HttpResponse;
import com.inspur.blockchain.UrlConfig;
import com.inspur.lib_base.net.NetCallback;
import com.inspur.lib_base.threadpool.ThreadPoolManager;

import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lichun
 */
public class ReceiveVoucherQrCodeRepository extends BaseRepository {

    private ScheduledExecutorService scheduledExecutorService;

    public LiveData<JSONObject> loopQuery(final ArrayMap<String,String> params){
        final MutableLiveData<JSONObject> mutableLiveData = new MutableLiveData<>();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                defaultHeaders().url(UrlConfig.QUERY_IF_ACCEPT_SEE_VOUCHER_URL).body(params).post().build().requestJson(new NetCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject object) {
                        if(object.optBoolean(HttpResponse.RESPONSE_STATUS)){
                            if(!scheduledExecutorService.isShutdown()){
                                scheduledExecutorService.shutdownNow();
                            }
                        }
                        mutableLiveData.postValue(object);
                    }

                    @Override
                    public void onFailure(JSONObject object) {

                    }

                    @Override
                    public void onError(JSONObject object) {

                    }
                });
            }
        },0,2000, TimeUnit.MILLISECONDS);

        return mutableLiveData;
    }

    public void shutDownSchedule(){
        if(scheduledExecutorService != null){
            if(!scheduledExecutorService.isShutdown()){
                scheduledExecutorService.shutdownNow();
            }
        }
    }
}
