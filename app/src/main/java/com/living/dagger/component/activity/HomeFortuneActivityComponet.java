package com.living.dagger.component.activity;

import com.living.dagger.module.activity.HomeFortuneActivityModule;
import com.living.presenter.activity.HomeFortuneActivityPresenter;
import com.living.ui.activity.HomeFortuneActivity;
import com.living.ui.fragment.home.FourForcunt.ThisFragment;

import dagger.Component;

/**
 * Created by weihao on 2017/12/29.
 */

@Component(modules = HomeFortuneActivityModule.class)
public interface HomeFortuneActivityComponet {
    void in(HomeFortuneActivity homeFortuneActivity);

}
