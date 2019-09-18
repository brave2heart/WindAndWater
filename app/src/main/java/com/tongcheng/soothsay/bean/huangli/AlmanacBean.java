package com.tongcheng.soothsay.bean.huangli;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Steven on 16/11/11.
 */

public class AlmanacBean {
    /**
     * reason : successed
     * result : {"id":"2436","yangli":"2016-11-11","yinli":"丙申(猴)年十月十二","wuxing":"山下火 开执位","chongsha":"冲兔(辛卯)煞东","baiji":"丁不剃头头必生疮 酉不宴客醉坐颠狂","jishen":"母仓 时阴 生气 圣心 除神 鸣犬","yi":"嫁娶 冠笄 祭祀 祈福 求嗣 斋醮  开光 出行 解除 动土 开市 交易 立券 挂匾 拆卸 破土","xiongshen":"灾煞 天火 五离 朱雀","ji":"伐木 上梁 修造 入殓 理发 会亲友 入宅 安门 安葬 作灶"}
     * error_code : 0
     */

    /*
    {
    "reason": "successed",
    "result": {
        "id": "2436",
        "yangli": "2016-11-11",
        "yinli": "丙申(猴)年十月十二",
        "wuxing": "山下火 开执位",
        "chongsha": "冲兔(辛卯)煞东",
        "baiji": "丁不剃头头必生疮 酉不宴客醉坐颠狂",
        "jishen": "母仓 时阴 生气 圣心 除神 鸣犬",
        "yi": "嫁娶 冠笄 祭祀 祈福 求嗣 斋醮  开光 出行 解除 动土 开市 交易 立券 挂匾 拆卸 破土",
        "xiongshen": "灾煞 天火 五离 朱雀",
        "ji": "伐木 上梁 修造 入殓 理发 会亲友 入宅 安门 安葬 作灶"
    },
    "error_code": 0
    }
     */

    private String reason;
    private ResultBean result;
    private int error_code;


    private List<HousYijiBean.ResultBean> hours;

    public List<HousYijiBean.ResultBean> getHours() {
        return hours;
    }

    public void setHours(List<HousYijiBean.ResultBean> hours) {
        this.hours = hours;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable{
        private static final long serialVersionUID = 8L;


        /**
         * id : 2436
         * yangli : 2016-11-11
         * yinli : 丙申(猴)年十月十二
         * wuxing : 山下火 开执位
         * chongsha : 冲兔(辛卯)煞东
         * baiji : 丁不剃头头必生疮 酉不宴客醉坐颠狂
         * jishen : 母仓 时阴 生气 圣心 除神 鸣犬
         * yi : 嫁娶 冠笄 祭祀 祈福 求嗣 斋醮  开光 出行 解除 动土 开市 交易 立券 挂匾 拆卸 破土
         * xiongshen : 灾煞 天火 五离 朱雀
         * ji : 伐木 上梁 修造 入殓 理发 会亲友 入宅 安门 安葬 作灶
         */

        private String id;
        private String yangli;
        private String yinli;
        private String wuxing;
        private String chongsha;
        private String baiji;
        private String jishen;
        private String yi;
        private String xiongshen;
        private String ji;

        public int getIntYangli(){
            String replace = yangli.replace("-", "");
            return Integer.valueOf(replace);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getYinli() {
            return yinli;
        }

        public void setYinli(String yinli) {
            this.yinli = yinli;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getBaiji() {
            return baiji;
        }

        public void setBaiji(String baiji) {
            this.baiji = baiji;
        }

        public String getJishen() {
            return jishen;
        }

        public void setJishen(String jishen) {
            this.jishen = jishen;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }


        public String[] getYiArray(){
            return getYi().split(" ");
        }

        public String[] getJiArray(){
            return getJi().split(" ");
        }

    }




}
