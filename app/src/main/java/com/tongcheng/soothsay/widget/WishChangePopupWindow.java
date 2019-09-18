package com.tongcheng.soothsay.widget;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.PrayingChooseFruitAdapter;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.FruitBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WishChangePopupWindow extends BasePopupWindow implements View.OnClickListener {


    private final String mDxName;

    public WishChangePopupWindow(Activity activity, int resId, String dxName) {
        super(activity, resId);
        mDxName = dxName;
    }

    @Override
    public void init(View layoutView) {
        TextView cancel = (TextView) layoutView.findViewById(R.id.tv_cancel);
        TextView submit = (TextView) layoutView.findViewById(R.id.tv_submit);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
    }



    @Override
    public void initListener() {

    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;

        switch (v.getId()) {
            case R.id.tv_cancel:
                this.closePop();
                break;
            case R.id.tv_submit:
//                这里将又跳到一个activity里改愿望  家任已经写了这个界面  到时候
                break;
        }

    }
}
