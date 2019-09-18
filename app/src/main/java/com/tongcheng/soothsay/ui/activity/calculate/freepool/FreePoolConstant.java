package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.bean.calculation.FishBean;

import java.util.List;

/**
 * Created by Steven on 16/11/29.
 */

public class FreePoolConstant {

    public interface View extends BaseUiView<Presenter>{
        void showFishList(List<FishBean> beans);
    }


    public interface Presenter extends BasePresenter{
        void getFishList();
    }

}
