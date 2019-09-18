package com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare;

import android.util.Log;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.WishingSquare;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ALing on 2016/12/8 0008.
 */

public class WishingSquareConstant {
    public final static String WISHINGLIGHT = "wishingLight";
    public final static String SELECTTAB = "select";
    public final static int[] lightArr = new int[]{R.drawable.xuyuandeng_icon_01_normal, R.drawable.xuyuandeng_icon_02_normal, R.drawable.xuyuandeng_icon_03_normal, R.drawable.xuyuandeng_icon_04_normal,
            R.drawable.xuyuandeng_icon_05_normal, R.drawable.xuyuandeng_icon_06_normal, R.drawable.xuyuandeng_icon_07_normal, R.drawable.xuyuandeng_icon_08_normal,
            R.drawable.xuyuandeng_icon_09_normal, R.drawable.xuyuandeng_icon_10_normal, R.drawable.xuyuandeng_icon_11_normal, R.drawable.xuyuandeng_icon_12_normal,
            R.drawable.xuyuandeng_icon_13_normal, R.drawable.xuyuandeng_icon_14_normal, R.drawable.xuyuandeng_icon_15_normal, R.drawable.xuyuandeng_icon_16_normal,
            R.drawable.xuyuandeng_icon_17_normal, R.drawable.xuyuandeng_icon_18_normal, R.drawable.xuyuandeng_icon_19_normal};
    public static int[] selectArr = new int[]{R.drawable.xuyuandeng_icon_01_selected, R.drawable.xuyuandeng_icon_02_selected, R.drawable.xuyuandeng_icon_03_selected, R.drawable.xuyuandeng_icon_04_selected,
            R.drawable.xuyuandeng_icon_05_selected, R.drawable.xuyuandeng_icon_06_selected, R.drawable.xuyuandeng_icon_07_selected, R.drawable.xuyuandeng_icon_08_selected,
            R.drawable.xuyuandeng_icon_09_selected, R.drawable.xuyuandeng_icon_10_selected, R.drawable.xuyuandeng_icon_11_selected, R.drawable.xuyuandeng_icon_12_selected,
            R.drawable.xuyuandeng_icon_13_selected, R.drawable.xuyuandeng_icon_14_selected, R.drawable.xuyuandeng_icon_15_selected, R.drawable.xuyuandeng_icon_16_selected,
            R.drawable.xuyuandeng_icon_17_selected, R.drawable.xuyuandeng_icon_18_selected, R.drawable.xuyuandeng_icon_19_selected};
    public final static int[] xyddArr = new int[]{R.drawable.xydd_yyd_1,R.drawable.xydd_scd_2,R.drawable.xydd_zcd_3,R.drawable.xydd_gmd_4,R.drawable.xydd_syd_5,R.drawable.xydd_tsd_6,
            R.drawable.xydd_jkd_7,R.drawable.xydd_wcd_8,R.drawable.xydd_pad_9,R.drawable.xydd_csd_10,R.drawable.xydd_zyd_11,R.drawable.xydd_qzd_12,R.drawable.xydd_tsd_13,
            R.drawable.xydd_thd_14,R.drawable.xydd_qhd_15,R.drawable.xydd_qxd_16,R.drawable.xydd_tyd_17,R.drawable.xydd_hyd_18,R.drawable.xydd_wsd_19};

        public interface View extends BaseUiView<WishingSquareConstant.Presenter> {
            void showWishingSquare(List<WishingSquare> bean);

        }

        public interface Presenter extends BasePresenter {
            void getWishingSquare(HashMap<String,String> map);

        }

    public static int findIndex(String[] firstArr,String secondArr) {
        boolean flag = false;
        for (int i = 0; i < firstArr.length; i++ ){
            if (firstArr[i].equals(secondArr)){
                flag = true;
                return i;
            }
        }
        if (flag == false){
            return  -1;
        }
        return -1;
    }


}
