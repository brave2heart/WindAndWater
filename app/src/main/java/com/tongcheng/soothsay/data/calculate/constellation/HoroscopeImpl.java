package com.tongcheng.soothsay.data.calculate.constellation;

import com.tongcheng.soothsay.bean.calculation.DayYunCheng;
import com.tongcheng.soothsay.bean.calculation.MonthYunCheng;
import com.tongcheng.soothsay.bean.calculation.WeekYunCheng;
import com.tongcheng.soothsay.data.BaseDataImp;
import com.tongcheng.soothsay.http.service.ConstellationApi;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ALing on 2016/11/28 0028.
 */

public class HoroscopeImpl extends BaseDataImp implements IHoroscope {
    @Override
    public void getHoroscope(String url, Callback<DayYunCheng> callback) {
        Call call = ConstellationApi.getInstance().getYunCheng(url);
        call.enqueue(callback);
        addApiCall(call);
    }

    @Override
    public void getWeekHoroscope(String url, Callback<WeekYunCheng> callback) {
        Call call = ConstellationApi.getInstance().getWeekYunCheng(url);
        call.enqueue(callback);
        addApiCall(call);
    }

    @Override
    public void getMonthHoroscope(String url, Callback<MonthYunCheng> callback) {
        Call call = ConstellationApi.getInstance().getMonthYunCheng(url);
        call.enqueue(callback);
        addApiCall(call);
    }

}
