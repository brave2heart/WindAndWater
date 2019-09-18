package com.tongcheng.soothsay.data.huangli.oldAlmanac;

import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBeanDao;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.bean.huangli.TodayWeatherBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.data.BaseDataImp;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.service.HuangliApi;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Steven on 16/10/26.
 */

public class OldAlmanacImp extends BaseDataImp implements IOldAlmanac {

    @Override
    public void getCalendar(String url, Callback<CalendarBean> call) {
        HuangliApi.getInstance().getCalendar(url).enqueue(call);
    }

    @Override
    public void getWeather(String url, Callback<WeatherBean> call) {
        HuangliApi.getInstance().getWeather(url).enqueue(call);
    }

    @Override
    public void getFortune(String url, Callback<HFortuneBean> call) {
        HuangliApi.getInstance().getFortune(url).enqueue(call);
    }

    @Override
    public void getNews(ApiCallBack<ApiResponseBean<List<NewsBean>>> callback) {
        HuangliApi.getInstance().getNews().enqueue(callback);
    }

    @Override
    public List<CalendarNoteBean> getCalendarNote(String date) {
        CalendarNoteBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCalendarNoteBeanDao();
        List<CalendarNoteBean> beans = dao.queryBuilder().where(CalendarNoteBeanDao.Properties.Date.eq(date)).list();
        return beans;
    }

    @Override
    public Void saveCalendarNote(CalendarNoteBean bean) {
        CalendarNoteBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCalendarNoteBeanDao();
        dao.insert(bean);
        return null;
    }

    @Override
    public Void updataCalendarNote(CalendarNoteBean bean) {
        CalendarNoteBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCalendarNoteBeanDao();
        dao.update(bean);
        return null;
    }

    @Override
    public Void deletCalendarNote(CalendarNoteBean bean) {
        CalendarNoteBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getCalendarNoteBeanDao();
        dao.delete(bean);
        return null;
    }
}
