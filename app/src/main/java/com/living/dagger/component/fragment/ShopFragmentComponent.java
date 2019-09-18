package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.ClassRoomFragmentModule;
import com.living.dagger.module.fragment.ShopFragmentModule;
import com.living.ui.fragment.ClassRoomFragment;
import com.living.ui.fragment.ShopFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/24.
 */
@Component(modules = ShopFragmentModule.class)
public interface ShopFragmentComponent {
    void in(ShopFragment shopFragment);
}
