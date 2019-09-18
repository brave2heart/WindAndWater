package com.tongcheng.soothsay.ui.activity.huangli.almanac;

import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;
import com.tongcheng.soothsay.data.huangli.almanac.AlmanacImp;
import com.tongcheng.soothsay.data.huangli.almanac.IAlmanac;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Steven on 16/11/11.
 */

public class AlmanacPresenter implements AlmanacConstant.Presenter{

    private AlmanacConstant.View mView;
    private IAlmanac iData;

    private List<HousYijiBean.ResultBean> hoursBean;

    public AlmanacPresenter(AlmanacConstant.View mView){
        this.mView = mView;
        iData = new AlmanacImp();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    //获取老黄历
    @Override
    public void getAlmanac(String date) {
        mView.showLoad();
        String url = String.format(Locale.CHINA,AlmanacConstant.ALMANAC_API,AlmanacConstant.ALMANAC_KEY,date);
        iData.getAlmanac(url, new Callback<AlmanacBean>() {
            @Override
            public void onResponse(Call<AlmanacBean> call, Response<AlmanacBean> response) {
                mView.showLoadFinish();
                if(response.isSuccessful()){
                    AlmanacBean bean = response.body();
                    if(bean != null && bean.getError_code() == 0){
                        bean.setHours(hoursBean);
                        mView.showAlmanac(bean);

                    }else{
                        mView.showEmpty();

                    }


                }
            }

            @Override
            public void onFailure(Call<AlmanacBean> call, Throwable t) {
                mView.showLoadFinish();
                mView.showNetError();
            }
        });
    }

    //获取时辰的宜忌
    @Override
    public void getHoursYiji(final String date) {
        String url = String.format(Locale.CHINA,AlmanacConstant.HOURSYIJI_API,AlmanacConstant.ALMANAC_KEY,date);
        iData.getHoursYiji(url, new Callback<HousYijiBean>() {
            @Override
            public void onResponse(Call<HousYijiBean> call, Response<HousYijiBean> response) {
                if(response.isSuccessful()){
                    HousYijiBean bean = response.body();

                    if(bean != null && bean.getError_code() == 0){
                        hoursBean = bean.getResult();
                        //请求老黄历
                        getAlmanac(date);
                    }
                }
            }

            @Override
            public void onFailure(Call<HousYijiBean> call, Throwable t) {
                mView.showNetError();
            }
        });
    }
}
