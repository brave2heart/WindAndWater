package com.living.presenter;

import com.google.gson.GsonBuilder;
import com.living.adapter.home.TopFragmentAdapter;
import com.living.bean.home.TopBean;
import com.living.constant.home.HomeAPI;
import com.living.http.RetrofitService;
import com.living.ui.fragment.home.TouTiao.NewsBaseFragment;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weihao on 2017/12/27.
 */

public class NewsBaseFragmentPresenter extends BasePresenter {



    private final NewsBaseFragment newsBaseFragment;

    public NewsBaseFragmentPresenter(NewsBaseFragment newsBaseFragment) {
        this.newsBaseFragment = newsBaseFragment;
    }

    public void getData(String type, String key) {

            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeAPI.TouTiaoBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<TopBean> observable = service.getTopDate(type, key);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopBean>() {


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
                    public void onNext(TopBean topBean) {
                        newsBaseFragment.setData((topBean.getResult().getData()));
                    }
                });


    }
}
