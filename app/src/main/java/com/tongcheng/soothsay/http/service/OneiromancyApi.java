package com.tongcheng.soothsay.http.service;

import android.support.annotation.StringDef;

import com.tongcheng.soothsay.bean.calculation.QQNumberBean;
import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiBuild;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * 解梦api
 */

public class OneiromancyApi {
    private static OneiromancyApi.Service instance;


    public interface Service {


        @GET(Constant.Url.BASE_ONEIROMANCY_URL+"query")
        Call<ZGJMDetailBean> getZGQuery(@Query("key") String key,
                                        @Query("q") String q,
                                        @Query("cid") String cid,
                                        @Query("full") String full);


    }

    public static OneiromancyApi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().
                    create(OneiromancyApi.Service.class);
        }
        return instance;
    }

}
