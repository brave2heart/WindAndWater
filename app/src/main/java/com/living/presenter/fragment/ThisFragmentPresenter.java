package com.living.presenter.fragment;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.living.bean.home.ConstellationBean;
import com.living.constant.home.HomeAPI;
import com.living.constant.home.HomeType;
import com.living.http.RetrofitService;

import com.living.ui.fragment.home.FourForcunt.ThisFragment;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weihao on 2018/1/8.
 */

public class ThisFragmentPresenter {
    private final ThisFragment thisFragment;

    public ThisFragmentPresenter(ThisFragment thisFragment) {
        this.thisFragment = thisFragment;
    }

    public void getConstellationData(int consDataType, String constellation, String type, String key) {
        thisFragment.getBaseLoadingView().showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeAPI.ConstellationBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        if (consDataType == HomeType.TOMORROW) {
            Observable<ConstellationBean.TomorrowConstellationBean> observable = service.getTomorrowConstellationDate(constellation, type, key);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ConstellationBean.TomorrowConstellationBean>() {
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
                        public void onNext(ConstellationBean.TomorrowConstellationBean tBean) {
                            thisFragment.getBaseLoadingView().hideLoading();
                            thisFragment.setConsData(tBean);

                        }


                    });
        } else if (consDataType == HomeType.WEEK) {
            Observable<ConstellationBean.WeekConstellationBean> observable = service.getWeekConstellationDate(constellation, type, key);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ConstellationBean.WeekConstellationBean>() {


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
                        public void onNext(ConstellationBean.WeekConstellationBean tBean) {
                            thisFragment.getBaseLoadingView().hideLoading();
                            thisFragment.setConsData(tBean);
                        }


                    });
        } else if (consDataType == HomeType.MONTH) {
            Observable<ConstellationBean.MonthConstellationBean> observable = service.getMonthConstellationDate(constellation, type, key);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ConstellationBean.MonthConstellationBean>() {


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
                        public void onNext(ConstellationBean.MonthConstellationBean tBean) {
                            thisFragment.getBaseLoadingView().hideLoading();
                            thisFragment.setConsData(tBean);
                        }


                    });

        } else if (consDataType == HomeType.YEAR) {
            Observable<ConstellationBean.YearConstellationBean> observable = service.getyearConstellationDate(constellation, type, key);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ConstellationBean.YearConstellationBean>() {


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
                        public void onNext(ConstellationBean.YearConstellationBean tBean) {
                            thisFragment.getBaseLoadingView().hideLoading();
                            thisFragment.setConsData(tBean);

                        }


                    });

        }

    }
}
