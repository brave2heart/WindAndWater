package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.DayYunCheng;
import com.tongcheng.soothsay.bean.calculation.MonthYunCheng;
import com.tongcheng.soothsay.bean.calculation.WeekYunCheng;
import com.tongcheng.soothsay.http.ApiBuild;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by ALing on 2016/11/25 0025.
 */

public class ConstellationApi {
    private static ConstellationApi.Service instance;

    public interface Service {

        @GET
        Call<DayYunCheng> getYunCheng(@Url String url);

        @GET
        Call<WeekYunCheng> getWeekYunCheng(@Url String url);

        @GET
        Call<MonthYunCheng> getMonthYunCheng(@Url String url);


    }

    public static ConstellationApi.Service getInstance(){
        if(instance == null){
            instance = ApiBuild.getRetrofit().create(ConstellationApi.Service.class);
        }
        return  instance;
    }

}
