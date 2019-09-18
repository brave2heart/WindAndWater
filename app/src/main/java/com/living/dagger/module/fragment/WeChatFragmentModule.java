package com.living.dagger.module.fragment;

import com.living.presenter.fragment.WeChatFragmentPresenter;
import com.living.ui.fragment.home.WeChat.WeChatFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/3.
 */
@Module
public class WeChatFragmentModule {
    private WeChatFragment mWeChatFragment;

    public WeChatFragmentModule(WeChatFragment weChatFragment) {
        mWeChatFragment = weChatFragment;
    }

    @Provides
    WeChatFragmentPresenter provideWeChatFragmentPresenter() {
        return new WeChatFragmentPresenter(mWeChatFragment);
    }
}
