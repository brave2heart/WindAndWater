package com.tongcheng.soothsay.data.calculate.life.zeri;

import com.tongcheng.soothsay.bean.calculation.ZeriBean;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;

import java.util.Date;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/23.
 */

public interface IZeriSource {
    String getCheckTitle();

    List<ZeriBean> getSelectEventData();

    void setCheckTitle(String checkTitle);

    void saveEventType(boolean flag);

    void saveSelectTime(Date date);

    String getTimeBtnText();

//    List<AlmanacBean.ResultBean> getResultBean();





    void getResultSource(OnResultListener onResultListener);

    AlmanacBean.ResultBean getDatas(int position);

    String getSelectEventBtnText();

    public interface  OnResultListener{
        void getResultDatas(List<AlmanacBean.ResultBean> datas);

        void onError();
    }
}
