package com.tongcheng.soothsay.data.pay;

import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;

/**
 * Created by Steven on 16/12/4.
 */

public interface Ipay {
    void getOrders(HashMap<String,String> map ,ApiCallBack<ApiResponseBean<OrdersResultBean>> callBack);
    void getRePayOrder(HashMap<String,String> map , ApiCallBack<ApiResponseBean<OrdersResultBean>> callBack);
    void validateOrder(String out_trade_no,ApiCallBack<ApiResponseBean<Void>> callBack);
}
