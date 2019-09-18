package com.living.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.living.ui.fragment.ClassRoomFragment;
import com.living.ui.fragment.HomeFragment;
import com.living.ui.fragment.MingLiFragment;
import com.living.ui.fragment.ShopFragment;
import com.living.ui.fragment.ShoppingHomeFragment;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.Mydemo.FragmentTabHost;
import com.tongcheng.soothsay.Mydemo.Tabs;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.mine.RyTokenBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.JPushHelper;
import com.tongcheng.soothsay.helper.RongImHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.receiver.NetworkConnectChangedReceiver;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.fragment.mine.MineFragment;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ReceiverUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import io.rong.imlib.RongIMClient;

public class MainActivity extends BaseTitleActivity {

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabhost;


    private NetworkConnectChangedReceiver networkReceiver;
    private long exitTime = 0;

    private List<Tabs> mTabsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        super.setContentView(R.layout.activity_main);

        //底部菜单栏+fragment实现
        initTabFragment();


        EventBusUtil.register(this);

        //连接融云
        connectRongIm();


        //注册网络广播接听
        networkReceiver = new NetworkConnectChangedReceiver();
        ReceiverUtil.receiverNetwork(this, networkReceiver);

    }


    private void initTabFragment() {
        //设置FragmentManager以及指定用于装载Fragment的布局容器
        mTabhost.setup(this, getSupportFragmentManager(), R.id.fl_fragment);
        //新建5个分页，并且添加到ArrayList中，便于管理
        //首页
        addToTabsList(HomeFragment.class, R.drawable.selector_btn_home, R.string.app_home);
        //命理
        addToTabsList(MingLiFragment.class, R.drawable.selector_btn_life, R.string.life);
        //学堂
        addToTabsList(ClassRoomFragment.class, R.drawable.selector_btn_study, R.string.study);
        //商城
        addToTabsList(ShoppingHomeFragment.class, R.drawable.selector_btn_shop, R.string.shop);
        //我的
        addToTabsList(MineFragment.class, R.drawable.selector_btn_mine, R.string.me);


        for (Tabs tab : mTabsList) {
            //新建5个TabSpec,并且设置好它的Indicator
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getIconName()));
            View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null);
            ImageView iv_tab_icon = view.findViewById(R.id.icon_tab);
            TextView tv_tab_iconName = view.findViewById(R.id.txt_indicator);

            iv_tab_icon.setImageResource(tab.getIcon());
            tv_tab_iconName.setText(tab.getIconName());

            tabSpec.setIndicator(view);

            //把每个TabSpec添加到FragmentTabHost里面
            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }

        //去掉分隔线
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);


    }

    private void addToTabsList(Class<? extends Fragment> fragment, int icon, int iconName) {
        Tabs tab = new Tabs(fragment, icon, iconName);
        mTabsList.add(tab);
    }


    //连接融云操作
    private void connectRongIm() {
        String ryToken = UserManager.getInstance().getRyToken();
        if (!TextUtils.isEmpty(ryToken)) {
            //连接融云服务器
            RongImHelper.connect(MyApplicationLike.getInstance(), ryToken, connectCallback);
        } else {
            getRyToken();
        }
    }


    private void getRyToken() {
        //获取融云token
        String token = UserManager.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            AllApi.getInstance().getImToken(token).enqueue(
                    new ApiCallBack<ApiResponseBean<RyTokenBean>>(new BaseCallBack() {

                        @Override
                        public void onSuccess(Object data) {
                            RyTokenBean bean = (RyTokenBean) data;
                            if (bean != null) {
                                UserManager.getInstance().setRyToken(bean.getRy_token());
                                //连接融云服务器
                                RongImHelper.connect(MyApplicationLike.getInstance(), bean.getRy_token(), connectCallback);
                            }
                        }

                        @Override
                        public void onError(String errorCode, String message) {
                            if (ApiCallBack.NET_ERROR.equals(errorCode)) {
                                ToastUtil.showShort(MainActivity.this, getString(R.string.showNeterr));

                            } else if (Constant.ErrorCode.TOKEN_ERROR_10001.equals(errorCode)) {
                                ToastUtil.showShort(MainActivity.this, getString(R.string.token_overdue));
                                GotoUtil.GoToActivity(MainActivity.this, LoginActivity.class);
                            }
                        }
                    })
            );

        }
    }

    //融云连接回调
    private RongIMClient.ConnectCallback connectCallback = new RongIMClient.ConnectCallback() {
        @Override
        public void onTokenIncorrect() {
            getRyToken();
        }

        @Override
        public void onSuccess(String userId) {

        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {

        }
    };


    @Subscribe
    public void onEvent(EventLoginBean bean) {
        if (bean.status == EventLoginBean.LOGIN) {
            connectRongIm();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //极光推送
        if (UserManager.getInstance().isLogin()) {
            String alias = UserManager.getInstance().getUserPhone(MainActivity.this);
            JPushHelper.getInstance().setJPushAlias(this, alias);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
        if (networkReceiver != null) {
            unregisterReceiver(networkReceiver);
        }
    }

    //手机返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showShort(getApplicationContext(), "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }



}
