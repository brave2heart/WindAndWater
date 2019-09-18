package com.tongcheng.soothsay.ui.activity.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.data.pay.Ipay;
import com.tongcheng.soothsay.data.pay.PayImp;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steven on 16/12/4.
 */
public class PayPresenter implements PayConstant.Presenter {


    private final int ALIPAY_STATE_SUCCESS = 9000;          //成功
    private final int ALIPAY_STATE_FAILURE = 4000;          //订单支付失败
    private final int ALIPAY_STATE_CANCEL = 6001;           //用户中途取消
    private final int ALIPAY_STATE_NETWORK_ERROR = 6002;    //网络连接出错
    private final int ALIPAY_STATE_PAYING = 8000;           //正在处理中，支付结果未知
    private final int ALIPAY_STATE_ORDER = 0000;            //其他支付错误


    private PayConstant.View mView;
    private Ipay iData;


    OrdersResultBean mResultBean;

    private IWXAPI api;

    //支付宝回调通知
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Map<String, String> map = (Map<String, String>) msg.obj;
            int resultCode = 0;
            try {
                resultCode = Integer.valueOf(map.get("resultStatus"));
            } catch (Exception e) {
                resultCode = ALIPAY_STATE_ORDER;
            }

            switch (resultCode) {
                //支付成功
                case ALIPAY_STATE_SUCCESS:
                    EventBusUtil.post(new EventPayResultBean(PayConstant.ERR_CODE_PAY_FINISH));
                    break;

                //订单支付失败
                case ALIPAY_STATE_FAILURE:
                    EventBusUtil.post(new EventPayResultBean(PayConstant.ERR_CODE_PAY_FAILURE));
                    break;

                //用户中途取消
                case ALIPAY_STATE_CANCEL:
                    EventBusUtil.post(new EventPayResultBean(PayConstant.ERR_CODE_PAY_CANCEL));
                    break;

                //网络连接出错
                case ALIPAY_STATE_NETWORK_ERROR:
                    mView.showNetError();
                    break;

                //其他支付错误
                case ALIPAY_STATE_ORDER:
                    EventBusUtil.post(new EventPayResultBean(PayConstant.ERR_CODE_PAY_FAILURE));
                    break;
            }
        }
    };

    public PayPresenter(PayConstant.View view) {
        mView = view;
        iData = new PayImp();
        mView.setPresenter(this);

    }


    @Override
    public void start() {
    }

    //获取订单商城
    @Override
    public void getOrders(final Activity activity, final String payType, OrdersRequestBean requestBean) {
        mView.showLoad();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", requestBean.getToken());
        map.put("transType", requestBean.getTransType());
        map.put("payType", requestBean.getPayType());
        map.put("totalPrice", requestBean.getTotalPrice());
        map.put("extra", requestBean.getExtra());

        iData.getOrders(map, new ApiCallBack<ApiResponseBean<OrdersResultBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mView.showLoadFinish();
                 mResultBean = (OrdersResultBean) data;

                if (mResultBean == null) {
                    mView.showPayFailure();
                }

                if (PayConstant.PAY_TYPE_ALIPAY.equals(payType)) {
                    aliPay(activity, mResultBean);

                } else if (PayConstant.PAY_TYPE_WX.equals(payType)) {
                    wxPay(activity, mResultBean);
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(mView,errorCode,message);
            }
        }));
    }

    @Override
    public void getRePayOrder(final Activity activity, final String payType, OrderRePayBean rePayBean) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", UserManager.getInstance().getToken());
        map.put("orderNo", rePayBean.getOrderNo());
        map.put("payType", payType);
        Log.e("repay", "getRePayOrder: " + rePayBean.getOrderNo());
        mResultBean=null;
        iData.getRePayOrder(map, new ApiCallBack<ApiResponseBean<OrdersResultBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mView.showLoadFinish();
                mResultBean = (OrdersResultBean) data;

                if (mResultBean == null) {
                    mView.showPayFailure();
                }

                if (PayConstant.PAY_TYPE_ALIPAY.equals(payType)) {
                    aliPay(activity, mResultBean);

                } else if (PayConstant.PAY_TYPE_WX.equals(payType)) {
                    wxPay(activity, mResultBean);
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(mView,errorCode,message);
            }
        }));
    }

    //去支付宝支付
    @Override
    public void aliPay(final Activity activity, OrdersResultBean resultBean) {
        final StringBuffer sb = new StringBuffer();
        sb.append("app_id=").append(resultBean.getApp_id())
                .append("&method=").append(resultBean.getMethod())
                .append("&charset=").append(resultBean.getCharset())
                .append("&sign_type=").append(resultBean.getSign_type())
                .append("&sign=").append(resultBean.getSign())
                .append("&timestamp=").append(resultBean.getTimestamp())
                .append("&version=").append(resultBean.getVersion())
                .append("&notify_url=").append(resultBean.getNotify_url())
                .append("&biz_content=").append(resultBean.getBiz_content());

        new Thread() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(activity);
                Map<String, String> map = payTask.payV2(sb.toString(), true);

                Message msg = Message.obtain();
                msg.obj = map;
                mHandle.sendMessage(msg);
            }
        }.start();
    }


    //去微信支付
    @Override
    public void wxPay(Activity activity, OrdersResultBean resultBean) {
        IWXAPI api = WXAPIFactory.createWXAPI(activity, resultBean.getAppid());
        PayReq request = new PayReq();
        request.appId = resultBean.getAppid();
        request.partnerId = resultBean.getPartnerid();
        request.prepayId = resultBean.getPrepayid();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = resultBean.getNoncestr();
        request.timeStamp = resultBean.getTimestamp();
        request.sign = resultBean.getSign();
        api.sendReq(request);
    }

    //验证订单是否成功
    @Override
    public void isPaySuccessful( ApiCallBack<ApiResponseBean<Void>> beanApiCallBack) {
        iData.validateOrder(mResultBean.getOut_trade_no(), beanApiCallBack);
    }

}
