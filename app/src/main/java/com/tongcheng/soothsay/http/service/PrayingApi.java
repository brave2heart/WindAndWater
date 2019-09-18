package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.Offeringsbean;
import com.tongcheng.soothsay.bean.calculation.QFAddGPBean;
import com.tongcheng.soothsay.bean.calculation.QfDxDetailListBean;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 祈福台api
 */

public class PrayingApi {
    private static PrayingApi.Service instance;

    //
    public interface Service {


        @POST("/publish/qfstore/getqfstore.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<List<Offeringsbean>>>> getOfferings(@Field("sort") String sort);

        @POST("/publish/qfstore/getqfstore.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<List<Offeringsbean>>>> getOfferings(@Field("sort") String sort, @Field("token") String token, @Field("isFee") String isFee);



        @POST("/publish/qfstore/qfAddGP.do")
        @FormUrlEncoded
        Call<ApiResponseBean<QFAddGPBean>> qfAddGp(@Field("token") String token, @Field("dxName") String dxName, @Field("gpName") String gpName);





        /**
         * 祈福台请大仙接口
         * @param token
         * @param dxName
         * @param content
         * @return
         */
        @POST("/publish/qfstore/qfDx.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Void>> qfDx( @Field("token") String token,@Field("dxName")String dxName,@Field("content")String content);



        @POST("/publish/qfstore/deleteDx.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Void>> deleteDx( @Field("token") String token,@Field("dxName")String dxName);

        @POST("/publish/qfstore/qfDxDetailList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<QfDxDetailListBean>> qfDxDetailList(@Field("token") String token, @Field("dxName")String dxName);

        @POST("/publish/qfstore/qfDxList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<QfDxBean>>> qfDxList(@Field("token") String token);




        @POST("/publish/qfstore/qfDxList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<QfDxBean>>qfDxList2(@Field("token") String token);


        @POST("/publish/qfstore/qfDxList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<String>>qfDxList3(@Field("token") String token);




        /**
         * 修改许愿内容
         * @param token
         * @param dxName
         * @param content
         * @return
         */
        @POST("/publish/qfstore/changeQfContent.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Void>> changeQfContent(@Field("token") String token,@Field("dxName") String dxName,@Field("content") String content);


        /**
         * 购买物品
         * @param token
         * @param gpName
         * @param count
         * @param jf
         * @return
         */
        @POST("/publish/qfstore/qfBuy.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Void>> qfBuy(@Field("token") String token,@Field("gpName") String gpName,@Field("count") String count,@Field("jf") String jf);
    }

    public static PrayingApi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().
                    create(PrayingApi.Service.class);
        }
        return instance;
    }

}
