package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import com.tongcheng.soothsay.data.calculate.freepool.IPool;
import com.tongcheng.soothsay.data.calculate.freepool.PoolImp;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.bean.calculation.FishBean;

import java.util.List;

/**
 * Created by Steven on 16/11/29.
 */

public class FreePoolPresenter implements FreePoolConstant.Presenter{

    private FreePoolConstant.View mView;
    private IPool iData;

    public FreePoolPresenter(FreePoolConstant.View view){
        this.mView = view;
        this.iData = new PoolImp();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    //获取鱼列表
    @Override
    public void getFishList() {
        mView.showLoad();
        String token = UserManager.getInstance().getToken();
        iData.getFishList(token,new ApiCallBack<ApiResponseBean<List<FishBean>>>(new BaseCallBack() {

            @Override
            public void onSuccess(Object data) {
                mView.showLoadFinish();
                List<FishBean> beans = (List<FishBean>) data;
                if(beans != null){
                    if(beans.size() != 0){
                        mView.showFishList(beans);
                    }else{
                        mView.showEmpty();
                    }
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
