package com.tongcheng.soothsay.ui.activity.huangli.almanac;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.huangli.AlmanacBean;

/**
 * Created by Steven on 16/11/11.
 */

public class AlmanacConstant {

    public static final String INTENT_DATE = "date";

    public static final String ALMANAC_KEY = "a3ddf48ea577a3050268a405db17d4ef";

    public static final String ALMANAC_API = "http://v.juhe.cn/laohuangli/d?key=%s&date=%s";
    public static final String HOURSYIJI_API = "http://v.juhe.cn/laohuangli/h?key=%s&date=%s";

    public interface View extends BaseUiView<AlmanacConstant.Presenter> {
        void showAlmanac(AlmanacBean bean);
    }

    public interface Presenter extends BasePresenter{
        void getAlmanac(String date);
        void getHoursYiji(String date);
    }

}
