package com.living.http;

import com.living.bean.home.JWeChatBean;
import com.living.bean.home.TopBean;
import com.living.bean.home.ConstellationBean;
import com.living.bean.home.WeChatBean;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.http.ApiResponseBean;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by weihao on 2017/12/25.
 */


public interface RetrofitService {
    // TODO: 2018/1/16 HomeAPI
    /*---------------------------------------home----------------------------------------------*/

    // 聚合：新闻头条
    //        http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    @GET("toutiao/index")
    Observable<TopBean> getTopDate(@Query("type") String type,
                                   @Query("key") String key);


    //聚合：星座运势
//    http://web.juhe.cn:8080/constellation/getAll?consName=处女座&type=today&key=56521a14b05ea90f0989adc973c41c97

    /*今天运势*/
    @GET("constellation/getAll")
    Observable<ConstellationBean.TodayConstellationBean> getConstellationDate(@Query("consName") String name,
                                                                              @Query("type") String type,
                                                                              @Query("key") String key);

    /*明天运势*/
    @GET("constellation/getAll")
    Observable<ConstellationBean.TomorrowConstellationBean> getTomorrowConstellationDate(@Query("consName") String name,
                                                                                         @Query("type") String type,
                                                                                         @Query("key") String key);

    /*本周运势*/
    @GET("constellation/getAll")
    Observable<ConstellationBean.WeekConstellationBean> getWeekConstellationDate(@Query("consName") String name,
                                                                                 @Query("type") String type,
                                                                                 @Query("key") String key);


    /*本月运势*/
    @GET("constellation/getAll")
    Observable<ConstellationBean.MonthConstellationBean> getMonthConstellationDate(@Query("consName") String name,
                                                                                   @Query("type") String type,
                                                                                   @Query("key") String key);

    /*本年运势*/
    @GET("constellation/getAll")
    Observable<ConstellationBean.YearConstellationBean> getyearConstellationDate(@Query("consName") String name,
                                                                                 @Query("type") String type,
                                                                                 @Query("key") String key);


    /*聚合：微信精选*/
//    pno 页数，默认1
//    ps 返回条数 默认20，最大50
//    http://v.juhe.cn/weixin/query?pno=1&ps=20&key=bc825580457c03c5da919f58f851ebb6
    @GET("weixin/query")
    Observable<WeChatBean> getJeHeWeChatData(@Query("pno") int pno,
                                             @Query("ps") int ps,
                                             @Query("key") String key);

    /*极速数据：微信精选*/

//    http://api.jisuapi.com/weixinarticle/get?channelid=1&start=0&num=10&appkey=846dc0d6176c6b95

    @GET("weixinarticle/get")
    Observable<JWeChatBean> getJWeChatData(@Query("channelid") int channelid,
                                           @Query("start") int start,
                                           @Query("num") int num,
                                           @Query("appkey") String key
    );

    // TODO: 2018/1/16 MingLiAPI
    /*--------------------------------------命理等API-----------------------------------------------------------*/

    /**
     * 商城首页
     *
     * @return
     */
    @POST("/publish/store/topMerchandiseList.do")
    @FormUrlEncoded
    Observable<ApiResponseBean<ShopHomeBean>> getShopHome(@Field("") String str);

}





