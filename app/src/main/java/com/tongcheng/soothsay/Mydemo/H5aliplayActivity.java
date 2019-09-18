package com.tongcheng.soothsay.Mydemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tongcheng.soothsay.R;

public class H5aliplayActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5aliplay);
        mWebview = (WebView) findViewById(R.id.webview);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl("http://shengtai.polms.cn/index.php/Bzcs/Index/index/t/7/p/1");
        
        mWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(final WebView view, String url) {
                // 获取上下文, H5PayDemoActivity为当前页面
                final Activity context = H5aliplayActivity.this;

                // ------  对alipays:相关的scheme处理 -------
                if(url.startsWith("alipays:") || url.startsWith("alipay")) {
                    try {
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    } catch (Exception e) {
                        new AlertDialog.Builder(context)
                                .setMessage("未检测到支付宝客户端，请安装后重试。")
                                .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri alipayUrl = Uri.parse("https://d.alipay.com");
                                        context.startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
                                    }
                                }).setNegativeButton("取消", null).show();
                    }
                    return true;
                }
                // ------- 处理结束 -------

                if (!(url.startsWith("http") || url.startsWith("https"))) {
                    return true;
                }

                view.loadUrl(url);
                return true;
            }
        });
    }
}
