package com.tongcheng.soothsay.data.huangli.weather;

import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;

import retrofit2.Callback;

/**
 * Created by Steven on 16/11/10.
 */

public interface IWeather {

    void getWeather(String url, Callback<WeatherBean> call);
}
