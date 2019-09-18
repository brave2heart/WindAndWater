package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.ChengGu;
import com.tongcheng.soothsay.bean.calculation.ZiweiAnalyseBean;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Steven on 16/11/16.
 */

public class chuantongApi {

    private static Service instance;

    public interface Service{

        /*紫微排盘*/
        @POST("publish/smZiWei/paiPan.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ZiweipaipanBean>> getZiweipaipan(@FieldMap HashMap<String,String> map);

        /*紫微排盘分析*/
        @POST("publish/smZiWei/paiPanDetail.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ZiweiAnalyseBean>> getZiweiAnalyse(@FieldMap HashMap<String,String> map);

        /*称骨接口*/
        @POST("publish/chenggu/chenggu.do")
        @FormUrlEncoded
        Call<ApiResponseBean<ChengGu>> getChengGu(@FieldMap HashMap<String,String> map);



    }


    public static Service getInstance(){
        if(instance == null){
            instance = ApiBuild.getRetrofit().create(chuantongApi.Service.class);
        }
        return instance;
    }


}
