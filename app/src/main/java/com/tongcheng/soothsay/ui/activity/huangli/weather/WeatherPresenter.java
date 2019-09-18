package com.tongcheng.soothsay.ui.activity.huangli.weather;

import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.data.huangli.weather.IWeather;
import com.tongcheng.soothsay.data.huangli.weather.WeatherImp;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Steven on 16/11/10.
 */

public class WeatherPresenter implements WeatherConstant.Presenter{

    private IWeather iData;
    private WeatherConstant.View mView;


    public WeatherPresenter(WeatherConstant.View view){
        mView = view;
        iData = new WeatherImp();
        mView.setPresenter(this);
    }

    @Override
    public void start() {}

    //获取天气预报    cityName  如 广州
    @Override
    public void getWeather(String cityName) {
        try {
            String temp = URLEncoder.encode(cityName,"utf-8");
            String url = String.format(Locale.CHINA, OldAlmanacConstant.WEATHER_API,temp,OldAlmanacConstant.WEATHER_KEY);
            iData.getWeather(url, new Callback<WeatherBean>() {

                @Override
                public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                    if(response.isSuccessful()){
                        WeatherBean bean = response.body();
                        mView.showWeather(bean);
                    }
                }

                @Override
                public void onFailure(Call<WeatherBean> call, Throwable t) {
                    mView.showNetError();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
