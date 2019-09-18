package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShenShaBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShiyeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziSzshishenBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziXinggeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziYinyuanBean;
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
 * Created by  on 2016/11/6.
 * 八字排盘请求api
 */

public class BaZiApi {
    private static BaZiApi.Service instance;

    //
    public interface Service {


        /**
         * 八字排盘
         * @param year 出生年
         * @param month  出生月
         * @param day  出生日
         * @param hour  出生时
         * @param minute 出生分
         * @param sex 性别（1：男；2：女）
         * @return 八字排盘数据
         */
        @POST("/publish/paipan/bazipaipan.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BaziPaipanBean>> baziPaipan(@Field("year") String year,
                                                       @Field("month") String month,
                                                       @Field("day") String day,
                                                       @Field("hour") String hour,
                                                       @Field("minute") String minute,
                                                       @Field("sex") String sex);

        @POST("/publish/paipan/bazipaipan.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BaziPaipanBean>> baziPaipan(@FieldMap HashMap<String,String> map);

        /**
         * 八字先天命盘接口
         * @param map
         * @return
         */
        @POST("/publish/paipan/szshishen.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<BaziSzshishenBean>>> baziSzShishen(@FieldMap HashMap<String,String> map);


        /**
         * 八字事业接口
         * @param map
         * @return
         */
        @POST("/publish/paipan/shiye.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BaziShiyeBean>> baziShiye(@FieldMap HashMap<String,String> map);


        /**
         * 八字婚恋接口
         * @param map
         * @return
         */
        @POST("/publish/paipan/yinyuan.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BaziYinyuanBean>> baziYinyuan(@FieldMap HashMap<String,String> map);


        /**
         * 八字性格接口
         * @param map
         * @return
         */
        @POST("/publish/paipan/xingge.do")
        @FormUrlEncoded
        Call<ApiResponseBean<BaziXinggeBean>> baziXingge(@FieldMap HashMap<String,String> map);



        @POST("/publish/paipan/shensha.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<BaziShenShaBean>>> getShensha(@Field("shenSha") String shenSha);
    }

    public static BaZiApi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().
                    create(BaZiApi.Service.class);
        }
        return instance;
    }

}
