package com.tongcheng.soothsay.ui.fragment.calculation.constellation;

import com.tongcheng.soothsay.bean.calculation.DayYunCheng;
import com.tongcheng.soothsay.bean.calculation.MonthYunCheng;
import com.tongcheng.soothsay.bean.calculation.WeekYunCheng;
import com.tongcheng.soothsay.data.calculate.constellation.HoroscopeImpl;
import com.tongcheng.soothsay.data.calculate.constellation.IHoroscope;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ALing on 2016/11/28 0028.
 */

public class HoroscopePresenter implements HoroscopeConstant.Presenter {

    private HoroscopeConstant.View mView;
    private IHoroscope iData;


    public HoroscopePresenter(HoroscopeConstant.View mView){
        this.mView = mView;
        iData = new HoroscopeImpl();
        mView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void getYunCheng(final String consName, final String type) {
        mView.showLoad();
        String url = String.format(Locale.CHINA, HoroscopeConstant.XINGZUO_API, consName, type, HoroscopeConstant.XINGZUO_KEY);
        iData.getHoroscope(url, new Callback<DayYunCheng>() {
            @Override
            public void onResponse(Call<DayYunCheng> call, Response<DayYunCheng> response) {

                if (response.isSuccessful()) {
                    mView.showLoadFinish();
                    DayYunCheng bean = response.body();
                    mView.showYunCheng(bean);
                }
            }

            @Override
            public void onFailure(Call<DayYunCheng> call, Throwable t) {
                mView.showLoadFinish();
                mView.showNetError();
            }
        });
        }

    @Override
    public void getWeekYunCheng(String consName, String type) {
        mView.showLoad();
        String url = String.format(Locale.CHINA, HoroscopeConstant.XINGZUO_API, consName, type, HoroscopeConstant.XINGZUO_KEY);
        iData.getWeekHoroscope(url, new Callback<WeekYunCheng>() {
            @Override
            public void onResponse(Call<WeekYunCheng> call, Response<WeekYunCheng> response) {

                if (response.isSuccessful()) {
                    mView.showLoadFinish();
                    WeekYunCheng bean = response.body();
                    mView.showWeekYunCheng(bean);
                }
            }

            @Override
            public void onFailure(Call<WeekYunCheng> call, Throwable t) {
                mView.showLoadFinish();
                mView.showNetError();
            }
        });
    }

    @Override
    public void getMonthYunCheng(String consName, String type) {
        mView.showLoad();
        String url = String.format(Locale.CHINA, HoroscopeConstant.XINGZUO_API, consName, type, HoroscopeConstant.XINGZUO_KEY);
        iData.getMonthHoroscope(url, new Callback<MonthYunCheng>() {
            @Override
            public void onResponse(Call<MonthYunCheng> call, Response<MonthYunCheng> response) {

                if (response.isSuccessful()) {
                    mView.showLoadFinish();
                    MonthYunCheng bean = response.body();
                    mView.showMonthCheng(bean);
                }
            }

            @Override
            public void onFailure(Call<MonthYunCheng> call, Throwable t) {
                mView.showLoadFinish();
                mView.showNetError();
            }
        });
    }

    @Override
    public void onDestroy() {
        iData.onDestroy();
    }
}
