package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi;

import android.util.Log;

import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShiyeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziSzshishenBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziXinggeBean;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziYinyuanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.data.calculate.zhuanyun.BaziPaipan.IBaZi;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.BaseCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public class BaziMainPresenter implements BaziMainContract.BaziMainPresener {

    /**
     * 要查询的八字
     */
    HashMap<String,String> map;

    private final IBaZi mBaZi;
    private final BaziMainContract.BaZiMainView mView;

    public BaziMainPresenter(IBaZi baZi, BaziMainContract.BaZiMainView view){
        mBaZi = baZi;
        mView = view;
        mView.setPresenter(this);

    }
    @Override
    public void getYinyun() {

        mBaZi.getYinYuan(map, new BaseCallBack<BaziYinyuanBean>() {
            @Override
            public void onSuccess(BaziYinyuanBean data) {
                if (data!=null) {
                    mView.showYinyun();
                    EventBus.getDefault().post(data);
                }
            }

            @Override
            public void onError(String errorCode, String message) {
            mView.showError(message);
            }
        });
//        mBaZi.getYinYuan();
    }

    @Override
    public void getXingge() {

        mBaZi.getXingGe(map, new BaseCallBack<BaziXinggeBean>() {
            @Override
            public void onSuccess(BaziXinggeBean data) {
                if (data!=null) {
                    mView.showXingge();
                    EventBus.getDefault().post(data);
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                mView.showError(message);

            }
        });

    }

    @Override
    public void getShiye() {

        mBaZi.getShiYe(map, new BaseCallBack<BaziShiyeBean>() {
            @Override
            public void onSuccess(BaziShiyeBean data) {
                if (data!=null) {
                    mView.showShiye();
                    EventBus.getDefault().post(data);
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                mView.showError(message);

            }
        });

    }

    @Override
    public void getXiantianmingpan() {
            mBaZi.getXTpapan(map, new BaseCallBack<List<BaziSzshishenBean>>() {
                @Override
                public void onSuccess(List<BaziSzshishenBean> data) {
                    if (data!=null) {
                        mView.showXiantiammingpan();
                        EventBus.getDefault().post(data);
                    }
                }

                @Override
                public void onError(String errorCode, String message) {
                    mView.showError(message);

                }
            });
    }

    @Override
    public void setMap( HashMap<String,String> map){
        this.map=map;
    }

    @Override
    public HashMap<String, String> getMap() {
        return map;
    }




    @Override
    public void getPaipan() {
        mView.showLoad();
        mBaZi.getPaipan(map, new BaseCallBack<BaziPaipanBean>() {
            @Override
            public void onSuccess(BaziPaipanBean data) {
                if(data != null){
                    mView.showLoadFinish();
                    String date = String.valueOf(map.get("year") + "年" + map.get("month") + "月" + map.get("day") + "日" + map.get("hour")+"时");
                    BaziUserBean baziUserBean = new BaziUserBean(1L, map.get("name"),  (map.get("sex").equals("1") ? "男" : "女"),date, 0);
                    mView.showPaipan(data,baziUserBean);
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



    @Override
    public void setUser() {
        EventBus.getDefault().post(map);
    }


    @Override
    public void start() {

    }


}
