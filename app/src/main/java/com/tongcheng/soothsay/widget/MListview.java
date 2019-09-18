package com.tongcheng.soothsay.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tongcheng.soothsay.R;

/**
 * Created by 宋家任 on 2016/11/8.
 */

public class MListview extends android.widget.ListView {
    private boolean mLockScroll = false;// 是否滚动

    public MListview(Context context) {
        super(context);
    }

    public MListview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        /* 获取TypedArray */
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.MListView);
        mLockScroll = typedArray.getBoolean(R.styleable.MListView_lockScroll, false);
        /* 释放TypedArray */
        typedArray.recycle();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mLockScroll)
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_MOVE && mLockScroll) {
//            return true;//禁止进行滑动
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}
