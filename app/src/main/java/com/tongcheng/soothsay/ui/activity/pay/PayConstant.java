package com.tongcheng.soothsay.ui.activity.pay;

import android.app.Activity;

import com.tongcheng.soothsay.BasePresenter;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;

import java.util.HashMap;

/**
 * Created by Steven on 16/12/4.
 */

public class PayConstant {

    public static final String PAY_TYPE_WX = "WX";
    public static final String PAY_TYPE_ALIPAY = "ALIPAY";

    public static final int PAY_RESULT = 168;

    public static final int ERR_CODE_PAY_FINISH = 0;          //支付完成
    public static final int ERR_CODE_PAY_FAILURE = -1;        //支付失败
    public static final int ERR_CODE_PAY_CANCEL = -2;         //取消支付

    public interface View extends BaseUserView<Presenter> {
        void showPayCallBackFinish();
        void showPayFailure();
        void showPayCallBackCancel();

    }

    public interface Presenter extends BasePresenter{
        void getOrders(Activity activity,String payType,OrdersRequestBean requestBean);
        void getRePayOrder(Activity activity,String payType,OrderRePayBean rePayBean);
        void aliPay(Activity activity, OrdersResultBean resultBean);
        void wxPay(Activity activity,OrdersResultBean resultBean);


        //验证订单是否成功
        void isPaySuccessful(ApiCallBack<ApiResponseBean<Void>> beanApiCallBack);
    }
}
