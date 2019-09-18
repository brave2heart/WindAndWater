package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi;

import android.view.View;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.BaseView;
import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;

import java.util.HashMap;

/**
 * Created by bozhihuatong on 2016/12/9.
 */

public interface BaziMainContract {
    interface BaziMainPresener extends BasePresenter {
        void getYinyun();

        void getXingge();

        void getShiye();

        void getXiantianmingpan();

        void setMap(HashMap<String, String> map);

        HashMap<String, String> getMap();

        void getPaipan();


        void setUser();
    }


    //    这个是给activity 继承的
    interface BaZiMainView extends BaseUiView<BaziMainPresener> {
        void showYinyun();
        void showXingge();
        void showShiye();
        void showXiantiammingpan();

        void showPaipan(BaziPaipanBean bean);

        void showPaipan(BaziPaipanBean bean, BaziUserBean inputBean);

        void showPaipan(BaziPaipanBean data, HashMap<String, String> map);
    }


    public interface OnBtnClickListener {
        void onClick(View view);
    }


}
