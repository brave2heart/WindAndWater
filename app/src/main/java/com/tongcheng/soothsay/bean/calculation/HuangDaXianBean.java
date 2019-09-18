package com.tongcheng.soothsay.bean.calculation;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.ResUtil;

/**
 * Created by Gubr on 2016/12/18.
 */

public class HuangDaXianBean {


    public String getdijiqian(){return mResUtil.getPositionFindContent(position, R.array.guanyin_dijiqian);}

    public String getyunshi(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_yunshi);}

    public String gettitle(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_title);}

    public String getqianci(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_qianci);}

    public String getqianjie(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_qianjie);}


    public String getliunian(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_liunian);}

    public String getshiye(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_shiye);}

    public String getcaifu(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_caifu);}

    public String getzishen(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_zishen);}

    public String getjiating(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_jiating);}

    public String gethunyin(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_hunyin);}

    public String getyiju(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_yiju);}

    public String getmingyu(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_mingyu);}

    public String getjiankang(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_jiankang);}

    public String getyouyi(){return mResUtil.getPositionFindContent(position, R.array.huangdaxian_youyi);}



    private final ResUtil mResUtil;
    int position;

    public HuangDaXianBean(String name){
        mResUtil=ResUtil.newInstance();
        position=mResUtil.getNamePositionByStringArray(R.array.mazu_qian,name);
    }

    public HuangDaXianBean(){
        mResUtil=ResUtil.newInstance();
        position=0;
    }

    public HuangDaXianBean(int position){
        if (position>=100){
            position=99;
        }
        mResUtil=ResUtil.newInstance();
        this.position=position;
    }

    public void setPosition(int position){
        this.position=position;
    }




    public String[] getQianCiArray(){
        String qianCi = getqianci();
        String[] split = qianCi.split("、");
        return split;
    }




    public int getPosition() {
        return position;
    }
}
