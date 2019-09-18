package com.living.dagger.module.fragment;

import com.living.presenter.fragment.JuHeWeChatFragmentPresenter;
import com.living.ui.fragment.home.WeChat.JuHeWeChatFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by weihao on 2018/1/5.
 */
@Module
public class JuHeWeChatFragmentModule {

    private JuHeWeChatFragment mJuHeWeChatFragment;

    public JuHeWeChatFragmentModule(JuHeWeChatFragment juHeWeChatFragment) {
        mJuHeWeChatFragment = juHeWeChatFragment;
    }

    @Provides
    JuHeWeChatFragmentPresenter provideJuHeWeChatFragmentPresenter() {
        return new JuHeWeChatFragmentPresenter(mJuHeWeChatFragment);
    }
}
