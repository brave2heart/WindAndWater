package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.classroom.GoodsListBean;
import com.tongcheng.soothsay.bean.classroom.ShopCartBean;
import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 商城请求api
 * Created by wuyunan on 16/10/26.
 */
public class StoreApi {

    private static Service instance;

    public interface Service {

        /**
         * 获取用户地址
         */
        @POST("publish/address/getAddressList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<AddressBean>>> getAddressList(@Field("token") String token);

        /**
         * 添加用户地址
         */
        @POST("publish/address/saveAddress.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> addAddress(@FieldMap Map<String,String> map);

        /**
         * 删除用户地址
         */
        @POST("publish/address/delAddress.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Object>> deletAddress(@Field("token") String token, @Field("addressId") String id);

        /**
         * 获取购物车商品
         */
        @POST("/publish/store/getShopCartList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<List<ShopCartBean>>> getShopCart(@Field("shopInfo") String shopInfo);

        /**
         * 获取开运商城商品列表
         */
        @POST("/publish/lecture/storeList.do")
        @FormUrlEncoded
        Call<ApiResponseBean<GoodsListBean>> getGoodsList(@Field("start") String start, @Field("type") String type);

    }

    public static Service getInstance() {
        if (instance == null) {
            instance = ApiBuild.getRetrofit().create(Service.class);
        }
        return instance;
    }

}
