package com.tongcheng.soothsay.data.huangli.oldAlmanac;


import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Steven on 16/10/26.
 */

public interface IOldAlmanac {

    void getCalendar(String url, Callback<CalendarBean> call);
    void getWeather(String url, Callback<WeatherBean> call);
    void getFortune(String url, Callback<HFortuneBean> call);
    void getNews(ApiCallBack<ApiResponseBean<List<NewsBean>>> callback);

    List<CalendarNoteBean> getCalendarNote(String date);
    Void saveCalendarNote(CalendarNoteBean bean);
    Void updataCalendarNote(CalendarNoteBean bean);
    Void deletCalendarNote(CalendarNoteBean bean);
}
