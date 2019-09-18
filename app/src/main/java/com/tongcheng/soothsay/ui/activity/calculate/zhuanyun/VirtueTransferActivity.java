package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.NewDadeListViewAdapter;
import com.tongcheng.soothsay.base.BaseActivity;
import com.tongcheng.soothsay.bean.calculation.LingFuBean;
import com.tongcheng.soothsay.bean.event.ChangeNameBean;
import com.tongcheng.soothsay.bean.event.RefreshLingFuBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DialogUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 宋家任 on 2016/11/9.
 * 大德符-运符运列表
 */

public class VirtueTransferActivity extends BaseActivity {


    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.rl_4)
    RelativeLayout rl4;
    @BindView(R.id.rl_5)
    RelativeLayout rl5;
    @BindView(R.id.rl_6)
    RelativeLayout rl6;
    @BindView(R.id.rl_7)
    RelativeLayout rl7;
    @BindView(R.id.rl_8)
    RelativeLayout rl8;
    @BindView(R.id.rl_9)
    RelativeLayout rl9;
    @BindView(R.id.lv_virtue_transfer)
    ListView mListview;
    @BindView(R.id.tv_back)
    TextView tvBack;

    private Animation baidongAnimation;
    private Animation inAnimation;
    private Animation outAnimation;
    private NewDadeListViewAdapter mAdapter;
    private List<RelativeLayout> mRlDatas;

    private String[] fuDesc = new String[]{};
    private String[] fuGongxiao = new String[]{};
    List<List<LingFuBean>> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtue_transfer);
        ButterKnife.bind(this);
        this.baidongAnimation = AnimationUtils.loadAnimation(this, R.anim.dade_top_anim);
        this.inAnimation = AnimationUtils.loadAnimation(this, R.anim.dade_bottom_in);
        this.outAnimation = AnimationUtils.loadAnimation(this, R.anim.dade_bottom_out);


        initData();
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        EventBusUtil.register(this);
    }


    @OnClick({R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.rl_4, R.id.rl_5,
            R.id.rl_6, R.id.rl_7, R.id.rl_8, R.id.rl_9, R.id.tv_back})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.rl_1://金钱财运
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_jinqiancaiyun_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_jinqiancaiyun_gongxiao);
                setAnimationListener("1", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_2://平安护身
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_pinganhushen_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_pinganhushen_gongxiao);
                setAnimationListener("2", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_3://文昌官运
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_wenchangguanyun_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_wenchangguanyun_gongxiao);
                setAnimationListener("3", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_4://桃花姻缘
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_taohuayinyuan_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_taohuayinyuan_gongxiao);
                setAnimationListener("4", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_5://必火赈灾
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
//                getFuInfo("5");
//                fuName = getResources().getStringArray(R.array.fy_dade_lingfu_bihuozhensha_names);
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_bihuozhensha_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_bihuozhensha_gongxiao);
                setAnimationListener("5", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_6://送子添丁
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_songzitianding_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_songzitianding_gongxiao);
                setAnimationListener("6", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_7://太岁符
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_taisui_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_taisui_gongxiao);
                setAnimationListener("7", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_8://安家赈灾
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_anjiazhenzhai_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_anjiazhenzhai_gongxiao);
                setAnimationListener("8", datas, fuDesc, fuGongxiao);
                break;
            case R.id.rl_9://护身符
                mListview.startAnimation(outAnimation);
                startAnim(view);
                datas.clear();
                fuDesc = getResources().getStringArray(R.array.fy_lingfu_hushen_desc_items);
                fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_hushen_gongxiao);
                setAnimationListener("9", datas, fuDesc, fuGongxiao);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }


    private void getFuInfo(final String id) {
        AllApi.getInstance().getLingfu(UserManager.getInstance().getToken(), id).
                enqueue(new ApiCallBack<ApiResponseBean<List<List<LingFuBean>>>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        datas = (List<List<LingFuBean>>) data;
//                            mAdapter.notifyChangeData(datas);
                        mAdapter = new NewDadeListViewAdapter(VirtueTransferActivity.this, id, datas, R.layout.item_dade_listview,
                                fuDesc, fuGongxiao, VirtueTransferActivity.this);
                        mListview.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        ErrorCodeUtil.showHaveTokenError(VirtueTransferActivity.this, errorCode);
                    }
                }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constant.RequestCode.DADE_LOGIN == requestCode && Constant.ResultCode.LOGIN == resultCode) {
            initData();
        }
    }

    @Override
    public void initData() {
        mRlDatas = new ArrayList<>();
        mRlDatas.add(rl1);
        mRlDatas.add(rl2);
        mRlDatas.add(rl3);
        mRlDatas.add(rl4);
        mRlDatas.add(rl5);
        mRlDatas.add(rl6);
        mRlDatas.add(rl7);
        mRlDatas.add(rl8);
        mRlDatas.add(rl9);

        fuDesc = getResources().getStringArray(R.array.fy_lingfu_jinqiancaiyun_desc_items);
        fuGongxiao = getResources().getStringArray(R.array.fy_lingfu_jinqiancaiyun_gongxiao);
        startAnim(rl1);
        if (UserManager.getInstance().isLogin()) {
            getFuInfo("1");
            mListview.startAnimation(inAnimation);
            setAnimationListener("1", datas, fuDesc, fuGongxiao);
        } else {//没登录弹出登录对话框
            new CusDialogUtil(this).showCusDialog(R.string.txt_dade_login, Constant.RequestCode.DADE_LOGIN);
        }
    }


    private void startAnim(View view) {
        clearAllAnim();
        view.setSelected(true);
        view.startAnimation(baidongAnimation);

    }

    private void clearAllAnim() {
        for (RelativeLayout view : mRlDatas)
            if (view.isSelected())
                view.clearAnimation();
    }


    private void setAnimationListener(final String id, final List<List<LingFuBean>> datas, final String[] desc, final String[] gx) {
        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getFuInfo(id);
                mListview.startAnimation(inAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 购买灵符后刷新
     *
     * @param bean
     */
    @Subscribe
    public void onEvent(RefreshLingFuBean bean) {
        getFuInfo(bean.id);
        mListview.startAnimation(inAnimation);
        setAnimationListener(bean.id, datas, fuDesc, fuGongxiao);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
        EventBusUtil.unregister(this);
    }

/**
 * 下面是数据写本地时的情形
 */
//    private void setAnimationListener(final String[] name, final String[] desc, final String[] gx) {
//        outAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                if (0 == name.length % 3)//正好
//                    for (int i = 0; i < name.length - 1; i = i + 3) {
//                        DadeBean bean = new DadeBean(name[i], name[i + 1], name[i + 2],
//                                desc[i], desc[i + 1], desc[i + 2], gx[i], gx[i + 1], gx[i + 2]);
//                        mDatas.add(bean);
//                    }
//                else if (1 == name.length % 3) {//多了一个
//                    for (int i = 0; i < name.length - 3; i = i + 3) {
//                        DadeBean bean = new DadeBean(name[i], name[i + 1], name[i + 2],
//                                desc[i], desc[i + 1], desc[i + 2], gx[i], gx[i + 1], gx[i + 2]);
//                        mDatas.add(bean);
//                    }
//                    DadeBean bean = new DadeBean(name[name.length - 1], "", "",
//                            desc[desc.length - 1], "", "", gx[gx.length - 1], "", "");
//                    mDatas.add(bean);
//                } else if (2 == name.length % 3) {//多了两个
//                    for (int i = 0; i < name.length - 4; i = i + 3) {
//                        DadeBean bean = new DadeBean(name[i], name[i + 1], name[i + 2],
//                                desc[i], desc[i + 1], desc[i + 2], gx[i], gx[i + 1], gx[i + 2]);
//                        mDatas.add(bean);
//                    }
//                    DadeBean bean = new DadeBean(name[name.length - 2], name[name.length - 1], "",
//                            desc[desc.length - 2], desc[desc.length - 1], "", gx[gx.length - 2], gx[gx.length - 1], "");
//                    mDatas.add(bean);
//                }
//
//                mAdapter = new DadeListViewAdapter(VirtueTransferActivity.this, mDatas, R.layout.item_dade_listview);
//                mListview.setAdapter(mAdapter);
//                mListview.startAnimation(inAnimation);
//                setOnQingFuClick();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }
}
