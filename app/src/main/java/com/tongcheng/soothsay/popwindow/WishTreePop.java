package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.WishTreeBean;

/**
 * Created by Steven on 16/12/5.
 */

public class WishTreePop extends BasePopupWindow {

   private  TextView tvContent;
    private WishTreeBean mBean;

    public WishTreePop(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public void init(View layoutView) {
        tvContent = (TextView) layoutView.findViewById(R.id.tv_tree_pop_content);


    }

    @Override
    public void initListener() {
    }

    public void setData(WishTreeBean bean){
       this.mBean = bean;
    }

    @Override
    public void showPopOnRootView(Activity activity) {
        tvContent.setText(mBean.getSayword());
        super.showPopOnRootView(activity);
    }
}
