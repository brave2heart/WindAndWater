package com.living.dagger.module.activity;

import com.living.presenter.activity.HomeFortuneActivityPresenter;
import com.living.presenter.fragment.ThisFragmentPresenter;
import com.living.ui.activity.HomeFortuneActivity;
import com.living.ui.fragment.home.FourForcunt.ThisFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2017/12/29.
 */

@Module
public class HomeFortuneActivityModule {

    private HomeFortuneActivity homeFortuneActivity;
    public HomeFortuneActivityModule(HomeFortuneActivity homeFortuneActivity) {
        this.homeFortuneActivity = homeFortuneActivity;
    }

    @Provides
    HomeFortuneActivityPresenter ProvideHomeFortuneActivityPresenter() {
        return new HomeFortuneActivityPresenter(homeFortuneActivity);
    }
}
