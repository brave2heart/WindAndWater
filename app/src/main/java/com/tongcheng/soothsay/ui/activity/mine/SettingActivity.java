package com.tongcheng.soothsay.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.BuglyHelper;
import com.tongcheng.soothsay.helper.JPushHelper;
import com.tongcheng.soothsay.utils.ApkUtil;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.SpUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.UMShareUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 宋家任 on 2016/11/25.
 * 个人中心设置界面
 */

public class SettingActivity extends BaseTitleActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.sw_setting_tuisong)
    Switch swSettingTuisong;//推送开关
    @BindView(R.id.rl_setting_our)
    RelativeLayout rlSettingOur;//关于我们
    @BindView(R.id.rl_setting_join)
    RelativeLayout rlSettingJoin;//加入我们
    @BindView(R.id.rl_setting_opinion)
    RelativeLayout rlSettingOpinion;//意见反馈
    @BindView(R.id.rl_setting_share)
    RelativeLayout rlSettingShare;//分享应用
    @BindView(R.id.rl_setting_update)
    RelativeLayout rlSettingUpdate;//版本更新
    @BindView(R.id.tv_mine_info_bangding)
    TextView tvMineInfoBangding;//版本号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_setting);
        initData();
        initListener();
    }

    @Override
    public void initData() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SettingActivity.this.finish();
            }
        });
        getBaseHeadView().showTitle("个人设置");
        Boolean istuisong = SpUtil.getBoolean(this, "tuisong", true);
        swSettingTuisong.setChecked(istuisong);

        String version = ApkUtil.getVersionName(this);
        tvMineInfoBangding.setText("版本：v" + version);
    }

    @Override
    public void initListener() {
        swSettingTuisong.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.rl_setting_our, R.id.rl_setting_join,
            R.id.rl_setting_opinion, R.id.rl_setting_share, R.id.rl_setting_update})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;

        switch (view.getId()) {
            case R.id.rl_setting_our://关于我们
                GotoUtil.GoToWebViewActivity(this, "关于风生水", Constant.Url.ABOUT_FSS);
                break;
            case R.id.rl_setting_join://加入我们
                GotoUtil.GoToWebViewActivity(this, "加入我们", Constant.Url.JOIN_OUS);
                break;
            case R.id.rl_setting_opinion://意见反馈
                if (UserManager.getInstance().isLogin()) {
                    GotoUtil.GoToActivity(this, FeedBackActivity.class);
                } else {
                    GotoUtil.GoToActivity(this, LoginActivity.class);
                }
                break;
            case R.id.rl_setting_share://分享应用
//                String imgurl = "http://wx.qlogo.cn/mmopen/4uyUrl0aMyhbnXJA6grr4vuBTf200dKJuyKvRVlpuoq540sgHic9Ur01heJYH3R1icmQGGD9SHH3zVdylJCwGFSvFtptObyCRA/0";

                new UMShareUtils(this).shareDefault(R.drawable.share_img, "风生水", getResources().getString(R.string.txt_share_msg), Constant.Url.SHARE_APP);
                break;
            case R.id.rl_setting_update://版本更新
                BuglyHelper.checkUpdata(true, false);
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ToastUtil.showShort(SettingActivity.this, "设置成功");
            SpUtil.putBoolean(SettingActivity.this, "tuisong", true);
            JPushInterface.resumePush(this);
            String alias = UserManager.getInstance().getUserPhone(SettingActivity.this);
            JPushHelper.getInstance().setJPushAlias(this,alias);

        } else {
            ToastUtil.showShort(SettingActivity.this, "设置成功");
            SpUtil.putBoolean(SettingActivity.this, "tuisong", false);
            JPushInterface.stopPush(this);
            JPushHelper.getInstance().setJPushAlias(this,null);
        }
    }

}
