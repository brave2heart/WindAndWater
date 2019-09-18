package com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Steven on 16/11/16.
 */

public class ZiweiInputConstant {

    public static final String INTENT_INPUT = "input";

    public interface View extends BaseUiView<ZiweiInputConstant.Presenter>{
        void showHistory(List<ZiweiUserBean> beans);
        void showPaipan(ZiweipaipanBean bean);
    }

    public interface Presenter extends BasePresenter{
        void getPaipan(HashMap<String,String> map);
        void getHistory();
        void saveHistory(ZiweiUserBean bean);
        void delHistory();
    }

}
