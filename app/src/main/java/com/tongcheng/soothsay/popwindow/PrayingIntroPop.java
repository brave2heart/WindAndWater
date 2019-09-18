package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.utils.ClickUtil;

/**
 * Created by bozhihuatong on 2016/11/28.
 */

public class PrayingIntroPop extends BasePopupWindow {

    private TextView tvContent;
    private Button btnCancel;

    public PrayingIntroPop(Activity activity, int resId, boolean outSide) {
        super(activity, resId, outSide);
    }

    @Override
    public void init(View layoutView) {
        tvContent = (TextView) layoutView.findViewById(R.id.md_dialog_content);
        btnCancel = (Button) layoutView.findViewById(R.id.md_dialog_cancel);
        String content = "贡品会在当天 <font color=\"#FF0000\">23点59分59秒</font>后送给天神，届时请重新上贡品。";
        tvContent.setText(Html.fromHtml(content));


    }


    @Override
    protected int[] loadAnimRes() {
        int[] anmis = {R.anim.dade_bottom_in, R.anim.dade_bottom_out};
        return anmis;
    }

    @Override
    public void initListener() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closePop();
            }
        });
    }


}



