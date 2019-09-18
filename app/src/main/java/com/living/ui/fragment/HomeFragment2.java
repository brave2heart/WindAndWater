package com.living.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.living.adapter.home.HomeFragmentPagerAdapter;
import com.living.ui.activity.HomeFortuneActivity;
import com.living.utils.WeatherUtil;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBean;
import com.tongcheng.soothsay.bean.dao.UserBean;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.bean.event.EventNoteBean;
import com.tongcheng.soothsay.bean.event.LoginRefreshBean;
import com.tongcheng.soothsay.bean.huangli.CalendarBean;
import com.tongcheng.soothsay.bean.huangli.FortuneBean;
import com.tongcheng.soothsay.bean.huangli.HFortuneBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.BuglyHelper;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.other.BaZi;
import com.tongcheng.soothsay.other.XingZuoUtils;
import com.tongcheng.soothsay.other.calendar.bizs.calendars.DPCNCalendar;
import com.tongcheng.soothsay.other.calendar.cons.DPMode;
import com.tongcheng.soothsay.ui.activity.classroom.NewsActivity;
import com.tongcheng.soothsay.ui.activity.huangli.AddNoteActivity;
import com.tongcheng.soothsay.ui.activity.huangli.UpdataNoteActivity;
import com.tongcheng.soothsay.ui.activity.huangli.almanac.AlmanacActivity;
import com.tongcheng.soothsay.ui.activity.huangli.almanac.AlmanacConstant;
import com.tongcheng.soothsay.ui.activity.huangli.weather.WeatherActivity;
import com.tongcheng.soothsay.ui.activity.huangli.weather.WeatherConstant;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacPresenter;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.AnimationProgress;
import com.tongcheng.soothsay.widget.CircularProgress;
import com.tongcheng.soothsay.widget.MonthView;
import com.tongcheng.soothsay.widget.RoundImageView;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 老黄历
 * Created by wuyunan on 16/10/24.
 */
public class HomeFragment2 extends BaseTitleFragment implements OldAlmanacConstant.View, MonthView.OnDateChangeListener, MonthView.OnDatePickedListener, View.OnClickListener {

    //    @BindView(R.id.huangli_monthScrollView)
//    MonthScrollView scrollView;

    @BindView(R.id.month_calendar)
    MonthView monthView;
        //个人运势
    @BindView(R.id.tv_add_note_date)
    TextView tvAddNoteDate;
    @BindView(R.id.tv_huangli_year)
    TextView tvTitleDate;
    @BindView(R.id.tv_huangli_old)
    TextView tvTitleOldDate;
    @BindView(R.id.tv_tianqi_reLoca)
    TextView tvReloca;           //百度定位结果回调
    @BindView(R.id.btn_add_note)
    TextView btnAddNote;         //添加记事
    @BindView(R.id.ll_add_note_hide)
    View viewAddHide;        //如果是同一天就不允许用户添加记事
    @BindView(R.id.rl_tianqi)
    View layoutWeather;
    @BindView(R.id.rl_almanac)
    View layoutAlmanac;
    @BindView(R.id.ll_huangli_add_note)
    LinearLayout layoutNote;



    //今天天气相关
    @BindView(R.id.tv_tianqi_status)
    TextView tvWeather;
    @BindView(R.id.tv_tianqi_fanwei)
    TextView tvFanwei;
    @BindView(R.id.tv_tianqi_wendu)
    TextView tvWendu;
    @BindView(R.id.tv_tianqi_loca)
    TextView tvLoca;
    @BindView(R.id.tv_tianqi_wind)
    TextView tvWind;
    @BindView(R.id.tv_tianqi_shidu)
    TextView tvShidu;
    @BindView(R.id.tv_tianqi_ziwai)
    TextView tvZiwai;
    @BindView(R.id.img_tianqi_icon)
    ImageView imgWeatherIcon;

    //今天万年历相关
    @BindView(R.id.tv_yunshi_date)
    TextView tvYunshiDate;
    @BindView(R.id.tv_yunshi_week)
    TextView mTvYunshiWeek;
    @BindView(R.id.tv_yunshi_intro)
    TextView tvDateIntro;
    @BindView(R.id.tv_yunshi_suit)
    TextView tvSuit;
    @BindView(R.id.tv_yunshi_ban)
    TextView tvBan;
    @BindView(R.id.home_tv_my_yunshi)
    TextView tvYunshi;
    @BindView(R.id.home_tv_ganenjie)
    TextView mHomeTvGanenjie;       //节假日

    //个人运势
    @BindView(R.id.img_yunshi_icon)
    RoundImageView imgIcon;
    @BindView(R.id.img_exmple_icon)
    ImageView exmpleIcon;
    @BindView(R.id.tv_yunshi_name)
    TextView tvName;
    @BindView(R.id.cp_yunshi_score)
    CircularProgress cProgress;
    @BindView(R.id.ap_yunshi_all)
    AnimationProgress apAll;
    @BindView(R.id.ap_yunshi_money)
    AnimationProgress apMoney;
    @BindView(R.id.ap_yunshi_love)
    AnimationProgress apLove;
    @BindView(R.id.ap_yunshi_work)
    AnimationProgress apWork;


    //查看我的运势前面大文字 23
    @BindView(R.id.text_data)
    TextView mTextData;
    @BindView(R.id.tv_yunshi_yi)
    ImageView mTvYunshiYi;
    Unbinder unbinder;


    @BindView(R.id.img_tianqi_right)
    ImageView mImgTianqiRight;
    @BindView(R.id.img_tianqi_0)
    ImageView mImgTianqi0;
    @BindView(R.id.tv_tianqi_tomorrow0)
    TextView mTvTianqiTomorrow0;
    @BindView(R.id.tv_tianqi_status_xiamian0)
    TextView mTvTianqiStatusXiamian0;
    @BindView(R.id.view_ge)
    View mViewGe;
    @BindView(R.id.tv_tianqi_you0)
    TextView mTvTianqiYou0;
    @BindView(R.id.tv_tianqi_fanwei0)
    TextView mTvTianqiFanwei0;
    @BindView(R.id.img_tianqi_1)
    ImageView mImgTianqi1;
    @BindView(R.id.tv_tianqi_tomorrow1)
    TextView mTvTianqiTomorrow1;
    @BindView(R.id.tv_tianqi_status_xiamian1)
    TextView mTvTianqiStatusXiamian1;
    @BindView(R.id.view_ge1)
    View mViewGe1;
    @BindView(R.id.tv_tianqi_you1)
    TextView mTvTianqiYou1;
    @BindView(R.id.tv_tianqi_fanwei1)
    TextView mTvTianqiFanwei1;
    @BindView(R.id.img_tianqi_2)
    ImageView mImgTianqi2;
    @BindView(R.id.tv_tianqi_status_xiamian2)
    TextView mTvTianqiStatusXiamian2;
    @BindView(R.id.tv_tianqi_you2)
    TextView mTvTianqiYou2;
    @BindView(R.id.tv_tianqi_tomorrow2)
    TextView mTvTianqiTomorrow2;
    @BindView(R.id.tv_tianqi_fanwei2)
    TextView mTvTianqiFanwei2;
    @BindView(R.id.ll_huangli_weather)
    LinearLayout mLlHuangliWeather;




    @BindView(R.id.tablayout_oldhuangli)
    XTabLayout mTablayoutOldhuangli;
    @BindView(R.id.home_ll_tablayout1)
    LinearLayout mHomeLlTablayout1;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.viewpager_oldhuangli)
    ViewPager mViewpagerOldhuangli;



    //viewpager
    private ViewPager mViewPager;





    private BaZi bazi;
    private Calendar cal;
    private FortuneBean fortuneBean;
    private WeatherBean weatherBean;

    private MDAlertDialog dialog;

    private OldAlmanacConstant.Presenter mPresenter;

    private int currYear;   //当前年
    private int currMonth;
    private int currDay;
    private int currHour;
    private int year;
    private int month;
    private int day;
    private int selectDay;
    private int noteFlag = 0;       //0 是日期相等 1是小于 (跳转记事)  2是大于 （跳转提醒）

    private String city = null;
    private String months;
    private String days;

    private String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};


    private List<CalendarNoteBean> noteBeans;


    private HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;

    private XTabLayout mTablayout;
    private String mWeather1;
    private String mStatus;
    private HFortuneBean mExmple;



    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        mViewPager =  view.findViewById(R.id.viewpager_oldhuangli);

        mTablayout = view.findViewById(R.id.tablayout_oldhuangli);
        setActionBarTopPadding(view.findViewById(R.id.rl_huangli_head));
        return view;
    }


    @Override
    public void initListener() {
        monthView.setOnDateChangeListener(this);
        monthView.setOnDatePickedListener(this);
        tvYunshi.setOnClickListener(this);
        layoutWeather.setOnClickListener(this);
        layoutAlmanac.setOnClickListener(this);
        btnAddNote.setOnClickListener(this);
        mTextData.setOnClickListener(this);
        tvYunshiDate.setOnClickListener(this);
        mTvYunshiWeek.setOnClickListener(this);
        tvDateIntro.setOnClickListener(this);


    }

    @Override
    public void initData() {

        new OldAlmanacPresenter(this);
        noteBeans = new ArrayList<CalendarNoteBean>();
        fortuneBean = new FortuneBean();

        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        selectDay = day;
        currYear = year;
        currMonth = month;
        currDay = day;
        currHour = cal.get(Calendar.HOUR_OF_DAY);
        bazi = new BaZi(cal);

        monthView.setDPMode(DPMode.SINGLE);
        monthView.setDate(year, month);
        monthView.setFestivalDisplay(true);  //绘制节日
        monthView.setTodayDisplay(true);





        months = month < 10 ? "0" + month : "" + month;
        days = day < 10 ? "0" + day : "" + day;

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String date = months + "月" + days + "日" + "  " + weekDays[dayOfWeek];
        tvAddNoteDate.setText(date);

        //大红文字
        mTextData.setText(days);
        tvTitleDate.setText(year + "." + months + "." + days);
        String oldDate = bazi.getYearGanZhi() + bazi.animalsYear() + "年" + bazi.getOldMonth();
        tvTitleOldDate.setText(oldDate);

        fortuneBean.setDate(currYear + "年" + date);
        fortuneBean.setBirthday(currYear + "." + currMonth + "." + currDay);
        fortuneBean.setOldDate(oldDate);
        fortuneBean.setCurrMon(currMonth);

        BuglyHelper.checkUpdata(false, false);

        EventBusUtil.register(this);
        checkPermissions();     //检查权限
        requestData();

        initTabLayout(); // TabLayout+ViewPager


    }


    private void initTabLayout() {
        //将ViewPager和Fragment进行绑定
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mHomeFragmentPagerAdapter);
        //将TabLayout和ViewPager进行绑定
        mTablayout.setupWithViewPager(mViewPager);


    }

    private void requestData() {


        //请求今天万年历数据
        mPresenter.getCalendar(year + "-" + month + "-" + day);

        //请求个人运势数据
//        getFortune();

        //请求最新资讯
//        mPresenter.getNews();
    }


    //检查权限
    private void checkPermissions() {
        Acp.getInstance(getActivity()).request(
                new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setRationalMessage(getString(R.string.pre_location_msg))
                        .build(), new AcpListener() {
                    @Override
                    public void onGranted() {
                        mPresenter.startLocation(getActivity().getApplicationContext());
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        ToastUtil.showShort(getActivity(), "未能获取位置权限，定位失败");
                    }
                });


    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (v.getId()) {
            //个人运势
            case R.id.home_tv_my_yunshi:
//                if (fortuneBean == null) {
//                    return;
//                }
                Intent intent = new Intent(getActivity(), HomeFortuneActivity.class);
                intent.putExtra(OldAlmanacConstant.INTENT_DATE, fortuneBean);
                intent.putExtra("HFortuneBean", mExmple);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imgIcon, "shareIcon");
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                break;

            //添加记事
            case R.id.btn_add_note:
                addNote();
                break;

            //天气
            case R.id.rl_tianqi:
                gotoWeather();
                break;


            //老黄历
            case R.id.text_data:
            case R.id.tv_yunshi_date:
            case R.id.tv_yunshi_week:
            case R.id.tv_yunshi_intro:

                Intent alIntent = new Intent(getActivity(), AlmanacActivity.class);
                alIntent.putExtra(AlmanacConstant.INTENT_DATE, new int[]{currYear, currMonth, currDay});
                startActivity(alIntent);
                break;

            //更多资讯
            case R.id.tv_news_more:
                Intent newIntent = new Intent(getActivity(), NewsActivity.class);
                newIntent.putExtra("NewsID", 0);
                startActivity(newIntent);
                break;
        }
    }

    private void gotoWeather() {
        if (!TextUtils.isEmpty(city)) {
            if (weatherBean != null) {
                Intent weatherIntent = new Intent(getActivity(), WeatherActivity.class);
                weatherIntent.putExtra(WeatherConstant.INTENT_CITY, city);
                weatherIntent.putExtra(WeatherConstant.INTENT_BEAN, weatherBean);
                weatherIntent.putExtra(WeatherConstant.INTENT_DATE, currYear + "-" + currMonth + "-" + currDay);
                weatherIntent.putExtra("mStatus", mStatus);
                weatherIntent.putExtra("mWeather1", mWeather1);
                startActivity(weatherIntent);
            }

        } else {
            mPresenter.startLocation(getActivity().getApplicationContext());
            tvReloca.setVisibility(View.INVISIBLE);
        }
    }

    //添加记事 or 提醒
    private void addNote() {
        String date = year + "." + month + "." + day;
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        intent.putExtra(OldAlmanacConstant.INTENT_KEY, noteFlag);
        intent.putExtra(OldAlmanacConstant.INTENT_DATE, date);
        startActivityForResult(intent, OldAlmanacConstant.INTENT_RESULT);
    }

    //修改记事 or 提醒
    private void updataNote(CalendarNoteBean bean) {
        Intent intent = new Intent(getActivity(), UpdataNoteActivity.class);
        intent.putExtra(OldAlmanacConstant.INTENT_DATE, bean);
        startActivityForResult(intent, OldAlmanacConstant.INTENT_UPDATA_RESULT);
    }
    //请求个人运势
    private void getFortune() {
        UserBean user = UserManager.getInstance().getUser();
        int month = 0;
        int day = 0;
        String xingZuoName = "";

        if (user == null) {
            month = this.month;
            day = this.day;
        } else {
            String date = UserManager.getInstance().getUserDate(getContext());
            if (TextUtils.isEmpty(date)) {
                month = this.month;
                day = this.day;
            } else {
                String[] temp = date.split("\\.");
                month = Integer.valueOf(temp[1]);
                day = Integer.valueOf(temp[2]);
            }
        }
        xingZuoName = XingZuoUtils.getConstellation(month, day);
        mPresenter.getFortune(xingZuoName);
    }


    @Override
    public void showNetError() {
        if (layoutAlmanac.getVisibility() == View.INVISIBLE) {
            HFortuneBean exmple = new HFortuneBean();
            exmple.exmpleData();
            showFortune(exmple);
        }
        ToastUtil.showShort(getContext(), getResources().getString(R.string.showNeterr));
    }

    @Override
    public void showError(String info) {
    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void showTitle(String title) {
    }

    @Override
    public void showLoad() {
    }

    @Override
    public void showLoadFinish() {
    }

    @Override
    public void showReLoad() {
    }

/*    @Override
    public void setPresenter(OldAlmanacConstant.Presenter presenter) {
        this.mPresenter = presenter;
    }*/

    @Override
    public void setPresenter(OldAlmanacConstant.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void setActionBarTopPadding(View v) {
        if (v != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int stataHeight = getStatusBarHeight();
                v.setPadding(v.getPaddingLeft(),
                        stataHeight + v.getPaddingTop(),
                        v.getPaddingRight(),
                        v.getPaddingBottom());
            }
        }
    }


    //显示今天天气
    @Override
    public void showWeather(WeatherBean weather) {
        if (weather == null) return;

        weatherBean = weather;

        View layout = getBaseRootView().findViewById(R.id.ll_huangli_weather);
        layout.setVisibility(View.VISIBLE);

        if (weather.getError_code() != 0) {
            Log.i("tag", "Weather.getReason : " + weather.getReason() + "   getError_code  : " + weather.getError_code());
            return;
        }

        WeatherBean.ResultBean result = weather.getResult();
        //实况天气
        WeatherBean.ResultBean.SkBean sk = result.getSk();
        String wendu = sk.getTemp();    //当前温度
        String wind = sk.getWind_direction() + sk.getWind_strength();     //风向风力
        String shidu = sk.getHumidity();        //湿度

        WeatherBean.ResultBean.TodayBean today = result.getToday();
        String fanwei = today.getTemperature();     //温度范围 temperature : 4℃~13℃
        String str1 = fanwei.replaceAll("℃", "°");
        String s = str1.replaceAll("~", "/");

        String ziwai = today.getUv_index();         //紫外线
        //天气描述
        mStatus = today.getWeather();

        //天气小图片
        Drawable draWeather = getResources().getDrawable(R.drawable.weather_0);
        String dayStatus = "0";
        int imgId;
        try {
            dayStatus = today.getWeather_id().getFa();
            imgId = getResources().getIdentifier("weather_" + dayStatus, "drawable", getContext().getPackageName());
            draWeather = getResources().getDrawable(imgId);
        } catch (Exception e) {
        }


        for (int i = 1; i < 4; i++) {  // 3个

            // 明天，后天，大后天
            String tv = "tv_tianqi_tomorrow";
            int tvId = getResources().getIdentifier(tv + (i - 1), "id", getContext().getPackageName());
            TextView tvDay = (TextView) getBaseRootView().findViewById(tvId);

            // 温度范围 "8°/20°"
            String tv1 = "tv_tianqi_fanwei";
            int fanweiId = getResources().getIdentifier(tv1 + (i - 1), "id", getContext().getPackageName());
            TextView tvf = (TextView) getBaseRootView().findViewById(fanweiId);

            //天气情况小图标
            String img = "img_tianqi_";
            int a = getResources().getIdentifier(img + (i - 1), "id", getContext().getPackageName());
            ImageView imgView = (ImageView) getBaseRootView().findViewById(a);

            //天气情况，晴，多云，雨
            String tvQing = "tv_tianqi_status_xiamian";
            int aqing = getResources().getIdentifier(tvQing + (i - 1), "id", getContext().getPackageName());
            TextView tQing = (TextView) getBaseRootView().findViewById(aqing);


            //空气质量  优，良，差
            String you = "tv_tianqi_you";
            int aYou = getResources().getIdentifier(you + (i - 1), "id", getContext().getPackageName());
            TextView tvYou = (TextView) getBaseRootView().findViewById(aYou);

            //未来天气情况
            WeatherBean.ResultBean.FutureWeahterBean future = result.getFuture().get(i);
            String week = future.getWeek();  //星期几
            //天气状况：晴
            mWeather1 = future.getWeather();
            String fanwei2 = future.getTemperature();
            String str2 = fanwei2.replaceAll("℃", "°");
            String s2 = str2.replaceAll("~", "/");

            //天气小图片
            Drawable d = getResources().getDrawable(R.drawable.weather_1);
//            if ("晴".equals(mWeather1)) {
//                String temp = "1";
//            } else if
//            String temp = "1";
//            int id;
//            try {
//                temp = future.getWeather_id().getFa();
//                id = getResources().getIdentifier("weather_" + temp, "drawable", getContext().getPackageName());
//                d = getResources().getDrawable(id);
//            } catch (Exception e) {
//
//            }

            imgView.setImageDrawable(WeatherUtil.getdraWeather(getActivity(), mWeather1));  //设置天气小图片
            if (i == 1) {
                tvDay.setText("明天");

            } else {
                String week2 = week.replaceAll("星期", "周");

                tvDay.setText(week2);
            }
            tvf.setText(s2);        //温度范围
            tQing.setText(mWeather1); //晴，多云
            tvYou.setText("优");     //优，良


        }
        tvWeather.setText(mStatus);
        tvWind.setText(wind);
        tvShidu.setText("湿度" + shidu);
        tvZiwai.setText("紫外线" + "[" + ziwai + "]");
        tvWendu.setText(wendu + "°");
        tvFanwei.setText(s);  //温度范围 8°/20°
        imgWeatherIcon.setImageDrawable(WeatherUtil.getdraWeather(getActivity(), mStatus)); //设置天气图片

    }


    //显示今天万年历
    @Override
    public void showCalendar(CalendarBean bean) {
        if (bean == null) return;
        if (bean.getError_code() != 0 || "参数格式错误".equals(bean.getReason())) {
            return;
        }

        CalendarBean.ResultBean.DataBean calBean = bean.getResult().getData();
        try {
            //农历动物年
            String year = calBean.getLunarYear();
            year = year.substring(0, year.length() - 1);
            String weekDay = calBean.getWeekday();
            //显示节假日
            String holiday = calBean.getHoliday();

            BaZi bazi = new BaZi(Calendar.getInstance());
            String lunar = bazi.getMonthAndDayGanZhi(currMonth, currDay);
            String lunarM = lunar.split(",")[0];
            String lunarD = lunar.split(",")[1];
            //显示星期几
            mTvYunshiWeek.setText(weekDay);
            tvDateIntro.setText(year + calBean.getAnimalsYear() + "年" + " " + lunarM + "月 " + lunarD + "日");

            //显示节日
            DPCNCalendar dpcnCalendar = new DPCNCalendar();
            String festivalG = dpcnCalendar.getFestivalG(month, day);
            mHomeTvGanenjie.setText(festivalG);

            String suit = calBean.getSuit().replace(".", " ");
            String avoid = calBean.getAvoid().replace(".", " ");
            tvSuit.setText(suit);
            tvBan.setText(avoid);

            fortuneBean.setGanzhi(year + "年" + " " + lunarM + "月 " + lunarD + "日");
            fortuneBean.setYi(suit);
            fortuneBean.setJi(avoid);

            //聚合api此字段偶尔没有 ,因为超出每日使用限制
            tvYunshiDate.setText(calBean.getLunar());
            layoutAlmanac.setVisibility(View.VISIBLE);

        } catch (Exception e) {
        }
    }


    //显示选中日期的记事
    @Override
    public void showCalendarNote(List<CalendarNoteBean> beans) {
        if (beans == null) return;

        if (layoutNote.getChildCount() != 0)
            layoutNote.removeAllViews();

        noteBeans.addAll(beans);
        Collections.sort(noteBeans, new TimeCompare());

        if (beans != null && noteBeans.size() > 0) {
            for (int i = 0; i < noteBeans.size(); i++) {
                CalendarNoteBean noteBean = noteBeans.get(i);
                View view = View.inflate(getContext(), R.layout.item_huangli_add_note, null);
                view.setTag(noteBean);

                //显示记事
                if (noteBean.getNoteFlag() == 1) {
                    TextView tvTime = (TextView) view.findViewById(R.id.tv_add_note_tiem);
                    TextView tvTitle = (TextView) view.findViewById(R.id.tv_add_note_title);
                    TextView tvContent = (TextView) view.findViewById(R.id.tv_add_note_content);

                    long time = noteBean.getTime();
                    cal.setTime(new Date(time));
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    String hours = hour < 10 ? "0" + hour : hour + "";
                    String mins = min < 10 ? "0" + min : min + "";
                    String times = hours + ":" + mins;
                    tvTime.setText(times);

                    tvTitle.setText(noteBean.getTitle());
                    tvContent.setText(noteBean.getContent());
                    tvContent.setVisibility(View.VISIBLE);


                    //显示提醒
                } else {
                    TextView tvTime = (TextView) view.findViewById(R.id.tv_add_note_tiem);
                    TextView tvTitle = (TextView) view.findViewById(R.id.tv_add_note_title);

                    long time = noteBean.getTime();
                    cal.setTime(new Date(time));
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    String hours = hour < 10 ? "0" + hour : hour + "";
                    String mins = min < 10 ? "0" + min : min + "";
                    String times = hours + ":" + mins;
                    tvTime.setText(times);
                    tvTime.setTextColor(getResources().getColor(R.color.add_note_warn));

                    tvTitle.setText(noteBean.getTitle());
                }


                View rl = view.findViewById(R.id.rl_add_note);
                rl.setTag(noteBean);
                rl.setOnClickListener(new View.OnClickListener() {

                    //修改记事 or 提醒
                    @Override
                    public void onClick(View v) {
                        if (ClickUtil.isFastClick()) {
                            return;
                        }
                        CalendarNoteBean bean = (CalendarNoteBean) v.getTag();
                        updataNote(bean);
                    }
                });
                rl.setOnLongClickListener(new View.OnLongClickListener() {

                    //长按删除记事 or 提醒
                    @Override
                    public boolean onLongClick(View v) {
                        CalendarNoteBean bean = (CalendarNoteBean) v.getTag();
                        showDeletDialog(bean);
                        return true;
                    }
                });
                layoutNote.addView(view);
            }
        }
    }

    @Override
    public void showFortune(HFortuneBean bean) {

    }

    //显示删除对话框
    public void showDeletDialog(final CalendarNoteBean bean) {
        String title = bean.getTitle().length() <= 15 ? bean.getTitle() : bean.getTitle().substring(0, 15) + "...";
        dialog = DialogUtil.createDialog(getContext(), "确认删除", title, "取消", "删除",
                new DialogOnClickListener() {

                    @Override
                    public void clickLeftButton(View view) {
                        dialog.dismiss();
                        dialog = null;
                    }

                    @Override
                    public void clickRightButton(View view) {
                        deleteNote(bean);
                        dialog.dismiss();
                        dialog = null;
                    }
                });
        dialog.show();
    }
//
//
//    //今天运势  老板说要去掉 ，先留条后路
//    @Override
//    public void showFortune(HFortuneBean bean) {
//        if (bean != null) {
//            showUserInfo();
//
//            String name = UserManager.getInstance().getUserName(getContext());
//            if (TextUtils.isEmpty(name)) {
//                name = "空城旧梦";
//            }
//            fortuneBean.setUserName(name);
//            tvName.setText(name);
//
////            String alls = bean.getAll();
////            int all = Integer.valueOf(alls.substring(0, alls.indexOf("%")));
////            all = all <= 75 ? all + 20 : all;
////
////            String moneys = bean.getMoney();
////            int money = Integer.valueOf(moneys.substring(0, moneys.indexOf("%")));
////            money = money <= 75 ? money + 20 : money;
////
////            String loves = bean.getLove();
////            int love = Integer.valueOf(loves.substring(0, loves.indexOf("%")));
////            love = love <= 75 ? love + 20 : love;
////
////            String works = bean.getWork();
////            int work = Integer.valueOf(works.substring(0, works.indexOf("%")));
////            work = work <= 75 ? work + 20 : work;
//
////            alls = getEvaluateByScore(all);
////            moneys = getEvaluateByScore(money);
////            loves = getEvaluateByScore(love);
////            works = getEvaluateByScore(work);
//////
////            apAll.setText(alls);
////            apMoney.setText(moneys);
////            apLove.setText(loves);
////            apWork.setText(works);
////
//////            cProgress.setValue(all);
//////            apAll.setValue(all);
////            apMoney.setValue(money);
////            apLove.setValue(love);
////            apWork.setValue(work);
//
////            fortuneBean.setScore(all + "");
//            fortuneBean   .setFortune(bean.getSummary());
//            fortuneBean.setLuckyColor(bean.getColor());
//
//        } else {
//            showUserInfo();
//        }
//    }

    @Override
    public void showNews(List<NewsBean> beans) {




//        monthView.requestFocus();
    }

    //请求不到今日运势
    @Override
    public void showNoFortune() {
//        mExmple = new HFortuneBean();
//        mExmple.exmpleData();
//        showFortune(mExmple);
//        exmpleIcon.setVisibility(View.VISIBLE);
//        ToastUtil.showShort(getContext(), getString(R.string.txt_no_fortune));

    }

    //定位成功
    @Override
    public void locationFinish(String city) {
        this.city = city;
        if (!TextUtils.isEmpty(this.city)) {
            this.city = this.city.substring(0, this.city.length() - 1);
            tvLoca.setText(this.city);
            tvLoca.setVisibility(View.VISIBLE);
            try {
                String temp = URLEncoder.encode(this.city, "utf-8");
                //定位成功后获取
                mPresenter.getWeather(temp);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    //定位失败
    @Override
    public void locationFailure() {
        if (TextUtils.isEmpty(city)) {
            imgWeatherIcon.setImageResource(R.drawable.loca_err);
            tvReloca.setVisibility(View.VISIBLE);

        }
    }


    //显示用户头像
    private void showUserInfo() {
        UserBean user = UserManager.getInstance().getUser();
        if (user != null) {
            String date = UserManager.getInstance().getUserDate(getContext());
            if (TextUtils.isEmpty(date)) {
                exmpleIcon.setVisibility(View.VISIBLE);
            } else {
                exmpleIcon.setVisibility(View.INVISIBLE);
            }

            String imgUrl = UserManager.getInstance().getUserPhotoUrl(getContext());
            if (!TextUtils.isEmpty(imgUrl)) {
                ImageHelper.getInstance().display(imgUrl, R.drawable.default_head_icon, imgIcon);
            }

        } else {
            exmpleIcon.setVisibility(View.VISIBLE);
            imgIcon.setImageResource(R.drawable.default_head_icon);
        }
    }


    //月份左右切换回调
    @Override
    public void onDateChange(int year, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String date = month == currMonth ? year + "." + month + "." + currDay : year + "." + month + ".01";
        try {
            cal.setTime(sdf.parse(date));
            BaZi lunar = new BaZi(cal);
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH) + 1;
            this.day = cal.get(Calendar.DAY_OF_MONTH);
            months = this.month < 10 ? "0" + this.month : "" + this.month;
            days = day < 10 ? "0" + day : "" + day;

            tvTitleDate.setText(year + "." + months + "." + days);

            tvTitleOldDate.setText(lunar.getYearGanZhi() + lunar.animalsYear() + "年" + lunar.getOldMonth());

            if ((noteFlag = mPresenter.compareDate(date, currYear, currMonth, currDay)) == 0) {
                viewAddHide.setVisibility(View.GONE);
            } else {
                if (viewAddHide.getVisibility() == View.GONE) {
                    viewAddHide.setVisibility(View.VISIBLE);
                }
                tvAddNoteDate.setText(months + "月" + days + "日  " + weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1]);


                if (noteFlag == 1) {
                    btnAddNote.setText("添加记事");
                } else {
                    btnAddNote.setText("添加提醒");
                }
            }

            String temp = year + "." + month + ".1";
            noteBeans.clear();
            mPresenter.getCalendarNote(temp);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    //点击日期的时候回调 如2016.11.3
    @Override
    public void onDatePicked(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            cal.setTime(sdf.parse(date));
            BaZi lunar = new BaZi(cal);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
            day = cal.get(Calendar.DAY_OF_MONTH);
            days = day < 10 ? "0" + day : "" + day;

            tvTitleDate.setText(year + "." + months + "." + days);
            tvTitleOldDate.setText(lunar.getYearGanZhi() + lunar.animalsYear() + "年" + lunar.getOldMonth());

            if ((noteFlag = mPresenter.compareDate(date, currYear, currMonth, currDay)) == 0) {
                viewAddHide.setVisibility(View.GONE);
            } else {
                if (viewAddHide.getVisibility() == View.GONE) {
                    viewAddHide.setVisibility(View.VISIBLE);
                }
                tvAddNoteDate.setText(months + "月" + days + "日  " + weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1]);

                if (noteFlag == 1) {
                    btnAddNote.setText("添加记事");
                } else {
                    btnAddNote.setText("添加提醒");
                }

            }

            selectDay = day;
            noteBeans.clear();
            mPresenter.getCalendarNote(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //添加记事
        if (resultCode == OldAlmanacConstant.INTENT_RESULT && data != null) {
            CalendarNoteBean bean = (CalendarNoteBean) data.getSerializableExtra(AddNoteActivity.BOUNDLE_KEY_DATA);
            if (bean != null) {
                mPresenter.saveCalendarNote(getContext(), bean);
                ArrayList<CalendarNoteBean> beans = new ArrayList<CalendarNoteBean>();
                beans.add(bean);
                showCalendarNote(beans);
            }
        }

        //修改记事
        if (resultCode == OldAlmanacConstant.INTENT_UPDATA_RESULT && data != null) {
            CalendarNoteBean bean = (CalendarNoteBean) data.getSerializableExtra(OldAlmanacConstant.INTENT_DATE);
            if (bean != null) {
                noteBeans.clear();
                mPresenter.updataCalendarNote(getContext(), bean);
            }
        }
    }


    private void deleteNote(CalendarNoteBean bean) {
        mPresenter.deletCalendarNote(getContext(), bean);
        for (int i = 0; i < layoutNote.getChildCount(); i++) {
            View v = layoutNote.getChildAt(i);

            if (v.getTag() != null) {
                CalendarNoteBean tempBean = (CalendarNoteBean) v.getTag();
                if (tempBean.equals(bean)) {
                    layoutNote.removeViewAt(i);
                    break;
                }
            }
        }

        for (int i = 0; i < noteBeans.size(); i++) {
            CalendarNoteBean temp = noteBeans.get(i);
            if (temp.equals(bean)) {
                noteBeans.remove(i);

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                    cal.setTime(sdf.parse(temp.getDate()));
                    String d = cal.get(Calendar.YEAR) + "." + cal.get(Calendar.MONTH) + 1 + "." + cal.get(Calendar.DAY_OF_MONTH);
                    EventBusUtil.post(new EventNoteBean(d, 0, "", noteFlag));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    //更新今日运势
    @Subscribe
    public void onEvent(EventLoginBean bean) {
//        getFortune();
    }

    //网络监听
    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            //有网络且没加载成功数据时，重新请求数据
            if (layoutAlmanac.getVisibility() == View.INVISIBLE) {
                requestData();
            }
        }
    }

    //刷新日历记事标记
    @Subscribe(priority = 0)
    public void onEvent(EventNoteBean bean) {
        monthView.invalidate();

    }

    //刷新头像
    @Subscribe
    public void onEvent(LoginRefreshBean bean) {
        String img = UserManager.getInstance().getUserPhotoUrl(getContext());
        if (!TextUtils.isEmpty(img)) {
            ImageHelper.getInstance().display(img, imgIcon);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.stopLocation();

        EventBusUtil.unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_huangli_year)
    public void onViewClicked() {
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }


    //月份排序类
    class TimeCompare implements Comparator<CalendarNoteBean> {

        @Override
        public int compare(CalendarNoteBean o1, CalendarNoteBean o2) {

            if (o1.getTime() > o2.getTime()) {
                return 1;
            } else if (o1.getTime() < o2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        }

    }


}