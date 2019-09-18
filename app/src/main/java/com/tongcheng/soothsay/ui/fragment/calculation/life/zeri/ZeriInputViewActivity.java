package com.tongcheng.soothsay.ui.fragment.calculation.life.zeri;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.ZeriResultAdapter;
import com.tongcheng.soothsay.adapter.calculation.ZeriSelectEventAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.base.LoadingView;
import com.tongcheng.soothsay.bean.calculation.ZeriBean;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.popwindow.ZeriSelectEventPop;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bozhihuatong on 2016/12/22.
 */

public class ZeriInputViewActivity extends BaseTitleActivity implements ZeriInputContract.View, TimePickerView.OnTimeSelectListener, AdapterView.OnItemClickListener, ZeriSelectEventPop.OnItemClickListener {

    //    显示要查询的日期
    @BindView(R.id.zeri_kaishi_riqi_button_zeri)
    Button mBtnSelectRiqi;
    //    查询事项
    @BindView(R.id.zeri_chaxun_shixiang_button_zeri)
    Button mBtnSelectEvent;
    //    listview 显示查询的结果
    @BindView(R.id.zeri_yiji_time_result_listView_zeri)
    ListView mListView;
    //    开始查询
    @BindView(R.id.zeri_kaishi_chaxun_button_zeri)
    Button mBtnStart;

    @BindView(R.id.zeri_chaxun_shixiang_yiji_button_zeri)
    Button mBtnYijiChange;
    @BindView(R.id.view_stub_base_loadingv2)
    ViewStub mViewStubBaseLoadingv2;
    LoadingView baseLoadingViewv2;


    private ZeriInputContract.Presenter mPresenter;
    private TimePickerView timeView;
    private ZeriSelectEventPop mZeriSelectEventPop;
    private ZeriResultAdapter mZeriResultAdapter;
    private AlertDialog mDialog;
    private String[] yijiData = {"宜", "忌"};
    private String myiji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeri_chaxun_layout);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    public LoadingView getBaseLoadingViewv2() {
        if (baseLoadingViewv2 == null) {
            baseLoadingViewv2 = new LoadingView(mViewStubBaseLoadingv2.inflate());
        }
        return baseLoadingViewv2;
    }


    @Override
    public void initView() {
        getBaseHeadView().showTitle("择日查询");
    }

    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        new ZeriInputPresenter(this, this);
    }

    @Override
    public void setTimeBtnText(String s) {
        mBtnSelectRiqi.setText(s);
    }


    @Override
    public void setSelectEventBtnText(String s) {
        mBtnSelectEvent.setText(s);
    }


    @Override
    public void showResultListView(List<AlmanacBean.ResultBean> datas) {
        if (mZeriResultAdapter == null) {
            mZeriResultAdapter = new ZeriResultAdapter(this, datas, R.layout.zeri_yiji_time_result_listview_item);
            mListView.setAdapter(mZeriResultAdapter);
        } else {
            mZeriResultAdapter.notifyChangeData(datas);
        }
    }

    @Override
    public void setSelectEventAdapter(int layoutid, ZeriSelectEventAdapter adapter) {
        mZeriSelectEventPop = new ZeriSelectEventPop(this, layoutid, adapter);
        mZeriSelectEventPop.setOnItemClickListener(this);
    }


    @Override
    public void showSelectEvent() {
        if (mZeriSelectEventPop != null) {
            mZeriSelectEventPop.showPopOnRootView(this);
        }
    }


    private void createProvinceAlert() {
        mDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(yijiData, 0,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                if (ClickUtil.isFastClick()) {
                                    return;
                                }
                                dialog.dismiss();
                                int index = which;
                                Log.e("tag", "onClick: " + index);
                                myiji = yijiData[index].toString();
                                mPresenter.changeEventType(index == 0);
                                mBtnYijiChange.setText(yijiData[index].toString());
                                mPresenter.setSelectBtnText("请选择你要查询的事项");

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
    }


    @Override
    public void showTimePicker() {
        if (timeView == null) {
            timeView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            timeView.setCyclic(false);
            timeView.setTime(new Date());
            timeView.setOnTimeSelectListener(this);
        }
        timeView.show();
    }

    @Override
    public void showResultActivity(AlmanacBean.ResultBean resultBean) {
//        这里跳去显示 结果界面
        GotoUtil.GoToActivityWithBean(this, ZeriDetailsActivity.class, resultBean);
    }

    @Override
    public void showSelectEventTost() {
        Toast.makeText(this, "请选择你要查的事项", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setListEmpty() {
        if (mZeriResultAdapter != null) {
            mZeriResultAdapter.notifyChangeData(new ArrayList<AlmanacBean.ResultBean>());
        }
    }

    @Override
    public void showSelectDate() {
        ToastUtil.showShort(this, "请选择日期");
    }

    @Override
    public void showDayError() {
        ToastUtil.showShort(this, "请选择2010年到2025年内的日期");
    }

    @Override
    public void showNetError() {
        ToastUtil.showShort(this, "网络出错");

    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this, "出错");
    }

    @Override
    public void showEmpty() {
        Toast.makeText(this, "所选日期一个星期内没有比配的日子", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingViewv2().showLoading();
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingViewv2().hideLoading();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(ZeriInputContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.zeri_kaishi_riqi_button_zeri, R.id.zeri_chaxun_shixiang_button_zeri, R.id.zeri_kaishi_chaxun_button_zeri, R.id.zeri_chaxun_shixiang_yiji_button_zeri})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.zeri_kaishi_riqi_button_zeri:
//               显示选择日期的dialog
                mPresenter.showTimePicker();
                break;
            case R.id.zeri_chaxun_shixiang_button_zeri:
//                显示选择事项
                mPresenter.showSelectEvent();
                break;
            case R.id.zeri_kaishi_chaxun_button_zeri:
                mPresenter.startCheck();
                break;
            case R.id.zeri_chaxun_shixiang_yiji_button_zeri:
                createProvinceAlert();
                break;

        }
    }

    @Override
    public void onTimeSelect(Date date) {
        mPresenter.saveTimeSelect(date);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.gotoShowResultActivity(position);

    }

    @Override
    public void onItemClick(ZeriBean zeriBean) {
        mPresenter.setSelectBtnText(zeriBean.getTitle());
    }
}
