package com.tongcheng.soothsay.ui.fragment.classroom;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.ClassRoomBannerAdapter;
import com.tongcheng.soothsay.adapter.classroom.ClassRoomRecycleViewAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.bazi.ClassRoomGridViewBeans;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.ui.activity.classroom.MoreMasterActivity;
import com.tongcheng.soothsay.ui.activity.classroom.NewsActivity;
import com.tongcheng.soothsay.ui.activity.classroom.ShoppingHomeActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.RecyclerSpace;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 宋家任 on 2016/10/25.
 * 大师讲堂
 */

public class ClassRoomFragment extends BaseTitleFragment implements View.OnClickListener {
    @BindView(R.id.cb_classroom_banner)
    ConvenientBanner mBannerView;
    @BindView(R.id.rv_classroom)
    RecyclerView rvClassroom;
    @BindView(R.id.rl_classroom_specia)
    RelativeLayout rlClassroomShop;
    @BindView(R.id.rl_classroom_newmsg)
    RelativeLayout rlClassroomNewmsg;
    @BindView(R.id.iv_classroom_star)
    ImageView ivClassroomStar;
    @BindView(R.id.iv_classroom_fengshui)
    ImageView ivClassroomWork;
    @BindView(R.id.iv_classroom_shouxian)
    ImageView ivClassroomSex;
    @BindView(R.id.iv_classroom_shengxiao)
    ImageView ivClassroomMoney;
    @BindView(R.id.iv_classroom_bazi)
    ImageView ivClassroomWater;
    @BindView(R.id.rl_classroom_circle)
    RelativeLayout rlClassroomCircle;
    @BindView(R.id.rl_classroom_master)
    RelativeLayout rlClassroomMaster;
    @BindView(R.id.ll_classroom_qs)
    LinearLayout llClassroomQs;
    @BindView(R.id.tv_classroom_gongxiu)
    TextView tvClassroomGongxiu;
    @BindView(R.id.iv_item_circletlist_facepic)
    ImageView ivClassroomQuan1Title;
    @BindView(R.id.tv_item_circletlist_name)
    TextView tvClassroomQuan1Title;
    @BindView(R.id.tv_item_circlelist_count)
    TextView tvClassroomQuan1Huati;
    @BindView(R.id.rl_classroom_quan1)
    RelativeLayout rlClassroomQuan1;
    @BindView(R.id.iv_classroom_quan2_title)
    ImageView ivClassroomQuan2Title;
    @BindView(R.id.tv_classroom_quan2_title)
    TextView tvClassroomQuan2Title;
    @BindView(R.id.tv_classroom_quan2_huati)
    TextView tvClassroomQuan2Huati;
    @BindView(R.id.rl_classroom_quan2)
    RelativeLayout rlClassroomQuan2;
    @BindView(R.id.iv_classroom_dasi1)
    ImageView ivClassroomDasi1;
    @BindView(R.id.tv_classroom_das1_title)
    TextView tvClassroomDas1Title;
    @BindView(R.id.tv_classroom_das1_des)
    TextView tvClassroomDas1Des;
    @BindView(R.id.iv_classroom_dasi2_des)
    ImageView ivClassroomDasi2Des;
    @BindView(R.id.tv_classroom_das2_title)
    TextView tvClassroomDas2Title;
    @BindView(R.id.tv_classroom_dasi2_des)
    TextView tvClassroomDasi2Des;
    @BindView(R.id.ll_classroom_qs1)
    LinearLayout llClassroomQs1;
    @BindView(R.id.ll_classroom_qs2)
    LinearLayout llClassroomQs2;
    @BindView(R.id.entry_recyclerview)
    RecyclerView mEntryRecyclerview;
    Unbinder unbinder;

    private ClassRoomRecycleViewAdapter mRecycleAdapter;
    private RecyclerSpace decor;

    private List<ClassRoomGridViewBeans> mClassRoomGridViewBeans = new ArrayList<>();

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_classroom, container, false);

        return view;
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle(getResources().getString(R.string.study));
        getBaseLoadingView().showLoading();
//        EventBusUtil.register(this);
        decor = new RecyclerSpace(10, Color.WHITE);
        getData();
        initEntryData();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mEntryRecyclerview.setLayoutManager(layoutManager);
        EntryAdapter entryAdapter = new EntryAdapter(mClassRoomGridViewBeans);
        mEntryRecyclerview.setAdapter(entryAdapter);


    }

    private void getData() {
        AllApi.getInstance().getClassRoomDatas("").enqueue(new ApiCallBack<ApiResponseBean<ClassRoomBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                ClassRoomBean bean = (ClassRoomBean) data;
                setAdvertise(bean.getAds());
                initRecycleView(bean.getStores());
                initGongxiu(bean.getCircles());
                initDasi(bean.getDs());
            }


            @Override
            public void onError(String errorCode, String message) {
                ToastUtil.showShort(ClassRoomFragment.this.getActivity(), message);
                getBaseLoadingView().hideLoading();
                if (ApiCallBack.NET_ERROR.equals(errorCode)) {
                    getBaseEmptyView().showNetWorkView(R.drawable.nonetwork, R.string.showNeterr, R.string.touch_again, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ClickUtil.isFastClick()) {
                                return;
                            }
                            initData();
                            getBaseEmptyView().hideEmptyView();
                        }
                    });
                } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {//服务器系统错误
                    getBaseEmptyView().showEmptyView(R.drawable.nonetwork, R.string.server_error, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ClickUtil.isFastClick()) {
                                return;
                            }
                            initData();
                            getBaseEmptyView().hideEmptyView();
                        }
                    });
                } else {
                    getBaseEmptyView().showEmptyContent();
                }
            }
        }));
    }

//    网络监听
//    @Subscribe
//    public void onEvent(EventNetWorkChangeBean bean) {
//        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED&&mRecycleAdapter.getDatas().size()!=0) {
//                if (flag) {
//                    initData();
//                    getBaseEmptyView().hideEmptyView();
//                }
//
//        }
//    }

    /**
     * 初始化广告条
     */
    private void setAdvertise(final List<ClassRoomBean.AdsBean> ads) {
        mBannerView.startTurning(2000);
        mBannerView.setPages(new CBViewHolderCreator<ClassRoomBannerAdapter>() {
            @Override
            public ClassRoomBannerAdapter createHolder() {
                return new ClassRoomBannerAdapter();
            }
        }, ads);
        //设置指示点样式
        mBannerView.setPageIndicator(new int[]{R.drawable.banner_icon_normal_yuanqiu, R.drawable.banner_icon_selected_yuanqiu});
        //设置指示点对其方式
        mBannerView.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mBannerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), ads.get(position).getTitle(), ads.get(position).getUrl());
            }
        });
    }

    /*
    * 4个条目，精选推荐，热门好课，显示特价，好评爆款 ，使用RecyclerView GlideLayout
    * */


    private boolean flag;

    /**
     * 初始化recycleview
     */
    private void initRecycleView(final List<ClassRoomBean.StoresBean> stores) {
        mRecycleAdapter = new ClassRoomRecycleViewAdapter(getActivity(), stores,
                R.layout.item_classroom_recycleview);
//        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

//        rvClassroom.setLayoutManager(manager);

        rvClassroom.setLayoutManager(linearLayoutManager);
        rvClassroom.setAdapter(mRecycleAdapter);
        if (!flag) {
            rvClassroom.addItemDecoration(decor);
            flag = true;
        }
        //item点击监听
        mRecycleAdapter.setOnItemClickListener(new AbsBaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int posotion) {
                String merchandiseId = stores.get(posotion).getMerchandiseId();//商品id
                Intent intent = new Intent(ClassRoomFragment.this.getActivity(), GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", stores.get(posotion).getRedirectUrl());
                startActivity(intent);
            }
        });
    }

    /**
     * 共修圈子
     */
    private void initGongxiu(List<ClassRoomBean.CirclesBean> circles) {
       // ImageHelper.getInstance().display(circles.get(0).getFacePic(), ivClassroomQuan1Title); //加图图片1
        tvClassroomQuan1Title.setText(circles.get(0).getName());
        tvClassroomQuan1Huati.setText(circles.get(0).getCount()+"个话题");
        rlClassroomQuan1.setTag(circles.get(0));
        if (circles.size() == 2) {
           // ImageHelper.getInstance().display(circles.get(1).getFacePic(), ivClassroomQuan2Title); //加载图片2
            tvClassroomQuan2Title.setText(circles.get(1).getName());
            tvClassroomQuan2Huati.setText( circles.get(1).getCount()+"个话题");
            rlClassroomQuan2.setTag(circles.get(1));
        }


    }

    private String dsUrl1, dsUrl2;

    /**
     * 初始化大师
     *
     * @param ds
     */
    private void initDasi(List<ClassRoomBean.DsBean> ds) {
//        ImageHelper.getInstance().display(ds.get(0).getTouXiang(), ivClassroomDasi1);
        ImageHelper.getInstance().display(ds.get(0).getTouXiang(), R.drawable.place_holder_banner_1, ivClassroomDasi1);
        tvClassroomDas1Title.setText(ds.get(0).getName() + "," + ds.get(0).getChengHao());
        tvClassroomDas1Des.setText(ds.get(0).getShanChang());
        dsUrl1 = ds.get(0).getUrl();
        if (ds.size() == 2) {
//            ImageHelper.getInstance().display(ds.get(1).getTouXiang(), ivClassroomDasi2Des);
            ImageHelper.getInstance().display(ds.get(1).getTouXiang(), R.drawable.place_holder_banner_1, ivClassroomDasi2Des);
            tvClassroomDas2Title.setText(ds.get(1).getName() + "," + ds.get(1).getChengHao());
            tvClassroomDasi2Des.setText(ds.get(1).getShanChang());
            dsUrl2 = ds.get(1).getUrl();
        } else {
            ivClassroomDasi2Des.setVisibility(View.GONE);
            tvClassroomDas2Title.setVisibility(View.GONE);
            tvClassroomDasi2Des.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.rl_classroom_specia, R.id.rl_classroom_newmsg,
            R.id.iv_classroom_star, R.id.iv_classroom_fengshui,
            R.id.iv_classroom_shouxian, R.id.iv_classroom_shengxiao,
            R.id.iv_classroom_bazi, R.id.rl_classroom_circle,
            R.id.rl_classroom_quan1, R.id.rl_classroom_quan2,
            R.id.rl_classroom_master, R.id.ll_classroom_qs1,
            R.id.ll_classroom_qs2})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.rl_classroom_specia://商城更多
                GotoUtil.GoToActivity(getActivity(), ShoppingHomeActivity.class);
                break;
            case R.id.rl_classroom_newmsg://资讯更多
                gotoNewsActivity(0);
                break;
            case R.id.iv_classroom_star://星座
                gotoNewsActivity(3);
                break;
            case R.id.iv_classroom_fengshui://风水
                gotoNewsActivity(2);
                break;
            case R.id.iv_classroom_shouxian://手相
                gotoNewsActivity(4);
                break;
            case R.id.iv_classroom_shengxiao://生肖
                gotoNewsActivity(1);
                break;
            case R.id.iv_classroom_bazi://八字
                gotoNewsActivity(5);
                break;
            case R.id.rl_classroom_circle://共修圈
                GotoUtil.GoToActivity(getActivity(), CircleListActivity.class);
                break;
            case R.id.rl_classroom_quan1://话题1
                ClassRoomBean.CirclesBean tag1 = (ClassRoomBean.CirclesBean) rlClassroomQuan1.getTag();
                if (tag1 != null) {
                    String url = tag1.getRedirectUrl();
                    if (UserManager.getInstance().isLogin()) {
                        url = url + UserManager.getInstance().getToken();
                    }
                    GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), tag1.getName(), url);
                }
                break;
            case R.id.rl_classroom_quan2://话题2
                ClassRoomBean.CirclesBean tag2 = (ClassRoomBean.CirclesBean) rlClassroomQuan2.getTag();
                if (tag2 != null) {
                    String url = tag2.getRedirectUrl();
                    if (UserManager.getInstance().isLogin()) {
                        url = url + UserManager.getInstance().getToken();
                    }
                    GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), tag2.getName(), url);
                }

                break;
            case R.id.rl_classroom_master://更多大师
                GotoUtil.GoToActivity(ClassRoomFragment.this.getActivity(), MoreMasterActivity.class);
                break;
            case R.id.ll_classroom_qs1://某个大师
                if (UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), "大师详情", dsUrl1 + UserManager.getInstance().getToken());
                } else {
                    GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), "大师详情", dsUrl1);
                }
                break;
            case R.id.ll_classroom_qs2://某个大师
                if (UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), "大师详情", dsUrl2 + UserManager.getInstance().getToken());

                } else {
                    GotoUtil.GoToWebViewActivity(ClassRoomFragment.this.getActivity(), "大师详情", dsUrl2);
                }
                break;
        }
    }

    public void gotoNewsActivity(int newsid) {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        intent.putExtra("NewsID", newsid);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


//    private String[] mTitlesClassification = {"精选推荐", "热门好课","限时特价", "好评爆款"};
//    private int[] mTitleClassImage = {R.drawable.mlcs_icon_qifuzhuanyun,
//            R.drawable.mlcs_icon_chuantongsuanming,
//            R.drawable.mlcs_icon_xingzuotaluo,
//            R.drawable.mlcs_icon_qiuqianwenshi};

    private void initEntryData() {
//        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.mlcs_icon_qifuzhuanyun, "精选推荐"));
//        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.mlcs_icon_chuantongsuanming, "热门好课"));
//        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.mlcs_icon_xingzuotaluo, "限时特价"));
//        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.mlcs_icon_qiuqianwenshi, "好评爆款"));
        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.xuetang_qingxuan, "精选推荐"));
        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.xuetang_remen, "热门好课"));
        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.xuetang_xianshi, "限时特价"));
        mClassRoomGridViewBeans.add(new ClassRoomGridViewBeans(R.drawable.xuetang_haoping, "好评爆款"));

    }


//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBusUtil.unregister(this);
//    }
}
