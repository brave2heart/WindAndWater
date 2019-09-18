package com.tongcheng.soothsay.data.huangli.almanac;

import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;
import com.tongcheng.soothsay.http.service.HuangliApi;

import retrofit2.Callback;

/**
 * Created by Steven on 16/11/11.
 */

public class AlmanacImp implements IAlmanac {

    @Override
    public void getAlmanac(String url, Callback<AlmanacBean> callback) {
        HuangliApi.getInstance().getAlmanac(url).enqueue(callback);
    }

    @Override
    public void getHoursYiji(String url, Callback<HousYijiBean> callback) {
        HuangliApi.getInstance().getHoursYiji(url).enqueue(callback);
    }
}
