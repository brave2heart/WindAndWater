package com.tongcheng.soothsay.base;

import android.support.annotation.StringDef;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tongcheng.soothsay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadView {
    @BindView(R.id.headBackButton)              ImageButton headBackButton;
    @BindView(R.id.headTitle)                   TextView headTitle;
    @BindView(R.id.headRightButton)             Button headRightButton;
    @BindView(R.id.HeadRightImageButton)        ImageButton HeadRightImageButton;
    @BindView(R.id.head_divide_line)            View headDivideLine;
    @BindView(R.id.headArea)                    RelativeLayout headArea;
    @BindView(R.id.head_parent)                 LinearLayout headParent;

    public HeadView(View view) {
        ButterKnife.bind(this, view);
    }

    public void showBaseHead() {
        headArea.setVisibility(View.VISIBLE);
    }

    public void hideBaseHead() {
        headArea.setVisibility(View.GONE);
    }

    public void showTitle(String s) {
        headTitle.setText(s);
    }


    public void showTitle(int i){
        headTitle.setText(i);
    }


    public void showBackButton(View.OnClickListener listener) {
        headBackButton.setOnClickListener(listener);
        headBackButton.setVisibility(View.VISIBLE);
    }

    public void showBackButton(int res, View.OnClickListener listener) {
        headBackButton.setOnClickListener(listener);
        headBackButton.setImageResource(res);
        headBackButton.setVisibility(View.VISIBLE);
    }

    public void showHeadRightButton(String s, View.OnClickListener listener) {
        headRightButton.setOnClickListener(listener);
        headRightButton.setVisibility(View.VISIBLE);
        headRightButton.setText(s);
    }

    public void showHeadRightImageButton(int resId, View.OnClickListener listener) {
        HeadRightImageButton.setOnClickListener(listener);
        HeadRightImageButton.setVisibility(View.VISIBLE);
        HeadRightImageButton.setImageResource(resId);
    }


    public void changeHeadBackground(int resId) {
        headArea.setBackgroundResource(resId);
    }

    public View getParentView(){ return headArea ;}

    public View getRootHeadView(){ return headParent; }

    public void changeTitleTextColor(int color) {
        headTitle.setTextColor(color);
    }

    public void hideDivideLine() {
        headDivideLine.setVisibility(View.GONE);
    }

    public void showDivideLine() {
        headDivideLine.setVisibility(View.VISIBLE);
    }

    public void hindBackButton() {
        headBackButton.setVisibility(View.GONE);
    }

    public TextView getHeadTitle() {
        return headTitle;
    }

    public ImageButton getHeadRightImageButton() {
        return HeadRightImageButton;
    }

}
