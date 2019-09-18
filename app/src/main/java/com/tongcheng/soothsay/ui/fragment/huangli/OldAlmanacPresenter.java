package com.tongcheng.soothsay.ui.fragment.huangli;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBean;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.data.huangli.oldAlmanac.IOldAlmanac;
import com.tongcheng.soothsay.data.huangli.oldAlmanac.OldAlmanacImp;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.utils.AlarmManagerUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Steven on 16/10/25.
 */

public class OldAlmanacPresenter implements OldAlmanacConstant.Presenter{
    private static final String TAG = "OldAlmanacPresenter";

    private OldAlmanacConstant.View mView;
    private IOldAlmanac iData;

    private LocationClient mLocationClient = null;

    public OldAlmanacPresenter(OldAlmanacConstant.View view) {
        mView = view;
        mView.setPresenter(this);
        iData = new OldAlmanacImp();
    }

    @Override
    public void start() {

    }


    /**
     * 获取当天的万年历
     * @param date 2012-1-1
     */
    @Override
    public void getCalendar(String date) {
        String url = String.format(Locale.CHINA,OldAlmanacConstant.CALENDAR_API,date,OldAlmanacConstant.HUANGLI_KEY);
        iData.getCalendar(url, new Callback<CalendarBean>() {
            @Override
            public void onResponse(Call<CalendarBean> call, Response<CalendarBean> response) {

                if(response.isSuccessful()){
                    CalendarBean bean = response.body();
                    mView.showCalendar(bean);
                }
            }

            @Override
            public void onFailure(Call<CalendarBean> call, Throwable t) {
                mView.showNetError();
            }
        });
    }

    /**
     * 获取当天的天气
     * @param cityName 城市名字
     */
    @Override
    public void getWeather(String cityName) {
        String url = String.format(Locale.CHINA,OldAlmanacConstant.WEATHER_API,cityName,OldAlmanacConstant.WEATHER_KEY);
        iData.getWeather(url, new Callback<WeatherBean>() {

            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                if(response.isSuccessful()){
                    WeatherBean bean = response.body();
                    mView.showWeather(bean);
                }
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                mView.showNetError();
            }
        });
    }

    //获取今日运势  consName 星座名字 白羊座
    @Override
    public void getFortune(String consName) {
        try {
            String temp = URLEncoder.encode(consName,"utf-8");
            String url = String.format(Locale.CHINA,OldAlmanacConstant.XINGZUO_API,temp,OldAlmanacConstant.XINGZUO_KEY);
            Log.v(TAG, url);
            iData.getFortune(url, new Callback<HFortuneBean>() {
                @Override
                public void onResponse(Call<HFortuneBean> call, Response<HFortuneBean> response) {
                    if(response.isSuccessful()){
                        HFortuneBean bean = response.body();

                        if(bean != null && "200".equals(bean.getResultcode())){
                            mView.showFortune(bean);
                        }else{
                            mView.showNoFortune();
                        }

                    }else{
                        mView.showFortune(null);
                    }
                }

                @Override
                public void onFailure(Call<HFortuneBean> call, Throwable t) {
                    mView.showNetError();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getNews() {
        iData.getNews(new ApiCallBack<ApiResponseBean<List<NewsBean>>>(new BaseCallBack() {

            @Override
            public void onSuccess(Object data) {
                List<NewsBean> newsBeen = (List<NewsBean>) data;
                if(newsBeen != null && newsBeen.size() > 0){
                    mView.showNews(newsBeen);
                }

            }

            @Override
            public void onError(String errorCode, String message) {
                mView.showError(message);
            }
        }));
    }

    //获取指定日期的记事or提醒  date 2015.2.1
    @Override
    public void getCalendarNote(String date) {
        //异步查询数据库
        Observable.just(iData.getCalendarNote(date))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CalendarNoteBean>>() {
                    @Override
                    public void call(List<CalendarNoteBean> calendarNoteBeen) {
                        mView.showCalendarNote(calendarNoteBeen);
                    }
                });

    }

    //保存记事
    @Override
    public void saveCalendarNote(Context context, CalendarNoteBean bean) {
        Observable.just(iData.saveCalendarNote(bean)).subscribeOn(Schedulers.io());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String time = sdf.format(bean.getTime());
        AlarmManagerUtil.setAlarm(context,0,time,bean.getAlarm(),0,bean.getTitle(),2);
    }

    //修改记事
    @Override
    public void updataCalendarNote(Context context, final CalendarNoteBean bean){
        Observable.just(iData.updataCalendarNote(bean))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getCalendarNote(bean.getDate());
                    }
                });
        AlarmManagerUtil.cancelAlarm(context,AlarmManagerUtil.ALARM_ACTION,bean.getAlarm());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String time = sdf.format(bean.getTime());
        AlarmManagerUtil.setAlarm(context,0,time,bean.getAlarm(),0,bean.getTitle(),2);
    }

    @Override
    public void deletCalendarNote(Context context, final CalendarNoteBean bean) {
        Observable.just(iData.deletCalendarNote(bean))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });
        AlarmManagerUtil.cancelAlarm(context,AlarmManagerUtil.ALARM_ACTION,bean.getAlarm());
    }

    /**
     * 开始定位
     */
    @Override
    public void startLocation(Context context) {
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    /**
     * 结束定位
     */
    @Override
    public void stopLocation() {
        if (mLocationClient == null) return ;
        mLocationClient.unRegisterLocationListener(locationListener);
        mLocationClient.stop();
        mLocationClient = null;
    }

    //比较指定日期是否大于今天   return 0 是日期相等 1是小于 2是大于  data "2012-10-1"
    @Override
    public int compareDate(String date, int currYear, int currMonth, int currDay) {
        try {
            Calendar currCal = Calendar.getInstance();
            Calendar dateCal = Calendar.getInstance();

            SimpleDateFormat cdf = new SimpleDateFormat("yyyy.MM.dd");
            currCal.setTime(cdf.parse(currYear+"."+currMonth+"."+currDay));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            dateCal.setTime(sdf.parse(date));

            long currTime = currCal.getTime().getTime();
            long dateTiem = dateCal.getTime().getTime();

            //表示过去的时间
            if(currTime > dateTiem){
                return 1;
            }

            //表示未来的时间
            if(currTime < dateTiem){
                return 2;
            }

            //表示今天的时间
            if(currTime < dateTiem){
                return 0;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 百度定位回调
     */
    private BDLocationListener locationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            switch (bdLocation.getLocType()){
                case BDLocation.TypeGpsLocation:
                case BDLocation.TypeNetWorkLocation:
                    mView.locationFinish(bdLocation.getCity());
                    stopLocation();
                    break;

                //网络连接失败
                case BDLocation.TypeServerCheckKeyError:
                case BDLocation.TypeNetWorkException:
                    mView.locationFailure();
                    break;

            }
        }
    };

}
