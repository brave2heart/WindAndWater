package com.tongcheng.soothsay.data.calculate.divination;

import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by bozhihuatong on 2016/12/7.
 * 周公解梦的数据接口
 */

public interface IOneiromancySource {

    /**
     * 查梦
     * @param query
     */
    void getOneiromancyList(String query,OnGetQueryBean o);


    void getOneiromancyList(String query, Callback<ZGJMDetailBean> callBack);

    void setResult(List<ZGJMDetailBean.ResultBean> result);

    /**
     * 获取对应的详情数据
     * @param positiong
     */
    ZGJMDetailBean.ResultBean getDetails(int positiong);

    public interface OnGetQueryBean{
        void getQueryBean( List<ZGJMDetailBean.ResultBean> bean);
    }
}
