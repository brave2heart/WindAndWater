package com.tongcheng.soothsay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.WelcomeAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.mine.SignInBean;
import com.tongcheng.soothsay.bean.splash.SplashBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.helper.JPushHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;

import com.tongcheng.soothsay.ui.activity.common.LaunchWebViewActivity;
import com.tongcheng.soothsay.utils.SpUtil;
import com.tongcheng.soothsay.widget.MyRadioGroup;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 启动界面
 *
 * @author 伍玉南
 */
public class WelcomeActivity extends BaseTitleActivity implements MyRadioGroup.OnPageChangeListener {

    private final int WAIT_TIME = 1000;

    @BindView(R.id.viewpager_welcome)
    ViewPager viewPager;
    @BindView(R.id.btn_welcome_access)
    TextView button;
    @BindView(R.id.layout_welcome)
    RelativeLayout layoutWelcome;
    @BindView(R.id.img_launch)
    ImageView imgLaunch;
    @BindView(R.id.radioGroup)
    MyRadioGroup radioGroup;
    @BindView(R.id.btn_skip)
    BootstrapButton btnSkip;

    private int time = 0;                //启动时间计时
    private List<View> views;
    private List<Integer> imgIds;            //欢迎页的imgId

    private Handler handler;
    private Runnable r;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_launch);

        //初始化第三方服务
        initThirdParties();

        boolean firstLaunch = SpUtil.getBoolean(getApplicationContext(), Constant.FIRST_OPEN, true);
        //判断是否是第一次启动APP
        if (firstLaunch) {
            SpUtil.putBoolean(getApplicationContext(), Constant.FIRST_OPEN, false);
            //第一次启动，显示欢迎4张图页面
            showWelcome();
        } else {
            //不是第一次启动，显示启动页
            showImg();
        }

        //签到
        signin();

        //滑动圆点指示器
        initListener();

    }

    //初始化第三方服务
    private void initThirdParties() {

        UMShareAPI.get(getApplication());
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;

        //设置新浪回调
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";

        //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wxdc89f6f6dcce9040", "99f486b5dce780d2c77a7fe7bf038a92");

        //新浪微博
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");

        //qq空间
        PlatformConfig.setQQZone("1105813767", "bp3oiYbA8cjISDYQ");

        //极光推送
        JPushHelper.init(getApplication());

//        //Bugly设置渠道名
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
//        strategy.setAppChannel(AppChannel());
//        CrashReport.initCrashReport(getApplicationContext(),strategy);
    }

//    private String AppChannel() {
//        String channel="";
//        ChannelInfo channelInfo= WalleChannelReader.getChannelInfo(this.getApplicationContext());
//        if (channelInfo != null) {
//            channel = channelInfo.getChannel();
//        }
//        return channel;
//    }

    /**
     * 签到
     */
    private void signin() {
        if (UserManager.getInstance().isLogin()) {
            AllApi.getInstance().signIn(UserManager.getInstance().getToken()).enqueue(new ApiCallBack<ApiResponseBean<SignInBean>>(new BaseCallBack<SignInBean>() {
                @Override
                public void onSuccess(SignInBean data) {
                    String jf = data.getJf(WelcomeActivity.this);
                    UserManager.getInstance().setUserJf(WelcomeActivity.this, jf);
                }

                @Override
                public void onError(String errorCode, String message) {
                }

            }));
        }
    }

    //滑动圆点指示器
    @Override
    public void initListener() {
        radioGroup.setOnPageChangeListener(this);
    }


    @OnClick({R.id.btn_welcome_access, R.id.btn_skip})
    public void onClick(View view) {
        Intent intent = new Intent(this, com.living.ui.MainActivity.class);
        switch (view.getId()) {

            case R.id.btn_welcome_access:   //马上学习
                startActivity(intent);
                finish();
                break;
            case R.id.btn_skip:             //跳过
                handler.removeCallbacks(r);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 显示欢迎页面（4张）
     */
    private void showWelcome() {
        btnSkip.setVisibility(View.GONE);
        views = new ArrayList<View>();
        imgIds = new ArrayList<Integer>();
//        imgIds.add(R.drawable.lead_in_pages_1);
//        imgIds.add(R.drawable.lead_in_pages_2);
//        imgIds.add(R.drawable.lead_in_pages_3);
//        imgIds.add(R.drawable.lead_in_pages_4);
        imgIds.add(R.drawable.lead_in_pages_11);
        imgIds.add(R.drawable.lead_in_pages_22);
        imgIds.add(R.drawable.lead_in_pages_33);
        imgIds.add(R.drawable.lead_in_pages_44);


        for (int i = 0; i < imgIds.size(); i++) {
            ImageView img = new ImageView(this);
            img.setScaleType(ScaleType.FIT_XY);
            views.add(img);
        }

        viewPager.setAdapter(new WelcomeAdapter(this, views, imgIds));
        radioGroup.setCount(imgIds.size(), viewPager);
    }

    /**
     * 显示启动页面   （改变你的一生——风生水图片）
     */
    private void showImg() {
        imgLaunch.setVisibility(View.VISIBLE);
        AllApi.getInstance().getSplash("").enqueue(new ApiCallBack<ApiResponseBean<SplashBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                final SplashBean bean = (SplashBean) data;
//                ImageHelper.getInstance().display(bean.getAdPicUrl(), imgLaunch);
                //加载图片,网络加载（改变你的一生——风生水图片）
                ImageHelper.getInstance().display(bean.getAdPicUrl(), R.drawable.launch_bg1, imgLaunch);
                //加载图片，本地加载
//                imgLaunch.setImageResource(R.drawable.home_ad);
                imgLaunch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String url = bean.getAdRedirectUrl();
                        Log.d("WelcomeActivity", url);
                        Intent intent = new Intent(WelcomeActivity.this, LaunchWebViewActivity.class);
                        intent.putExtra("web_title", bean.getTitle());
                        intent.putExtra("web_url", bean.getAdRedirectUrl());
                        startActivity(intent);
                        handler.removeCallbacks(r);
                        WelcomeActivity.this.finish();
                        return;
                    }
                });
            }

            @Override
            public void onError(String errorCode, String message) {
            }
        }));
        r = new Runnable() {

            @Override
            public void run() {
                time++;
                if (time == 3) {
                    Intent intent = new Intent(WelcomeActivity.this, com.living.ui.MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    handler.postDelayed(this, WAIT_TIME);
                }

            }
        };
        handler = new Handler();
        handler.post(r);
    }


    @Override
    public void onPageSelected(int arg0) {

        if (arg0 == views.size() - 1) {
            button.setVisibility(View.VISIBLE);
        } else {
            if (button.getVisibility() == View.INVISIBLE) {
                return;
            }
            button.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(r);
        }
    }
}
