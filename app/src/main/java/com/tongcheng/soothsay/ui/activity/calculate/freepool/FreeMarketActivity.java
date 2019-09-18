package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.FreeMarketAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.FreeGoodsBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 放生商城
 */
public class FreeMarketActivity extends BaseTitleActivity implements FreeMarketConstant.View {

    private final String transType = "FSC" ;  //订单类型

    @BindView(R.id.grid_free_market)            GridView    gridView;
    @BindView(R.id.btn_free_market_pay)         Button      btnPay;

    private FreeMarketAdapter adapter;

    private FreeMarketConstant.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_market);
        initView();
        initListener();
        initData();

    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_free_market));
    }

    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                payNow();
            }
        });

    }


    @Override
    public void initData() {
        new FreeMarketPresenter(this);
        mPresenter.getGoodsList();
    }

    @Override
    public void showGoodsList(List<FreeGoodsBean> beans) {
        adapter = new FreeMarketAdapter(this, beans, R.layout.item_grid_free_market);
        gridView.setAdapter(adapter);
    }


    private void payNow(){
        int count = 0;
        if(adapter != null){
            for(FreeGoodsBean goodsBean : adapter.getDatas()){
                if(goodsBean.getPayCount() != 0){
                    count ++;
                }
            }
        }

        if(count == 0){
            ToastUtil.showShort(this,getString(R.string.goods_no_select));
            return ;
        }

        String token = UserManager.getInstance().getToken();
        if(TextUtils.isEmpty(token)){
            login();
            return;
        }


        double money = 0.0d;
        JSONArray jArray = new JSONArray();
        try {
            //将商品列表转成json数组
            for (FreeGoodsBean goodsBean : adapter.getDatas()){
                if(goodsBean.getPayCount() != 0){
                    JSONObject jObj = new JSONObject();
                    jObj.put("amount",goodsBean.getPayCount()+"");
                    jObj.put("merchandiseId",goodsBean.getFsStoreId());
                    jArray.put(jObj);

                    double price = Double.valueOf(goodsBean.getPrice());
                    money += price * goodsBean.getPayCount();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        OrdersRequestBean requestBean = new OrdersRequestBean();
        requestBean.setTotalPrice(money * 100 + "");
        requestBean.setToken(token);
        requestBean.setExtra(jArray.toString());
        requestBean.setTransType(transType);
        requestBean.setGoodsName(getString(R.string.goods_fsc_name));

        Intent intent = new Intent(FreeMarketActivity.this, PayActivity.class);
        intent.putExtra(Constant.INTENT_DATA,requestBean);
        startActivityForResult(intent,2);
    }

    private void login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,1);
    }


    @Override
    public void showNetError() {
        getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;
                mPresenter.getGoodsList();
                getBaseEmptyView().hideEmptyView();
            }
        });
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this, info);
    }

    @Override
    public void showEmpty() {
        getBaseEmptyView().showEmptyContent();
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
    }

    @Override
    public void setPresenter(FreeMarketConstant.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Constant.ResultCode.LOGIN){
            payNow();
        }

        if(resultCode == PayConstant.PAY_RESULT){
            int result = data.getIntExtra(Constant.INTENT_DATA,-1);
            if(result == PayConstant.ERR_CODE_PAY_FINISH){
                ToastUtil.showShort(this,getString(R.string.free_pay_finish));
                finish();
            }

        }
    }
}
