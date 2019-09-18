package com.tongcheng.soothsay.ui.activity.classroom.goodsList;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.classroom.GoodsListBean;

import java.util.List;

/**
 * Created by Steven on 17/1/3.
 */

public class GoodsListContract {

    public interface View extends BaseUiView<GoodsListContract.Presenter>{
        void showGoodsList(List<GoodsListBean.StoresBean> beans);
        void refreshFinish(List<GoodsListBean.StoresBean> beans);
        void loadMoreFinish(List<GoodsListBean.StoresBean> beans);
    }

    public interface Presenter extends BasePresenter{
        void getGoodsList(String type, boolean isRefresh);
        void loadMore(String type);
    }
}
