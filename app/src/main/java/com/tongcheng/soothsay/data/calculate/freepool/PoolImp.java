package com.tongcheng.soothsay.data.calculate.freepool;

import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.service.FreePoolApi;
import com.tongcheng.soothsay.bean.calculation.FishBean;

import java.util.List;

/**
 * Created by Steven on 16/11/29.
 */

public class PoolImp implements IPool{

    @Override
    public void getFishList(String token,ApiCallBack<ApiResponseBean<List<FishBean>>> callBack) {
        FreePoolApi.getInstance().getFishList(token).enqueue(callBack);
    }
}
