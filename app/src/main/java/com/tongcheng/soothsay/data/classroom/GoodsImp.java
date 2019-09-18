package com.tongcheng.soothsay.data.classroom;

import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.classroom.GoodsListBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.service.StoreApi;

import java.util.List;

/**
 * Created by Steven on 16/12/26.
 */

public class GoodsImp implements IGoods{

    @Override
    public void getAddressList(String token, ApiCallBack<ApiResponseBean<List<AddressBean>>> callBack) {
        StoreApi.getInstance().getAddressList(token).enqueue(callBack);
    }

    @Override
    public void getGoodsList(String start,String type, ApiCallBack<ApiResponseBean<GoodsListBean>> callBack) {
        StoreApi.getInstance().getGoodsList(start,type).enqueue(callBack);
    }
}
