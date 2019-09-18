package com.tongcheng.soothsay.ui.activity.classroom.goodsList;

import com.tongcheng.soothsay.bean.classroom.GoodsListBean;
import com.tongcheng.soothsay.data.classroom.GoodsImp;
import com.tongcheng.soothsay.data.classroom.IGoods;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;

import java.util.List;

public class GoodsListPresenter implements GoodsListContract.Presenter{

    private GoodsListContract.View mView;

    private IGoods mData;

    private int start = 0;

    public GoodsListPresenter(GoodsListContract.View mView) {
        this.mView = mView;
        mData = new GoodsImp();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoodsList(String type, final boolean isRefresh) {
        if(isRefresh)
            mView.showLoad();
        start = 0;
        mData.getGoodsList(start+"",type,new ApiCallBack<ApiResponseBean<GoodsListBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mView.showLoadFinish();

                GoodsListBean bean = (GoodsListBean) data;
                if(bean == null){
                    mView.showEmpty();
                    return;
                }
                List<GoodsListBean.StoresBean> beans = bean.getStores();
                if(beans.size() == 0){
                    mView.showEmpty();
                    return;
                }

                if(isRefresh){
                   mView.refreshFinish(beans);
                }else{
                    mView.showGoodsList(beans);
                }

            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(mView,errorCode,message);
            }
        }));
    }

    @Override
    public void loadMore(String type) {
        start += 15;
        mData.getGoodsList(start + "",type,new ApiCallBack<ApiResponseBean<GoodsListBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mView.showLoadFinish();

                GoodsListBean bean = (GoodsListBean) data;
                if(bean == null){
                    mView.showEmpty();
                    start -= 15;
                    return;
                }
                List<GoodsListBean.StoresBean> beans = bean.getStores();
                if(beans.size() == 0){
                    mView.showEmpty();
                    start -= 15;
                    return;
                }

                mView.loadMoreFinish(beans);

            }

            @Override
            public void onError(String errorCode, String message) {
                start -= 15;
                ErrorCodeUtil.handleErr(mView,errorCode,message);
            }
        }));

    }
}
