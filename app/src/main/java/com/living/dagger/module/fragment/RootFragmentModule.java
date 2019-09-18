package com.living.dagger.module.fragment;

import android.support.v4.app.Fragment;

import com.living.constant.root.RootType;
import com.living.presenter.fragment.RootFragmentPresenter;
import com.living.ui.fragment.ClassRoomFragment;
import com.living.ui.fragment.MingLiFragment;
import com.living.ui.fragment.ShopFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/16.
 */
@Module
public class RootFragmentModule {

    private final int fragmentType;
    private Fragment mFragment;

    public RootFragmentModule(Fragment fragment, int fragmentType) {
        mFragment = fragment;
        this.fragmentType = fragmentType;
    }

    @Provides
    RootFragmentPresenter provideRootFragmentPresenter() {
        if (fragmentType == RootType.CLASSROOM) {
            return new RootFragmentPresenter((ClassRoomFragment)mFragment);
        }else if (fragmentType == RootType.SHOP) {
            return new RootFragmentPresenter((ShopFragment)mFragment);
        }
        return null;

    }
}
