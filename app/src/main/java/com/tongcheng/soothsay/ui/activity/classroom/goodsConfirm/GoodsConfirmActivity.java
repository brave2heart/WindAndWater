package com.tongcheng.soothsay.ui.activity.classroom.goodsConfirm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.tongcheng.soothsay.bean.classroom.ShopCartBean;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.bean.event.EventUpdataAddrBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.activity.mine.MineOrderActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品确认
 */
public class GoodsConfirmActivity extends BaseTitleActivity implements View.OnClickListener, GoodsConfirmConstant.View {

    @BindView(R.id.tv_select_address)       TextView tvSelectAdd;
    @BindView(R.id.tv_name)                 TextView tvName;
    @BindView(R.id.tv_phone)                TextView tvPhone;
    @BindView(R.id.tv_is_default)           TextView tvIsDefault;
    @BindView(R.id.tv_address)              TextView tvAddress;
    @BindView(R.id.tv_jifen)                TextView tvJifen;            //用户自己的积分
    @BindView(R.id.tv_goods_price)          TextView tvGoodsPrice;
    @BindView(R.id.tv_jifen_price)          TextView tvJifenPrice;       //积分可以抵扣的价格
    @BindView(R.id.tv_order_price)          TextView tvOrderPrice;
    @BindView(R.id.btn_pay)                 Button btnPay;
    @BindView(R.id.cbox_goods)              CheckBox boxJifen;
    @BindView(R.id.rl_address_btn)          RelativeLayout btnAddress;
    @BindView(R.id.ll_goods_list)           LinearLayout llGoodsList;
    @BindView(R.id.activity_goods_confirm)  LinearLayout layoutContent;

    private boolean isDefult = false;

    private List<AddressBean> beans;
    private List<ShopCartBean> goods;
    private AddressBean currAddress;

    private GoodsConfirmConstant.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_confirm);

        EventBusUtil.register(this);

        initView();
        initListener();
        initData();
    }


    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_goods_confirm));
        getBaseHeadView().showBackButton(this);

    }


    @OnClick({R.id.rl_address_btn, R.id.cbox_goods, R.id.btn_pay})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.headBackButton:
                if (ClickUtil.isFastClick())
                    return;
                finish();
                break;

            //选择收货地址
            case R.id.rl_address_btn:
                if (ClickUtil.isFastClick())
                    return;
                mPresenter.gotoAddress(isDefult, beans, this);
                break;

            //使用积分抵扣
            case R.id.cbox_goods:
                mPresenter.useJifen(boxJifen.isChecked());
                break;

            //立即付款
            case R.id.btn_pay:
                mPresenter.payForNow(this, currAddress, goods);
                break;
        }
    }

    @Override
    public void useJifen(double money) {
        tvOrderPrice.setText("实付款: ¥ " + String.format("%.2f", money));
    }

    @Override
    public void initData() {
        new GoodsConfirmPresenter(this);
        goods = (List<ShopCartBean>) getIntent().getSerializableExtra(Constant.INTENT_DATA);
        getAddressList();

    }

    private void getAddressList() {
        if (UserManager.getInstance().isLogin(this)) {
            String token = UserManager.getInstance().getToken();
            mPresenter.getAddressList(token);
        }
    }

    private void showBean(AddressBean bean){
        String name = bean.getName();
        String phone = bean.getPhone();
        String address = bean.getProvince() + bean.getCity();
        String area = bean.getArea();
        if (!TextUtils.isEmpty(area)) {
            address += area;
        }
        address += bean.getAddress();

        //是默认地址
        String isDefult = bean.getIsdefault();
        if ("1".equals(isDefult)) {
            tvIsDefault.setVisibility(View.VISIBLE);
        }else{
            tvIsDefault.setVisibility(View.INVISIBLE);
        }
        tvName.setText(name);
        tvPhone.setText(phone);
        tvAddress.setText(address);
    }

    //显示地址
    @Override
    public void showAddress(List<AddressBean> beans) {
        layoutContent.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.VISIBLE);
        tvPhone.setVisibility(View.VISIBLE);
        tvAddress.setVisibility(View.VISIBLE);
        tvSelectAdd.setVisibility(View.INVISIBLE);

        this.beans = beans;

        boolean flag = false;
        for (int i = 0; i < beans.size(); i++) {
            AddressBean bean = beans.get(i);

            String isDefult = bean.getIsdefault();
            //是默认地址
            if ("1".equals(isDefult)) {
                flag = true;
                currAddress = bean;
                beans.get(i).setSelect(true);
                showBean(bean);
                break;
            }

        }

        //假如没有默认地址   取第一个
        if (!flag) {
            beans.get(0).setSelect(true);
            currAddress = beans.get(0);
            showBean(currAddress);
        }
    }

    //填充商品列表
    @Override
    public void showGoodsList() {
        if (llGoodsList.getChildCount() > 0)
            llGoodsList.removeAllViews();

        int canUseJifen = 0;    //所有商品可用的积分
        double totalPrice = 0;  //商品总价
        if (goods != null) {

            for (ShopCartBean bean : goods) {
                View view = View.inflate(this, R.layout.item_list_goods, null);
                ImageView img = (ImageView) view.findViewById(R.id.img_goods);
                TextView tvName = (TextView) view.findViewById(R.id.tv_goods_name);
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_goods_title);
                TextView tvMoney = (TextView) view.findViewById(R.id.tv_goods_money);
                TextView tvCount = (TextView) view.findViewById(R.id.tv_goods_count);

                ImageHelper.getInstance().display(bean.getFacePic(), img);

                String name = bean.getName();
                String title = bean.getYinyu();
                String money = bean.getPrice();
                int count = bean.getPayCount();

                tvName.setText(name);
                tvTitle.setText(title);
                tvMoney.setText(money);
                tvCount.setText("x" + count);

                llGoodsList.addView(view);

                int jifen = Integer.valueOf(bean.getJf());
                canUseJifen += jifen * count;
                totalPrice += Double.valueOf(bean.getPrice()) * count;
            }
        }

        int jifen = Integer.valueOf(UserManager.getInstance().getUserJf(this));     //用户自己的积分

        tvGoodsPrice.setText("¥" + String .format("%.2f",totalPrice));
        tvOrderPrice.setText("实付款: ¥ " + String .format("%.2f",totalPrice));

        mPresenter.calculateMoney(jifen, canUseJifen, totalPrice);
    }

    //计算可以用积分抵消多少价格
    @Override
    public void calculateMoney(boolean canChecked, int canUseJifen, double offsetMoney) {
        boxJifen.setEnabled(canChecked);
        tvJifen.setText(canUseJifen + "");
        tvJifenPrice.setText("¥ " + String .format("%.2f",offsetMoney));
    }

    private void displayAddress(AddressBean bean) {
        String name = currAddress.getName();
        String phone = currAddress.getPhone();
        String address = currAddress.getProvince() + currAddress.getCity();
        String area = currAddress.getArea();
        if (!TextUtils.isEmpty(area)) {
            address += area;
        }
        address += currAddress.getAddress();

        tvName.setText(name);
        tvPhone.setText(phone);
        tvAddress.setText(address);

        if ("1".equals(currAddress.getIsdefault())) {
            tvIsDefault.setVisibility(View.VISIBLE);
        } else {
            tvIsDefault.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showNetError() {
        getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    return;
                }
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
        isDefult = true;
        layoutContent.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.INVISIBLE);
        tvPhone.setVisibility(View.INVISIBLE);
        tvIsDefault.setVisibility(View.INVISIBLE);
        tvAddress.setVisibility(View.INVISIBLE);
        tvSelectAdd.setVisibility(View.VISIBLE);

        if(beans != null){
            beans.clear();
        }
        currAddress = null;
    }

    @Override
    public void showTokenOverdue() {
        ToastUtil.showShort(this, getString(R.string.token_overdue));
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 101);
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
        getBaseEmptyView().hideEmptyView();
        getBaseLoadingView().showLoading();
    }

    @Override
    public void setPresenter(GoodsConfirmConstant.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            //添加新的收货地址
            case Constant.ResultCode.ADD_ADDRESS:
                getAddressList();
                break;

            //选择地址
            case Constant.ResultCode.SELECT_ADDRESS:
                if(data != null){
                    this.beans = (List<AddressBean>) data.getSerializableExtra(Constant.INTENT_DATA);
                    for(AddressBean bean : beans){
                        if(bean.isSelect()){
                            showBean(bean);
                            break;
                        }
                    }
                }
                break;

            case Constant.ResultCode.LOGIN:
                getAddressList();
                break;

        }

    }

    /**
     * 修改地址回调
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvenUpdataAddress(EventUpdataAddrBean event) {
        AddressBean tag = event.bean;
        if (tag.getId().equals(currAddress.getId()) || TextUtils.isEmpty(tag.getId())) {
            currAddress = tag;
            displayAddress(currAddress);
        }
    }

    /**
     * 支付回调
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPayResult(EventPayResultBean bean) {
        int code = bean.status;
        switch (code) {
            case PayConstant.ERR_CODE_PAY_FINISH:
                mPresenter.changeJifen(this);
                Intent intent = new Intent(this, MineOrderActivity.class);
                intent.putExtra(Constant.INTENT_DATA,1);
                startActivity(intent);
                finish();
                break;

            case PayConstant.ERR_CODE_PAY_FAILURE:
            case PayConstant.ERR_CODE_PAY_CANCEL:
                Intent intent1 = new Intent(this, MineOrderActivity.class);
                intent1.putExtra(Constant.INTENT_DATA,0);
                startActivity(intent1);
                finish();
                break;

        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
