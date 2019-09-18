package com.tongcheng.soothsay.http;


public interface BaseCallBack<T> {
    void onSuccess(T data);

    void onError(String errorCode, String message);
}
