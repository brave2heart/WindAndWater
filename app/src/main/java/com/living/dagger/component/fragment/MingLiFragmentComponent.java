package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.MingLiFragmentModule;
import com.living.ui.fragment.MingLiFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/24.
 */
@Component(modules = MingLiFragmentModule.class)
public interface MingLiFragmentComponent {
    void in(MingLiFragment mingLiFragment);
}
