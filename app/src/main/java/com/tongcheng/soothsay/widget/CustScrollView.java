package com.tongcheng.soothsay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * 只为顶部ScrollView使用
 * 如果使用了其它的可拖拽的控件，请仿照该类实现isAtBottom方法
 */
public class CustScrollView extends ScrollView {
    private static final int TOUCH_IDLE = 0;
    private static final int TOUCH_INNER_CONSIME = 1; // touch事件由ScrollView内部消费
    private static final int TOUCH_DRAG_LAYOUT = 2; // touch事件由上层的DragLayout去消费
    private int lastScrollY;
    boolean isAtBottom; // 按下的时候是否在底部
    private int mTouchSlop = 4; // 判定为滑动的阈值，单位是像素
    private int scrollMode;
    private float downY;
    private OnScrollListener onScrollListener;

    public CustScrollView(Context arg0) {
        this(arg0, null);
    }

    public CustScrollView(Context arg0, AttributeSet arg1) {
        this(arg0, arg1, 0);
    }

    public CustScrollView(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getRawY();
            isAtBottom = isAtBottom();
            scrollMode = TOUCH_IDLE;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (scrollMode == TOUCH_IDLE) {
                float yOffset = downY - ev.getRawY();
                float yDistance = Math.abs(yOffset);
                if (yDistance > mTouchSlop) {
                    if (yOffset > 0 && isAtBottom) {
                        scrollMode = TOUCH_DRAG_LAYOUT;
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    } else {
                        scrollMode = TOUCH_INNER_CONSIME;
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    private boolean isAtBottom() {
        return getScrollY() + getMeasuredHeight() >= computeVerticalScrollRange() - 2;
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScroll(t);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollMode == TOUCH_DRAG_LAYOUT) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     *
     * @author xiaanming
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         *
         * @param scrollY 、
         */
        public void onScroll(int scrollY);
    }
}