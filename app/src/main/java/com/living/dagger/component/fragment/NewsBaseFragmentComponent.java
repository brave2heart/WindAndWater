package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.NewsBaseFragmentModule;
import com.living.ui.fragment.home.TouTiao.NewsBaseFragment;

import dagger.Component;

/**
 * Created by weihao on 2017/12/27.
 */
@Component(modules = NewsBaseFragmentModule.class)
public interface NewsBaseFragmentComponent {
    void in(NewsBaseFragment newsBaseFragment);

}
