package com.tongcheng.soothsay.bean.calculation;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.ResUtil;

import java.io.Serializable;

/**
 * Created by Gubr on 2016/12/18.
 */

public class MaZuBean  {

    private final ResUtil mResUtil;
    int position;

    public MaZuBean(String name){
        mResUtil=ResUtil.newInstance();
        position=mResUtil.getNamePositionByStringArray(R.array.mazu_qian,name);
    }

    public MaZuBean(){
        mResUtil=ResUtil.newInstance();
        position=0;
    }

    public MaZuBean(int position){
        if (position>=60){
            position=59;
        }
        mResUtil=ResUtil.newInstance();
        this.position=position;
    }

    public void setPosition(int position){
        this.position=position;
    }


    public  String getdiJiQian(){
        return mResUtil.getPositionFindContent(position, R.array.mazu_qian);
    }

    public String getsubTitle(){
        return mResUtil.getPositionFindContent(position,R.array.mazu_subTitle);
    }


    public String getTitle(){
        return mResUtil.getPositionFindContent(position,R.array.mazu_title);
    }

    public String getQianCi(){
        return mResUtil.getPositionFindContent(position,R.array.mazu_qianCi);
    }

    public String[] getQianCiArray(){
        String qianCi = getQianCi();
        String[] split = qianCi.split("、");
        return split;
    }


    public String getJieXi(){
        return mResUtil.getPositionFindContent(position,R.array.mazu_jieXi);
    }

    public int getPosition() {
        return position;
    }
}
