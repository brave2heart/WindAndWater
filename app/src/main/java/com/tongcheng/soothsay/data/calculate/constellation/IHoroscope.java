package com.tongcheng.soothsay.data.calculate.constellation;

import com.tongcheng.soothsay.bean.calculation.DayYunCheng;
import com.tongcheng.soothsay.bean.calculation.MonthYunCheng;
import com.tongcheng.soothsay.bean.calculation.WeekYunCheng;

import retrofit2.Callback;

/**
 * Created by ALing on 2016/11/28 0028.
 */

public interface IHoroscope {
    void getHoroscope(String url , Callback<DayYunCheng> callback);
    void getWeekHoroscope(String url, Callback<WeekYunCheng> callback);
    void getMonthHoroscope(String url, Callback<MonthYunCheng> callback);
    void onDestroy();
}
