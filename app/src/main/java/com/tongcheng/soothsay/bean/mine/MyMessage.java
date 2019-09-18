package com.tongcheng.soothsay.bean.mine;

import java.util.List;

/**
 * Created by ALing on 2017/1/3 0003.
 */

public class MyMessage {


    /**
     * createtime :  1483082310000
     * isAnswer : 0
     * redirectUrl : http://127.0.0.1:8080/html/dashixiangqing.html?dsId=2&token=ef800a88852fc09960b2517dd63ad491
     * replytime : 2016-12-30 15:18:30
     * title : 姓名张三，性别男，生辰1987年6月25日上午9时，问今年是否适合结婚。
     */


    private  List<MyMyssageListBean> communList;

    public List<MyMyssageListBean> getCommunList() {
        return communList;
    }

    public void setCommunList(List<MyMyssageListBean> communList) {
        this.communList = communList;
    }

    public static class MyMyssageListBean{
        private String createtime;
        private String isAnswer;
        private String redirectUrl;
        private String replytime;
        private String title;
        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIsAnswer() {
            return isAnswer;
        }

        public void setIsAnswer(String isAnswer) {
            this.isAnswer = isAnswer;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getReplytime() {
            return replytime;
        }

        public void setReplytime(String replytime) {
            this.replytime = replytime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
