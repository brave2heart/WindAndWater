package com.living.dagger.component.fragment;

import com.living.dagger.module.fragment.WeChatFragmentModule;
import com.living.ui.fragment.home.WeChat.WeChatFragment;

import dagger.Component;

/**
 * Created by weihao on 2018/1/3.
 */
@Component(modules = WeChatFragmentModule.class)
public interface WeChatFragmentComponent {
    void in(WeChatFragment weChatFragment);
}
