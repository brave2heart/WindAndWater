package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan;

import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShiyeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziSzshishenBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziXinggeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziYinyuanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.data.calculate.chuantong.ziwei.IZiwei;
import com.tongcheng.soothsay.data.calculate.chuantong.ziwei.ZiweiImp;
import com.tongcheng.soothsay.data.calculate.zhuanyun.BaziPaipan.BaZiImp;
import com.tongcheng.soothsay.data.calculate.zhuanyun.BaziPaipan.IBaZi;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.BaZiApi;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;



public class BaziPaipanInputPresenter implements BaziPaipanInputConstant.Presenter{

    private IBaZi iData;
    private BaziPaipanInputConstant.View mView;


    public BaziPaipanInputPresenter(BaziPaipanInputConstant.View view){
        iData = new BaZiImp();
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {}

    //获取排盘
    @Override
    public void getPaipan(HashMap<String,String> map) {
        mView.showLoad();
        iData.getPaipan(map, new BaseCallBack<BaziPaipanBean>() {
            @Override
            public void onSuccess(BaziPaipanBean data) {
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


    /**
     * 获取八字婚姻
     * @param map
     */
    @Override
    public void getYinYuan(HashMap<String, String> map) {
        iData.getYinYuan(map, new BaseCallBack<BaziYinyuanBean>(){
            @Override
            public void onSuccess(BaziYinyuanBean data) {

            }

            @Override
            public void onError(String errorCode, String message) {
            }
        });
    }

    /**
     * 获取八字事业
     * @param map
     */
    @Override
    public void getShiYe(HashMap<String, String> map) {
        iData.getShiYe(map, new BaseCallBack<BaziShiyeBean>() {
            @Override
            public void onSuccess(BaziShiyeBean data) {

            }

            @Override
            public void onError(String errorCode, String message) {

            }
        });
    }

    /**
     * 获取八字性格
     * @param map
     */
    @Override
    public void getXingGe(HashMap<String, String> map) {
        iData.getXingGe(map, new BaseCallBack<BaziXinggeBean>() {
            @Override
            public void onSuccess(BaziXinggeBean data) {

            }

            @Override
            public void onError(String errorCode, String message) {

            }
        });
    }

    /**
     * 获取八字先天命盘
     * @param map
     */
    @Override
    public void getXTpapan(HashMap<String, String> map) {
        iData.getXTpapan(map, new BaseCallBack<BaziSzshishenBean>() {
            @Override
            public void onSuccess(BaziSzshishenBean data) {

            }

            @Override
            public void onError(String errorCode, String message) {

            }
        });
    }






    //获取历史记录
    @Override
    public void getHistory() {
        Observable.just(iData.getHistory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<BaziUserBean>>() {
                    @Override
                    public void call(List<BaziUserBean> baziUserBeen) {
                        mView.showHistory(baziUserBeen);
                    }
                });
    }

    //插入一条历史记录
    @Override
    public void saveHistory(final BaziUserBean bean) {
        Observable.just(iData.saveHistory(bean))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(aLong != -1){
                            List<BaziUserBean> beans = new ArrayList<BaziUserBean>();
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
