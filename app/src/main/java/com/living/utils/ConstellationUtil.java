package com.living.utils;

import com.tongcheng.soothsay.R;

import java.util.Random;

/**
 * Created by weihao on 2018/1/2.
 */

public class ConstellationUtil {

    /*获取星座名*/
    public static String getConstellationName(String mon, String dat) {
        int month = Integer.parseInt(mon);
        int date = Integer.parseInt(dat);
        switch (month) {
            case 1: {
                if (date >= 20) {
                    return "水瓶座";
                } else {
                    return "摩羯座";
                }
            }
            case 2:
                if (date <= 18) {
                    return "水瓶座";
                } else {
                    return "双鱼座";
                }
            case 3:
                if (date <= 20) {
                    return "双鱼座";
                } else {
                    return "白羊座";
                }
            case 4:
                if (date <= 19) {
                    return "白羊座";
                } else {
                    return "金牛座";
                }
            case 5:
                if (date <= 20) {
                    return "金牛座";
                } else {
                    return "双子座";
                }
            case 6:
                if (date <= 21) {
                    return "双子座";
                } else {
                    return "巨蟹座";
                }
            case 7:
                if (date <= 22) {
                    return "巨蟹座";
                } else {
                    return "狮子座";
                }
            case 8:
                if (date <= 22) {
                    return "狮子座";
                } else {
                    return "处女座";
                }
            case 9:
                if (date <= 22) {
                    return "处女座";
                } else {
                    return "天秤座";
                }
            case 10:
                if (date <= 23) {
                    return "天秤座";
                } else {
                    return "天蝎座";
                }
            case 11:
                if (date <= 22) {
                    return "天蝎座";
                } else {
                    return "射手座";
                }
            case 12:
                if (date <= 21) {
                    return "射手座";
                } else {
                    return "摩羯座";
                }

            default:
                break;
        }


        return "";
    }


    /*获取星座图片*/

    public static int getConstellationIcon(String name) {
        switch (name) {
            case "水瓶座":
                return R.drawable.homeforcunt_constellation_aquarius;
            case "双鱼座":
                return R.drawable.homeforcunt_constellation_pisces;
            case "白羊座":
                return R.drawable.homeforcunt_constellation_aries;
            case "金牛座":
                return R.drawable.homeforcunt_constellation_taurus;
            case "双子座":
                return R.drawable.homeforcunt_constellation_gemini;
            case "巨蟹座":
                return R.drawable.homeforcunt_constellation_cancer;
            case "狮子座":
                return R.drawable.homeforcunt_constellation_lion;
            case "处女座":
                return R.drawable.homeforcunt_constellation_virgo;
            case "天秤座":
                return R.drawable.homeforcunt_constellation_libra;
            case "天蝎座":
                return R.drawable.homeforcunt_constellation_scorpio;
            case "射手座":
                return R.drawable.homeforcunt_constellation_sagittarius;
            case "摩羯座":
                return R.drawable.homeforcunt_constellation_capricorn;
            default:
                break;
        }

        return R.drawable.weather_no;

    }


    //获取星座运势Int类型的分数
    public static int getInt(String str) {
        int mInt = Integer.parseInt(str.replace("%", ""));
        return mInt;
    }

    /*获取星座运势星星个数
    * 总共5颗星，也就是一颗代表20分
    * */
    public static float getConsRating(String s) {
        int anInt = getInt(s);
        return anInt / 20;
    }

    /*增加的随机星运分数*/
    public static int getConsGrade(int consGrade) {
        Random random = new Random();
        consGrade = consGrade + random.nextInt(20) + 1;
        return consGrade;
    }

    /*获取星座时间段*/
    public static String getConsTimeSlot(String consName) {
        switch (consName) {
            case "水瓶座":
                return "1.20—2.18";
            case "双鱼座":
                return "2.19—3.20";
            case "白羊座":
                return "3.21—4.19";
            case "金牛座":
                return "4.20—5.20";
            case "双子座":
                return "5.21—6.21";
            case "巨蟹座":
                return "6.22—7.22";
            case "狮子座":
                return "7.23—8.22";
            case "处女座":
                return "8.23—9.22";
            case "天秤座":
                return "9.23—10.23";
            case "天蝎座":
                return "10.24—11.22";
            case "射手座":
                return "11.23—12.21";
            case "摩羯座":
                return "12.22—1.19";

            default:
                break;
        }
        return "无";
    }

    /*获取星座类型*/
    public static String getConsType(String consName) {
        switch (consName) {
            case "双鱼座":
            case "巨蟹座":
            case "天蝎座":
                return "水象星座";
            case "射手座":
            case "狮子座":
            case "白羊座":
                return "火象星座";
            case "天秤座":
            case "双子座":
            case "水瓶座":
                return "风象星座";
            case "处女座":
            case "摩羯座":
            case "金牛座":
                return "土象星座";

            default:
                break;
        }
        return "无";
    }

}
