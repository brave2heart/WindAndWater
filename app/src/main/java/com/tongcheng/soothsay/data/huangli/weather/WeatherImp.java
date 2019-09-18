package com.tongcheng.soothsay.data.huangli.weather;

import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.data.BaseDataImp;
import com.tongcheng.soothsay.http.service.HuangliApi;

import retrofit2.Callback;

/**
 * Created by Steven on 16/11/10.
 */
public class WeatherImp extends BaseDataImp implements IWeather{

    @Override
    public void getWeather(String url, Callback<WeatherBean> call) {
        HuangliApi.getInstance().getWeather(url).enqueue(call);
    }
}
