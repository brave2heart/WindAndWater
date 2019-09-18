package com.tongcheng.soothsay.data.calculate.life;

import com.tongcheng.soothsay.bean.calculation.WishingSquare;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.service.WishingSquareApi;

import java.util.HashMap;
import java.util.List;


/**
 * Created by ALing on 2016/12/8 0008.
 */

public class WishingImpl implements IWishing {
    @Override
    public void getWishing(HashMap<String, String> map, ApiCallBack<ApiResponseBean<List<WishingSquare>>> callback) {
        WishingSquareApi.getInstance().getWishing(map).enqueue(callback);
    }
}
