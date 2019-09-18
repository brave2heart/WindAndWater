package com.tongcheng.soothsay.data.calculate.divination;

import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.service.OneiromancyApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bozhihuatong on 2016/12/7.
 * 周公解梦数据
 */

public class OneiromancySourceImpl implements IOneiromancySource {
    private List<ZGJMDetailBean.ResultBean> mResult;

    @Override
    public void getOneiromancyList(String query, final OnGetQueryBean o) {
        OneiromancyApi.getInstance().getZGQuery(Constant.BASE_ONEIROMANCY_KEY,query,null,"1").enqueue(new Callback<ZGJMDetailBean>() {
            @Override
            public void onResponse(Call<ZGJMDetailBean> call, Response<ZGJMDetailBean> response) {

                mResult = response.body().getResult();
                o.getQueryBean(mResult);
            }
            @Override
            public void onFailure(Call<ZGJMDetailBean> call, Throwable t) {
            }
        });
    }

    @Override
    public void getOneiromancyList(String query, Callback<ZGJMDetailBean> callBack){
        OneiromancyApi.getInstance().getZGQuery(Constant.BASE_ONEIROMANCY_KEY,query,null,"1").enqueue(callBack);
    }


    @Override
    public void setResult(List<ZGJMDetailBean.ResultBean> result) {
        mResult = result;
    }

    /**
     * 获取titile对应的的数据
     * @param position
     */
    @Override
    public ZGJMDetailBean.ResultBean getDetails(int position) {
        if (mResult != null&&mResult.size()>position) {
            return mResult.get(position);
        }
        return null;
    }






}
