package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Steven on 16/12/2.
 */
public class PayApi {

    private static PayApi.Service instance;

    public interface Service{

        /**
         * 获取支付订单
         */
        @POST("/publish/store/createOrder.do")
        @FormUrlEncoded
        Call<ApiResponseBean<OrdersResultBean>> getOrders(@FieldMap HashMap<String,String> map);

//        未支付订单重新支付
        @POST("/publish/store/repay.do")
        @FormUrlEncoded
        Call<ApiResponseBean<OrdersResultBean>> getRepayOrder(@FieldMap HashMap<String,String> map);


        @POST("/publish/store/validateOrder.do")
        @FormUrlEncoded
        Call<ApiResponseBean<Void>> validateOrder(@Field("out_trade_no")String out_trade_no);




    }


    public static PayApi.Service getInstance(){
        if(instance == null){
            instance = ApiBuild.getRetrofit().create(PayApi.Service.class);
        }
        return instance;
    }
}
