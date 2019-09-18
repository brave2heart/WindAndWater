package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.FishBean;
import com.tongcheng.soothsay.bean.calculation.FreeGoodsBean;
import com.tongcheng.soothsay.bean.calculation.FreeRecordBean;
import com.tongcheng.soothsay.bean.calculation.FreeTopBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Steven on 16/12/2.
 */
public class FreePoolApi {

    private static FreePoolApi.Service instance;

    public interface Service{

        @POST("/publish/fsstore/fscList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<FishBean>>> getFishList(@Field("token") String token);

        @POST("/publish/fsstore/getfsStore.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<FreeGoodsBean>>> getFishGoodsList(@Field("sort") String sort);


        @POST("publish/fsstore/fsRecordList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<FreeRecordBean>> getFreeRecordList(@Field("token") String token, @Field("start") String start);

        @POST("publish/fsstore/fsTopList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<FreeTopBean>> getFreeTopList(@Field("token") String token);
    }


    public static FreePoolApi.Service getInstance(){
        if(instance == null){
            instance = ApiBuild.getRetrofit().create(FreePoolApi.Service.class);
        }
        return instance;
    }
}
