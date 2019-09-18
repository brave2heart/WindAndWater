package com.tongcheng.soothsay.base;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.tongcheng.soothsay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyView {
    @BindView(R.id.base_reload)
    Button baseReload;
    @BindView(R.id.emptyImageView)
    ImageView emptyImageView;
    @BindView(R.id.emptyTextView)
    TextView emptyTextView;
    @BindView(R.id.base_empty_area)
    FrameLayout baseEmptyArea;

    public EmptyView(View view) {
        ButterKnife.bind(this, view);
        emptyImageView.setVisibility(View.GONE);
        baseReload.setVisibility(View.GONE);
        baseEmptyArea.setVisibility(View.GONE);
    }


    public View getBaseEmptyView() {
        return baseEmptyArea;
    }

    public void hideEmptyView() {
        baseEmptyArea.setVisibility(View.GONE);
    }

    public void setReloadOnClick(View.OnClickListener listener) {
        baseReload.setOnClickListener(listener);
        baseReload.setVisibility(View.VISIBLE);
    }

    public void setReloadOnClick(String text, View.OnClickListener listener) {
        baseReload.setOnClickListener(listener);
        baseReload.setText(text);
        baseReload.setVisibility(View.VISIBLE);

    }


    public void hideButton() {
        baseReload.setVisibility(View.GONE);

    }

    public void showEmptyView() {
        baseEmptyArea.setVisibility(View.VISIBLE);
    }

    /**
     * 显示网络错误页面
     *
     * @param imageRes
     * @param textRes
     */
    public void showNetWorkView(int imageRes, int textRes, int btnTextRes, View.OnClickListener listener) {
        emptyImageView.setImageResource(imageRes);
        emptyImageView.setVisibility(View.VISIBLE);
        emptyTextView.setText(textRes);
        if (listener != null) {
            baseReload.setOnClickListener(listener);
            baseReload.setText(btnTextRes);
            baseReload.setVisibility(View.VISIBLE);
        }
        showEmptyView();
    }

    public void showNetWorkView(View.OnClickListener listener) {
        emptyImageView.setImageResource(R.drawable.nonetwork);
        emptyImageView.setVisibility(View.VISIBLE);
        emptyTextView.setText("无法连接网络");
        if (listener != null) {
            baseReload.setOnClickListener(listener);
            baseReload.setText("重试");
            baseReload.setVisibility(View.VISIBLE);
            showEmptyView();
        }
        showEmptyView();
    }

    public void showEmptyView(int imageRes, String text) {
        emptyTextView.setText(text);
        emptyImageView.setImageResource(imageRes);
        emptyImageView.setVisibility(View.VISIBLE);
        showEmptyView();
    }


    public void showEmptyView(int imageRes) {
        emptyImageView.setImageResource(imageRes);
        emptyImageView.setVisibility(View.VISIBLE);
        showEmptyView();
    }

    public void showEmptyView(int imageRes, String text, String buttonText, View.OnClickListener listener) {
        emptyImageView.setImageResource(imageRes);
        emptyImageView.setVisibility(View.VISIBLE);
        emptyTextView.setText(text);
        baseReload.setOnClickListener(listener);
        baseReload.setText(buttonText);
        baseReload.setVisibility(View.VISIBLE);
        showEmptyView();
    }

    public void showEmptyView(int imageRes, int textRes, View.OnClickListener listener) {
        emptyImageView.setImageResource(imageRes);
        emptyImageView.setVisibility(View.VISIBLE);
        emptyTextView.setText(textRes);
        baseReload.setOnClickListener(listener);
        baseReload.setVisibility(View.VISIBLE);
        showEmptyView();
    }

    public void setEmptyView(int imageRes, String text) {
        emptyTextView.setText(text);
        emptyImageView.setImageResource(imageRes);
        emptyImageView.setVisibility(View.VISIBLE);
    }

    public void setEmptyViewText(String text) {
        emptyTextView.setText(text);
        emptyTextView.setVisibility(View.VISIBLE);
    }

    /**
     * 不显示重新加载按钮
     */
    public void showEmptyContent(){
        showEmptyView();
        emptyTextView.setVisibility(View.VISIBLE);
        emptyImageView.setVisibility(View.VISIBLE);
    }
}
