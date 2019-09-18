package com.tongcheng.soothsay.ui.activity.huangli.weather;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.huangli.WeatherBean.ResultBean.FutureWeahterBean;
import com.tongcheng.soothsay.bean.huangli.WeatherBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.living.utils.WeatherUtil;
import com.tongcheng.soothsay.widget.LineCircle;
import com.tongcheng.soothsay.widget.WeatherLineChart;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 天气预报
 * 接口中的空气污染字段为空 所以 写的死数据
 */
public class WeatherActivity extends BaseTitleActivity implements WeatherConstant.View, View.OnClickListener {

    @BindView(R.id.headArea)
    View headView;
    @BindView(R.id.headBackButton)
    ImageButton btnBack;
    @BindView(R.id.headTitle)
    TextView tvTitle;

    @BindView(R.id.tv_weather_status)
    TextView tvStatus;
    @BindView(R.id.tv_weather_week)
    TextView tvWeek;
    @BindView(R.id.img_weather_icon)
    ImageView imgIcon;
    @BindView(R.id.ll_weather_future)
    LinearLayout llFuture;
    @BindView(R.id.lineCircle)
    LineCircle lineView;
    @BindView(R.id.mylinechart)
    WeatherLineChart chart;

    @BindView(R.id.rl_weather_chuanyi)
    View btnChuanyi;    //穿衣
    @BindView(R.id.tv_chuanyi)
    TextView tvChuanyi;
    @BindView(R.id.tv_chuanyi_yi)
    TextView tvChuanyiYi;
    @BindView(R.id.rl_weather_chenlian)
    View btnChenlian;   //晨练
    @BindView(R.id.tv_chenlian)
    TextView tvChenlian;
    @BindView(R.id.tv_chenlian_yi)
    TextView tvChenlianYi;
    @BindView(R.id.rl_weather_xiche)
    View btnXiche;      //洗车
    @BindView(R.id.tv_xiche)
    TextView tvXiche;
    @BindView(R.id.tv_xiche_yi)
    TextView tvXicheyi;
    @BindView(R.id.rl_weather_ganmao)
    View btnGanmao;     //感冒
    @BindView(R.id.tv_ganmao)
    TextView tvGanmao;
    @BindView(R.id.tv_ganmao_yi)
    TextView tvGanmaoYi;
    @BindView(R.id.rl_weather_wuran)
    View btnWuran;      //污染
    @BindView(R.id.tv_wuran)
    TextView tvWuran;
    @BindView(R.id.tv_wuran_yi)
    TextView tvWuranYi;
    @BindView(R.id.rl_weather_ziwaixian)
    View btnZiwaixian;  //紫外线
    @BindView(R.id.tv_ziwaixian)
    TextView tvZiwaixian;
    @BindView(R.id.tv_ziwaixian_yi)
    TextView tvZiwaixianYi;

    private MDAlertDialog dialog;

    private String city;
    private String date;
    private WeatherBean weatherBean;

    private WeatherConstant.Presenter mPresenter;
    private String mWea_status;
    private String mWea_weather1;
    private String mSStatus;
    private String mStatus;
    private String[] mF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_weather);

        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        city = getIntent().getStringExtra(WeatherConstant.INTENT_CITY);
        date = getIntent().getStringExtra(WeatherConstant.INTENT_DATE);
        weatherBean = (WeatherBean) getIntent().getSerializableExtra(WeatherConstant.INTENT_BEAN);
        mWea_status = getIntent().getStringExtra("mStatus");
        mWea_weather1 = getIntent().getStringExtra("mWeather1");

        setActionBarTopPadding(headView);
        headView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        btnBack.setVisibility(View.VISIBLE);
        tvTitle.setText(city);
        Drawable d = getResources().getDrawable(R.drawable.bottom_arrow);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        tvTitle.setCompoundDrawables(null, null, d, null);

        List<Integer> YValueMax = null;
        List<Integer> YValueMin = null;
        YValueMax = new ArrayList<>();
        YValueMin = new ArrayList<>();

        YValueMax.add(0);
        YValueMax.add(0);
        YValueMax.add(0);
        YValueMax.add(0);
        YValueMax.add(0);
        YValueMax.add(0);

        YValueMin.add(0);
        YValueMin.add(0);
        YValueMin.add(0);
        YValueMin.add(0);
        YValueMin.add(0);
        YValueMin.add(0);

        chart.setYValueMax(YValueMax);
        chart.setYValueMin(YValueMin);
        chart.invalidate();

    }

    @Override
    public void initListener() {
        tvTitle.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new WeatherPresenter(this);
//        mPresenter.getWeather(city);
        showWeather(weatherBean);
    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;

        switch (v.getId()) {
            case R.id.headTitle:
                Intent intent = new Intent(this, SelecterCityActivity.class);
                intent.putExtra(Constant.INTENT_DATA, city);
                startActivityForResult(intent, 0);
                break;

            case R.id.headBackButton:
                finish();
                break;

            case R.id.rl_weather_chenlian:
                showDialog(v);
                break;

            case R.id.rl_weather_ganmao:
                showDialog(v);
                break;

            case R.id.rl_weather_xiche:
                showDialog(v);
                break;

            case R.id.rl_weather_chuanyi:
                showDialog(v);
                break;

            case R.id.rl_weather_ziwaixian:
                showDialog(v);
                break;

            //污染字段没有   用空调字段
            case R.id.rl_weather_wuran:
                showDialog(v);
                break;
        }
    }

    private void showDialog(View v) {
        String content = (String) v.getTag();
        dialog = DialogUtil.createDialog(this, "", content, "", "朕知道了",
                new DialogOnClickListener() {

                    @Override
                    public void clickLeftButton(View view) {
                        dialog.dismiss();
                        dialog = null;
                    }

                    @Override
                    public void clickRightButton(View view) {
                        dialog.dismiss();
                        dialog = null;
                    }
                });
        dialog.show();
    }


    //显示今天天气
    @Override
    public void showWeather(WeatherBean weather) {
        if (weather != null && weather.getError_code() == 0) {
            WeatherBean.ResultBean result = weather.getResult();
            WeatherBean.ResultBean.TodayBean today = result.getToday();


            String wendu = result.getSk().getTemp();    //当前温度
            String week = today.getWeek().replace("星期", "周");
            mStatus = today.getWeather();

            if (mStatus.contains("转")) {
                mStatus = mStatus.split("转")[1];
            }



            //天气小图片
            Drawable draWeather = getResources().getDrawable(R.drawable.weather_no);
            String dayStatus = "0";
            int imgId;
            try {
                dayStatus = today.getWeather_id().getFa();
                imgId = getResources().getIdentifier("weather_" + dayStatus, "drawable", this.getPackageName());
                draWeather = getResources().getDrawable(imgId);
            } catch (Exception e) {
            }

            tvStatus.setText(mStatus);  //设置今天天气情况
            tvWeek.setText(week);


            imgIcon.setImageDrawable(WeatherUtil.getdraWeather(this, mStatus));

            List<Integer> YValueMax = null;
            List<Integer> YValueMin = null;
            YValueMax = new ArrayList<>();
            YValueMin = new ArrayList<>();

            result.getFuture().remove(0);
            //未来天气
            for (int i = 0; i < result.getFuture().size(); i++) {
                View view = llFuture.getChildAt(i);
                FutureWeahterBean bean = result.getFuture().get(i);

                ImageView img = (ImageView) view.findViewById(R.id.img_future_icon);
                TextView tvFStatus = (TextView) view.findViewById(R.id.tv_future_status);
                TextView tvFanwei = (TextView) view.findViewById(R.id.tv_future_fanwei);
                TextView tvWeek = (TextView) view.findViewById(R.id.tv_future_week);

                String sWeek = bean.getWeek().replace("星期", "周");
                mSStatus = bean.getWeather();
                String sFanwei = bean.getTemperature().replace("℃", "").replace("~", "/");

                if (mSStatus.contains("转")) {
                    mSStatus = mSStatus.split("转")[1];
                }

                if (week.equals(sWeek)) {
                    tvWeek.setText("今天");
                } else {
                    tvWeek.setText(sWeek);
                }

                //天气小图片
                Drawable d = getResources().getDrawable(R.drawable.weather_0);
//                String ds = "0";
//                int id;
//                try {
//                    ds = bean.getWeather_id().getFa();
//                    id = getResources().getIdentifier("weather_" + ds, "drawable", this.getPackageName());
//                    d = getResources().getDrawable(id);
//                } catch (Exception e) {
//                }

                img.setImageDrawable(WeatherUtil.getdraWeather(this, mSStatus));
                tvFanwei.setText(sFanwei);
                tvFStatus.setText(mSStatus);

                mF = sFanwei.split("/");
                YValueMin.add(Integer.valueOf(mF[0]));
                YValueMax.add(Integer.valueOf(mF[1]));

                chart.setYValueMax(YValueMax);
                chart.setYValueMin(YValueMin);
                chart.invalidate();

                //今天温度范围
                String temp[] = today.getTemperature().replace("℃", "").split("~");
                if (temp != null) {
                    int min = Integer.valueOf(temp[0]);
                    int max = Integer.valueOf(temp[1]);
                    lineView.setStartTem(min);
                    lineView.setStopTem(max);
                    lineView.setCenterTemp(wendu);
                }
            }


            //生活
            tvChuanyiYi.setText(today.getDressing_index());
            tvChenlianYi.setText(today.getExercise_index());
            tvXicheyi.setText(today.getWash_index());
            tvZiwaixianYi.setText(today.getUv_index());
            tvGanmaoYi.setText(today.getTravel_index());
            tvWuranYi.setText(today.getExercise_index());

            btnChenlian.setTag(today.getExercise_index());
            btnChuanyi.setTag(today.getDressing_advice());
            btnGanmao.setTag(today.getTravel_index());
            btnXiche.setTag(today.getWash_index());
            btnZiwaixian.setTag(today.getUv_index());
            btnWuran.setTag(today.getDressing_index());

            btnChenlian.setOnClickListener(this);
            btnXiche.setOnClickListener(this);
            btnChuanyi.setOnClickListener(this);
            btnGanmao.setOnClickListener(this);
            btnZiwaixian.setOnClickListener(this);
            btnWuran.setOnClickListener(this);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (date != null && resultCode == 1) {
            city = data.getStringExtra(Constant.INTENT_DATA);
            tvTitle.setText(city);
            mPresenter.getWeather(city);
        }
    }

    @Override
    public void showNetError() {
        ToastUtil.showShort(this, getResources().getString(R.string.showNeterr));
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


    @Override
    public void setPresenter(WeatherConstant.Presenter presenter) {
        this.mPresenter = presenter;
    }


}
