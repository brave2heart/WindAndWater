package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.ClassRoomFragmentModule;
import com.living.ui.fragment.ClassRoomFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/24.
 */
@Component(modules = ClassRoomFragmentModule.class)
public interface ClassRoomFragmentComponent {
    void in(ClassRoomFragment classRoomFragment);
}
