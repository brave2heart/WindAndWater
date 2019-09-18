package com.living.bean.home;

import java.util.List;

/**
 * Created by weihao on 2018/1/5.
 */

public class JWeChatBean {

    /**
     * msg : ok
     * result : {"channel":"热门","channelid":"1","list":[{"channelid":"1","content":"最近刷了不少以前迷过的电视剧，所以今天突然很想写写李金铭。","likenum":"238","pic":"http://api.jisuapi.com/weixinarticle/upload/20170302/161854_24894.jpeg","readnum":"60881","time":"2017-03-01","title":"深陷整容、隆胸的美嘉竟然跑去结婚，说好的励志上位呢？！","url":"http://mp.weixin.qq.com/s?src=3×tamp=1488442388","weixinaccount":"bbbbayi","weixinname":"毒舌扒姨太","weixinsummary":"时尚小公举"},{"channelid":"1","content":"回复我的问题，就给200元。","likenum":"2605","pic":"http://api.jisuapi.com/weixinarticle/upload/20170302/161853_37460.jpeg","readnum":"100001","time":"2017-03-01","title":"爱要烟火气，不要性冷淡","url":"http://mp.weixin.qq.com/s?src=3×tamp=1488442388&ver=1","weixinaccount":"ilianyue","weixinname":"连岳","weixinsummary":"连岳文字"}],"num":"10","start":"0","total":"19"}
     * status : 0
     */

    private String msg;
    private ResultBean result;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * channel : 热门
         * channelid : 1
         * list : [{"channelid":"1","content":"最近刷了不少以前迷过的电视剧，所以今天突然很想写写李金铭。","likenum":"238","pic":"http://api.jisuapi.com/weixinarticle/upload/20170302/161854_24894.jpeg","readnum":"60881","time":"2017-03-01","title":"深陷整容、隆胸的美嘉竟然跑去结婚，说好的励志上位呢？！","url":"http://mp.weixin.qq.com/s?src=3×tamp=1488442388","weixinaccount":"bbbbayi","weixinname":"毒舌扒姨太","weixinsummary":"时尚小公举"},{"channelid":"1","content":"回复我的问题，就给200元。","likenum":"2605","pic":"http://api.jisuapi.com/weixinarticle/upload/20170302/161853_37460.jpeg","readnum":"100001","time":"2017-03-01","title":"爱要烟火气，不要性冷淡","url":"http://mp.weixin.qq.com/s?src=3×tamp=1488442388&ver=1","weixinaccount":"ilianyue","weixinname":"连岳","weixinsummary":"连岳文字"}]
         * num : 10
         * start : 0
         * total : 19
         */

        private String channel;
        private String channelid;
        private String num;
        private String start;
        private String total;
        private List<ListBean> list;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getChannelid() {
            return channelid;
        }

        public void setChannelid(String channelid) {
            this.channelid = channelid;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * channelid : 1
             * content : 最近刷了不少以前迷过的电视剧，所以今天突然很想写写李金铭。
             * likenum : 238
             * pic : http://api.jisuapi.com/weixinarticle/upload/20170302/161854_24894.jpeg
             * readnum : 60881
             * time : 2017-03-01
             * title : 深陷整容、隆胸的美嘉竟然跑去结婚，说好的励志上位呢？！
             * url : http://mp.weixin.qq.com/s?src=3×tamp=1488442388
             * weixinaccount : bbbbayi
             * weixinname : 毒舌扒姨太
             * weixinsummary : 时尚小公举
             */

            /*channel 	string 	频道名称
              channelid 	string 	频道ID
              total 	string 	总数量
              num 	string 	数量
              start 	string 	起始数量
              title 	string 	标题
              time 	string 	时间
              weixinname 	string 	微信公众号名称
              weixinaccount 	string 	微信号
              weixinsummary 	string 	微信公众号介绍
              pic 	string 	图片
              content 	string 	内容
              url 	string 	文章微信网址
              readnum 	string 	阅读数
              likenum 	string 	点赞数*/

            private String channelid;
            private String content;
            private String likenum;
            private String pic;
            private String readnum;
            private String time;
            private String title;
            private String url;
            private String weixinaccount;
            private String weixinname;
            private String weixinsummary;

            public String getChannelid() {
                return channelid;
            }

            public void setChannelid(String channelid) {
                this.channelid = channelid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLikenum() {
                return likenum;
            }

            public void setLikenum(String likenum) {
                this.likenum = likenum;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getReadnum() {
                return readnum;
            }

            public void setReadnum(String readnum) {
                this.readnum = readnum;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWeixinaccount() {
                return weixinaccount;
            }

            public void setWeixinaccount(String weixinaccount) {
                this.weixinaccount = weixinaccount;
            }

            public String getWeixinname() {
                return weixinname;
            }

            public void setWeixinname(String weixinname) {
                this.weixinname = weixinname;
            }

            public String getWeixinsummary() {
                return weixinsummary;
            }

            public void setWeixinsummary(String weixinsummary) {
                this.weixinsummary = weixinsummary;
            }
        }
    }

}
