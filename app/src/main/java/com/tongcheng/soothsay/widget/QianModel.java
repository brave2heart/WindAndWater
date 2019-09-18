package com.tongcheng.soothsay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tongcheng.soothsay.R;

/**
 * Created by Gubr on 2016/12/15.
 */

public class QianModel extends TextView {
    public QianModel(Context context) {
        this(context,null);
    }

    public QianModel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEms(1);
        setBackgroundResource(R.drawable.qqws_mazu_qian);
    }
}
