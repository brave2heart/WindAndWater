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
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RatingBar;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/11/28 0028.
 */

public class HoroscopeTomorrowFragment extends BaseLazyFragment implements HoroscopeConstant.View {


    @BindView(R.id.img_constellation_head)
    ImageView imgConstellationHead;
    @BindView(R.id.tv_consname)
    TextView tvConsname;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_switch_constellation)
    Button btnSwitchConstellation;
    @BindView(R.id.rt_all_detial)
    RatingBar rtAllDetial;
    @BindView(R.id.tv_summary_detial)
    TextView tvSummaryDetial;
    @BindView(R.id.rt_love_detial)
    RatingBar rtLoveDetial;
    @BindView(R.id.rt_work_detail)
    RatingBar rtWorkDetail;
    @BindView(R.id.rt_health_detial)
    RatingBar rtHealthDetial;
    @BindView(R.id.tv_QFriend_detial)
    TextView tvQFriendDetial;
    @BindView(R.id.tv_color_detail)
    TextView tvColorDetail;
    @BindView(R.id.tv_lucky_number_detail)
    TextView tvLuckyNumberDetail;
    @BindView(R.id.rt_money_detial)
    RatingBar rtMoneyDetial;
    @BindView(R.id.ll)
    LinearLayout ll;
    private View view;
    private AlertDialog mDialog;
    private int index;
    private String constellationName;
    private HoroscopeConstant.Presenter mPresenter;
    private DayYunCheng dayYunChengBean;


    public static HoroscopeTomorrowFragment getInstance() {
        HoroscopeTomorrowFragment fragment = new HoroscopeTomorrowFragment();
        return fragment;
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
    private void getTomorrowYunCheng(String constellationName) {
        if (constellationName == null) {
            constellationName = "天蝎座";
            imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[7]);
        } else {
            setContellationHead();
        }
        mPresenter.getYunCheng(constellationName, HoroscopeConstant.XINGZUO_TYPE[1]);

    }

    //    切换星座
    @OnClick(R.id.btn_switch_constellation)
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) return;

        showChooseDialog();

    }

    private void showChooseDialog() {
        mDialog = new AlertDialog.Builder(getContext())
                .setSingleChoiceItems(HoroscopeConstant.constellationArr, -1,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                index = which;
                                Log.e("tag", "onClick: " + index);
                                constellationName = HoroscopeConstant.constellationArr[index].toString();
                                tvConsname.setText(HoroscopeConstant.constellationArr[index].toString());
                                imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[index]);
                                getTomorrowYunCheng(constellationName);
                                /*String[] constellationArr = HoroscopeConstant.constellationArr;
                                int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr,constellationName);
                                imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);*/
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
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_horoscope, container, false);

    }

    private float changeFloat(String s) {
        Float detail = Float.valueOf(s.substring(0, s.indexOf("%"))) / 10 / 2;
        Log.e("tag", "detial:" + detail);
        return detail;
    }

    private void setContellationHead() {
        String[] constellationArr = HoroscopeConstant.constellationArr;
        int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr, constellationName);
        imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);
    }

    @Override
    public void showYunCheng(DayYunCheng bean) {
        if (bean != null) {
            /*String s = DateUtil.formatDate(bean.getDatetime(), "yyyy年MM月dd日");
            String s1 = DateUtil.formatYMD(s);
            String[] split = s1.split("-");
            String month = split[1];
            String day = split[2];*/
            dayYunChengBean = bean;
            if (bean.getError_code() != 0) {
                getBaseEmptyView().showEmptyView(R.drawable.nodata, dayYunChengBean.getReason());
            } else {
                String date = dayYunChengBean.getDatetime();
                tvTime.setText(date + "运势");
                tvConsname.setText(dayYunChengBean.getName());
                rtAllDetial.setStar(changeFloat(dayYunChengBean.getAll()));
                tvSummaryDetial.setText(dayYunChengBean.getSummary());
                rtLoveDetial.setStar(changeFloat(dayYunChengBean.getLove()));
                rtWorkDetail.setStar(changeFloat(dayYunChengBean.getWork()));
                rtHealthDetial.setStar(changeFloat(dayYunChengBean.getHealth()));
                tvQFriendDetial.setText(dayYunChengBean.getQFriend());
                tvColorDetail.setText(dayYunChengBean.getColor());
                tvLuckyNumberDetail.setText(dayYunChengBean.getNumber() + "");
                rtMoneyDetial.setStar(changeFloat(dayYunChengBean.getMoney()));
            }
        }
        ll.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWeekYunCheng(WeekYunCheng bean) {

    }

    @Override
    public void showMonthCheng(MonthYunCheng bean) {

    }

    @Override
    public void showNetError() {
        /*getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReLoad();
                getBaseEmptyView().hideEmptyView();
            }
        });*/
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
        getTomorrowYunCheng(constellationName);
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
            getTomorrowYunCheng(constellationName);
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
        if (dayYunChengBean == null){
            if (!TextUtils.isEmpty(constellationName)) {
                getTomorrowYunCheng(constellationName);        // 加载数据的方法
                setContellationHead();
            } else {
                if (UserManager.getInstance().isLogin()) {  //获取用户出生日期
                    String userDate = UserManager.getInstance().getUserDate(getActivity());
                    Log.e("tag", "用户出生日期" + userDate);
                    if (!TextUtils.isEmpty(userDate)) {
                        String[] split = userDate.split("\\.");
                        String year = split[0];
                        String month = split[1];
                        String day = split[2];
                        Log.e("tag", "年、月 日:" + year + "..." + month + ".." + day);
                        String name = XingZuoUtils.getConstellation(Integer.valueOf(month), Integer.valueOf(day));
                        constellationName = name;
                        getTomorrowYunCheng(constellationName);        // 加载数据的方法
                        setContellationHead();
                /*String[] constellationArr = HoroscopeConstant.constellationArr;
                int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr,constellationName);
                imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);*/
                    } else {
                        getTomorrowYunCheng(constellationName);
                    }
                } else {
                    //未登录  默认星座：天蝎座
                    getTomorrowYunCheng(constellationName);
                    return;
                }
            }
        }else {
            //            显示数据
            showYunCheng(dayYunChengBean);
            setContellationHead();
        }
    }
}
