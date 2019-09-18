package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.RootFragmentModule;
import com.living.ui.fragment.ClassRoomFragment;
import com.living.ui.fragment.MingLiFragment;
import com.living.ui.fragment.ShopFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/16.
 */
@Component(modules = RootFragmentModule.class)
public interface RootFragmentComponent {

//    void in(MingLiFragment fragment);
//    void in(ClassRoomFragment fragment);
//    void in(ShopFragment fragment);
}
