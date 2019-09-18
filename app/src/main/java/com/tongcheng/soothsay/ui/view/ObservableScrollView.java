package com.tongcheng.soothsay.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by weihao on 2017/12/14.
 */

public class ObservableScrollView extends ScrollView {

    private OnObservableScrollViewScrollChanged mChanged;

    public void setChanged(OnObservableScrollViewScrollChanged changed) {
        mChanged = changed;
    }

    public ObservableScrollView(Context context) {
        this(context,null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    * 参数一：当前滑动的X轴距离
    * 参数二：当前滑动的y轴距离
    * 参数三：上一次滑动的x轴距离
    * 参数四：上一次滑动的y轴距离
    * */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mChanged != null) {
            mChanged.OnObservableScrollViewScrollChanged(l, t, oldl, oldt);
        }
    }

    public interface OnObservableScrollViewScrollChanged {

        void OnObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt);


    }


}
