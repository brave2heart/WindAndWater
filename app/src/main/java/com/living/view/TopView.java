package com.living.view;

import com.living.bean.home.ConstellationBean;

/**
 * Created by weihao on 2017/12/26.
 */

public interface TopView extends View {
    void onSuccess(ConstellationBean constellationBean);

    void onError(String rerult);

}
