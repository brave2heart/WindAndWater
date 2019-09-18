package com.tongcheng.soothsay.ui.activity.classroom.goodsConfirm;

import android.app.Activity;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.classroom.ShopCartBean;

import java.util.List;

/**
 * Created by Steven on 16/12/26.
 */
public class GoodsConfirmConstant {

    public static String INTENT_ISDEFULT = "isDefult";

    public interface View extends BaseUserView<GoodsConfirmConstant.Presenter> {
        void useJifen(double money);
        void showAddress(List<AddressBean> beans);
        void showGoodsList();
        void calculateMoney(boolean canCheCked,int canUseJifen,double offsetMoney);
    }

    public interface Presenter extends BasePresenter{
        void getAddressList(String token);
        void calculateMoney(int jifen, int canUseJifen,double totalPrice);
        void gotoAddress(boolean isDefult,List<AddressBean> beans, Activity activity);
        void useJifen(boolean checked);
        void payForNow(Activity activity,AddressBean addr, List<ShopCartBean> goods);
        void changeJifen(Activity activity);
    }
}
