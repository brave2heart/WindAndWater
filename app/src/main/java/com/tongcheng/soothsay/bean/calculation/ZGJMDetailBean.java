package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/7.
 */

public class ZGJMDetailBean {


    /**
     * reason : successed
     * result : [{"id":"eff00c0db9003509fab5fb58b8f8b464","title":"跳舞","des":"梦见跳舞的场面，通常预示你会有好运气，收到好消息。按照心理分析的观点，梦见舞蹈还象征求爱或是性交。","list":["梦见跳舞的场面，通常预示你会有好运气，收到好消息。按照心理分析的观点，梦见舞蹈还象征求爱或是性交。","梦见自己在跳舞，预示你会得到提升，带来将获得成功或爱情如意。未婚男人梦见自己跳舞，预示会娶漂亮的妻子。","梦见自己注视别人跳舞，暗示近期你充满挑战欲，有实现自己目标雄心壮志，近期要好好把握机会。但是如果梦见别人跳舞，自己却躲得很远，坐在角落里观看，则暗示你可能会收到不好的消息，即别人获得成功。","梦见和恋人共舞，暗示恋爱甜蜜，你们的关系即将更进一步。","梦见和陌生女人一起跳舞，则要提"]}]
     * error_code : 0
     */

    @SerializedName("reason")
    private String reason;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("result")
    private List<ResultBean> result;

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<ResultBean> getResult() {
        if (result==null){
            return new ArrayList<ResultBean>();
        }
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : eff00c0db9003509fab5fb58b8f8b464
         * title : 跳舞
         * des : 梦见跳舞的场面，通常预示你会有好运气，收到好消息。按照心理分析的观点，梦见舞蹈还象征求爱或是性交。
         * list : ["梦见跳舞的场面，通常预示你会有好运气，收到好消息。按照心理分析的观点，梦见舞蹈还象征求爱或是性交。","梦见自己在跳舞，预示你会得到提升，带来将获得成功或爱情如意。未婚男人梦见自己跳舞，预示会娶漂亮的妻子。","梦见自己注视别人跳舞，暗示近期你充满挑战欲，有实现自己目标雄心壮志，近期要好好把握机会。但是如果梦见别人跳舞，自己却躲得很远，坐在角落里观看，则暗示你可能会收到不好的消息，即别人获得成功。","梦见和恋人共舞，暗示恋爱甜蜜，你们的关系即将更进一步。","梦见和陌生女人一起跳舞，则要提"]
         */

        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("des")
        private String des;
        @SerializedName("list")
        private List<String> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
