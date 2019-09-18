package com.tongcheng.soothsay.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;

import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2017/1/5.
 * 闪屏广告跳转的Activity
 */

public class LaunchWebViewActivity extends BaseTitleActivity implements BaseUiView {

    @BindView(R.id.webView)
    WebView webView;

    String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_web_view);
        initData();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("web_url");
        String webTitle = intent.getStringExtra("web_title");
        getBaseHeadView().showTitle(webTitle);
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                startActivity(new Intent(LaunchWebViewActivity.this, com.living.ui.MainActivity.class));
                LaunchWebViewActivity.this.finish();
            }
        });
        getBaseLoadingView().showLoading();
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl(mUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getBaseLoadingView().hideLoading();
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == WebViewClient.ERROR_CONNECT) {
                    showNetError();
                }
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("WebViewActivity", url);
                if (url.contains("gooddetails") && url.contains("storeId=")) {
                    String[] split = url.split("storeId=");
                    if (split.length == 2) {
                        Intent intent1 = new Intent(LaunchWebViewActivity.this, GoodsDetailsActivity.class);
                        intent1.putExtra("goodsid", split[1]);
                        intent1.putExtra("redirectUrl", url);
                        startActivity(intent1);
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        });
    }

    @Override
    public void showNetError() {
        getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReLoad();
            }
        });
    }

    @Override
    public void showError(String info) {
        webView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading();
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {
        webView.reload();
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(Object presenter) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(LaunchWebViewActivity.this, com.living.ui.MainActivity.class));
            overridePendingTransition(R.anim.activity_in_from_left,
                    R.anim.activity_out_of_right);
            LaunchWebViewActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
