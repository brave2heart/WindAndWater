package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.MyWishingOrderAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.WishingOrder;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishingSquareApi;
import com.tongcheng.soothsay.ui.activity.mine.MineOrderActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.CustomDialog;
import com.tongcheng.soothsay.widget.DividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/12/6 0006.
 * 我的许愿灯
 */

public class MyWishingActivity extends BaseTitleActivity {
    @BindView(R.id.et_order)
    EditText etOrder;
    @BindView(R.id.rc_my_order)
    RecyclerView rcMyOrder;
    @BindView(R.id.btn_query)
    Button btnQuery;

    private MyWishingOrderAdapter mAdapter;
    private HashMap<String, String> map;
    private String token;
    private String orderNo = null;
    private CustomDialog mCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_my_wishing);
        EventBusUtil.register(this);
        initListener();
        initData();

    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_my_wishing));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        if (UserManager.getInstance().isLogin()) {
            token = UserManager.getInstance().getUser().getToken();
            map = new HashMap<>();
            map.put("token", token);
            getData(map);
        } else if (token != UserManager.getInstance().getToken()){//没登录弹出登录对话框
            new CusDialogUtil(this).showCusDialog(R.string.txt_login_first, Constant.RequestCode.MY_WISH);
        }else {
            new CusDialogUtil(this).showCusDialog(R.string.txt_login_first, Constant.RequestCode.MY_WISH);
        }
    }

    @OnClick(R.id.btn_query)
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;
        query();
    }

    private void query() {
        orderNo = etOrder.getText().toString();
        if (TextUtils.isEmpty(orderNo)) {
            ToastUtil.showShort(MyWishingActivity.this, orderNo);
            return;
        }
        map = new HashMap<>();
        map.put("token", token);
        map.put("orderNo", orderNo);
        Log.e(TAG, "query: token+orderNo::" + token + "..." + orderNo);
        getData(map);
    }

    private void initRecycleView(List<WishingOrder> list) {

        mAdapter = new MyWishingOrderAdapter(MyWishingActivity.this, list, R.layout.item_rc_mywishing_order);
        rcMyOrder.setLayoutManager(new LinearLayoutManager(this));
        rcMyOrder.setAdapter(mAdapter);
        //分割线
        rcMyOrder.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 20, getResources().getColor(R.color.divide_line)));
    }

    private void getData(final HashMap map) {
        getBaseLoadingView().showLoading();
        WishingSquareApi.getInstance().getMyWishingLight(map).enqueue(new ApiCallBack<ApiResponseBean<List<WishingOrder>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                List<WishingOrder> list = (List<WishingOrder>) data;
                if (list.size() != 0) {
                    initRecycleView(list);
                }else {
                    getBaseEmptyView().showEmptyView(R.drawable.nodata,"未查询到点亮过许愿灯");
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                ErrorCodeUtil.showEmptyView(MyWishingActivity.this, errorCode, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData(map);
                        getBaseEmptyView().hideEmptyView();
                    }
                });
            }
        }));
    }

    //网络监听
    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            //有网络且没加载成功数据时，重新请求数据
            initData();
            getBaseEmptyView().hideEmptyView();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (requestCode == Constant.RequestCode.MY_WISH) {
            if (UserManager.getInstance().isLogin()) {
                mCustomDialog.dismiss();
                query();
            } else {
                finish();
            }
        }
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
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
                ToastUtil.showShort(MyWishingActivity.this,getResources().getString(R.string.pay_result_success));
                Intent intent = new Intent(this, MyWishingActivity.class);
                startActivity(intent);
                mAdapter.paySuccess();
                break;

            case PayConstant.ERR_CODE_PAY_CANCEL:
                Intent intent1 = new Intent(MyWishingActivity.this, MyWishingActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
