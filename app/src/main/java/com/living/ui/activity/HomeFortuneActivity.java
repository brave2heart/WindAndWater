package com.living.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.living.adapter.home.GeRenFragmentPagerAdapter;
import com.living.bean.home.ConstellationBean;
import com.living.constant.home.HomeAPI;
import com.living.dagger.component.activity.DaggerHomeFortuneActivityComponet;
import com.living.dagger.module.activity.HomeFortuneActivityModule;
import com.living.presenter.activity.HomeFortuneActivityPresenter;
import com.living.ui.MainActivity;
import com.living.utils.ConstellationUtil;
import com.living.utils.NumAnimUtil;
import com.living.utils.ScreenUtil;
import com.living.view.JudgeNestedScrollView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.huangli.FortuneBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.widget.CircularProgress;
import com.tongcheng.soothsay.widget.RoundImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.living.utils.ConstellationUtil.getInt;

public class HomeFortuneActivity extends BaseTitleActivity {
    //顶部标题栏
    @BindView(R.id.headArea)
    View headView;
    @BindView(R.id.headBackButton)
    ImageButton btnBack;
    @BindView(R.id.headTitle)
    TextView tvTitle;
    //今日运势，明日运势，本周运势，本月运势
    @BindView(R.id.geren_head_image)
    ImageView mGerenHeadImage;
    @BindView(R.id.geren_quanquan)
    LinearLayout mGerenQuanquan;
    @BindView(R.id.geren_four_grade)
    LinearLayout mGerenFourGrade;
    @BindView(R.id.geren_ll_xingyun)
    RelativeLayout mGerenLlXingyun;
    @BindView(R.id.geren_ll_yiji)
    RelativeLayout mGerenLlYiji;

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.tv_fortune_color)
    TextView tvColor;                   //幸运色
    @BindView(R.id.tv_fortune_yi)
    TextView tvYi;                      //宜
    @BindView(R.id.tv_fortune_ji)
    TextView tvJi;                      //忌
    @BindView(R.id.img_fortune_icon)
    RoundImageView imgIcon;             //头像
    @BindView(R.id.img_exmple_icon)
    ImageView imgExmple;                //头像示例小图标
    @BindView(R.id.tv_fortune_birthday)
    TextView tvBirthday;                //生日
    @BindView(R.id.tv_fortune_name)
    TextView tvName;                    //用户名字

    @BindView(R.id.ap_yunshi_all)
    TextView apAll;
    @BindView(R.id.ap_yunshi_money)
    TextView apMoney;
    @BindView(R.id.ap_yunshi_love)
    TextView apLove;
    @BindView(R.id.ap_yunshi_work)
    TextView apWork;

    @BindView(R.id.homeforcunt_forcunt_list)
    LinearLayout mHomeforcuntForcuntList;

    @BindView(R.id.homeforcunt_forcunt_add)
    LinearLayout mHomeforcuntForcuntAdd;

    @BindView(R.id.homeforcunt_cp_yunshi_score)
    CircularProgress cProgress;

    @BindView(R.id.homeforcunt_tv_add)
    TextView mHomeforcuntTvAdd;
    @BindView(R.id.homeforcunt_tv_geren_constellation)
    TextView mHomeforcuntTvGerenConstellation;  //顶部星座名
    @BindView(R.id.head_parent)
    LinearLayout mHeadParent;
    @BindView(R.id.tablayout_top)
    TabLayout mTablayoutTop;
    @BindView(R.id.homeforcunt_jn_scrollview)
    JudgeNestedScrollView mHomeforcuntJnScrollview;

    @BindView(R.id.homeforcunt_one_content)
    TextView mHomeforcuntOneContent;


    private FortuneBean fortuneBean;

    @Inject
    public HomeFortuneActivityPresenter homeFortuneActivityPresenter;
    private boolean openForcunt = false;
    private String mConstellationName = "处女座";
    private int mToolbarPositionY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homefortune);
        ButterKnife.bind(this);


        initView();  //设置标题栏
        initTabLayoutViewPager();  //设置TabLayout+ViewPager
        initData();  //设置日期
        initConstellationData(); //设置个人运势得分
        initScroll();

    }

    private void initScroll() {
        mHeadParent.post(new Runnable() {
            @Override
            public void run() {
                mToolbarPositionY = mHeadParent.getHeight();
                ViewGroup.LayoutParams params = mViewpager.getLayoutParams();
                int screenHeightPx = ScreenUtil.getScreenHeightPx(getApplicationContext());
                params.height = screenHeightPx - mToolbarPositionY - mTablayout.getHeight() + 1;
                mViewpager.setLayoutParams(params);
            }
        });

        mHomeforcuntJnScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                mTablayout.getLocationOnScreen(location);
                int xPosition = location[0];
                int yPosition = location[1];
                if (yPosition < mToolbarPositionY) {
                    mTablayoutTop.setVisibility(View.VISIBLE);
                    mHomeforcuntJnScrollview.setNeedScroll(false);

                } else {
                    mTablayoutTop.setVisibility(View.GONE);
                    mHomeforcuntJnScrollview.setNeedScroll(true);
                }
            }
        });
    }

    // 如果有多个id，使用（{R.id.homeforcunt_cp_yunshi_score，R.id.homeforcunt_cp_yunshi_score}）
    @OnClick(R.id.homeforcunt_cp_yunshi_score)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.homeforcunt_cp_yunshi_score:
                if (openForcunt == false) {
                    mHomeforcuntForcuntAdd.setVisibility(View.VISIBLE);
                    mHomeforcuntTvAdd.setText("收起运势");
                    openForcunt = true;
                } else {
                    mHomeforcuntForcuntAdd.setVisibility(View.GONE);
                    mHomeforcuntTvAdd.setText("    点我\n提升运势");
                    openForcunt = false;
                }

                break;
            default:
                break;
        }
    }


    @Override
    public void initView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.title_fortune));
        setActionBarTopPadding(headView);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFortuneActivity.this, MainActivity.class));
            }
        });

    }

    private void initTabLayoutViewPager() {
        //将viewPager和Fragmnet绑定
        GeRenFragmentPagerAdapter fragmentPagerAdapter = new GeRenFragmentPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(fragmentPagerAdapter);
        //将TabLayout和ViewPager绑定
//        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayoutTop.setupWithViewPager(mViewpager);

    }


    @Override
    public void initData() {

        fortuneBean = (FortuneBean) getIntent().getSerializableExtra(OldAlmanacConstant.INTENT_DATE);

        String month = fortuneBean.getCurrMon() >= 10 ? fortuneBean.getCurrMon() + "" : "0" + fortuneBean.getCurrMon();
        int imgId = getResources().getIdentifier("fortune_bg_" + month, "drawable", getPackageName());
        // imgBg.setImageResource(imgId); 旧版个人运势背景图

        //tvDate.setText(bean.getDate());  //日期
        // tvOldDate.setText(bean.getOldDate());  农历
        // tvGanzhi.setText(bean.getGanzhi());  干支
        tvColor.setText(fortuneBean.getLuckyColor()); //幸运色
        tvYi.setText(fortuneBean.getYi());
        tvJi.setText(fortuneBean.getJi());
//        tvScore.setText(bean.getScore());
        //tvFortune.setText(bean.getFortune()); 今日运势

//        mHomeforcuntOneContent.setText(fortuneBean.getFortune());

        String name = fortuneBean.getUserName();
        String day = fortuneBean.getBirthday();
        if (UserManager.getInstance().isLogin() && !TextUtils.isEmpty(UserManager.getInstance().getUserName(this))) {
            name = UserManager.getInstance().getUserName(this);
            day = UserManager.getInstance().getUserDate(this);
            String imgUrl = UserManager.getInstance().getUserPhotoUrl(this);
            ImageHelper.getInstance().display(imgUrl, imgIcon);
            imgExmple.setVisibility(View.INVISIBLE);

            if (!TextUtils.isEmpty(day)) {
                String temp[] = day.split("\\.");
                day = temp[0] + "年" + temp[1] + "月" + temp[2] + "日";

                mConstellationName = ConstellationUtil.getConstellationName(temp[1], temp[2]);
                mHomeforcuntTvGerenConstellation.setText("(" + mConstellationName + ")");
                tvBirthday.setText(day);
//                //设置星座图片
//                mConstellation_image.setImageResource(ConstellationUtil.getConstellationIcon(mConstellation));
//                mConstellation_name.setText(mConstellation); //设置底部星座名
//
//
//
//                Bundle bundle = new Bundle();
//                bundle.putString("ConstellationName", constellationName);
//                todayFragment.setArguments(bundle);


            }

        }
        tvName.setText(name);

    }

    public String getConstellationName() {

        return mConstellationName;
    }

    private void initConstellationData() {
        getBaseLoadingView().showLoading();
        DaggerHomeFortuneActivityComponet.builder().homeFortuneActivityModule(new HomeFortuneActivityModule(this)).build().in(this);

        homeFortuneActivityPresenter.getConstellationDate(mConstellationName, HomeAPI.ConstellationType[0], HomeAPI.Constellationkey);
    }

    public void setData(ConstellationBean.TodayConstellationBean todayConstellationBean) {
        cProgress.setValue(getInt(todayConstellationBean.getAll()));
        NumAnimUtil.startAnim(apAll, getInt(todayConstellationBean.getHealth()));  //将数字滚动从0开始显示至具体数值大小
        NumAnimUtil.startAnim(apWork, getInt(todayConstellationBean.getWork()));
        NumAnimUtil.startAnim(apMoney, getInt(todayConstellationBean.getMoney()));
        NumAnimUtil.startAnim(apLove, getInt(todayConstellationBean.getLove()));
        mHomeforcuntOneContent.setText(todayConstellationBean.getSummary());  //每日一言
    }


}