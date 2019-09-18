package com.tongcheng.soothsay.ui.activity.huangli.other;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FortuneActivity3 extends BaseTitleActivity  {


    //fragment的适配器
    private MainTabFragmentAdapter mainTabFragmentAdapter;

    //viewpager
    private ViewPager mViewPager;

    //AppBarLayout
    private AppBarLayout mAppBarLayout;

    //顶部HeaderLayout
    private LinearLayout headerLayout;

    private SmartTabLayout mTabs;

    //是否隐藏了头部
    private boolean isHideHeaderLayout = false;

    //顶部标题栏
    @BindView(R.id.headArea)
    View headView;
    @BindView(R.id.headBackButton)
    ImageButton btnBack;
    @BindView(R.id.headTitle)
    TextView tvTitle;
    private int mHeaderheight;


//    Tab标题文字
    private String[] titles=new String[]{
            "今日运势", "明日运势",
            "本周运势", "本月运势"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune3);
        ButterKnife.bind(this);
        initView();
        initTop();

    }

    private void initTop() {

        mTabs = (SmartTabLayout) findViewById(R.id.tabs);
        mainTabFragmentAdapter = new MainTabFragmentAdapter(getSupportFragmentManager(), this,titles);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mainTabFragmentAdapter);
        mTabs.setViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(mainTabFragmentAdapter.getCount());
        headerLayout = (LinearLayout) findViewById(R.id.ll_header_layout);
        initAppBarLayout();
    }


    @Override
    public void initView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.title_fortune));
        btnBack.setVisibility(View.VISIBLE);
        setActionBarTopPadding(headView);


    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            mHeaderheight = headerLayout.getMeasuredHeight();
//        }
//    }

    // 初始化AppBarLayout
    private void initAppBarLayout() {
        LayoutTransition mTransition = new LayoutTransition();
        /**
         * 添加View时过渡动画效果
         */
        ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, "translationY", 0, 1.f).
                setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, addAnimator);

   //         final int headerHeight = mHeaderheight;
        //header layout height
     final int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_height1);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mAppBarLayout.setLayoutTransition(mTransition);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                verticalOffset = Math.abs(verticalOffset);
                if (verticalOffset >= headerHeight) {
                    isHideHeaderLayout = true;
                    //当偏移量超过顶部layout的高度时，我们认为他已经完全移动出屏幕了
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
                            mParams.setScrollFlags(0);
                            headerLayout.setLayoutParams(mParams);
                            headerLayout.setVisibility(View.GONE);
                        }
                    }, 100);
                }
            }
        });
    }

    //返回键设置处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //监听返回键
            if (isHideHeaderLayout) {
                isHideHeaderLayout = false;
                ((MainTabFragment) mainTabFragmentAdapter.getFragments().get(0)).getRvList().scrollToPosition(0);
                headerLayout.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
                        mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                                AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                        headerLayout.setLayoutParams(mParams);
                    }
                }, 300);


            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}


