package com.tongcheng.soothsay.bean.huangli;

import java.util.List;

/**
 * Created by Steven on 16/11/3.
 */

public class FutureWeatherBean {


    /**
     * date : 2014-10-15
     * info : {"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]}
     * week : 三
     * nongli : 九月廿二
     */

    private String date;
    private InfoBean info;
    private String week;
    private String nongli;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public static class InfoBean {
        private List<String> day;
        private List<String> night;

        public List<String> getDay() {
            return day;
        }

        public void setDay(List<String> day) {
            this.day = day;
        }

        public List<String> getNight() {
            return night;
        }

        public void setNight(List<String> night) {
            this.night = night;
        }
    }
}
