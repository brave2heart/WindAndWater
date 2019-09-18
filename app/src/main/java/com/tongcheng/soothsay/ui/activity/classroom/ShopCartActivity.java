package com.tongcheng.soothsay.ui.activity.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.ShopCartAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.base.PayBean;
import com.tongcheng.soothsay.bean.classroom.ShopCartBean;
import com.tongcheng.soothsay.bean.dao.GoodsBean;
import com.tongcheng.soothsay.bean.event.ChangeCartBean;
import com.tongcheng.soothsay.bean.event.ChangePricebean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.StoreApi;
import com.tongcheng.soothsay.ui.activity.classroom.goodsConfirm.GoodsConfirmActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.GoodsIdUtils;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/12/23.
 * 购物车界面
 */

public class ShopCartActivity extends BaseTitleActivity {

    @BindView(R.id.cb_all)
    CheckBox cbAll;//全选
    @BindView(R.id.btn_account)
    BootstrapButton btnAccount;

    String[] goodsids;
    String[] goodsCount;
    @BindView(R.id.rv_shop_cart)
    RecyclerView rvShopCart;
    @BindView(R.id.tv_goods_amount)
    TextView tvGoodsAmount;//总金额
    private ShopCartAdapter mAdapter;
    ArrayList<ShopCartBean> mCartDatas;//总的购物车
    ArrayList<ShopCartBean> mSelectDatas;//选中的数据
    ArrayList<ShopCartBean> misBuyDatas;//进入购买状态下的数据

    //double只保留前两位
    DecimalFormat df = new DecimalFormat("######0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_shop_cart);
        EventBusUtil.register(this);
        initData();
        initListener();

    }

    List<GoodsBean> goodsList;
    double allTotalPrice = 0.0d;//所选商品总价

    @Override
    public void initData() {
        getBaseLoadingView().showLoading();
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopCartActivity.this.finish();
            }
        });
        getBaseLoadingView().showLoading();
        goodsList = GoodsIdUtils.getInstance().getGoodsList();
        if (goodsList.size() != 0) {
            goodsids = new String[goodsList.size()];
            goodsCount = new String[goodsList.size()];
            for (int i = 0; i < goodsList.size(); i++) {
                String goodsId = goodsList.get(i).getGoodsId();
                goodsids[i] = goodsId;
                String amount = goodsList.get(i).getAmount();
                goodsCount[i] = amount;

            }
            getBaseHeadView().showTitle("购物车(" + goodsList.size() + ")");
            getCartDatas();
        } else {
            getBaseHeadView().showTitle("购物车");
            getBaseEmptyView().showEmptyView(R.drawable.nodata, "购物车为空，去商城看看吧", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GotoUtil.GoToActivity(ShopCartActivity.this, ShoppingHomeActivity.class);
                    ShopCartActivity.this.finish();
                }
            });
            getBaseLoadingView().hideLoading();
        }
        mCartDatas = new ArrayList<>();
        mSelectDatas = new ArrayList<>();
        misBuyDatas = new ArrayList<>();
    }


    @Override
    public void initListener() {
        super.initListener();

    }

    /**
     * 获取购物车数据
     */

    private void getCartDatas() {
        LogUtil.printD("aaaaaa");
        JSONArray jsonarray = new JSONArray();
        try {
            for (int i = 0; i < goodsids.length; i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("merchandiseId", goodsids[i]);
                jObj.put("shopCartCount", goodsCount[i]);
                jsonarray.put(jObj);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        StoreApi.getInstance().getShopCart(jsonarray.toString()).
                enqueue(new ApiCallBack<ApiResponseBean<List<ShopCartBean>>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        mCartDatas = (ArrayList<ShopCartBean>) data;
                        mAdapter = new ShopCartAdapter(ShopCartActivity.this, mCartDatas, R.layout.item_shop_cart);
                        final LinearLayoutManager manager = new LinearLayoutManager(ShopCartActivity.this);
                        rvShopCart.setLayoutManager(manager);
                        rvShopCart.setAdapter(mAdapter);
                        getBaseHeadView().showTitle("购物车(" + mCartDatas.size() + ")");
                        //全选监听
                        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {//选中状态
                                    mSelectDatas.clear();
                                    for (int i = 0; i < mCartDatas.size(); i++) {
                                        if (!mCartDatas.get(i).isCheck()) {//如果原先就选中了，钱不加
                                            mCartDatas.get(i).setCheck(true);
                                            double totalPrice = mCartDatas.get(i).getTotalPrice();
                                            allTotalPrice = totalPrice + allTotalPrice;
                                        }
                                    }
                                    mSelectDatas.addAll(mCartDatas);
                                    tvGoodsAmount.setText(String.valueOf(df.format(allTotalPrice)));
                                    btnAccount.setText("结算(" + mSelectDatas.size() + ")");
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    boolean flag = false;
                                    for (ShopCartBean bean : mAdapter.getList()) {
                                        if (!bean.isCheck()) {
                                            flag = true;
                                        }
                                    }
                                    if (flag) {
                                        return;
                                    }
                                    for (int i = 0; i < mCartDatas.size(); i++) {
                                        mCartDatas.get(i).setCheck(false);
                                    }
                                    allTotalPrice = 0.0d;
                                    tvGoodsAmount.setText("0.0");
                                    mSelectDatas.clear();
                                    btnAccount.setText("结算(" + mSelectDatas.size() + ")");
                                    mAdapter.notifyDataSetChanged();
                                }
                            }
                        });

                        //点击条目监听
                        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                String merchandiseId = mCartDatas.get(position).getMerchandiseId();//商品id
                                Intent intent = new Intent(ShopCartActivity.this, GoodsDetailsActivity.class);
                                intent.putExtra("goodsid", merchandiseId);
                                intent.putExtra("redirectUrl", mCartDatas.get(position).getRedirectUrl());
                                startActivity(intent);
//                                ShopCartActivity.this.finish();
                            }
                        });

                        //删除条目监听
                        mAdapter.setOnDelItemListener(new ShopCartAdapter.OnDelItemListener() {
                            @Override
                            public void onDel(BaseRecyclerAdapter.ViewHolder holder, int position, GoodsBean bean) {
                                mAdapter.removeData(holder, position);
                                GoodsIdUtils.getInstance().deletGoods(bean);
                                goodsList = GoodsIdUtils.getInstance().getGoodsList();
                                if (goodsList.size() == 0) {
                                    getBaseHeadView().showTitle("购物车");
                                    getBaseEmptyView().showEmptyView(R.drawable.nodata, "购物车为空，去商城看看吧", "确定", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            GotoUtil.GoToActivity(ShopCartActivity.this, ShoppingHomeActivity.class);
                                            ShopCartActivity.this.finish();
                                        }
                                    });
                                } else {
                                    getBaseHeadView().showTitle("购物车(" + goodsList.size() + ")");
                                }

                                //刷新上一页面购物车数
                                EventBusUtil.post(new ChangeCartBean(goodsList.size()));
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                        getBaseLoadingView().hideLoading();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        if (ApiCallBack.NET_ERROR.equals(errorCode)) {
                            getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    initData();
                                    getBaseEmptyView().hideEmptyView();
                                }
                            });
                        }
                        getBaseLoadingView().hideLoading();
                        boolean b = ErrorCodeUtil.shownetWorkAndServerError(ShopCartActivity.this, errorCode);
                        if (b == false) {
                            ToastUtil.showShort(ShopCartActivity.this, message);
                        }
                    }
                }));
    }


    @OnClick({R.id.cb_all, R.id.btn_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_all:
                break;
            case R.id.btn_account://结算..
                if (mSelectDatas.size() != 0) {
                    Intent intent = new Intent(this, GoodsConfirmActivity.class);
//                for (int i = 0; i < mSelectDatas.size(); i++) {//从所选中把数据删除出购物车
//                    GoodsBean goodsBean = GoodsIdUtils.getInstance().getGoodsBean(mSelectDatas.get(i).getMerchandiseId());
//                    GoodsIdUtils.getInstance().deletGoods(goodsBean);
//                }
                    intent.putParcelableArrayListExtra(Constant.INTENT_DATA, mSelectDatas);
                    boolean refreshFlag = false;
                    for (int i = 0; i < mCartDatas.size(); i++) {
                        if (mCartDatas.get(i).isCheck()) {
                            mCartDatas.get(i).setCheck(false);
                            refreshFlag = true;
                        }
                    }
                    if (refreshFlag) {
                        mAdapter.notifyDataSetChanged();
                        allTotalPrice = 0.0d;
                        tvGoodsAmount.setText("0.0");
                        btnAccount.setText("结算(0)");
                    }
                    startActivity(intent);
                    if (cbAll.isChecked())//如果原先就是全选，设置成取消全选
                        cbAll.setChecked(false);
                    misBuyDatas.addAll(mSelectDatas);
                    mSelectDatas.clear();
                } else {
                    ToastUtil.showShort(this, "请选择需要结算的商品");
                }

                break;
        }
    }

    /**
     * 支付结果返回返回
     *
     * @param bean
     */
    @Subscribe
    public void onEvent(PayBean bean) {
        for (int i = 0; i < misBuyDatas.size(); i++) {
            GoodsBean goodsBean = GoodsIdUtils.getInstance().getGoodsBean(String.valueOf(misBuyDatas.get(i).getMerchandiseId()));
            GoodsIdUtils.getInstance().deletGoods(goodsBean);
            List<GoodsBean> goodsList = GoodsIdUtils.getInstance().getGoodsList();
            //刷新上一页面购物车数
            EventBusUtil.post(new ChangeCartBean(goodsList.size()));
        }
        mAdapter.notifyDataSetChanged();
        finish();
    }

    /**
     * 修改价格
     */
    @Subscribe
    public void onEvent(ChangePricebean bean) {
        allTotalPrice = 0.0d;
        mSelectDatas.clear();
        for (int i = 0; i < mCartDatas.size(); i++) {
            if (bean.status == -1) {
                cbAll.setChecked(false);
            }
            if (mCartDatas.get(i).isCheck() == true) {//选中才加
                double totalPrice = mCartDatas.get(i).getTotalPrice();
                allTotalPrice = totalPrice + allTotalPrice;
                mSelectDatas.add(mCartDatas.get(i));
            }
        }
        tvGoodsAmount.setText(String.valueOf(df.format(allTotalPrice)));
        btnAccount.setText("结算(" + mSelectDatas.size() + ")");
        mAdapter.notifyDataSetChanged();
    }

    //    /**
//     * 支付回调
//     *
//     * @param bean
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onPayResult(EventPayResultBean bean) {
////        List<GoodsBean> goodsList = GoodsIdUtils.getInstance().getGoodsList();
////        //刷新上一页面购物车数
////        EventBusUtil.post(new ChangeCartBean(goodsList.size()));
//        for (int i = 0; i < misBuyDatas.size(); i++) {
//            GoodsBean goodsBean = GoodsIdUtils.getInstance().getGoodsBean(String.valueOf(misBuyDatas.get(i).getMerchandiseId()));
//            GoodsIdUtils.getInstance().deletGoods(goodsBean);
//            List<GoodsBean> goodsList = GoodsIdUtils.getInstance().getGoodsList();
//            //刷新上一页面购物车数
//            EventBusUtil.post(new ChangeCartBean(goodsList.size()));
//        }
//        finish();
//        mAdapter.notifyDataSetChanged();
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
