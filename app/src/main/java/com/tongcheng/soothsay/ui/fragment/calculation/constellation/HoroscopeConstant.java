package com.tongcheng.soothsay.ui.fragment.calculation.constellation;

import android.util.Log;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.DayYunCheng;
import com.tongcheng.soothsay.bean.calculation.MonthYunCheng;
import com.tongcheng.soothsay.bean.calculation.WeekYunCheng;


/**
 * Created by ALing on 2016/11/28 0028.
 */

public class HoroscopeConstant {

    public final static String[] constellationArr = new String[] { "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座","水瓶座", "双鱼座" };
    public final static int[] imgHoroscopeArr = new int[]{R.drawable.xztl_xzyc_0,R.drawable.xztl_xzyc_1,R.drawable.xztl_xzyc_2,R.drawable.xztl_xzyc_3,
            R.drawable.xztl_xzyc_4,R.drawable.xztl_xzyc_5,R.drawable.xztl_xzyc_6,R.drawable.xztl_xzyc_7,
            R.drawable.xztl_xzyc_8,R.drawable.xztl_xzyc_9,R.drawable.xztl_xzyc_10,R.drawable.xztl_xzyc_11};
    public static final String XINGZUO_KEY = "56521a14b05ea90f0989adc973c41c97";
    public static final String XINGZUO_API = "http://web.juhe.cn:8080/constellation/getAll?consName=%s&type=%s&key=%s";
    public static final String[] XINGZUO_TYPE = new String[]{"today", "tomorrow", "week", "month"};
    public static final String INTENT_DATE = "date";

    /**
     * 找到星座在数组中的索引
     * @param constellationArr  星座数组
     * @param constellationName  星座名
     * @return
     */

    public static int findContellationIndex(String[] constellationArr,String constellationName) {
        boolean flag = false;
        for (int i = 0; i < constellationArr.length; i++ ){
            if (constellationArr[i].equals(constellationName)){
                flag = true;
                Log.e("tag", "findContellationIndex: "+ i );
                return i;
            }
        }
        if (flag == false){
            return  7;
        }
        return -1;
    }

    public interface View extends BaseUiView<HoroscopeConstant.Presenter> {
        void showYunCheng(DayYunCheng bean);
        void showWeekYunCheng(WeekYunCheng bean);
        void showMonthCheng(MonthYunCheng bean);
    }

    public interface Presenter extends BasePresenter {
        void getYunCheng(String consName,String type);
        void getWeekYunCheng(String consName,String type);
        void getMonthYunCheng(String consName,String type);
        void onDestroy();
    }
}
