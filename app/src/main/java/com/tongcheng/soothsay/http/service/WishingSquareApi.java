package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.WishingCount;
import com.tongcheng.soothsay.bean.calculation.WishingOrder;
import com.tongcheng.soothsay.bean.calculation.WishingSquare;
import com.tongcheng.soothsay.bean.calculation.XYDPriceBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ALing on 2016/12/8 0008.
 */

public class WishingSquareApi {
    private static WishingSquareApi.Service instance;

    public interface Service {
        /*获取许愿灯点灯人数*/
        @POST("/publish/xyd/getXydCount.do")
        Call<ApiResponseBean<WishingCount>>  getWishingCount();
        /*获取我的许愿灯*/
        @POST("/publish/xyd/myXydList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<WishingOrder>>> getMyWishingLight(@FieldMap HashMap<String, String> map);
//        删除我的订单
        @POST("/publish/store/deleteOrder.do")
        @FormUrlEncoded
        Call<ApiResponseBean<WishingOrder>> getDeleteOrder(@FieldMap HashMap<String,String> map);

    /*获取许愿灯广场的许愿灯*/
        @POST("/publish/xyd/getXydList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<WishingSquare>>> getWishing(@FieldMap HashMap<String,String> map);

        /*许愿广场祈福*/
        @POST("/publish/xyd/xydBlessing.do")
        @FormUrlEncoded
        Call<ApiResponseBean<WishingSquare>> getClifford(@FieldMap HashMap<String,String> map);


        /*许愿灯价格*/
        @POST("/publish/xyd/getXydPrice.do")
        Call<ApiResponseBean<XYDPriceBean>> getXYDPrice();

    }

    public static WishingSquareApi.Service getInstance(){
        if(instance == null){
            instance = ApiBuild.getRetrofit().create(WishingSquareApi.Service.class);
        }
        return  instance;
    }
}
