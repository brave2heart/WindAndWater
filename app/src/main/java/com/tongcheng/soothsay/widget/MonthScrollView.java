package com.tongcheng.soothsay.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.tongcheng.soothsay.R;


public class MonthScrollView extends ScrollView implements MonthView.OnLineChooseListener, MonthView.OnLineCountChangeListener, MonthView.OnMonthDateClick, MonthView.OnMonthViewChangeListener, WeekView.OnWeekViewChangeListener, WeekView.OnWeekDateClick {

    private final String TAG = "MonthScrollView";

    private MonthView monthView;
    private WeekView weekView;

    private int monthHeight;

    private int line;
    private int lineCount;

    public MonthScrollView(Context context) {
        this(context, null);
    }

    public MonthScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    public void setWeekView(WeekView weekView) {
        this.weekView = weekView;
        if (weekView != null) {
            this.weekView.setOnWeekClickListener(this);
            this.weekView.setOnWeekViewChangeListener(this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(ev);
    }

    private int clickTop;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (t >= clickTop) {
            if (weekView.getVisibility() == INVISIBLE) {
                weekView.setVisibility(VISIBLE);

            }
        } else {
            if (weekView.getVisibility() == VISIBLE) {
                weekView.setVisibility(INVISIBLE);

            }
        }
        if (mOnObservableScrollViewScrollChanged != null) {
            mOnObservableScrollViewScrollChanged.onObservableScrollViewScrollChanged(l, t, oldl, oldt);
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFinishInflate() {
        monthView = (MonthView) findViewById(R.id.month_calendar);
        monthView.setOnLineChooseListener(this);
        monthView.setOnLineCountChangeListener(this);
        monthView.setOnMonthDateClickListener(this);
        monthView.setOnMonthViewChangeListener(this);

    }

    @Override
    public void onLineChange(int line) {
        if (weekView != null) {
            this.line = line;
            weekView.setLine(line);
        }
    }

    @Override
    public void onLineCountChange(int lineCount) {
        if (weekView != null) {
            this.lineCount = lineCount;
            if (lineCount == 6) {
                weekView.setCount(lineCount);
            }
            changeClickTop(line);
        }
    }


    @Override
    public void onMonthDateClick(int x, int y) {
        if (weekView != null) {
            weekView.changeChooseDate(x, y - (monthView.getHeight() * (line) / lineCount));

            if (weekView.getVisibility() == VISIBLE) {
                weekView.setVisibility(INVISIBLE);
            }

        }
    }

    @Override
    public void onMonthViewChange(boolean isforward) {
        if (isforward && weekView != null) {
            weekView.moveForwad();
        } else if (weekView != null) {
            weekView.moveBack();
        }

        changeClickTop(line);
    }

    @Override
    public void onWeekViewChange(boolean isForward) {
        if (isForward) {
            monthView.moveForwad();
        } else {
            monthView.moveBack();
        }
    }

    @Override
    public void onWeekDateClick(int x, int y) {
        monthView.changeChooseDate(x, y + (monthView.getHeight() * (line) / lineCount));
    }

    private void changeClickTop(int nLine) {
        clickTop = (monthView.getHeight() / lineCount) * nLine;
    }
    private OnObservableScrollViewScrollChanged mOnObservableScrollViewScrollChanged;

    public void setOnObservableScrollViewScrollChanged(OnObservableScrollViewScrollChanged mOnObservableScrollViewScrollChanged) {
        this.mOnObservableScrollViewScrollChanged = mOnObservableScrollViewScrollChanged;
    }


    public interface OnObservableScrollViewScrollChanged {
        void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt);
    }

    /**
     * @param l    Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t    Current vertical scroll origin. 当前滑动的y轴距离
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距离
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        if (mOnObservableScrollViewScrollChanged != null) {
//            mOnObservableScrollViewScrollChanged.onObservableScrollViewScrollChanged(l, t, oldl, oldt);
//        }
//    }
}
