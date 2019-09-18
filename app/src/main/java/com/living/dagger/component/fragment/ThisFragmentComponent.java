package com.living.dagger.component.fragment;

import com.living.dagger.module.activity.ThisFragmentModule;
import com.living.ui.fragment.home.FourForcunt.ThisFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/8.
 */
@Component(modules = ThisFragmentModule.class)
public interface ThisFragmentComponent {
    void in(ThisFragment thisFragment);
}
