package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.BloodType;
import com.tongcheng.soothsay.bean.calculation.HeHun;
import com.tongcheng.soothsay.bean.calculation.ZodiacPairing;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class MarriageLoveApi {

    private static MarriageLoveApi.Service instance;

    public interface Service {

        /*合婚接口*/
        @POST("publish/hehun/hehun.do")
        @FormUrlEncoded
        Call<ApiResponseBean<HeHun>> getMarriageLove(@FieldMap HashMap<String, String> map);

        /*血型配对*/
        @POST("publish/bmatch/match.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BloodType>> getBloodType(@FieldMap HashMap<String, String> map);

        /*生肖配对*/
        @POST("publish/smatch/match.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ZodiacPairing>> getZodiacPairing(@FieldMap HashMap<String, String> map);
    }

    public static MarriageLoveApi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().create(MarriageLoveApi.Service.class);
        }
        return instance;
    }
}
