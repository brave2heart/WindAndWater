package com.tongcheng.soothsay.ui.fragment.calculation.life.zeri;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.ZeriSelectEventAdapter;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;
import com.tongcheng.soothsay.data.calculate.life.zeri.IZeriSource;
import com.tongcheng.soothsay.data.calculate.life.zeri.ZeriSourceImp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/22.
 */

public class ZeriInputPresenter implements ZeriInputContract.Presenter, IZeriSource.OnResultListener {
    ZeriInputContract.View mView;
    List<AlmanacBean.ResultBean> mResultBeanList;
    Activity mActivity;
    IZeriSource mZeriSource;
    private ZeriSelectEventAdapter mZeriSelectEventAdapter;
    private Date mDate;
    private String mTemp;
    private Integer mYear;


    public ZeriInputPresenter(Activity activity,ZeriInputContract.View view){
        view.setPresenter(this);
        mActivity=activity;
        mView=view;
        mZeriSource=new ZeriSourceImp(activity);
    }

    @Override
    public void setSelectBtnText(String string){
        mZeriSource.setCheckTitle(string);
        mView.setSelectEventBtnText(mZeriSource.getSelectEventBtnText());
    }


    @Override
    public void showTimePicker() {
        mView.showTimePicker();
    }

    @Override
    public void showSelectEvent() {
        if (mZeriSelectEventAdapter == null) {
            mZeriSelectEventAdapter = new ZeriSelectEventAdapter(mActivity, mZeriSource.getSelectEventData(), R.layout.zeri_chaxun_shixiang_listview_item);
            mView.setSelectEventAdapter(R.layout.base_listview,mZeriSelectEventAdapter);
        } else {
            mZeriSelectEventAdapter.notifyChangeData(mZeriSource.getSelectEventData());
        }
        mView.showSelectEvent();
    }

    @Override
    public void startCheck() {
        if (null==mDate){
            mView.showSelectDate();
            return;
        }else if ((mYear<2010||mYear>2025)){
            mView.showDayError();
            return;
        }
        if (TextUtils.isEmpty(mZeriSource.getCheckTitle())||mZeriSource.getCheckTitle().equals("请选择你要查询的事项")){
            mView.showSelectEventTost();
            return ;
        }
        mView.setListEmpty();
        mView.showLoad();
        mZeriSource.getResultSource(this);
    }


    /**
     * @param flag true 是宜  false 是忌
     */
    @Override
    public void changeEventType(boolean flag) {
        mZeriSource.saveEventType(flag);
    }

    @Override
    public void saveTimeSelect(Date date) {
        saveDate(date);
        mZeriSource.saveSelectTime(date);
        mView.setTimeBtnText(mZeriSource.getTimeBtnText());
    }

    private void saveDate(Date date) {
        mDate = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
         mTemp = sdf.format(mDate);
        mYear = Integer.valueOf(mTemp);
    }


    @Override
    public void onResultItemClick(AdapterView<?> parent, View view, int position, long id) {
        mView.showResultActivity(mZeriSource.getDatas(position));
    }

    @Override
    public void gotoShowResultActivity(int position) {
        mView.showResultActivity(mResultBeanList.get(position));
    }

    @Override
    public void start() {

    }

    @Override
    public void getResultDatas(List<AlmanacBean.ResultBean> datas) {
        if (datas.size()==0){
            mView.showEmpty();
        }
        mResultBeanList=datas;
        mView.showLoadFinish();
        mView.showResultListView(datas);
    }

    @Override
    public void onError() {
        mView.showLoadFinish();
        mView.showNetError();
    }


    @Override
    public void setCheckEventBtnText(){
        mView.setSelectEventBtnText(mZeriSource.getSelectEventBtnText());
    }
}
