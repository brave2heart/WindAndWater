package com.tongcheng.soothsay.data.huangli.almanac;

import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;

import retrofit2.Callback;

/**
 * Created by Steven on 16/11/11.
 * 老黄历详情
 */

public interface IAlmanac {
    void getAlmanac(String url , Callback<AlmanacBean> callback);
    void getHoursYiji(String url, Callback<HousYijiBean> callback);
}
