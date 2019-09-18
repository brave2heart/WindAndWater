package com.living.dagger.module.fragment;

import com.living.presenter.NewsBaseFragmentPresenter;
import com.living.ui.fragment.home.TouTiao.NewsBaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2017/12/27.
 */

@Module
public class NewsBaseFragmentModule {

    private NewsBaseFragment mNewsBaseFragment;

    public NewsBaseFragmentModule(NewsBaseFragment newsBaseFragment) {

        mNewsBaseFragment = newsBaseFragment;
    }

    @Provides
    NewsBaseFragmentPresenter provideNewsBaseFragmentPresenter() {
        return new NewsBaseFragmentPresenter(mNewsBaseFragment);
    }
}
