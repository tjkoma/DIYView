package com.example.tjjunlintx.diyview.utils;

import android.util.Log;
import android.util.SparseArray;

import com.example.tjjunlintx.diyview.OkResultListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tjjunlintx on 2017/6/24.
 */

public class HttpUtils {
    public static void getResult(HashMap<String,String> map,String path,OkResultListener okResultListener){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(path);
        if(map != null){
            Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                Log.e("TAG",entry.getKey()+ "=" +entry.getValue());
                builder.addHeader(entry.getKey()+"",entry.getValue()+"");
            }
        }
        Request request = builder.get().build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String str = response.body().string();
            if(okResultListener != null){
                okResultListener.onSuccess(str);
            }
        } catch (IOException e) {
            if(okResultListener != null){
                okResultListener.onFail(e);
            }
            e.printStackTrace();
        }

    }
}
