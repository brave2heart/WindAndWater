package com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare;

import android.view.View;

import com.tongcheng.soothsay.bean.calculation.WishingSquare;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.calculate.life.IWishing;
import com.tongcheng.soothsay.data.calculate.life.WishingImpl;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.ui.activity.calculate.life.MyWishingActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ALing on 2016/12/8 0008.
 */

public class WishingSquarePresenter implements WishingSquareConstant.Presenter {
    private WishingSquareConstant.View mView;
    private IWishing iData;

    public WishingSquarePresenter(WishingSquareConstant.View mView) {
        this.mView = mView;
        iData = new WishingImpl();
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


      @Override
    public void getWishingSquare(HashMap<String, String> map) {
          mView.showLoad();
          iData.getWishing(map,new ApiCallBack<ApiResponseBean<List<WishingSquare>>>(new BaseCallBack() {

              @Override
              public void onSuccess(Object data) {
                  mView.showLoadFinish();
                  List<WishingSquare> beans = (List<WishingSquare>)  data;
                  if(beans != null){
                      if(beans.size() != 0){
                          mView.showWishingSquare(beans);
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
                  }else if (errorCode.equals(Constant.ErrorCode.SERVER_ERROR_99999)){
                      mView.showError(message);
                  }else if ((Constant.ErrorCode.SERVER_BUSY).equals(errorCode)){
                      mView.showError(message);
                  }else{
                      mView.showError(message);
                  }
              }
          }));
    }
}
