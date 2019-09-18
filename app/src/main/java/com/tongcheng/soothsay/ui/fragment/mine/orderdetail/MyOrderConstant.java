package com.tongcheng.soothsay.ui.fragment.mine.orderdetail;

import android.app.Activity;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.mine.OrderDetail;
import com.tongcheng.soothsay.ui.activity.mine.OrderDetailActivity;

/**
 * Created by Administrator on 2016/12/30.
 */

public class MyOrderConstant {


    public interface View extends BaseUserView<MyOrderConstant.Presenter> {
        void useJifen(double money);
        void calculateMoney(boolean canCheCked,int canUseJifen,double offsetMoney);
    }

    public interface Presenter extends BasePresenter {
        void calculateMoney(int jifen, int canUseJifen,double totalPrice);
        void useJifen(boolean checked);
        void changeJifen(Activity activity);
    }
}
