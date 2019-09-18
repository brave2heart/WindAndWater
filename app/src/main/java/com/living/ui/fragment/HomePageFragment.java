package com.living.ui.fragment;

import android.Manifest;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.living.adapter.home.HomeFragmentPagerAdapter;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.List;

/**
 * Created by weihao on 2018/1/19.
 */

public class HomePageFragment extends BaseTitleFragment implements CalendarView.OnYearChangeListener, CalendarView.OnDateSelectedListener {


    private TextView mTextMonthDay;
    private TextView mTextYear;

    private TextView mTextLunar;

    private TextView mTextCurrentDay;

    private CalendarView mCalendarView;

    private RelativeLayout mRelativeTool;

    private ViewPager mViewPager;
    private XTabLayout mTablayout;

    private int mYear;
    private CalendarLayout mCalendarLayout;
    private HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;



    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        initView();
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        mViewPager = view.findViewById(R.id.viewpager_oldhuangli);
        mTablayout = view.findViewById(R.id.tablayout_oldhuangli);

        mTextMonthDay = view.findViewById(R.id.tv_month_day);
        mTextYear = view.findViewById(R.id.tv_year);
        mTextLunar = view.findViewById(R.id.tv_lunar);
        mRelativeTool = view.findViewById(R.id.rl_tool);
        mCalendarView = view.findViewById(R.id.calendarView);
        mTextCurrentDay = view.findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showSelectLayout(mYear);
                    return;
                }
                mCalendarView.showSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        view.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

        mCalendarLayout = view.findViewById(R.id.calendarLayout);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

        return view;


    }

    private void initView() {

    }


    @Override
    public void initData() {
        initTabLayout();


    }




    private void initTabLayout() {
        //将ViewPager和Fragment进行绑定
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mHomeFragmentPagerAdapter);
        //将TabLayout和ViewPager进行绑定
        mTablayout.setupWithViewPager(mViewPager);


    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }


}
