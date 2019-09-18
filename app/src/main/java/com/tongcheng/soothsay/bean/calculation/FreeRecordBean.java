package com.tongcheng.soothsay.bean.calculation;

import java.util.List;

/**
 * Created by Steven on 16/12/21.
 */

public class FreeRecordBean {


    /**
     * monthCount : 15
     * recordList : [{"buyTime":"16:26:36","buyDate":"2016年12月14日","info":"拯救2个众生生命"},{"buyTime":"17:47:41","buyDate":"2016年12月14日","info":"拯救1个众生生命"},{"buyTime":"17:50:21","buyDate":"2016年12月14日","info":"拯救1个众生生命"},{"buyTime":"17:54:54","buyDate":"2016年12月14日","info":"拯救1个众生生命"},{"buyTime":"17:55:23","buyDate":"2016年12月14日","info":"拯救1个众生生命"},{"buyTime":"09:53:02","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"09:57:02","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"10:08:34","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"10:08:59","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"14:53:57","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"15:57:11","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"16:07:16","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"16:18:27","buyDate":"2016年12月15日","info":"拯救1个众生生命"},{"buyTime":"17:24:59","buyDate":"2016年12月15日","info":"拯救1个众生生命"}]
     * totalCount : 15
     */

    private String monthCount;
    private String totalCount;
    private List<RecordListBean> recordList;

    public String getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(String monthCount) {
        this.monthCount = monthCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public static class RecordListBean {
        /**
         * buyTime : 16:26:36
         * buyDate : 2016年12月14日
         * info : 拯救2个众生生命
         */

        private String buyTime;
        private String buyDate;
        private String info;

        public String getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(String buyTime) {
            this.buyTime = buyTime;
        }

        public String getBuyDate() {
            return buyDate;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
