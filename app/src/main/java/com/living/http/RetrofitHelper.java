package com.living.http;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.living.constant.home.HomeAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 用于Retrofit的初始化
 * Created by weihao on 2017/12/26.
 */

public class RetrofitHelper {
    private Context mContext;
    private static RetrofitHelper instance = null;

    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private Retrofit mRetrofit;


    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }


    public RetrofitHelper(Context context) {
        mContext = context;
        init();

    }

    private void init() {
        resetApp();
    }

    private void resetApp() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(HomeAPI.ConstellationBaseUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();
    }

    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }
}
