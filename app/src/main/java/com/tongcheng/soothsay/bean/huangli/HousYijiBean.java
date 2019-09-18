package com.tongcheng.soothsay.bean.huangli;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Steven on 16/11/12.
 */

public class HousYijiBean {


    /**
     * reason : successed
     * result : [{"yangli":"2016-11-12","hours":"1-3","des":"冲猴 煞北 时冲庚申 天兵 六合 进贵 喜神","yi":"赴任 出行","ji":" 修造 盖屋 移徙 安床 入宅 开市 开仓 见贵 求财 嫁娶 进人口"},{"yangli":"2016-11-12","hours":"3-5","des":"冲猪 煞东 时冲癸亥 日破","yi":"上梁 盖屋 入殓","ji":" 祈福 求嗣 订婚 嫁娶 出行 求财 开市 交易 安床"},{"yangli":"2016-11-12","hours":"5-7","des":"冲狗 煞南 时冲壬戍 六戊 雷兵 司命 右弼","yi":"祭祀 祈福 酬神 出行 求财 见贵 订婚 嫁娶 修造 安葬 青龙 赴任","ji":" 白虎须用 麒麟符制 否则 诸事不宜"},{"yangli":"2016-11-12","hours":"7-9","des":"冲马 煞南 时冲戊午 白虎 贵人 大进 贪狼","yi":"","ji":"祭祀 祈福 酬神 出行 求财 见贵 订婚 嫁娶 修造 安葬 青龙 赴任"},{"yangli":"2016-11-12","hours":"9-11","des":"冲羊 煞东 时冲己未 不遇 玉堂 右弼 唐符","yi":"","ji":"祈福 求嗣 订婚 嫁娶 出行 求财 开市 交易 安床 修造 入宅 安葬 赴任"},{"yangli":"2016-11-12","hours":"11-13","des":" 冲鼠 煞北 时冲甲子 地兵 青龙 日禄 金星","yi":"祈福 订婚 嫁娶 安床 移徙 入宅 安葬 赴任 出行 求财 见贵","ji":"修造 动土"},{"yangli":"2016-11-12","hours":"13-15","des":" 冲牛 煞西 时冲乙丑 三合 福星 明星 武曲","yi":"祈福 求嗣 订婚 嫁娶 出行 求财 开市 交易 安床 祭祀","ji":"-"},{"yangli":"2016-11-12","hours":"15-17","des":" 冲虎 煞南 时冲丙寅 天刑 路空 禄贵","yi":"求嗣 出行 求财 嫁娶 安葬","ji":"赴任 词讼 祭祀 祈福 斋醮 开光"},{"yangli":"2016-11-12","hours":"17-19","des":" 冲兔 煞东 时冲丁卯 朱雀 天贼 路空 长生","yi":"求嗣 嫁娶 移徙 入宅 开市 交易 修造 安葬","ji":"朱雀须用 凤凰符制 否则 诸事不宜 祭祀 祈福 斋醮 酬神"},{"yangli":"2016-11-12","hours":"19-21","des":" 冲龙 煞北 时冲戊辰 狗食 金匮","yi":"订婚 嫁娶 开市 安葬","ji":"祭祀 祈福 斋醮 酬神"},{"yangli":"2016-11-12","hours":"21-23","des":" 冲蛇 煞西 时冲己已 不遇 建刑 天德 宝光","yi":"祭祀 祈福 斋醮 酬神 修造 作灶","ji":"赴任 出行 造桥 乘船"},{"yangli":"2016-11-12","hours":"23-1","des":"冲鸡 煞西 时冲辛酉 三合 进贵 天赦 贪狼","yi":"-","ji":" 日时相冲 诸事不宜"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{

        private static final long serialVersionUID = 1L;

        /**
         * yangli : 2016-11-12
         * hours : 1-3
         * des : 冲猴 煞北 时冲庚申 天兵 六合 进贵 喜神
         * yi : 赴任 出行
         * ji :  修造 盖屋 移徙 安床 入宅 开市 开仓 见贵 求财 嫁娶 进人口
         */

        private String yangli;
        private String hours;
        private String des;
        private String yi;
        private String ji;

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }
    }
}
