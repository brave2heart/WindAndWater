package com.living.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.living.bean.home.JWeChatBean;
import com.living.constant.home.HomeAPI;
import com.living.http.RetrofitService;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
*
* */

public class WeChatActivity extends AppCompatActivity {

    private TextView mTv_title;
    private TextView mTv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);

        mTv_title = (TextView) findViewById(R.id.tv_title_wechat);
        mTv_content = (TextView) findViewById(R.id.tv_content_wechat);
        Button button_wechat = (Button) findViewById(R.id.button_wechat);

        button_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(HomeAPI.JWeChatBaseUrl)
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //支持Rx
                        .build();

                RetrofitService service = retrofit.create(RetrofitService.class);
                Observable<JWeChatBean> observable = service.getJWeChatData(HomeAPI.JWeChatChannelid, HomeAPI.JWeChatStart, HomeAPI.JWeChatNum, HomeAPI.JWeChatkey);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<JWeChatBean>() {


                            @Override
                            public void onCompleted() {
                                //所有事情完成，可以做些操作
//                        mRecyclerview.setAdapter(new TopFragmentAdapter(getActivity(), mData));
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(JWeChatBean jWeChatBean) {
                                List<JWeChatBean.ResultBean.ListBean> list = jWeChatBean.getResult().getList();
                                for (int i = 0; i < list.size(); i++) {
                                    JWeChatBean.ResultBean.ListBean listBean = list.get(1);
                                    String title = listBean.getTitle();
                                    mTv_title.setText(title);
//                                    mTv_content.setText(Html.fromHtml(listBean.getContent()));
                                    Spanned sp = Html.fromHtml(listBean.getContent(), new Html.ImageGetter() {
                                        @Override
                                        public Drawable getDrawable(String source) {
                                            InputStream is = null;
                                            try {
                                                is = (InputStream) new URL(source).getContent();
                                                Drawable d = Drawable.createFromStream(is, "src");
                                                d.setBounds(0, 0, d.getIntrinsicWidth(),
                                                        d.getIntrinsicHeight());
                                                is.close();
                                                return d;
                                            } catch (Exception e) {
                                                return null;
                                            }
                                        }
                                    }, null);

                                    mTv_content.setText(sp);

                                    Intent intent = new Intent(WeChatActivity.this, WebViewActivity.class);
                                    intent.putExtra("web_url", listBean.getUrl());
                                    intent.putExtra("web_title", "微信精选");
                                    intent.putExtra("web_share", true);
                                    startActivity(intent);

                                }
                            }


                        });
            }
        });


    }
}
