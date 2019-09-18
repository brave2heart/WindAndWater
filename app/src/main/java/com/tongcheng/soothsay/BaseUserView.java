package com.tongcheng.soothsay;

/**
 * Created by Steven on 16/12/26.
 */

public interface BaseUserView<T> extends BaseUiView<T>{

    //token过期
    void showTokenOverdue();
}
