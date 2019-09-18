package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.JuHeWeChatFragmentModule;
import com.living.ui.fragment.home.WeChat.JuHeWeChatFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/5.
 */
@Component(modules = JuHeWeChatFragmentModule.class)
public interface JuHeWeChatFragmentComponent {
    void in(JuHeWeChatFragment juHeWeChatFragment);
}
