package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;

import java.util.HashMap;
import java.util.List;



public class BaziPaipanInputConstant {

    public static final String INTENT_INPUT = "input";

    public interface View extends BaseUiView<BaziPaipanInputConstant.Presenter>{
        void showHistory(List<BaziUserBean> beans);
        void showPaipan(BaziPaipanBean bean);
    }

    public interface Presenter extends BasePresenter{
        void getPaipan(HashMap<String, String> map);

        void getYinYuan(HashMap<String, String> map);

        void getShiYe(HashMap<String, String> map);

        void getXingGe(HashMap<String, String> map);

        void getXTpapan(HashMap<String, String> map);

        void getHistory();
        void saveHistory(BaziUserBean bean);
        void delHistory();
    }

}
