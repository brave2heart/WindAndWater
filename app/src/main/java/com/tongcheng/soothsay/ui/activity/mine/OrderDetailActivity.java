package com.tongcheng.soothsay.ui.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.bean.mine.OrderDetail;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.ui.fragment.mine.orderdetail.MyOrderConstant;
import com.tongcheng.soothsay.ui.fragment.mine.orderdetail.MyOrderDetailPresenter;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/12/28 0028.
 */

public class OrderDetailActivity extends BaseTitleActivity implements MyOrderConstant.View {
    @BindView(R.id.address_icon)
    ImageView addressIcon;
    @BindView(R.id.tv_select_address)
    TextView tvSelectAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_is_default)
    TextView tvIsDefault;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.address_go)
    ImageView addressGo;
    @BindView(R.id.rl_address_btn)
    RelativeLayout rlAddressBtn;
    @BindView(R.id.ll_goods_list)
    LinearLayout llGoodsList;
    @BindView(R.id.text_1)
    TextView text1;
    @BindView(R.id.text_3)
    TextView text3;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.text_2)
    TextView text2;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_jifen_price)
    TextView tvJifenPrice;
    @BindView(R.id.cbox_goods)
    CheckBox boxJifen;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.activity_goods_confirm)
    LinearLayout activityGoodsConfirm;
    @BindView(R.id.tv_orderNO)
    TextView tvOrderNo;
    @BindView(R.id.rl_orderno)
    RelativeLayout rlOrderno;
    @BindView(R.id.tv_topstatus)
    TextView tvTopstatus;
    private String token, orderNo;
    private HashMap<String, String> map;
    private OrderDetail bean;
    private String storeName;
    private String price;

    private MyOrderConstant.Presenter mPresenter;
    private AddressBean addressBean;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_goods_confirm);
        initData();
        initListener();
    }

    @Override
    public void initData() {
        super.initData();
        status = getIntent().getStringExtra("status");
        token = getIntent().getStringExtra("token");
        orderNo = getIntent().getStringExtra("orderNo");
        storeName = getIntent().getStringExtra("storeName");
        price = getIntent().getStringExtra("price");
        Log.e(TAG, "initData: " + token + "..." + orderNo);
        addressGo.setVisibility(View.GONE);
        //已购买就不可点击
        if (status.equals("1")) {
            tvTopstatus.setText("已付款");
            btnPay.setText("已付款");
            btnPay.setEnabled(false);
            btnPay.setBackgroundColor(Color.parseColor(String.valueOf("#dddddd")));
        } else {
            tvTopstatus.setText("付款");
            btnPay.setText("立即购买");
            btnPay.setEnabled(true);
        }
        getOrderDetail();
        new MyOrderDetailPresenter(this);

    }

    @OnClick({R.id.cbox_goods, R.id.btn_pay})
    public void onClick(View v) {

        switch (v.getId()) {
            //使用积分抵扣
            case R.id.cbox_goods:
                mPresenter.useJifen(boxJifen.isChecked());
                break;

            //立即付款
            case R.id.btn_pay:
                OrderRePayBean rePayBean = new OrderRePayBean();
                rePayBean.setGoodsname(storeName);
                rePayBean.setGoodsprice(price);
                rePayBean.setToken(token);
                rePayBean.setOrderNo(orderNo);
                Intent intent = new Intent(this, PayActivity.class);
                intent.putExtra(Constant.REPAY, rePayBean);
                this.startActivityForResult(intent, 4);
//
//
//                mPresenter.payForNow(this, addressBean,bean);
                break;
        }
    }


    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_order_detail));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getOrderDetail() {
        map = new HashMap<>();
        map.put("token", token);
        map.put("orderNo", orderNo);
        LogUtil.i("token: " + token + "orderNo :" + orderNo);
        AllApi.getInstance().getOrderDetail(map).enqueue(new ApiCallBack<ApiResponseBean<OrderDetail>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                bean = ((OrderDetail) data);
                showDatas(bean);
            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.showHaveTokenError(OrderDetailActivity.this, errorCode);
                if (b == false){
                    ToastUtil.showShort(OrderDetailActivity.this,message);
                }
            }
        }));

    }

    private void showDatas(OrderDetail bean) {

        if (llGoodsList.getChildCount() > 0)
            llGoodsList.removeAllViews();

        int canUseJifen = 0;    //所有商品可用的积分
        double totalPrice = 0;  //商品总价
        if (bean != null) {
            //        订单号
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(bean.getName());
            String province = bean.getProvince();
            String city = bean.getCity();
            String area = bean.getArea();
            String address = bean.getAddress();
            rlOrderno.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.VISIBLE);
            tvAddress.setText(province + city + area + address);
            tvPhone.setVisibility(View.VISIBLE);
            tvPhone.setText(bean.getTel());

            addressBean = new AddressBean();
            addressBean.setName(bean.getName());
            addressBean.setPhone(bean.getTel());
            addressBean.setProvince(province);
            addressBean.setCity(city);
            addressBean.setArea(area);
            addressBean.setAddress(address);
//        列表
            View view = View.inflate(this, R.layout.item_list_goods, null);
            ImageView img = (ImageView) view.findViewById(R.id.img_goods);
            TextView tvName = (TextView) view.findViewById(R.id.tv_goods_name);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_goods_title);
            TextView tvMoney = (TextView) view.findViewById(R.id.tv_goods_money);
            TextView tvCount = (TextView) view.findViewById(R.id.tv_goods_count);
//            订单信息
            tvName.setText(storeName);
            tvTitle.setVisibility(View.GONE);
            tvOrderNo.setText("订单号: " + orderNo);
            tvMoney.setText(price);
            tvCount.setText("x" + bean.getOrderList().size());

            llGoodsList.addView(view);

            int jifen = Integer.valueOf(bean.getJf());
            canUseJifen += jifen;

            totalPrice += Double.valueOf(bean.getOrderMoney());

        }

        int jifen = Integer.valueOf(UserManager.getInstance().getUserJf(this));     //用户自己的积分

        tvGoodsPrice.setText("¥" + totalPrice);
        tvOrderPrice.setText("实付款: ¥ " + totalPrice);

        mPresenter.calculateMoney(jifen, canUseJifen, totalPrice);
    }

    @Override
    public void useJifen(double money) {
        tvOrderPrice.setText("实付款: ¥ " + String.format("%.2f", money));
    }

    @Override
    public void calculateMoney(boolean canCheCked, int canUseJifen, double offsetMoney) {
        boxJifen.setEnabled(canCheCked);
        tvJifen.setText(canUseJifen + "");
        tvJifenPrice.setText("¥ " + String.format("%.2f", offsetMoney));
    }

    @Override
    public void showTokenOverdue() {
        ToastUtil.showShort(this, getString(R.string.token_overdue));
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 101);
    }

    @Override
    public void showNetError() {
        getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showReLoad();
            }
        });
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
        getBaseLoadingView().showLoading();
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();

    }

    @Override
    public void showReLoad() {
        getBaseEmptyView().hideEmptyView();
        getBaseLoadingView().showLoading();
    }

    @Override
    public void setPresenter(MyOrderConstant.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 支付回调
     *
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPayResult(EventPayResultBean bean) {
        int code = bean.status;
        switch (code) {
            case PayConstant.ERR_CODE_PAY_FINISH:
                mPresenter.changeJifen(this);
                Intent intent = new Intent(this, MineOrderActivity.class);
                intent.putExtra(Constant.INTENT_DATA, 1);
                startActivity(intent);
                finish();
                break;

            case PayConstant.ERR_CODE_PAY_FAILURE:
            case PayConstant.ERR_CODE_PAY_CANCEL:
                Intent intent1 = new Intent(this, MineOrderActivity.class);
                intent1.putExtra(Constant.INTENT_DATA, 0);
                startActivity(intent1);
                finish();
                break;

        }
        finish();
    }
}
