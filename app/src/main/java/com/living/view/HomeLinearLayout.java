package com.living.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by weihao on 2017/12/28.
 */

public class HomeLinearLayout extends LinearLayout {


    private int mLastXIntercept;
    private int mLastYIntercept;

    public HomeLinearLayout(Context context) {
        super(context);
    }

    public HomeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HomeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaY) > Math.abs(deltaX)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }

                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;


        return intercepted;
    }
}
