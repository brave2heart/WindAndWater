package com.living.dagger.module.fragment;

import com.living.presenter.fragment.ClassRoomFragmentPresenter;
import com.living.ui.fragment.ClassRoomFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/24.
 */
@Module
public class ClassRoomFragmentModule {

    private ClassRoomFragment mClassRoomFragment;

    public ClassRoomFragmentModule(ClassRoomFragment classRoomFragment) {
        mClassRoomFragment = classRoomFragment;
    }

    @Provides
    ClassRoomFragmentPresenter provideClassRoomFragmentPresenter() {
        return new ClassRoomFragmentPresenter(mClassRoomFragment);
    }
}
