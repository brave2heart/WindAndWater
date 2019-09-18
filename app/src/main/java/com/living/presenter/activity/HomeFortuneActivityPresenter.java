package com.living.presenter.activity;

import com.google.gson.GsonBuilder;
import com.living.bean.home.ConstellationBean;
import com.living.constant.home.HomeAPI;
import com.living.http.RetrofitService;
import com.living.presenter.BasePresenter;
import com.living.ui.activity.HomeFortuneActivity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weihao on 2017/12/29.
 */

public class HomeFortuneActivityPresenter extends BasePresenter {
    private final HomeFortuneActivity homeFortuneActivity;

    public HomeFortuneActivityPresenter(HomeFortuneActivity homeFortuneActivity) {
        this.homeFortuneActivity = homeFortuneActivity;
    }

    public void getConstellationDate(String constellation, String type, String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeAPI.ConstellationBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<ConstellationBean.TodayConstellationBean> observable = service.getConstellationDate(constellation, type, key);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConstellationBean.TodayConstellationBean>() {


                    @Override
                    public void onCompleted() {
                        //所有事情完成，可以做些操作
//                        mRecyclerview.setAdapter(new TopFragmentAdapter(getActivity(), mData));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ConstellationBean.TodayConstellationBean todayConstellationBean) {
                        homeFortuneActivity.getBaseLoadingView().hideLoading();
                        homeFortuneActivity.setData(todayConstellationBean);
                    }


                });
    }
}
