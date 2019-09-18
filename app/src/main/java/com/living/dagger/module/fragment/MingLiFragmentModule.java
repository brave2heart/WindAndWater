package com.living.dagger.module.fragment;

import com.living.presenter.fragment.MingLiFragmentPresenter;
import com.living.ui.fragment.MingLiFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/24.
 */
@Module
public class MingLiFragmentModule {

    private MingLiFragment mMingLiFragment;

    public MingLiFragmentModule(MingLiFragment liFragment) {
        mMingLiFragment = liFragment;
    }

    @Provides
    MingLiFragmentPresenter provideMingLiFragmentPresenter() {
        return new MingLiFragmentPresenter(mMingLiFragment);
    }
}
