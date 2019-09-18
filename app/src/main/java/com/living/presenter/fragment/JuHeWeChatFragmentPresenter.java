package com.living.presenter.fragment;

import com.google.gson.GsonBuilder;
import com.living.bean.home.WeChatBean;
import com.living.constant.home.HomeAPI;
import com.living.http.RetrofitService;
import com.living.presenter.BasePresenter;
import com.living.ui.fragment.home.WeChat.JuHeWeChatFragment;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weihao on 2018/1/5.
 */

public class JuHeWeChatFragmentPresenter extends BasePresenter {

    private final JuHeWeChatFragment juHeWeChatFragment;

    public JuHeWeChatFragmentPresenter(JuHeWeChatFragment juHeWeChatFragment) {
        this.juHeWeChatFragment = juHeWeChatFragment;
    }

    /*getWeChatData(@Query("pno") int pno,
                                         @Query("ps") int ps,
                                         @Query("key") String key);*/
    public void getJuHeWeChatData(int pro, int ps, String key, final JuHeWeChatFragment juHeWeChatFragment) {
        juHeWeChatFragment.getBaseLoadingView().showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeAPI.WeChatBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<WeChatBean> observable = service.getJeHeWeChatData(pro, ps, key);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeChatBean weChatBean) {
                        juHeWeChatFragment.getBaseLoadingView().hideLoading();
                        List<WeChatBean.ResultBean.ListBean> listBeans = weChatBean.getResult().getList();
                        JuHeWeChatFragmentPresenter.this.juHeWeChatFragment.setJuHeWeChatData(listBeans);
                    }
                });
    }
}
