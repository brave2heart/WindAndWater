package com.living.dagger.module.fragment;

import com.living.presenter.fragment.ShopFragmentPresenter;
import com.living.ui.fragment.ShopFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/24.
 */
@Module
public class ShopFragmentModule {

    private ShopFragment mShopFragment;

    public ShopFragmentModule(ShopFragment shopFragment) {
        mShopFragment = shopFragment;
    }

    @Provides
    ShopFragmentPresenter provideShopFragmentPresenter() {
        return new ShopFragmentPresenter(mShopFragment);
    }
}
