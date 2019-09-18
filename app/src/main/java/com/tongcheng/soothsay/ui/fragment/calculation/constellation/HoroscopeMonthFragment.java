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

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/11/28 0028.
 */

public class HoroscopeMonthFragment extends BaseLazyFragment implements HoroscopeConstant.View {


    @BindView(R.id.img_constellation_head)
    ImageView imgConstellationHead;
    @BindView(R.id.tv_consname)
    TextView tvConsname;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_switch_constellation)
    Button btnSwitchConstellation;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_all_detial)
    TextView tvAllDetial;
    @BindView(R.id.tv_healthy)
    TextView tvHealthy;
    @BindView(R.id.tv_healthy_detial)
    TextView tvHealthyDetial;
    @BindView(R.id.tv_love)
    TextView tvLove;
    @BindView(R.id.tv_love_detial)
    TextView tvLoveDetial;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_detial)
    TextView tvMoneyDetial;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.tv_work_detail)
    TextView tvWorkDetail;
    @BindView(R.id.ll)
    LinearLayout ll;
    private View view;
    private String constellationName;
    private HoroscopeConstant.Presenter mPresenter;

    private AlertDialog mDialog;
    private int index;
    private MonthYunCheng monthYunChengBean;

    public static HoroscopeMonthFragment getInstance() {
        HoroscopeMonthFragment fragment = new HoroscopeMonthFragment();
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

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_month_horoscope, container, false);

    }

    private void setContellationHead() {
        String[] constellationArr = HoroscopeConstant.constellationArr;
        int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr, constellationName);
        imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);
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
                                getMonthYunCheng(constellationName);
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

    public void getMonthYunCheng(String constellationName) {
        if (constellationName == null) {
            constellationName = "天蝎座";
            imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[7]);
        } else {
            setContellationHead();
        }
        mPresenter.getMonthYunCheng(constellationName, HoroscopeConstant.XINGZUO_TYPE[3]);
    }

    @Override
    public void showYunCheng(DayYunCheng bean) {

    }

    @Override
    public void showWeekYunCheng(WeekYunCheng bean) {

    }

    @Override
    public void showMonthCheng(MonthYunCheng bean) {
        if (bean != null) {
            monthYunChengBean = bean;
            if (bean.getError_code() != 0) {
                getBaseEmptyView().showEmptyView(R.drawable.nodata, bean.getReason());
            } else {
                tvConsname.setText(monthYunChengBean.getName());
                tvTime.setText(monthYunChengBean.getMonth() + "月运势");
                tvAllDetial.setText(monthYunChengBean.getAll());
                tvHealthyDetial.setText(monthYunChengBean.getHealth());
                tvLoveDetial.setText(monthYunChengBean.getLove());
                tvMoneyDetial.setText(monthYunChengBean.getMonth() + "");
                tvWorkDetail.setText(monthYunChengBean.getWork());
            }
        }
        ll.setVisibility(View.VISIBLE);
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
        getMonthYunCheng(constellationName);
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
            getMonthYunCheng(constellationName);
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
        if (monthYunChengBean == null) {
            if (!TextUtils.isEmpty(constellationName)) {
                getMonthYunCheng(constellationName);        // 加载数据的方法
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
                        getMonthYunCheng(constellationName);        // 加载数据的方法
                        setContellationHead();
                /*String[] constellationArr = HoroscopeConstant.constellationArr;
                int contellationIndex = HoroscopeConstant.findContellationIndex(constellationArr,constellationName);
                imgConstellationHead.setImageResource(HoroscopeConstant.imgHoroscopeArr[contellationIndex]);*/
                    } else {
                        getMonthYunCheng(constellationName);
                    }

                } else {
                    //未登录  默认星座：天蝎座
                    getMonthYunCheng(constellationName);
                    return;
                }
            }
        } else {
            //            显示数据
            showMonthCheng(monthYunChengBean);
            setContellationHead();
        }
    }
}
