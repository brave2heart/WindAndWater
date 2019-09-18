package com.living.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by weihao on 2017/12/28.
 */

public class AppBarView extends AppBarLayout {
    private AppBarLayout mAppBarLayout;

    public AppBarView(Context context) {
        super(context);
    }


    public AppBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float per = Math.abs(mAppBarLayout.getY()) / mAppBarLayout.getTotalScrollRange();
            boolean setExpanded = (per <= 0.5F);
            mAppBarLayout.setExpanded(setExpanded, true);
        }
        return super.dispatchTouchEvent(event);
    }
}
