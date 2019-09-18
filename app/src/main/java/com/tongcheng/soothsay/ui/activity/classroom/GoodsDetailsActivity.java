package com.tongcheng.soothsay.ui.activity.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.classroom.TabBean;
import com.tongcheng.soothsay.bean.dao.GoodsBean;
import com.tongcheng.soothsay.bean.event.ChangeCartBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.GoodsIdUtils;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;
import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * Created by 宋家任 on 2016/12/23.
 * 商品详情页
 */

public class GoodsDetailsActivity extends BaseTitleActivity {
//    boolean isLoad = false;

    @BindView(R.id.btn_cart)
    BootstrapButton btnCart;//加入购物车
    @BindView(R.id.btn_buy)
    BootstrapButton btnBuy;//立即购买
    @BindView(R.id.ctl_model_shop)
    CommonTabLayout ctlModelShop;
    @BindView(R.id.wv_goods_info)
    CacheWebView webview;

    private ArrayList<CustomTabEntity> gwcEntity;
    private String[] mTitles = {"客服", "购物车"};
    private int[] mIconNormalIds = {R.drawable.ic_kefu, R.drawable.ic_tab_cart_normal};
    private int[] mIconPressIds = {R.drawable.ic_kefu, R.drawable.ic_tab_cart_normal};
    private File mFile;
    private String mExtension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_goods_detail);
        initData();
        initListener();
    }

    String goodsid;//商品id
    String amount;//商品数量
    List<GoodsBean> goodsList;

    @Override
    public void initData() {
        super.initData();
        EventBusUtil.register(this);
        gwcEntity = new ArrayList<>();
        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsid");//商品id

        getBaseHeadView().showTitle("商品详情");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsDetailsActivity.this.finish();
            }
        });
        for (int i = 0; i < mTitles.length; i++) {
            gwcEntity.add(new TabBean(mTitles[i], mIconPressIds[i], mIconNormalIds[i]));
        }
        try {
            ctlModelShop.setTabData(gwcEntity);
        } catch (Exception e) {

        }

        String url = intent.getStringExtra("redirectUrl");
        webview.loadUrl(url);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //使用自定义的缓存
        webview.setEnableCache(true);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        //阻塞图片加载，让页面加载更快
        webview.setBlockNetworkImage(true);
        getBaseLoadingView().hideLoading();

        goodsList = GoodsIdUtils.getInstance().getGoodsList();
        if (goodsList.size() > 0) {
            ctlModelShop.showMsg(1, goodsList.size());
            ctlModelShop.setMsgMargin(1, -12, -1);
        }

    }

    @Override
    public void initListener() {
        ctlModelShop.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0://客服
                        gotoKefu();
                        break;
                    case 1://购物车
                        GotoUtil.GoToActivity(GoodsDetailsActivity.this, ShopCartActivity.class);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {
                switch (position) {
                    case 0://客服
                        gotoKefu();
                        break;
                    case 1://购物车
                        GotoUtil.GoToActivity(GoodsDetailsActivity.this, ShopCartActivity.class);
                        break;
                }
            }
        });

    }

    /**
     * GreenDao操作不熟悉
     *
     * @param view
     */
    @OnClick({R.id.btn_cart, R.id.btn_buy})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_cart://加入购物车
                boolean haveGoods = GoodsIdUtils.getInstance().isHaveGoods(goodsid);
                if (haveGoods) {//加入的商品跟购物车的商品相同
                    GoodsBean bean = GoodsIdUtils.getInstance().getGoodsBean(goodsid);
                    amount = bean.getAmount();
                    int count = Integer.valueOf(amount);
                    count++;
                    amount = String.valueOf(count);
                    bean.setAmount(amount);
                    GoodsIdUtils.getInstance().updataGoods(bean);
                    ToastUtil.showShortWithPic(GoodsDetailsActivity.this, "添加成功，在购物车等亲~",
                            R.drawable.sc_icon_gou, Gravity.CENTER);
//                    ToastUtil.showShort(GoodsDetailsActivity.this, "添加成功，在购物车等亲~");
                } else {
                    GoodsBean bean = new GoodsBean();
                    bean.setGoodsId(goodsid);
                    bean.setAmount("1");
                    GoodsIdUtils.getInstance().saveGoods(bean);
                    goodsList = GoodsIdUtils.getInstance().getGoodsList();
                    ctlModelShop.showMsg(1, goodsList.size());
                    ctlModelShop.setMsgMargin(1, -12, -1);
//                    ToastUtil.showShort(GoodsDetailsActivity.this, "添加成功，在购物车等亲~");
                    ToastUtil.showShortWithPic(GoodsDetailsActivity.this, "添加成功，在购物车等亲~",
                            R.drawable.sc_icon_gou, Gravity.CENTER);
                }

                break;
            case R.id.btn_buy://立即购买
                boolean havegoods = GoodsIdUtils.getInstance().isHaveGoods(goodsid);
                if (havegoods) {//加入的商品跟购物车的商品相同
                    GoodsBean bean = GoodsIdUtils.getInstance().getGoodsBean(goodsid);
                    amount = bean.getAmount();
                    int count = Integer.valueOf(amount);
                    count++;
                    amount = String.valueOf(count);
                    bean.setAmount(amount);
                    GoodsIdUtils.getInstance().updataGoods(bean);
                    GotoUtil.GoToActivity(GoodsDetailsActivity.this, ShopCartActivity.class);
                } else {
                    GoodsBean bean = new GoodsBean();
                    bean.setGoodsId(goodsid);
                    bean.setAmount("1");
                    GoodsIdUtils.getInstance().saveGoods(bean);
                    goodsList = GoodsIdUtils.getInstance().getGoodsList();
                    ctlModelShop.showMsg(1, goodsList.size());
                    ctlModelShop.setMsgMargin(1, -12, -1);
                    GotoUtil.GoToActivity(GoodsDetailsActivity.this, ShopCartActivity.class);
                }
                break;
        }
    }


    private void gotoKefu() {
        if (UserManager.getInstance().isLogin(this) && RongIM.getInstance() != null) {
            //首先需要构造使用客服者的用户信息
            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
            CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

            /**
             * 启动客户服聊天界面。
             *
             * @param context           应用上下文。
             * @param customerServiceId 要与之聊天的客服 Id。
             * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
             * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
             */
            RongIM.getInstance().startCustomerServiceChat(this, Constant.KEFU_ID, "在线客服", csInfo);
        }

    }


    @Subscribe
    public void onEvent(ChangeCartBean bean) {
        if (bean.goodsSize > 0) {
            ctlModelShop.showMsg(1, bean.goodsSize);
            ctlModelShop.setMsgMargin(1, -12, -1);
        } else {
            ctlModelShop.hideMsg(1);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
