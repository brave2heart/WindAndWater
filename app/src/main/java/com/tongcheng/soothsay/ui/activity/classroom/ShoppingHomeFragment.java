package com.tongcheng.soothsay.ui.activity.classroom;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.ShopHomeBannerAdapter;
import com.tongcheng.soothsay.adapter.classroom.ShopHomeHeadRecyleViewAdapter;
import com.tongcheng.soothsay.adapter.classroom.ShopHomeInfoRecyclerViewAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.bean.classroom.ShopHomeHeadBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.classroom.goodsList.GoodsListActivity;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RecyclerSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/28.
 * 商城首页
 */

public class ShoppingHomeFragment extends BaseTitleFragment {

    @BindView(R.id.cb_shop_banner)
    ConvenientBanner mBannerView;
    @BindView(R.id.rv_shop_head)
    RecyclerView rvShopHead;
    @BindView(R.id.rv_shop_clauses)
    RecyclerView rvShopClauses;

    private String[] title = {"新品推荐", "热销圣品", "招财转运", "本命佛"
            , "12生肖", "脱单", "镇宅", "开运"};

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.acitivity_shopping_home);
//        initData();
//    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.acitivity_shopping_home, null);

        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getBaseHeadView().showTitle("开运商城");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentActivity activity = getActivity();
                activity.finish();
            }
        });
        getBaseLoadingView().showLoading();
        AllApi.getInstance().getShopHome("").enqueue(new ApiCallBack<ApiResponseBean<ShopHomeBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                ShopHomeBean bean = (ShopHomeBean) data;
                setAdvertise(bean.getAds());  //轮播图
                initHeadRecycleView();
                initContentRecycleView(bean.getStoreInfos());  //商品信息
            }


            @Override
            public void onError(String errorCode, String message) {
                ToastUtil.showShort(getActivity().getApplicationContext(), message);
                getBaseLoadingView().hideLoading();
                if (errorCode == ApiCallBack.NET_ERROR) {//网络错误
                    getBaseEmptyView().showNetWorkView(R.drawable.nonetwork, R.string.showNeterr, R.string.touch_again, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            initData();
                        }
                    });
                } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {//服务器系统错误
                    getBaseEmptyView().showEmptyView(R.drawable.nonetwork, R.string.server_error, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            initData();
                        }
                    });
                } else {
                    getBaseEmptyView().showEmptyContent();
                }
            }
        }));

    }


    /**
     * 轮播图数据
     *
     * @param advertise
     */
    public void setAdvertise(final List<ShopHomeBean.AdsBean> advertise) {
        mBannerView.startTurning(2000);
        mBannerView.setPages(new CBViewHolderCreator<ShopHomeBannerAdapter>() {
            @Override
            public ShopHomeBannerAdapter createHolder() {
                return new ShopHomeBannerAdapter();
            }
        }, advertise);
        //设置指示点样式
        mBannerView.setPageIndicator(new int[]{R.drawable.banner_icon_normal_yuanqiu, R.drawable.banner_icon_selected_yuanqiu});
        //设置指示点对其方式
        mBannerView.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mBannerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GotoUtil.GoToWebViewActivity(getActivity().getApplicationContext(),
                        advertise.get(position).getTitle(), advertise.get(position).getUrl());
            }
        });
    }

    /**
     * 头部的八个数据
     */
    private void initHeadRecycleView() {
        List<ShopHomeHeadBean> list = new ArrayList<>();
        int[] iv = {R.drawable.sc_icon_xinpin, R.drawable.sc_icon_erxiao, R.drawable.sc_icon_zhaocai
                , R.drawable.sc_icon_benming, R.drawable.sc_icon_shengxiao, R.drawable.sc_icon_tuodan
                , R.drawable.sc_icon_zhenzhai, R.drawable.sc_icon_kaiyun};

        for (int i = 0; i < title.length; i++) {
            ShopHomeHeadBean bean = new ShopHomeHeadBean(iv[i], title[i]);
            list.add(bean);
        }
        ShopHomeHeadRecyleViewAdapter recycleAdapter = new ShopHomeHeadRecyleViewAdapter(getActivity().getApplicationContext(), list,
                R.layout.item_shop_head_recyclerview);
        rvShopHead.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 4));
        rvShopHead.setAdapter(recycleAdapter);
        //item点击监听
        recycleAdapter.setOnItemClickListener(new AbsBaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int posotion) {
                switch (posotion) {
                    //新品推荐  or 热销圣品
                    case 0:
                    case 1:
                        gotoGoodsList(posotion);
                        break;

                    //其他的都是跳转到webview
//                    default:
//                        if (posotion>1) {
//                            String s = title[posotion];
//                            int i = posotion - 1;
//                            String url = "http://120.76.219.201:8080/html/sale.html?sortId=" + i;
//                            GotoUtil.GoToWebViewActivity(ShoppingHomeActivity.this, s, url);
//                        }
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        gotoWebView(posotion);
                        break;
                }

            }
        });
    }

    private void gotoWebView(int pos) {
        pos--;
        String temp = Constant.Url.BASE_URL + "html/sale.html?sortId=%d";
        String url = String.format(Locale.CHINA, temp, pos);

        Intent intent = new Intent(getActivity().getApplicationContext(), WebViewActivity.class);
        intent.putExtra("web_url", url);
        intent.putExtra("web_title", title[++pos]);
        startActivity(intent);
    }

    private void gotoGoodsList(int pos) {
        String title = this.title[pos];
        Intent intent = new Intent(getActivity().getApplicationContext(), GoodsListActivity.class);
        intent.putExtra(Constant.INTENT_DATA, title);
        startActivity(intent);
    }

    ShopHomeInfoRecyclerViewAdapter adapter;

    /**
     * 内容填充
     *
     * @param storeInfos
     */
    private void initContentRecycleView(List<ShopHomeBean.StoreInfosBean> storeInfos) {
        adapter = new ShopHomeInfoRecyclerViewAdapter(getActivity(), getActivity().getApplicationContext(), storeInfos,
                R.layout.item_shop_content_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvShopClauses.setLayoutManager(manager);
        rvShopClauses.setAdapter(adapter);
        rvShopClauses.setHasFixedSize(true);
        rvShopClauses.addItemDecoration(new RecyclerSpace(10, getResources().getColor(R.color.background_color)));
        rvShopClauses.setNestedScrollingEnabled(false);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
    }

}
