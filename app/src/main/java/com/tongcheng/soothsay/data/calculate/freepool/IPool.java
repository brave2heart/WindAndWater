package com.tongcheng.soothsay.data.calculate.freepool;

import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.bean.calculation.FishBean;

import java.util.List;

/**
 * Created by Steven on 16/11/29.
 */

public interface IPool {

    void getFishList(String token,ApiCallBack<ApiResponseBean<List<FishBean>>> callBack);
}
