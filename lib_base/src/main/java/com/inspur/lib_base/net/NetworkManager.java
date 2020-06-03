package com.inspur.lib_base.net;

import android.text.TextUtils;
import android.util.Log;

import androidx.collection.ArrayMap;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author lichun
 * 网络管理类
 */
public class NetworkManager {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String TAG = "NetworkManager";

    private static NetworkManager mManager;
    private OkHttpClient mClient;
    private static Request mRequest;

    private NetworkManager(){
        if(mClient == null){
            mClient = new OkHttpClient().newBuilder().build();
        }
    }

    public static NetworkManager getInstance(){
        if(mManager == null){
            mManager = new NetworkManager();
        }
        return mManager;
    }

    public static class Builder{
        private String url;
        private JSONObject header;
        private JSONObject body;
        private Request.Builder requestBuilder;
        private RequestBody requestBody;

        public Builder(){
            this.requestBuilder = new Request.Builder();
        }

        public Builder url(String url){
            this.url = url;
            this.requestBuilder.url(url);
            return this;
        }

        public Builder header(ArrayMap<String,String> headers){
            this.header = new JSONObject(headers);
            for (String key:headers.keySet()) {
                this.requestBuilder.header(key, TextUtils.isEmpty(headers.get(key))?"":headers.get(key));
            }
            return this;
        }


        public Builder body(ArrayMap<String,String> params){
            this.body = new JSONObject(params);
            return this;
        }


        public Builder get(){
            this.requestBuilder.get();
            return this;
        }

        public Builder post(){
            if(this.body != null){
                requestBody = RequestBody.create(this.body.toString(),JSON);
            }else{
                requestBody = RequestBody.create("",JSON);
            }
            this.requestBuilder.post(requestBody);
            return this;
        }

        public void build(){
            mRequest = this.requestBuilder.build();
            Log.i(TAG, "request: url=" + mRequest.url().toString()+ ",header="+ (this.header==null?"":header.toString()) + ",method=" + mRequest.method()+",params=" + (this.body==null?"":this.body.toString()));
        }
    }

    public void access(final NetCallback<JSONObject> callback){
        mClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(callback != null){
                    Log.i(TAG, "onError: " + e.getMessage());
                    JSONObject object = new JSONObject();
                    try {
                        object.put("reason",e.getMessage());
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    callback.onError(object);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    if(responseBody != null){
                        String data = responseBody.string();
                        if(callback != null){
                            try {
                                Log.i(TAG, "onResponse: success " + data);
                                callback.onSuccess(new JSONObject(data));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else{
                    ResponseBody responseBody = response.body();
                    if(responseBody != null){
                        String data = responseBody.string();
                        if(callback != null){
                            try {
                                Log.i(TAG, "onResponse: failure " + data);
                                callback.onFailure(new JSONObject(data));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
    }


}
