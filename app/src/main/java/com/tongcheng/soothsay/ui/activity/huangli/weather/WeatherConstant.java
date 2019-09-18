package com.tongcheng.soothsay.ui.activity.huangli.weather;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;

/**
 * Created by Steven on 16/11/10.
 */

public class WeatherConstant {

    public static final String INTENT_CITY = "city";
    public static final String INTENT_DATE = "date";
    public static final String INTENT_BEAN = "bean";

    public interface View extends BaseUiView<Presenter>{
        void showWeather(WeatherBean Weather);
    }

    public interface Presenter extends BasePresenter{
        void getWeather(String cityName);
    }


}
