package com.tongcheng.soothsay.ui.activity.classroom;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.NewsPagerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.NewsTypeBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.fragment.classroom.news.NewFragment;
import com.tongcheng.soothsay.ui.fragment.classroom.news.NewsContract;
import com.tongcheng.soothsay.ui.fragment.classroom.news.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 资讯activity
 */
public class NewsActivity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.vp_fragment)
    ViewPager mVpFragment;
    @BindView(R.id.tb_new_tab)
    TabLayout mPssTab;
    List<NewsTypeBean> titles;


    private List<NewFragment> mFragments;
    private NewsContract.Presenter mPresenter;
    private NewsPagerAdapter mNewsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        super.setContentView(R.layout.activity_news);
        getBaseHeadView().showTitle(R.string.zhouyi_title);
        getBaseHeadView().showBackButton(this);
        initData();
    }

    @Override
    public void initListener() {
        int newsID = getIntent().getIntExtra("NewsID", 0);

        if (titles != null && newsID != 0) {
            for (int i = 0; i < titles.size(); i++) {
                if (titles.get(i).getId() == newsID) {
                    mVpFragment.setCurrentItem(i);
                }
            }
        }
    }


    @Override
    public void initData() {
//        加载周易知识

        getBaseLoadingView().showLoading();
        AllApi.getInstance().getNewsType().enqueue(new ApiCallBack<ApiResponseBean<List<NewsTypeBean>>>(new BaseCallBack<List<NewsTypeBean>>() {


            @Override
            public void onSuccess(List<NewsTypeBean> data) {
                titles = data;
                initView();
                initListener();
                getBaseEmptyView().hideEmptyView();
                getBaseLoadingView().hideLoading();
            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getBaseEmptyView().hideEmptyView();
                        getBaseLoadingView().showLoading();
                        initData();
                    }
                });
            }
        }));


    }


    @Override
    public void initView() {

        mFragments = new ArrayList<>();

        mPresenter = new NewsPresenter();


        for (NewsTypeBean title : titles) {
            mFragments.add(getNewFragment(title.getId()));
        }

//        for (int i = 0; i < titles.size(); i++) {
//          mFragments.add(getNewFragment(i+1));
//        }


        mVpFragment.setOffscreenPageLimit(titles.size() - 1);
        mNewsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        mVpFragment.setAdapter(mNewsPagerAdapter);
        mPssTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mPssTab.setupWithViewPager(mVpFragment);
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    public NewFragment getNewFragment(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("newsId", id);
        NewFragment newsFragment = new NewFragment();
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    public NewsContract.Presenter getPresenter() {
        return mPresenter;
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        super.onDestroy();
    }
}
