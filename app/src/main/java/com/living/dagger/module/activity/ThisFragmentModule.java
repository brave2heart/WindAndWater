package com.living.dagger.module.activity;

import com.living.presenter.fragment.ThisFragmentPresenter;
import com.living.ui.fragment.home.FourForcunt.ThisFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/8.
 */
@Module
public class ThisFragmentModule {

    private ThisFragment mThisFragment;

    public ThisFragmentModule(ThisFragment thisFragment) {
        mThisFragment = thisFragment;
    }

    @Provides
    ThisFragmentPresenter provideThisFragmentPresenter() {
        return new ThisFragmentPresenter(mThisFragment);
    }
}
