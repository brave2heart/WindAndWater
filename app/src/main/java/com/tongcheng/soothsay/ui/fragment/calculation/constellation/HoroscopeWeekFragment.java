package com.tongcheng.soothsay.ui.fragment.calculation.constellation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseLazyFragment;
import com.tongcheng.soothsay.bean.calculation.DayYunCheng;
import com.tongcheng.soothsay.bean.calculation.MonthYunCheng;
import com.tongcheng.soothsay.bean.calculation.WeekYunCheng;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.other.XingZuoUtils;
import com.tongcheng.soothsay.utils.AlertDialogHeightUtil;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/11/28 0028.
 */

public class HoroscopeWeekFragment extends BaseLazyFragment implements HoroscopeConstant.View {

    @BindView(R.id.img_constellation_head)
    ImageView imgConstellationHead;
    @BindView(R.id.tv_consname)
    TextView tvConsname;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_switch_constellation)
    Button btnSwitchConstellation;
    @BindView(R.id.tv_healthy)
    TextView tvHealthy;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_love)
    TextView tvLove;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.ll)
    LinearLayout ll;
    private View view;
    private AlertDialog mDialog;
    private int index;
    private String constellationName;
    private HoroscopeConstant.Presenter mPresenter;
    private WeekYunCheng weekYunChengBean;

    public static HoroscopeWeekFragment getInstance() {
        HoroscopeWeekFragment fragment = new HoroscopeWeekFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBusUtil.register(this);
        ll.setVisibility(View.INVISIBLE);
        showLoad();
    }

    @Override
    public void onStart() {
        super.onStart();
        lazyLoad();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_week_horoscope, container, false);

    }

    private void setContellationHead() {
        String[] constellationArr = HoroscopeConstant.constellationArr;
        int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr, constellationName);
        imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);
    }

    private void getWeekYunCheng(String constellationName) {
        if (constellationName == null) {
            constellationName = "天蝎座";
            imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[7]);
        } else {
            setContellationHead();
        }
        mPresenter.getWeekYunCheng(constellationName, HoroscopeConstant.XINGZUO_TYPE[2]);

    }

    //    切换星座
    @OnClick(R.id.btn_switch_constellation)
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        showChooseDialog();

    }

    private void showChooseDialog() {
        mDialog = new AlertDialog.Builder(getContext())
                .setSingleChoiceItems(HoroscopeConstant.constellationArr, -1,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                if (ClickUtil.isFastClick()) {
                                    return;
                                }
                                dialog.dismiss();
                                index = which;
                                Log.e("tag", "onClick: " + index);
                                constellationName = HoroscopeConstant.constellationArr[index].toString();
                                tvConsname.setText(HoroscopeConstant.constellationArr[index].toString());
                                imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[index]);
                                getWeekYunCheng(constellationName);
                            }
                        }
                )
                .setCancelable(true)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                })
                .show();
        //        设置对话框 高度
        AlertDialogHeightUtil.halfHeght(mDialog, getActivity());

    }


    @Override
    public void showYunCheng(DayYunCheng bean) {

    }

    @Override
    public void showWeekYunCheng(WeekYunCheng bean) {
        if (bean != null) {
            weekYunChengBean = bean;
            if (weekYunChengBean.getError_code() != 0) {
                getBaseEmptyView().showEmptyView(R.drawable.nodata, weekYunChengBean.getReason());
            } else {
                String date = weekYunChengBean.getDate();
                Log.e("tag", "date:" + date);
                String[] fileTime = date.split("-");
                String startTime = fileTime[0];
                String endTime = fileTime[1];
                Log.e("tag", "startTime---endTime" + startTime + "........." + endTime);


                String stime = DateUtil.formatDate(startTime, "yyyy年MM月dd日");
                Log.e("tag", "s " + weekYunChengBean.getDate() + "......" + stime);

                String stime1 = DateUtil.formatYMD(stime);
                Log.e("tag", "s1 " + stime + "......" + stime1);
                String[] split = stime1.split("-");
                String smonth = split[1];
                String sday = split[2];

                String etime = DateUtil.formatDate(endTime, "yyyy年MM月dd日");
                Log.e("tag", "end " + weekYunChengBean.getDate() + "......" + etime);

                String etime1 = DateUtil.formatYMD(etime);
                Log.e("tag", "end " + etime + "......" + etime1);
                String[] split2 = etime1.split("-");
                String emonth = split2[1];
                String eday = split2[2];

                tvTime.setText(smonth + "/" + sday + "-" + emonth + "/" + eday + "运势");
                tvConsname.setText(weekYunChengBean.getName());
                tvHealthy.setText(weekYunChengBean.getHealth());
                tvJob.setText(weekYunChengBean.getJob());
                tvLove.setText(weekYunChengBean.getLove());
                tvMoney.setText(weekYunChengBean.getMoney());
                tvWork.setText(weekYunChengBean.getWork());
            }
        }
        ll.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMonthCheng(MonthYunCheng bean) {

    }

    @Override
    public void showNetError() {
        showEmpty();
        ToastUtil.showShort(getContext(), getResources().getString(R.string.net_error));
    }

    @Override
    public void showError(String info) {
//        ToastUtil.showShort(getActivity(),info);
        ToastUtil.showShort(getContext(), getResources().getString(R.string.server_error));
        getBaseEmptyView().showEmptyView(R.drawable.loadfailed, getString(R.string.server_error));

    }

    @Override
    public void showEmpty() {
        getBaseEmptyView().showEmptyView(R.drawable.nonetwork, getString(R.string.showNeterr));
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading();
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {
        getBaseEmptyView().hideEmptyView();
        getWeekYunCheng(constellationName);
    }

    @Override
    public void setPresenter(HoroscopeConstant.Presenter presenter) {
        mPresenter = presenter;
    }

    //网络监听
    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            //有网络且没加载成功数据时，重新请求数据
            getWeekYunCheng(constellationName);
            getBaseEmptyView().hideEmptyView();
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null)
            mPresenter.onDestroy();
        EventBusUtil.unregister(this);
        showLoadFinish();
    }

    @Override
    protected void lazyLoad() {
        new HoroscopePresenter(this);
        if (weekYunChengBean == null) {
            if (!TextUtils.isEmpty(constellationName)) {
                getWeekYunCheng(constellationName);        // 加载数据的方法
                setContellationHead();
            } else {
                if (UserManager.getInstance().isLogin()) {  //获取用户出生日期
                    String userDate = UserManager.getInstance().getUserDate(getActivity());
                    Log.e("tag", "用户出生日期" + userDate);
                    if (!TextUtils.isEmpty(userDate)) {
                        String[] split = userDate.split("\\.");
                        String month = split[1];
                        String day = split[2];
                        Log.e("tag", "年、日:" + month + day);
                        String name = XingZuoUtils.getConstellation(Integer.valueOf(month), Integer.valueOf(day));
                        constellationName = name;
                        getWeekYunCheng(constellationName);        // 加载数据的方法
                        setContellationHead();
                /*String[] constellationArr = HoroscopeConstant.constellationArr;
                int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr,constellationName);
                imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);*/
                    } else {
                        getWeekYunCheng(constellationName);
                    }

                } else {
                    //未登录  默认星座：天蝎座
                    getWeekYunCheng(constellationName);
                    return;
                }
            }
        } else {
            //            显示数据
            showWeekYunCheng(weekYunChengBean);
            setContellationHead();
        }
    }
}
