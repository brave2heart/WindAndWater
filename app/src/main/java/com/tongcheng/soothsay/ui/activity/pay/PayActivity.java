package com.tongcheng.soothsay.ui.activity.pay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.base.PayBean;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 支付页面
 */
public class PayActivity extends BaseTitleActivity implements PayConstant.View {

    private final int ALI = 0x01;
    private final int WX = 0x02;


    @BindView(R.id.tv_pay_title)    TextView        tvTitle;
    @BindView(R.id.tv_pay_money)    TextView        tvMoney;
    @BindView(R.id.rb_pay_alipay)   RadioButton     rbAlipay;
    @BindView(R.id.rl_pay_alipay)   RelativeLayout  rlAlipay;
    @BindView(R.id.rb_pay_wx)       RadioButton     rbWx;
    @BindView(R.id.rl_pay_wx)       RelativeLayout  rlWx;
    @BindView(R.id.btn_pay_now)     Button          btnPay;

    private String payType = "ALIPAY";  //WX, ALIPAY

    private OrdersRequestBean requestBean;

    private PayConstant.Presenter mPresenter;

    ProgressDialog mDialog;

    private String goodsname, goodsprice, orderNo, token;

    private OrderRePayBean rePayBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initView();
        initData();
    }


    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_pay));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PayBean(false));
                finish();
            }
        });

        rePayBean = (OrderRePayBean) getIntent().getSerializableExtra(Constant.REPAY);
        if (rePayBean instanceof OrderRePayBean) {
            goodsname = rePayBean.getGoodsname();
            goodsprice = rePayBean.getGoodsprice();
            orderNo = rePayBean.getOrderNo();
            token = rePayBean.getToken();
            Log.e(TAG, "订单号: " + this.orderNo);
            rePayBean.setToken(token);
            rePayBean.setOrderNo(this.orderNo);
            rePayBean.setPayType(payType);
            tvTitle.setText("商品名称: " + goodsname);
            tvMoney.setText("商品总金额: " + this.goodsprice + " 元");

        } else {

            requestBean = (OrdersRequestBean) getIntent().getSerializableExtra(Constant.INTENT_DATA);
            requestBean.setPayType(payType);
            double money = Double.valueOf(requestBean.getTotalPrice());
            //保留0个小数，往上升，206.99999997->207
            BigDecimal mData = new BigDecimal(String.valueOf(money)).setScale(0, BigDecimal.ROUND_HALF_UP);
            int moneyi = mData.intValue();
//            int moneyi = (int) money;
            requestBean.setTotalPrice(moneyi + "");
            tvTitle.setText("商品名称: " + requestBean.getGoodsName());
            tvMoney.setText("商品总金额: " + Double.valueOf(requestBean.getTotalPrice()) / 100 + " 元");
        }

        EventBusUtil.register(this);
    }

    @OnClick({R.id.rl_pay_alipay,
            R.id.rl_pay_wx,
            R.id.rb_pay_wx,
            R.id.rb_pay_alipay,
            R.id.btn_pay_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_pay_alipay:
            case R.id.rl_pay_alipay:
                if (rePayBean instanceof OrderRePayBean) {
                    payType = "ALIPAY";
                    rePayBean.setPayType(payType);
                    changeBtnState(ALI);
                } else {
                    payType = "ALIPAY";
                    requestBean.setPayType(payType);
                    changeBtnState(ALI);
                }
                break;

            case R.id.rb_pay_wx:
            case R.id.rl_pay_wx:
                if (rePayBean instanceof OrderRePayBean) {
                    payType = "WX";
                    rePayBean.setPayType(payType);
                    changeBtnState(WX);
                } else {
                    payType = "WX";
                    requestBean.setPayType(payType);
                    changeBtnState(WX);
                }
                break;

            case R.id.btn_pay_now:
                if (ClickUtil.isFastClick()) return;
                if (rePayBean instanceof OrderRePayBean) {
                    mPresenter.getRePayOrder(this, payType, rePayBean);
                    mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mDialog.setMessage("正在跳转支付界面，请稍等..");
                    mDialog.show();
                } else {
                    mPresenter.getOrders(this, payType, requestBean);
                    mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mDialog.setMessage("正在跳转支付界面，请稍等..");
                    mDialog.show();
                }

                break;

        }
    }


    @Subscribe
    public void onPayResult(EventPayResultBean bean) {
        final int code = bean.status;
        switch (code) {
            case PayConstant.ERR_CODE_PAY_FINISH:
                mPresenter.isPaySuccessful(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.showShort(PayActivity.this, "支付成功");
                        Intent intent = new Intent();
                        intent.putExtra(Constant.INTENT_DATA, code);
                        PayActivity.this.setResult(PayConstant.PAY_RESULT, intent);
                        EventBus.getDefault().post(new PayBean(true));
                        PayActivity.this.finish();
                        mDialog.hide();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        ToastUtil.showShort(PayActivity.this, "支付失败");
                        EventBus.getDefault().post(new PayBean(false));
                        PayActivity.this.finish();
                        mDialog.hide();
                    }
                }));
                break;

            case PayConstant.ERR_CODE_PAY_FAILURE:
                ToastUtil.showShort(this, getString(R.string.pay_result_failure));
                new CusDialogUtil(this).showDefaultDialog("支付失败", "请再次选择支付方式并重新确认支付", false);
                EventBus.getDefault().post(new PayBean(false));
                mDialog.hide();
                break;

            case PayConstant.ERR_CODE_PAY_CANCEL:
                ToastUtil.showShort(this,"取消支付");
//                new CusDialogUtil(this).showDefaultDialog("支付失败", "请再次选择支付方式并重新确认支付", false);
                EventBus.getDefault().post(new PayBean(false));
                mDialog.hide();
                break;

        }
    }


    @Override
    public void initData() {
        new PayPresenter(this);
        mDialog = new ProgressDialog(this);
    }

    @Override
    public void showTokenOverdue() {
        ToastUtil.showShort(this,getString(R.string.token_overdue));
        GotoUtil.GoToActivity(this, LoginActivity.class);
    }

    @Override
    public void showPayCallBackFinish() {
        ToastUtil.showShort(this, "支付成功，查询服务器订单状态");
    }

    @Override
    public void showPayFailure() {
        ToastUtil.showShort(this, "支付失败");
    }

    @Override
    public void showPayCallBackCancel() {
        ToastUtil.showShort(this, "取消支付");
    }


    @Override
    public void showNetError() {
        ToastUtil.showShort(this, getString(R.string.showNeterr));
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this, info);
    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void showTitle(String title) {
    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading(true);
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {
    }

    @Override
    public void setPresenter(PayConstant.Presenter presenter) {
        mPresenter = presenter;
    }

    private void changeBtnState(int tag) {
        rbAlipay.setChecked(tag == ALI);
        rbWx.setChecked(tag == WX);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }


}
