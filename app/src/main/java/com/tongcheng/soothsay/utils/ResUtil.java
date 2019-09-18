package com.tongcheng.soothsay.utils;

import android.content.Context;
import android.util.Log;

import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.http.ApiBuild;

/**
 * Created by Gubr on 2016/12/4.
 */

public class ResUtil {

    private static Context context;


    private static int[] godImage = {
            R.drawable.god_0, R.drawable.god_1, R.drawable.god_2, R.drawable.god_3, R.drawable
            .god_4, R.drawable.god_5, R.drawable.god_6, R.drawable.god_7, R.drawable.god_8, R
            .drawable.god_9, R.drawable.god_10, R.drawable.god_11, R.drawable.god_12, R.drawable
            .god_13, R.drawable.god_14, R.drawable.god_15, R.drawable.god_16, R.drawable.god_17, R
            .drawable.god_18, R.drawable.god_19, R.drawable.god_20, R.drawable.god_21, R.drawable
            .god_22, R.drawable.god_23, R.drawable.god_24, R.drawable.god_25, R.drawable.god_26, R
            .drawable.god_27, R.drawable.god_28, R.drawable.god_29, R.drawable.god_30, R.drawable
            .god_31};

    private static int[] gpImage = {
            R.drawable.qfzy_qifutai_gp1, R.drawable.qfzy_qifutai_gp2, R.drawable
            .qfzy_qifutai_gp3, R.drawable.qfzy_qifutai_gp4, R.drawable.qfzy_qifutai_gp5, R
            .drawable.qfzy_qifutai_gp6, R.drawable.qfzy_qifutai_gp7, R.drawable.qfzy_qifutai_gp8,
            R.drawable.qfzy_qifutai_gp9, R.drawable.qfzy_qifutai_gp10, R.drawable
            .qfzy_qifutai_gp11, R.drawable.qfzy_qifutai_gp12, R.drawable.qfzy_qifutai_gp13, R
            .drawable.qfzy_qifutai_gp14, R.drawable.qfzy_qifutai_gp15, R.drawable.qfzy_qifutai_gp16,
            R.drawable.qfzy_qifutai_gp17, R.drawable.qfzy_qifutai_gp18, R.drawable
            .qfzy_qifutai_gp19, R.drawable.qfzy_qifutai_gp20, R.drawable.qfzy_qifutai_gp21, R
            .drawable.qfzy_qifutai_gp22, R.drawable.qfzy_qifutai_gp23, R.drawable.qfzy_qifutai_gp24,
            R.drawable.qfzy_qifutai_gp25, R.drawable.qfzy_qifutai_gp26, R.drawable
            .qfzy_qifutai_gp27, R.drawable.qfzy_qifutai_gp27, R.drawable.qfzy_qifutai_gp27, R
            .drawable.qfzy_qifutai_gp27, R.drawable.qfzy_qifutai_gp28, R.drawable
            .qfzy_qifutai_gp29, R.drawable.qfzy_qifutai_gp30, R.drawable.qfzy_qifutai_gp31, R
            .drawable.qfzy_qifutai_gp32, R.drawable.qfzy_qifutai_gp33, R.drawable
            .qfzy_qifutai_gp34, R.drawable.qfzy_qifutai_gp35, R.drawable.qfzy_qifutai_gp36, R
            .drawable.qfzy_qifutai_gp37, R.drawable.qfzy_qifutai_gp38, R.drawable
            .qfzy_qifutai_gp39, R.drawable.qfzy_qifutai_gp40, R.drawable.qfzy_qifutai_gp41, R
            .drawable.qfzy_qifutai_gp42, R.drawable.qfzy_qifutai_gp43, R.drawable
            .qfzy_qifutai_gp44, R.drawable.qfzy_qifutai_gp45, R.drawable.qfzy_qifutai_gp46, R
            .drawable.qfzy_qifutai_gp47, R.drawable.qfzy_qifutai_gp48, R.drawable
            .qfzy_qifutai_gp49, R.drawable.qfzy_qifutai_gp50, R.drawable.qfzy_qifutai_gp51, R
            .drawable.qfzy_qifutai_gp52, R.drawable.qfzy_qifutai_gp53, R.drawable
            .qfzy_qifutai_gp54, R.drawable.qfzy_qifutai_gp55, R.drawable.qfzy_qifutai_gp56, R
            .drawable.qfzy_qifutai_gp57, R.drawable.qfzy_qifutai_gp58, R.drawable
            .qfzy_qifutai_gp59, R.drawable.qfzy_qifutai_gp60, R.drawable.qfzy_qifutai_gp61, R
            .drawable.qfzy_qifutai_gp62, R.drawable.qfzy_qifutai_gp63, R.drawable
            .qfzy_qifutai_gp64, R.drawable.qfzy_qifutai_gp65, R.drawable.qfzy_qifutai_gp66, R
            .drawable.qfzy_qifutai_gp67, R.drawable.qfzy_qifutai_gp68, R.drawable
            .qfzy_qifutai_gp69, R.drawable.qfzy_qifutai_gp70, R.drawable.qfzy_qifutai_gp71, R
            .drawable.qfzy_qifutai_gp72, R.drawable.qfzy_qifutai_gp73, R.drawable.qfzy_qifutai_gp74
    };

    private ResUtil() {
    }


    private ResUtil(Context context) {
        ResUtil.context = context;
    }


    public static ResUtil resUtil;

    public static ResUtil newInstance() {
        if (resUtil == null) {
            synchronized (ApiBuild.class) {
                if (resUtil == null) {
                    resUtil = new ResUtil(MyApplicationLike.getInstance());

                }
            }
        }
        return resUtil;
    }


    public int getNamePositionByStringArray(int stringArrayId, String name) {
        String[] stringArray = context.getResources().getStringArray(stringArrayId);
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }


    public String getNameFindContent(String name, int nameArrayId, int contentArrayId) {
        int stringPositionByStringArray = getNamePositionByStringArray(nameArrayId, name);
        return getPositionFindContent(stringPositionByStringArray, contentArrayId);
    }


    public String getPositionFindContent(int position, int contentArrayId) {
        return getPositionFindContent(position, contentArrayId, "");
    }

    public String getPositionFindContent(int position, int contentArrayId, String def) {
        String[] stringArray = context.getResources().getStringArray(contentArrayId);
        if (position < stringArray.length) {
            return stringArray[position];
        }
        return def;
    }


    public int getNamePositionByStringArray(String stringArrayName, String name) {
        int id = context.getResources().getIdentifier(stringArrayName, "id", context
                .getPackageName());
        if (id != 0) {
            return getNamePositionByStringArray(id, name);
        }
        return -1;
    }


    private int getDaxianIdByName(String name) {
        String[] daxianArray = context.getResources().getStringArray(R.array.daxian_name);

        for (int i = 0; i < daxianArray.length; i++) {
            if (daxianArray[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * @param name
     * @return 找到返回position 没有找到将返回-1;
     */
    public int getFuMingIdByNmaeV2(String name){
        int position=-1;
        if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_jinqiancaiyun_names,name))!=-1)return position;
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_pinganhushen_names,name))!=-1)return position;
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_wenchangguanyun_names,name))!=-1)return position;//5
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_taohuayinyuan_names,name))!=-1)return position;
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_bihuozhensha_names,name))!=-1)return position;
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_songzitianding_names,name))!=-1)return position;//2
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_taisui_names,name))!=-1)return position;
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_anjiazhenzhai_names,name))!=-1)return position;
         if ((position=getNamePositionByStringArray(R.array.fy_dade_lingfu_hushen_names,name))!=-1)return position;
        return position;
    }




    /**
     * 通过符名获取id 代码待优化
     *
     * @param name
     * @return
     */
    public static int getFuMingIdByName(String name, Context context) {
        String[] jqArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_jinqiancaiyun_names);
        String[] paArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_pinganhushen_names);
        String[] wcArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_wenchangguanyun_names);//5
        String[] thArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_taohuayinyuan_names);
        String[] bhArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_bihuozhensha_names);
        String[] szArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_songzitianding_names);//2
        String[] tsArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_taisui_names);
        String[] ajArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_anjiazhenzhai_names);
        String[] hsArray = context.getResources().getStringArray(R.array.fy_dade_lingfu_hushen_names);
        for (int i = 0; i < jqArray.length; i++) {
            if (jqArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < paArray.length; i++) {
            if (paArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < wcArray.length; i++) {
            if (wcArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < thArray.length; i++) {
            if (thArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < bhArray.length; i++) {
            if (bhArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < szArray.length; i++) {
            if (szArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < tsArray.length; i++) {
            if (tsArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < ajArray.length; i++) {
            if (ajArray[i].equals(name)) {
                return i;
            }
        }
        for (int i = 0; i < hsArray.length; i++) {
            if (hsArray[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private int getPusaIdByName(String name) {
        String[] stringArray = context.getResources().getStringArray(R.array.pusa_name);

        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getGodContentByName(String name) {
        int position = -1;
        if ((position = getDaxianIdByName(name)) != -1) {
            String[] stringArray = context.getResources().getStringArray(R.array.daxian);
            return stringArray[position];
        }
        if ((position = getPusaIdByName(name)) != -1) {
            String[] stringArray = context.getResources().getStringArray(R.array.pusa);
            return stringArray[position];
        }

        return "";
    }

    public int getGodImageIDByGodName(String name) {
        int daxianIdByName = getDaxianIdByName(name);
        if (daxianIdByName == -1) {
            int pusaIdByName = getPusaIdByName(name);
            if (pusaIdByName==-1){
                return -1;
            }
            return pusaIdByName + getDaxianLengh();
        }
        return daxianIdByName;
    }


    public int getGodImageRes(String name) {
//        int drawable = context.getResources().getIdentifier("god_" + getGodImageIDByGodName(name)
//                , "drawable", context.getPackageName());
//        if (drawable == 0) {
//            return R.drawable.qifu_qingxian;
//        }
//        return drawable;
        int position = getGodImageIDByGodName(name);
        if (position == -1) {
            return R.drawable.qifu_qingxian;
        }
        return godImage[getGodImageIDByGodName(name)];

    }

    public int getDaxianLengh() {
        String[] stringArray = context.getResources().getStringArray(R.array.daxian);
        return stringArray.length;
    }


    public int getGPIdByName(String gpName) {
        String[] stringArray = context.getResources().getStringArray(R.array.gongping_name);
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(gpName)) {
                return i;
            }
        }
        return -1;
    }


    public String getGpContentByName(String gpname) {
        String[] stringArray = context.getResources().getStringArray(R.array.gongping_content);
        int gpIdByName = getGPIdByName(gpname);
        if (stringArray.length > gpIdByName) {
            return stringArray[gpIdByName];
        }
        return "";
    }


    /**
     * 获取供品的图片
     *
     * @param gpName
     * @return
     */
    public int getGpImageByName(String gpName) {
        int gpIdByName = getGPIdByName(gpName);
//        int drawable = context.getResources().getIdentifier("qfzy_qifutai_gp" + (gpIdByName + 1),
//                "drawable", context.getPackageName());
//        if (drawable == 0) {
//            return R.drawable.gp_29;
//        }
//        return drawable;

        if (gpIdByName != -1 && gpIdByName < gpImage.length)
            return gpImage[gpIdByName];
        return R.drawable.gp_29;
    }


    //    public int getResPosition(String string){
//        context.getResources().getIdentifier()
//    }


}
