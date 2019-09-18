package com.tongcheng.soothsay.bean.calculation;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.ResUtil;

/**
 * Created by Gubr on 2016/12/18.
 */

public class GuanYinBean {



    public String getDijiqian() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_dijiqian);
    }

    public String getYunshi() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_yunshi);
    }

    public String getTitle() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_title);
    }

    public String getQianci() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_qianci);
    }

    public String getJieyue() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_jieyue);
    }

    public String getDiangu() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_diangu);
    }

    public String getJiazhai() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_jiazhai);
    }

    public String getZishen() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_zishen);
    }

    public String getQiuchai() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_qiuchai);
    }

    public String getJibing() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_jibing);
    }

    public String getHunyin() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_hunyin);
    }

    public String getLiujia() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_liujia);
    }

    public String getXingren() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_xingren);
    }

    public String getTiancan() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_tiancan);
    }

    public String getLiuchu() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_liuchu);
    }

    public String getXunren() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_xunren);
    }

    public String getGongsong() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_gongsong);
    }

    public String getYiqian() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_yiqian);
    }

    public String getShiwu() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_shiwu);
    }

    public String getJiaoyi() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_jiaoyi);
    }

    public String getShanfen() {
        return mResUtil.getPositionFindContent(position,R.array.guanyin_shanfen);
    }


    private final ResUtil mResUtil;
    int position;

    public GuanYinBean(String name) {
        mResUtil = ResUtil.newInstance();
        position = mResUtil.getNamePositionByStringArray(R.array.guanyin_dijiqian, name);
    }

    public GuanYinBean() {
        mResUtil = ResUtil.newInstance();
        position = 0;
    }

    public GuanYinBean(int position) {
        if (position >= 100) {
            position = 99;
        }
        mResUtil = ResUtil.newInstance();
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String[] getQianCiArray() {
        String qianCi = getQianci();
        String[] split = qianCi.split("，");
        return split;
    }


    public int getPosition() {
        return position;
    }
}
