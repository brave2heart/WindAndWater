package com.tongcheng.soothsay.data.pay;

import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.service.PayApi;

import java.util.HashMap;

/**
 * Created by Steven on 16/12/4.
 */
public class PayImp implements Ipay {

    @Override
    public void getOrders(HashMap<String, String> map, ApiCallBack<ApiResponseBean<OrdersResultBean>> callBack) {
        PayApi.getInstance().getOrders(map).enqueue(callBack);
    }

    @Override
    public void getRePayOrder(HashMap<String, String> map, ApiCallBack<ApiResponseBean<OrdersResultBean>> callBack) {
        PayApi.getInstance().getRepayOrder(map).enqueue(callBack);
    }

    @Override
    public void validateOrder(String out_trade_no, ApiCallBack<ApiResponseBean<Void>> callBack) {
        PayApi.getInstance().validateOrder(out_trade_no).enqueue(callBack);
    }
}
