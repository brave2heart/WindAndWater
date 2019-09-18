package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.WishTreeBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 许愿树pi
 */

public class WishTreeAPi {
    private static WishTreeAPi.Service instance;

    //
    public interface Service {


        /**
         * 获取许愿树上的许愿列表接口
         * @param token
         * @return
         */
        @POST("/publish/xyTree/getXyTreeList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<WishTreeBean>>> getXyTreeList(@Field("token") String token);


        /**
         * 保存许愿树许愿信息接口
         * @param token
         * @param myname
         * @param toname
         * @param sayword
         * @return
         */
        @POST("/publish/xyTree/saveXyTreeInfo.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Void>> saveXyTreeInfo(@Field("token") String token, @Field("myname") String myname, @Field("toname") String toname, @Field("sayword") String sayword);

        /**
         * 获取指定许愿树许愿信息接口
         * @param xytId
         * @return
         */
        @POST("/publish/xyTree/getXyTreeInfo.do")
        @FormUrlEncoded
        Call<ApiResponseBean<WishTreeBean>> getXyTreeInfo(@Field("xytId") String xytId);

        /**
         * @param token
         * @return
         */
        @POST("/publish/xyTree/getXyTreeSelfList.do.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<WishTreeBean>>> getXyTreeSelfList(@Field("token") String token);


    }

    public static WishTreeAPi.Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().
                    create(WishTreeAPi.Service.class);
        }
        return instance;
    }

}
