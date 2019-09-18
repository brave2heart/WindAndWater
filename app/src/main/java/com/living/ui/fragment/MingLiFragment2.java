package com.living.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.BannerAdapter;
import com.tongcheng.soothsay.adapter.calculation.ClassificationAdapter;
import com.tongcheng.soothsay.adapter.calculation.HotTestAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.CalculationBannerBean;
import com.tongcheng.soothsay.bean.calculation.CalculationGridViewBean;
import com.tongcheng.soothsay.bean.calculation.MingLiBean;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.TraditionFaceActivity;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei.ZiweiInputActivity;
import com.tongcheng.soothsay.ui.activity.calculate.constellation.ConstellationActivity;
import com.tongcheng.soothsay.ui.activity.calculate.constellation.Tarot.TarotIntroActivity;
import com.tongcheng.soothsay.ui.activity.calculate.divination.DivinationActivity;
import com.tongcheng.soothsay.ui.activity.calculate.freepool.FreePoolActivity;
import com.tongcheng.soothsay.ui.activity.calculate.life.LifeGuideActivity;
import com.tongcheng.soothsay.ui.activity.calculate.love.MarriageLoveActivity;
import com.tongcheng.soothsay.ui.activity.calculate.nametest.NameTestNameActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan.BaziPaipanInputActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.CliffordActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.PrayingStationActivity;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.VirtueTransferActivity;
import com.tongcheng.soothsay.ui.activity.classroom.MoreMasterActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.widget.DividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @description: 命理推算
 * @author: lijuan
 * @date: 2016-10-24
 * @time: 15:04
 */
public class MingLiFragment2 extends BaseTitleFragment implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.card_free_pool)
    View pool;

    public View view;
    private ViewPager mVpClassification, mVpMeasure;
    private ConvenientBanner mBannerView;

    private List<CalculationBannerBean> mBannerDatas = new ArrayList<>();
    private String[] mTitlesClassification = {"八字测算", "合婚恋爱", "起名测名", "星座塔罗", "大师精品", "求签问事", "祈福转运", "生活指南"};
    private int[] mTitleClassImage = {R.drawable.mingli_bazi, R.drawable.mingli_hehun,
            R.drawable.mingli_qiming, R.drawable.mingli_xingzuo, R.drawable.mingli_dashi,
            R.drawable.mingli_qiuqian, R.drawable.mingli_qifu, R.drawable.mingli_shenghuo};
    private String[] mTitlesMeasure = {"祈福许愿", "紫薇斗数", "开运符灵", "塔罗占卜"/*, "传统命相", "生活指南"*/};//传统命相，生活指南暂时不用用到。
    private List<CalculationGridViewBean> mClassicationDatas, mMeasureDatas;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int mClassificationSize = 8, mMeasureSize = 6;
    private RecyclerView mMyGridView;
    private HotTestAdapter mHotTestAdapter;
    private MingLiBean mMingLiBean;
    private RecyclerView mTuiJianRecycler;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_mingli2,
                container, false);
        return view;
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("命理推算");
        getListInfo();

    }

    //    是否加载成功
    private boolean flag;

    /**
     * 获取轮播图信息及大师亲算信息
     */
    private void getListInfo() {
        AllApi.getInstance().getMingLiDatas("").enqueue(new ApiCallBack<ApiResponseBean<MingLiBean>>(new BaseCallBack<MingLiBean>() {
            @Override
            public void onSuccess(MingLiBean data) {
                if (flag) return;
                flag = true;
                mMingLiBean = data;
                setAdvertise(mMingLiBean.getAds());
                setMroeMaster(mMingLiBean.getDs());
            }

            @Override
            public void onError(String errorCode, String message) {
            }
        }));
    }


    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            if (!flag) {
                getListInfo();
            }
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setAdvertise();
//        setClassificationGridView();
        setClassificationRecyclerView();

        mMyGridView = (RecyclerView) view.findViewById(R.id.hot_test_gridview);
        mTuiJianRecycler = (RecyclerView) view.findViewById(R.id.tuijian_test_gridview);

        //热门测算、推荐测算
        setMeasureGridView(mMyGridView, "mingli_remen_big");
        setMeasureGridView(mTuiJianRecycler, "mingli_tuijian_big");


//        setMroeMaster();
    }

    private void setMroeMaster(final List<MingLiBean.DsBean> ds) {
        if (ds != null && ds.size() > 0) {
            ((TextView) view.findViewById(R.id.tv_name)).setText(ds.get(0).getChengHao());
            ((TextView) view.findViewById(R.id.tv_jianjie)).setText(ds.get(0).getJianJie());

            TextView tvState = (TextView) view.findViewById(R.id.tv_state);
            tvState.setText(ds.get(0).getShanChang());
            ImageView ivGoods = (ImageView) view.findViewById(R.id.iv_goods);
            ImageHelper.getInstance().display(ds.get(0).getTouXiang(), ivGoods);
            view.findViewById(R.id.rl_mroe_master).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) {
                        return;
                    }
                    GotoUtil.GoToActivity(getActivity(), MoreMasterActivity.class);
                }
            });
            ((LinearLayout) view.findViewById(R.id.gotoMaster)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = ds.get(0).getUrl();
                    if (UserManager.getInstance().isLogin()) {
                        url = url + UserManager.getInstance().getToken();
                    }
                    GotoUtil.GoToWebViewActivity(getActivity(), ds.get(0).getName(), url);

                }
            });
        }
    }

    @OnClick({R.id.card_free_pool})
    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (v.getId()) {

            //放生池
            case R.id.card_free_pool:
                GotoUtil.GoToActivity(getActivity(), FreePoolActivity.class);
                break;
        }
    }

    //左右滑动查看更多分类
    private void setClassificationRecyclerView() {
        initClassificationDatas();
        RecyclerView classificdation = (RecyclerView) view.findViewById(R.id.rc_classification);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        classificdation.setLayoutManager(gridLayoutManager);
        classificdation.setHasFixedSize(true);
        classificdation.setNestedScrollingEnabled(false);
        ClassificationAdapter classificationAdapter = new ClassificationAdapter(getActivity(), mClassicationDatas, R.layout.item_calculation_classification);
        gridLayoutManager.setAutoMeasureEnabled(true);
        classificdation.setAdapter(classificationAdapter);
        classificationAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    case 0://祈福转运
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", mClassicationDatas.get(position).getName());
                        GotoUtil.GoToActivityWithData(getActivity(), CliffordActivity.class, map);
                        break;
                    case 1: //传统命相
                        GotoUtil.GoToActivity(getActivity(), TraditionFaceActivity.class);
                        break;
                    case 2://星座罗塔
                        GotoUtil.GoToActivity(getActivity(), ConstellationActivity.class);
                        break;
                    case 3://求签问世
                        GotoUtil.GoToActivity(getActivity(), DivinationActivity.class);
                        break;
                    case 4:// 婚姻爱恋
                        GotoUtil.GoToActivity(getActivity(), MarriageLoveActivity.class);
                        break;
                    case 5://大师精品
                        GotoUtil.GoToActivity(getActivity(), MoreMasterActivity.class);
                        break;
                    case 6://生活指南
                        GotoUtil.GoToActivity(getActivity(), LifeGuideActivity.class);
                        break;
                    case 7://  起名测名
                        GotoUtil.GoToActivity(getActivity(), NameTestNameActivity.class);
                        break;
                }
            }
        });

    }

//
//    /**
//     * 利用ViewPager+GridView的方式实现左右滑动查看更多分类   这个不用了 替换成setClassificationRecyclerView（）
//     */
//    private void setClassificationGridView() {
//        mVpClassification = (ViewPager) view.findViewById(R.id.vp_classification);
//        //初始化数据源
//        initClassificationDatas();
//        //总的页数=总数/每页数量，并取整
//        pageCount = (int) Math.ceil(mClassicationDatas.size() * 1.0 / mClassificationSize);
//        List<View> mPagerList = new ArrayList<View>();
//        for (int i = 0; i < pageCount; i++) {
//            //每个页面都是inflate出一个新实例
//            GridView gridView = new GridView(getActivity());
//            gridView.setNumColumns(4);
//            gridView.setAdapter(new GridViewAdapter(getActivity(), mClassicationDatas, R.layout.item_calculation_classification, i, mClassificationSize));
//            mPagerList.add(gridView);
//
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                    switch (position) {
//                        case 0://祈福转运
//                            HashMap<String, String> map = new HashMap<>();
//                            map.put("name", mClassicationDatas.get((int) id).getName());
//                            GotoUtil.GoToActivityWithData(getActivity(), CliffordActivity.class, map);
//                            break;
//                        case 1: //传统命相
//                            GotoUtil.GoToActivity(getActivity(), TraditionFaceActivity.class);
//                            break;
//                        case 2://星座罗塔
//                            GotoUtil.GoToActivity(getActivity(), ConstellationActivity.class);
//                            break;
//                        case 3://求签问世
//                            GotoUtil.GoToActivity(getActivity(), DivinationActivity.class);
//                            break;
//                        case 4:// 婚姻爱恋
//                            GotoUtil.GoToActivity(getActivity(), MarriageLoveActivity.class);
//                            break;
//                        case 5://大师精品
//                            GotoUtil.GoToActivity(getActivity(), MoreMasterActivity.class);
//                            break;
//                        case 6://生活指南
//                            GotoUtil.GoToActivity(getActivity(), LifeGuideActivity.class);
//                            break;
//                        case 7://  起名测名
//                            GotoUtil.GoToActivity(getActivity(), NameTestNameActivity.class);
//                            break;
//                    }
//                }
//            });
//        }
//        //设置适配器
//        mVpClassification.setAdapter(new ViewPagerAdapter(mPagerList));
//    }

    /**
     * 利用ViewPager+GridView的方式实现左右滑动查看更多分类
     */
    private void setMeasureGridView(RecyclerView recyclerView, String iconName) {
//        mVpMeasure = (ViewPager) view.findViewById(R.id.vp_measure);
        //初始化数据源
        initMeasureDatas(iconName);
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mMeasureDatas.size() * 1.0 / mMeasureSize);
        List<View> mPagerList = new ArrayList<View>();
//        for (int i = 0; i < pageCount; i++) {
        //每个页面都是inflate出一个新实例
//        recyclerView = (RecyclerView) view.findViewById(R.id.hot_test_gridview);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.BOTH_SET));
        manager.setAutoMeasureEnabled(true);
        mHotTestAdapter = new HotTestAdapter(getContext(), mMeasureDatas, R.layout.item_calculation_measure);
        recyclerView.setAdapter(mHotTestAdapter);
        mHotTestAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    case 0://祈福许愿
                        GotoUtil.GoToActivity(getActivity(), PrayingStationActivity.class);
                        break;
                    case 1://紫薇斗数
                        GotoUtil.GoToActivity(getActivity(), ZiweiInputActivity.class);
                        break;
                    case 2://八字
                        GotoUtil.GoToActivity(getActivity(), BaziPaipanInputActivity.class);
                        break;
                    case 3://开运符灵
                        GotoUtil.GoToActivity(getActivity(), VirtueTransferActivity.class);
                        break;
                    case 4://姻缘配对
                        GotoUtil.GoToActivity(getActivity(), MarriageLoveActivity.class);
                        break;
                    case 5://塔罗占卜
                        GotoUtil.GoToActivity(getActivity(), TarotIntroActivity.class);
                        break;


                }
            }
        });
//        GridView gridView = new GridView(getActivity());
//        mMyGridView.setNumColumns(2);
//        mMyGridView.setAdapter(new GridViewAdapter(getActivity(), mMeasureDatas, R.layout.item_calculation_measure, 1, mMeasureSize));
//            mPagerList.add(mMyGridView);

//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(getActivity(), mMeasureDatas.get((int) id).getName(), Toast.LENGTH_SHORT).show();
//
//                    switch (position) {
//                        case 0://祈福许愿
//                            GotoUtil.GoToActivity(getActivity(), WishTreeActivity.class);
//                            break;
//                        case 1://紫薇斗数
//                            GotoUtil.GoToActivity(getActivity(), ZiweiInputActivity.class);
//                            break;
//                        case 2://开运符灵
//                            GotoUtil.GoToActivity(getActivity(), VirtueTransferActivity.class);
//                            break;
//                        case 3://塔罗占卜
//                            GotoUtil.GoToActivity(getActivity(), TarotIntroActivity.class);
//                            break;
//                        case 4://姻缘配对
//                            GotoUtil.GoToActivity(getActivity(),MarriageLoveActivity.class);
//                            break;
//                        case 5://八字
//                            GotoUtil.GoToActivity(getActivity(), BaziPaipanInputActivity.class);
//                            break;
//                    }
//                }
//            });
//        }
        //设置适配器
//        mVpMeasure.setAdapter(new ViewPagerAdapter(mPagerList));
    }

    /**
     * 初始化广告条
     */
    private void setAdvertise(final List<MingLiBean.AdsBean> advertise) {
        mBannerView = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
//        ViewGroup.LayoutParams params = mBannerView.getLayoutParams();
//        params.height = WindowUtil.dip2px(getActivity(), getResources().getDimensionPixelSize(R.dimen.px236dp));
//        mBannerView.setLayoutParams(params);
        mBannerView.setOnItemClickListener(this);
        mBannerView.startTurning(2000);

//        CalculationBannerBean bean1 = new CalculationBannerBean(1, "金九银十如何收尽五方之财？", "");
//        CalculationBannerBean bean2 = new CalculationBannerBean(2, "我结婚后会怎么样？婚后生活全透视！", "");
//        CalculationBannerBean bean3 = new CalculationBannerBean(3, "李嘉诚都在用的招财风水秘籍", "");
//        mBannerDatas.add(bean1);
//        mBannerDatas.add(bean2);
//        mBannerDatas.add(bean3);

        mBannerView.setPages(new CBViewHolderCreator<BannerAdapter>() {
            @Override
            public BannerAdapter createHolder() {
                return new BannerAdapter();
            }
        }, advertise);
        //设置指示点样式
        mBannerView.setPageIndicator(new int[]{R.drawable.banner_icon_normal_yuanqiu, R.drawable.banner_icon_selected_yuanqiu});
        //设置指示点对其方式
        mBannerView.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mBannerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GotoUtil.GoToWebViewActivity(getActivity(), advertise.get(position).getTitle(), advertise.get(position).getUrl());
            }
        });
    }


    @Override
    public void onItemClick(int position) {

    }

    /**
     * 初始化数据源，轮播图下面8个条目
     */
    private void initClassificationDatas() {
        mClassicationDatas = new ArrayList<CalculationGridViewBean>();
        for (int i = 0; i < mTitlesClassification.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
//            int imageId = getResources().getIdentifier("ic_category" + i, "drawable", getActivity().getPackageName());
            mClassicationDatas.add(new CalculationGridViewBean(mTitlesClassification[i], mTitleClassImage[i]));
        }
    }

    /**
     * 初始化数据源,获取图片资源
     */
    private void initMeasureDatas(String imageName) {
        mMeasureDatas = new ArrayList<CalculationGridViewBean>();
        for (int i = 0; i < mTitlesMeasure.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
//            int imageId = getResources().getIdentifier("mlcs_" + (i + 1), "drawable", getActivity().getPackageName());
            int imageId = getResources().getIdentifier(imageName + (i + 1), "drawable", getActivity().getPackageName());
            mMeasureDatas.add(new CalculationGridViewBean(mTitlesMeasure[i], imageId));
        }
    }
}
