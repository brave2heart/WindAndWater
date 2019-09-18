package com.tongcheng.soothsay.data.classroom;

import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.classroom.GoodsListBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;

/**
 * Created by Steven on 16/12/26.
 */

public interface IGoods {

    void getAddressList(String token, ApiCallBack<ApiResponseBean<List<AddressBean>>> callBack);

    void getGoodsList(String start,String type, ApiCallBack<ApiResponseBean<GoodsListBean>> callBack);
}
