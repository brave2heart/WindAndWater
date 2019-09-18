package com.tongcheng.soothsay.bean.mine;

/**
 * Created by 宋家任 on 2016/11/22.
 * 个人信息实体bean
 */

public class MineInfoBean {


    /**
     * bornTime :
     * createTime : 1479618683000
     * headPic :
     * marriageStatus : 0
     * name :
     * phone : 13538769334
     * sex : 0
     * token : d2b047bdbbbbe85ce6bd4b3919ba6336
     * workStatus : 0
     */

    private String bornTime;
    private String createTime;
    private String headPic;
    private String marriageStatus;
    private String name;
    private String phone;
    private String sex;
    private String token;
    private String workStatus;
    private String jf;

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    /**
     * 出生日期
     *
     * @return
     */
    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

    /**
     * 注册时间
     *
     * @return
     */
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 头像
     *
     * @return
     */
    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    /**
     * 婚姻状况
     *
     * @return
     */
    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    /**
     * 昵称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 手机号
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 性别
     *
     * @return
     */
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 工作状况
     *
     * @return
     */
    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
