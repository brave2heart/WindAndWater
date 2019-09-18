package com.tongcheng.soothsay.ui.fragment.calculation.life.zeri;

import android.view.View;
import android.widget.AdapterView;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.adapter.calculation.ZeriSelectEventAdapter;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;

import java.util.Date;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/22.
 */

public interface ZeriInputContract {
    interface View extends BaseUiView<Presenter>{
        void setTimeBtnText(String s);

        void setSelectEventBtnText(String s);

        void showResultListView(List<AlmanacBean.ResultBean> datas);

        void setSelectEventAdapter(int layoutid, ZeriSelectEventAdapter adapter);

        void showSelectEvent();

        //显示选择日期的dialog
      void   showTimePicker();

        //显示结果
        void showResultActivity(AlmanacBean.ResultBean resultBean);

        void showSelectEventTost();

        void setListEmpty();

        void showSelectDate();

        void showDayError();


        //显示选择事项的popwindow
        //显示查询结果
    }


    interface Presenter extends BasePresenter{
        void setSelectBtnText(String string);

        //显示选择日期的dialog
        //显示选择事项的popwindow
        //开始查询
        void   showTimePicker();

        void showSelectEvent();

        void startCheck();

        void changeEventType(boolean flag);

        void saveTimeSelect(Date date);


        void onResultItemClick(AdapterView<?> parent, android.view.View view, int position, long id);

        void gotoShowResultActivity(int position);

        void setCheckEventBtnText();
    }
}
