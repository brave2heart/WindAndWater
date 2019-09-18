package com.tongcheng.soothsay.base;

import android.animation.Animator;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.tongcheng.soothsay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingView {

    @BindView(R.id.base_progressBar)        ProgressBar progressBar;
    @BindView(R.id.img_base_loading)        ImageView imgLoading;
    @BindView(R.id.base_loading_area)       FrameLayout baseLoadingArea;

    private Animation anim;

    public LoadingView(View view) {
        ButterKnife.bind(this, view);
        anim = AnimationUtils.loadAnimation(view.getContext(),R.anim.anim_base_loding);
        imgLoading.setAnimation(anim);
    }

    /**
     * 显示加载背景
     */
    public void showLoading() {
        showLoading(false);
    }

    /**
     * 显示半透明加载背景
     * @param translucenceBg
     */
    public void showLoading(boolean translucenceBg){
        baseLoadingArea.setVisibility(View.VISIBLE);
        if(translucenceBg){
            baseLoadingArea.setBackgroundColor(Color.parseColor("#66000000"));
        }
        if(anim != null){
            anim.start();
        }
    }

    public void hideLoading() {
        if(baseLoadingArea.getVisibility() == View.VISIBLE){
            anim.cancel();
            baseLoadingArea.setVisibility(View.GONE);
        }

    }
}
