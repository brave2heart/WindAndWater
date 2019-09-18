package com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei;

import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.data.calculate.chuantong.ziwei.IZiwei;
import com.tongcheng.soothsay.data.calculate.chuantong.ziwei.ZiweiImp;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Steven on 16/11/16.
 */

public class ZiweiInputPresenter implements ZiweiInputConstant.Presenter{

    private IZiwei iData;
    private ZiweiInputConstant.View mView;


    public ZiweiInputPresenter(ZiweiInputConstant.View view){
        iData = new ZiweiImp();
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {}

    //获取紫微排盘
    @Override
    public void getPaipan(HashMap<String,String> map) {
        mView.showLoad();
        iData.getPaipan(map, new BaseCallBack<ZiweipaipanBean>() {
            @Override
            public void onSuccess(ZiweipaipanBean data) {
                if(data != null){
                    mView.showLoadFinish();
                    mView.showPaipan(data);
                }
            }

            @Override
            public void onError(String info,String msg) {
                mView.showLoadFinish();
                if(ApiCallBack.NET_ERROR.equals(info)){
                    mView.showNetError();
                }else {
                    mView.showError(msg);
                }
            }
        });
    }

    //获取历史记录
    @Override
    public void getHistory() {
        Observable.just(iData.getHistory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ZiweiUserBean>>() {
                    @Override
                    public void call(List<ZiweiUserBean> ziweiUserBeen) {
                        mView.showHistory(ziweiUserBeen);
                    }
                });
    }

    //插入一条历史记录
    @Override
    public void saveHistory(final ZiweiUserBean bean) {
        Observable.just(iData.saveHistory(bean))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(aLong != -1){
                            List<ZiweiUserBean> beans = new ArrayList<ZiweiUserBean>();
                            beans.add(bean);
                            mView.showHistory(beans);
                        }
                    }
                });
    }

    //清空历史记录
    @Override
    public void delHistory() {
        Observable.just(iData.delHistory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.showHistory(null);
                    }
                });
    }
}
