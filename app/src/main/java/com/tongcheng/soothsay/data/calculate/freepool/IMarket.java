package com.tongcheng.soothsay.data.calculate.freepool;

import com.tongcheng.soothsay.bean.calculation.FreeGoodsBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.List;

/**
 * Created by Steven on 16/12/1.
 */

public interface IMarket {
    void getGoodsList(ApiCallBack<ApiResponseBean<List<FreeGoodsBean>>> callBack);
}
