package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import com.tongcheng.soothsay.bean.calculation.FreeGoodsBean;
import com.tongcheng.soothsay.data.calculate.freepool.IMarket;
import com.tongcheng.soothsay.data.calculate.freepool.MarketImp;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;

import java.util.List;

/**
 * Created by Steven on 16/12/1.
 */

public class FreeMarketPresenter implements FreeMarketConstant.Presenter{

    private FreeMarketConstant.View mView;
    private IMarket iData;

    public FreeMarketPresenter(FreeMarketConstant.View mView) {
        this.mView = mView;
        iData = new MarketImp();
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoodsList() {
        mView.showLoad();
        iData.getGoodsList(new ApiCallBack<ApiResponseBean<List<FreeGoodsBean>>>(new BaseCallBack(){
            @Override
            public void onSuccess(Object data) {
                List<FreeGoodsBean> datas = (List<FreeGoodsBean>) data;
                mView.showLoadFinish();
                if(datas.size() != 0){
                    mView.showGoodsList(datas);
                }else{
                    mView.showEmpty();
                }

            }

            @Override
            public void onError(String errorCode, String message) {
                mView.showLoadFinish();
                if(errorCode == ApiCallBack.NET_ERROR){
                    mView.showNetError();
                }else{
                    mView.showError(message);
                }
            }
        }));
    }
}
