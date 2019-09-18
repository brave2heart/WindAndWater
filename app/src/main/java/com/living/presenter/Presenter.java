package com.living.presenter;

import android.content.Intent;

import com.living.view.View;

/**
 * Created by weihao on 2017/12/26.
 */

public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);

}
