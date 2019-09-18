package com.tongcheng.soothsay.ui.fragment.huangli;

import android.content.Context;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBean;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;

import java.util.List;

/**
 * Created by Steven on 16/10/25.
 */

public class OldAlmanacConstant {

    public static final int PERMISSIONS_REQUEST = 100;
    public static final int INTENT_RESULT = 10;
    public static final int INTENT_UPDATA_RESULT = 11;
    public static final String INTENT_KEY = "flag";
    public static final String INTENT_DATE = "date";

    public static final String HUANGLI_KEY = "c11f7459b95b0961ff5ff00335223a20";
    public static final String WEATHER_KEY = "85fe0970ec66efbe412cf688e6db5482";
    public static final String XINGZUO_KEY = "56521a14b05ea90f0989adc973c41c97";

    public static final String CALENDAR_API = "http://v.juhe.cn/calendar/day?date=%s&key=%s";
    public static final String WEATHER_API = "http://v.juhe.cn/weather/index?format=2&cityname=%s&key=%s";
    public static final String XINGZUO_API = "http://web.juhe.cn:8080/constellation/getAll?consName=%s&type=today&key=%s";


    public interface View extends BaseUiView<Presenter>{
        void showWeather(WeatherBean Weather);
        void showCalendar(CalendarBean bean);
        void showCalendarNote(List<CalendarNoteBean> bean);
        void showFortune(HFortuneBean bean);
        void showNews(List<NewsBean> beans);
        void showNoFortune();
        void locationFinish(String city);
        void locationFailure();
    }

    public interface Presenter extends BasePresenter{
        void getCalendar(String date);
        void getWeather(String cityName);
        void getFortune(String consName);
        void getNews();

        void getCalendarNote(String date);
        void saveCalendarNote(Context context, CalendarNoteBean bean);
        void updataCalendarNote(Context context,CalendarNoteBean bean);
        void deletCalendarNote(Context context,CalendarNoteBean bean);

        void startLocation(Context context);
        void stopLocation();

        int compareDate(String date,int currYear,int currMonth, int currDay);
    }

}
