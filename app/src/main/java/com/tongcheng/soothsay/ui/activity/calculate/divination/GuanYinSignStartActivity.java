package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.GuanYinBean;
import com.tongcheng.soothsay.service.PlayYaoQIanMusicService;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.ViewPath;
import com.tongcheng.soothsay.utils.ViewPathEvaluator;
import com.tongcheng.soothsay.utils.ViewPoint;
import com.tongcheng.soothsay.widget.AskSkewerView;
import com.tongcheng.soothsay.widget.QianModel;

import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 求签
 */

public class GuanYinSignStartActivity extends BaseTitleActivity {
    @BindView(R.id.iv_sign)
    ImageView ivSign;
    @BindView(R.id.iv_tong)
    ImageView ivTong;
    @BindView(R.id.btn_auto_sign)
    Button btnAutoSign;
    @BindView(R.id.view_back)
    View mViewBack;

    @BindView(R.id.ask_skewer_view)
    AskSkewerView mMoveview;
    @BindView(R.id.qian)
    QianModel mQian;
    @BindView(R.id.cb_play_music)
    CheckBox mCbPlayMusic;
    @BindView(R.id.ll_bg)
    LinearLayout mLlBg;


    private AnimatorSet animatorSet1;
    private ObjectAnimator mTranslationX;
    private AnimatorSet mAnimatorSet;

    private GuanYinBean mGuanYinBean = new GuanYinBean();

    private Random mRandom = new Random();
    private Intent service;
    private PlayYaoQIanMusicService mService;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((PlayYaoQIanMusicService.MyBinder) service).getService();
            mService.setMusic(GuanYinSignStartActivity.this, R.raw.lingji_yaoqian);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mazu_start);
        mLlBg.setBackgroundResource(R.drawable.qqws_gylq_bg);

        initListener();
        initData();
        ToastUtil.showLongWithPic(this, getString(R.string.txt_mazu_shake), R.drawable.qqws_mz_sjyq, Gravity.BOTTOM);

    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.gylq_app_name));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        service = new Intent(this, PlayYaoQIanMusicService.class);
        bindService(service, conn, BIND_AUTO_CREATE);
    }

    @OnClick({R.id.btn_auto_sign, R.id.qian})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_sign:
//                抽签详情

                break;
            case R.id.btn_auto_sign:
//                自动抽签  开始动画
                btnAutoSign.setClickable(false);
                mMoveview.AutoShake();
                break;
            case R.id.qian:
                jieqian();
        }
    }

    private void jieqian() {
//        这里跳到别的地方
        HashMap<String, String> map = new HashMap<>();
        map.put("position", String.valueOf(mGuanYinBean.getPosition()));
        GotoUtil.GoToActivityWithData(this, GuanYinJieQianActivity.class, map);
    }

    private void startAnimate() {
        ivSign.setVisibility(View.VISIBLE);
        animatorSet1 = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(ivSign, "alpha", 1, 0, 1).setDuration(2000);//透明动画
        ObjectAnimator translationX = ObjectAnimator.ofFloat(ivSign, "translationX", 0, -200).setDuration(3000);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(ivSign, "translationY", 0, -100).setDuration(3000);
        ivSign.setPivotX(ivSign.getWidth() / 2);
        ivSign.setPivotY(ivSign.getHeight() / 2);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ivSign, "scaleX", 0, 1).setDuration(2000);//缩放动画
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivSign, "scaleY", 0, 1).setDuration(2000);

       /* animatorSet.playTogether(ObjectAnimator.ofFloat(iv_Sign, "scaleX", 0, 1).setDuration(2000));  //缩放动画
        animatorSet.playTogether(ObjectAnimator.ofFloat(iv_Sign, "scaleY", 0, 1).setDuration(2000));*/

        ObjectAnimator TongtranslationX = ObjectAnimator.ofFloat(ivTong, "translationX", 0, 200).setDuration(3000);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(ivSign, "rotation", 720, 0)
                .setDuration(3000);

        animatorSet1.play(alpha).before(translationX);
        animatorSet1.playTogether(scaleX, scaleY, rotation, translationX, translationY);
        animatorSet1.play(translationX).with(TongtranslationX);
        animatorSet1.start();
    }


    public static final String TAG = "AskMainActivity";


    @Override
    public void initData() {
        mMoveview.setQianText("观音灵签");
        mMoveview.setOnShakeSkewerListener(new AskSkewerView.OnShakeSkewerListener() {
            @Override
            public void onShakeFinish() {
                mMoveview.unregisterSensor();
                anim();
                mQian.setVisibility(View.VISIBLE);
                mQian.setScaleX(0.5f);
                mQian.setScaleY(0.5f);
                mAnimatorSet.start();
                mTranslationX.start();
            }

            @Override
            public void onMeetWall() {
//                这里播放音乐
                if (mService != null && !mCbPlayMusic.isChecked()) {
                    mService.playOrPause();
                }
            }
        });
    }


    //    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void anim() {

        //签筒的移动动画
        mTranslationX = ObjectAnimator.ofFloat(mMoveview, "X",
                mMoveview.getX(), mMoveview.getX() * 1.5f).setDuration(2000);


        mAnimatorSet = new AnimatorSet().setDuration(2000);
//        签的移动与旋转动画
        final ObjectAnimator rotation = ObjectAnimator.ofFloat(mQian, "rotation", 0, -(360 * 3)).setDuration(2000);


        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(mQian, "scaleX", 0.5f, 1).setDuration(2000);//缩放动画
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(mQian, "scaleY", 0.5f, 1).setDuration(2000);

        ViewPath path2 = new ViewPath(); //保存View的移动路径
        path2.moveTo(mQian.getX(), mQian.getY());
        path2.quadTo(mQian.getX() / 2, mQian.getY() / 3, mQian.getHeight() / 4, mQian.getY());

        // 自定义fabLoc动画。
        // 第三个参数传入用于计算坐标的估值器，第四个参数传入用于估值的路径集合
        final ObjectAnimator anim = ObjectAnimator.ofObject(this, "fabLoc", new ViewPathEvaluator(), path2.getPoints().toArray()).setDuration(2000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());

        mAnimatorSet.playTogether(rotation, anim);
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//
//                这里做缩放动画
                mViewBack.setVisibility(View.VISIBLE);
                scaleX.start();
                scaleY.start();
            }
        });


        mTranslationX.addListener(new AnimatorListenerAdapter() {



            @Override
            public void onAnimationEnd(Animator animation) {

                int i = mRandom.nextInt(99);
                mGuanYinBean = new GuanYinBean(i);
                mQian.setText(mGuanYinBean.getDijiqian());
            }
        });

        scaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
    }


    // 基于ObjectAnimation的实现原理定义：定义setFabLoc函数。参数为路径信息对象
    public void setFabLoc(ViewPoint newLoc) {
        mQian.setX(newLoc.x);
        mQian.setY(newLoc.y);
    }

    @Override
    protected void onDestroy() {
        if (mService != null) {
            unbindService(conn);
            mService = null;
        }
        super.onDestroy();
    }
}
