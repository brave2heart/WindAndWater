package com.living.presenter.fragment;

import com.google.gson.GsonBuilder;
import com.living.bean.home.JWeChatBean;
import com.living.constant.home.HomeAPI;
import com.living.http.RetrofitService;
import com.living.presenter.BasePresenter;
import com.living.ui.fragment.home.WeChat.WeChatFragment;
import com.tongcheng.soothsay.base.BaseTitleFragment;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weihao on 2018/1/3.
 */

public class WeChatFragmentPresenter extends BasePresenter {

    private final WeChatFragment weChatFragment;

    public WeChatFragmentPresenter(WeChatFragment weChatFragment) {
        this.weChatFragment = weChatFragment;
    }

    /*getJWeChatData(@Query("channelid") int channelid,
                                           @Query("start") int start,
                                           @Query("num") int num,
                                           @Query("appkey") String key*/

    public void getJWeChatData(int channelid, int start, int num, String key, final WeChatFragment weChatFragment) {
        weChatFragment.getBaseLoadingView().showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeAPI.JWeChatBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<JWeChatBean> observable = service.getJWeChatData(channelid, start, num, key);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JWeChatBean>() {


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
                    public void onNext(JWeChatBean jweChatBean) {
                        weChatFragment.getBaseLoadingView().hideLoading();
                        List<JWeChatBean.ResultBean.ListBean> listBeans = jweChatBean.getResult().getList();
                        WeChatFragmentPresenter.this.weChatFragment.setData(listBeans);
                    }
                });


    }

}
