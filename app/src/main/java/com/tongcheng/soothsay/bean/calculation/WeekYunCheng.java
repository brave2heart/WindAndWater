package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by ALing on 2016/11/28 0028.
 */

public class WeekYunCheng {

    /**
     * date : 2016年11月27日-2016年12月03日
     * name : 白羊座
     * health : 健康：心态放松，尤其是参与体育锻炼，对身心有优于平日的效果。 作者：马子晴
     * job : 求职：文化、教育、旅游行业机会多。
     * love : 恋情：贪欲会打破感情的平稳，反而失去原本拥有的。子晴小提醒：珍惜眼前人。
     * money : 财运：回报机会多数出现在长期坚守的项目。决策失误，往往因为贪欲。见好就收是本周的理财建议
     * weekth : 49
     * work : 工作：任务不多，靠经验就可应付，你考虑的是更长远的未来，有学习交流出差机会。周末，水星入事业宫，事业目标会变得明晰。
     * resultcode : 200
     * error_code : 0
     */

    private String date;
    private String name;
    private String health;
    private String job;
    private String love;
    private String money;
    private int weekth;
    private String work;
    private String resultcode;
    private int error_code;
    private String reason;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getWeekth() {
        return weekth;
    }

    public void setWeekth(int weekth) {
        this.weekth = weekth;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
