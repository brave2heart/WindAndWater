package com.tongcheng.soothsay.ui.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.living.utils.NoAdWebViewClient;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.utils.UMShareUtils;

import butterknife.BindView;
import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * Created by 宋家任 on 2016/12/6.
 * 通用WebView页面
 */

public class WebViewActivity extends BaseTitleActivity implements BaseUiView {

    @BindView(R.id.webView)
    CacheWebView webView;

    String mUrl;

    @BindView(R.id.progressBar1)
    ProgressBar mProgressBar1;
    private String mWebTitle;

    private boolean isShare;
    private String mImage_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_web_view);
        initData();
    }

    @Override
    public void initData() {
        final Intent intent = getIntent();
        mUrl = intent.getStringExtra("web_url");
        mWebTitle = intent.getStringExtra("web_title");
        isShare = intent.getBooleanExtra("web_share", false);
        mImage_url = intent.getStringExtra("web_image");
        getBaseHeadView().showTitle(mWebTitle);
        if (isShare) {
            getBaseHeadView().showHeadRightImageButton(R.drawable.icon_share, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UMShareUtils(WebViewActivity.this).shareDefault(mImage_url, "风生水", mWebTitle, mUrl);
                }
            });
        }
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                    return;
                }
                WebViewActivity.this.finish();
            }
        });
        getBaseLoadingView().showLoading();
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl(mUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBlockNetworkImage(true);

         NoAdWebViewClient noAdWebViewClient = new NoAdWebViewClient(this, mUrl);

        //  webView.setWebViewClient(noAdWebViewClient);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getBaseLoadingView().hideLoading();
//                去除webview广告方法
                removeAd(view, ".hot-news");
                removeAd(view, ".gg-item");
                removeAd(view, ".gg-item news-gg-img3");
                removeAd(view, ".baiduimageplusm_fake_mask");
                removeAd(view, ".appdownload-swiper");
                removeAd(view, ".interest-news");
                removeAd(view, ".xszh-toindex");
                removeAd(view, ".baiduimageplusm-title-img-only-close  lu_style_border");
                removeAd(view, ".fasgsjbbzdh");

//                //顶部广告
//                view.loadUrl("javascript:function setTop(){document.querySelector('.gg-item').style.display=\"none\";}setTop();");
//                //图片上的广告
//                view.loadUrl("javascript:function setTop(){document.querySelector('.baiduimageplusm_fake_mask').style.display=\"none\";}setTop();");
//                //热点新闻
//                view.loadUrl("javascript:function setTop(){document.querySelector('.hot-news').style.display=\"none\";}setTop();");
//                //顶部轮播
//                view.loadUrl("javascript:function setTop(){document.querySelector('.appdownload-swiper').style.display=\"none\";}setTop();");
//
//                //猜你喜欢
//                view.loadUrl("javascript:function setTop(){document.querySelector('.interest-news').style.display=\"none\";}setTop();");
//                //返回首页
//                view.loadUrl("javascript:function setTop(){document.querySelector('.xszh-toindex').style.display=\"none\";}setTop();");

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
                        Intent intent1 = new Intent(WebViewActivity.this, GoodsDetailsActivity.class);
                        intent1.putExtra("goodsid", split[1]);
                        intent1.putExtra("redirectUrl", url);
                        startActivity(intent1);
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }


        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                view.loadUrl("javascript:function setTop(){document.querySelector('.hn-list').style.display=\"none\";}setTop();");
//                view.loadUrl("javascript:function setTop(){document.querySelector('.appdownload-swiper').style.display=\"none\";}setTop();");
//                view.loadUrl("javascript:function setTop(){document.querySelector('.gg-item').style.display=\"none\";}setTop();");
                removeAd(view, ".hot-news");
                removeAd(view, ".gg-item");
                removeAd(view, ".gg-item news-gg-img3");
                removeAd(view, ".baiduimageplusm_fake_mask");
                removeAd(view, ".appdownload-swiper");
                removeAd(view, ".interest-news");
                removeAd(view, ".xszh-toindex");
                removeAd(view, ".baiduimageplusm-title-img-only-close  lu_style_border");
                removeAd(view, ".fasgsjbbzdh");

                // TODO 自动生成的方法存根

                if (newProgress == 100) {
                    mProgressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    mProgressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProgressBar1.setProgress(newProgress);//设置进度值
                }

            }
        });


    }
    private void removeAd(WebView view, String str) {
        view.loadUrl("javascript:function setTop(){document.querySelector('" + str + "').style.display=\"none\";}setTop();");
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

    //点击回退按钮不会直接退出整个应用程序而是返回上一个页面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
