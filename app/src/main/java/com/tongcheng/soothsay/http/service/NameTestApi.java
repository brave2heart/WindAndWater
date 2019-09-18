package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.BornBoyOrGirl;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ALing on 2016/12/5 0005.
 */

public class NameTestApi {
    private static NameTestApi.Service instance;

    public interface Service {

        /*s生男生女*/
        @POST("publish/nnmatch/match.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BornBoyOrGirl>> getBoyOrGirl(@FieldMap HashMap<String, String> map);


    }

    public static NameTestApi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().create(NameTestApi.Service.class);
        }
        return instance;
    }
}
