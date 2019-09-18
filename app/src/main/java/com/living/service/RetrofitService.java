package com.living.service;


import com.living.bean.home.ConstellationBean;
import com.living.service.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("consName") String name,
                                    @Query("type") String type,
                                    @Query("key") String key);


    //聚合：星座运势
//    http://web.juhe.cn:8080/constellation/getAll?consName=处女座&type=today&key=56521a14b05ea90f0989adc973c41c97
    @GET("constellation/getAll")
    Observable<ConstellationBean> getConstellationData(@Query("consName") String name,
                                                       @Query("type") String type,
                                                       @Query("key") String key);
}
