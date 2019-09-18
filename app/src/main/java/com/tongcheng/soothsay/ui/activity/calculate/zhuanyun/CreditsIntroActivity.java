package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.RequestImmortalViewPagerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.EventJiFenBean;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.CreditsObtainFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.CreditsObtainIntroFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;

/**
 */

public class CreditsIntroActivity extends BaseTitleActivity {


    @BindView(R.id.vp_immortal)
    ViewPager vp;
    @BindView(R.id.pss_tab_immortal)
    MPagerSlidingTabStrip pssTab;

    private ArrayList<Fragment> container;

    //    用来保存  要购买分数  等回调的时候  来修改积分
    private int buyJifen;

    private Fragment creditsObtainFragment;
    private Fragment creditsObtainIntroFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_credits_intro);
        initBaseHeadView();
        EventBusUtil.register(this);
        initData();
    }

    private void initBaseHeadView() {
        getBaseHeadView();
        getBaseHeadView().showTitle("积分说明");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreditsIntroActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        String[] datas = {"积分获取", "积分说明"};
        container = new ArrayList<>();
        creditsObtainFragment = new CreditsObtainFragment();
        creditsObtainIntroFragment = new CreditsObtainIntroFragment();
        container.add(creditsObtainFragment);
        container.add(creditsObtainIntroFragment);
        vp.setAdapter(new RequestImmortalViewPagerAdapter(getSupportFragmentManager(), this.container, datas));
        pssTab.setViewPager(vp);
    }


    /**
     * 在fragment里调用修改
     *
     * @param jifen
     */
    public void setBuyJifen(int jifen) {
        buyJifen = jifen;
    }


    /**
     * pay 回调 这个来修改积分值
     */
    public void setChangeJifen() {
        String userJf = UserManager.getInstance().getUserJf(this);
        Integer temp = Integer.valueOf(userJf);
        temp += buyJifen;
        UserManager.getInstance().setUserJf(this, temp.toString());
    }


    @Subscribe
    public void changeJiFen(EventPayResultBean bean) {
        if (bean.status == 0) {
            setChangeJifen();
            EventBusUtil.post(new EventJiFenBean(UserManager.getInstance().getUserJf(this)));
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == PayConstant.PAY_RESULT) {
//            int result = data.getIntExtra(Constant.INTENT_DATA, -1);
//            if (result == PayConstant.ERR_CODE_PAY_FINISH) {
////                ToastUtil.showShort(this,getString(R.string.free_pay_finish));
//
////                setChangeJifen();
//
//            }
//
//        }
//    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }
}
