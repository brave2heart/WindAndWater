package com.tongcheng.soothsay.http.service;

import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;
import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.http.ApiBuild;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 黄历请求api
 * Created by wuyunan on 16/10/26.
 */
public class HuangliApi {

    public static final String ALMANAC_KEY = "a3ddf48ea577a3050268a405db17d4ef";

    public static final String ALMANAC_API = "http://v.juhe.cn/laohuangli/d?key=%s&date=%s";

    private static Service instance;

    public interface Service {

        @GET
        Call<CalendarBean> getCalendar(@Url String url);

        @GET
        Call<WeatherBean> getWeather(@Url String url);

        @GET
        Call<HFortuneBean> getFortune(@Url String url);

        @GET
        Call<AlmanacBean> getAlmanac(@Url String url);

        @GET
        Call<HousYijiBean> getHoursYiji(@Url String url);

        @POST("publish/news/getLatestNewsList.do")
        Call<ApiResponseBean<List<NewsBean>>> getNews();

    }

    public static Service getInstance(){
        if(instance == null){
            instance = ApiBuild.getRetrofit().create(Service.class);
        }
        return  instance;
    }

}
