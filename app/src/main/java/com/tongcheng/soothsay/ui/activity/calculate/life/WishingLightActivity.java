package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.GridLightTpyeAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.WishingCount;
import com.tongcheng.soothsay.bean.calculation.WishingType;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishingSquareApi;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquareConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.MyGridView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/12/2 0002.
 * 许愿点灯
 */

public class WishingLightActivity extends BaseTitleActivity {


    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_banner)
    TextView tvBanner;
    @BindView(R.id.grid_light)
    MyGridView gridLight;
    @BindView(R.id.iv_light_type)
    ImageView ivLightType;
    @BindView(R.id.iv_huanyuan_lefe)
    ImageView ivHuanyuanLefe;
    @BindView(R.id.tv_light_type)
    TextView tvLightType;
    @BindView(R.id.iv_huanyuan_right)
    ImageView ivHuanyuanRight;
    @BindView(R.id.ll_huanyuan)
    RelativeLayout llHuanyuan;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.btn_wish)
    Button btnWish;
    @BindView(R.id.tv_wishing_count)
    TextView tvWishingCount;
    @BindView(R.id.tv_light_mine)
    TextView tvLightMine;
    @BindView(R.id.icon_wish)
    ImageView iconWish;
    @BindView(R.id.rl_light_mine)
    RelativeLayout rlLightMine;
    @BindView(R.id.tv_light_square)
    TextView tvLightSquare;
    @BindView(R.id.icon_square)
    ImageView iconSquare;
    @BindView(R.id.rl_light_square)
    RelativeLayout rlLightSquare;
    private GridLightTpyeAdapter adapter;
    private int index;

    private List<WishingType> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_wishing_light);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_wishing_light));
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
        EventBusUtil.register(this);
        for (int i = 0; i < WishingSquareConstant.lightArr.length; i++) {
            WishingType bean = new WishingType();
            list.add(bean);
            LogUtil.e("listSize:" + list.size());
            getWishingCount();
        }

        adapter = new GridLightTpyeAdapter(WishingLightActivity.this, list, WishingSquareConstant.lightArr, WishingSquareConstant.selectArr, R.layout.item_light_type);
        gridLight.setAdapter(adapter);
        gridLight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                adapter.setSelect(position);
                adapter.notifyDataSetChanged();

                changeLightInfo(index);
            }
        });


    }

    private void changeLightInfo(int index) {
        String[] lightType = getResources().getStringArray(R.array.light_typeArr);
        String[] titleLeft = getResources().getStringArray(R.array.light_title_left);
        String[] titleRight = getResources().getStringArray(R.array.light_title_right);
        String[] lightDesc = getResources().getStringArray(R.array.light_desc);
        tvLightType.setText(lightType[index]);
        tvTitleLeft.setText(titleLeft[index]);
        tvTitleRight.setText(titleRight[index]);
        tvDesc.setText(lightDesc[index]);
        ivLightType.setImageResource(WishingSquareConstant.xyddArr[index]);
    }

    @OnClick({R.id.btn_wish, R.id.rl_light_mine, R.id.rl_light_square})
    public void clickMethod(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_wish:  //我要许愿点灯
                HashMap<String,String> map = new HashMap();
                map.put("index",String.valueOf(index));
                map.put("from","WishingLightActivity");
                GotoUtil.GoToActivityWithData(WishingLightActivity.this,MakeAWishActivity.class,map);
                break;
            case R.id.rl_light_mine:      //我的许愿灯
                GotoUtil.GoToActivity(WishingLightActivity.this, MyWishingActivity.class);
                break;
            case R.id.rl_light_square:      //点灯许愿广场
                GotoUtil.GoToActivity(WishingLightActivity.this, WishingSquareActivity.class);
                break;
        }
    }

    /**
     * 获取许愿人数
     */
    public void getWishingCount() {
        getBaseLoadingView().showLoading();
        WishingSquareApi.getInstance().getWishingCount().enqueue(new ApiCallBack<ApiResponseBean<WishingCount>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                WishingCount bean = (WishingCount) data;
                bean.setCount(bean.getCount());
                tvWishingCount.setText(bean.getCount());
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                boolean b = ErrorCodeUtil.showHaveTokenError(WishingLightActivity.this, errorCode);
                if (b == false){
                    ToastUtil.showShort(WishingLightActivity.this,message);
                }
                ErrorCodeUtil.showEmptyView(WishingLightActivity.this, errorCode, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWishingCount();
                        getBaseEmptyView().hideEmptyView();
                    }
                });
            }
        }));
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
                getWishingCount();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
