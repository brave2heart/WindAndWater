package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.calculation.FreeGoodsBean;

import java.util.List;

/**
 * Created by Steven on 16/12/1.
 */
public class FreeMarketConstant {

    public interface View extends BaseUiView<FreeMarketConstant.Presenter>{
        void showGoodsList(List<FreeGoodsBean> beans);
    }

    public interface Presenter extends BasePresenter{
        void getGoodsList();
    }

}
