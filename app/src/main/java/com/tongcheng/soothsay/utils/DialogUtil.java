package com.tongcheng.soothsay.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.dialog.LoginAlertDialog;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;


/**
 * Created by Steven on 16/10/13.
 */

public class DialogUtil {


    public static MDAlertDialog createDialog(Context context, String title, String content, String cancelText, String okText, DialogOnClickListener callBack) {
        return DialogUtil.createDialog(context, title, content, cancelText, okText, callBack, true);
    }


    public static MDAlertDialog createDialog(Context context, String title, String content, String cancelText, String okText, DialogOnClickListener callBack, boolean OutSideCancel) {
        MDAlertDialog dialog4 = new MDAlertDialog.Builder(context)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText(title)
                .setTitleTextColor(R.color.colorAccent)
                .setContentText(content)
                .setContentTextColor(R.color.tint_gray_text)
                .setLeftButtonText(cancelText)
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText(okText)
                .setRightButtonTextColor(R.color.red_text)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setCanceledOnTouchOutside(OutSideCancel)
                .setOnclickListener(callBack)
                .build();
        return dialog4;
    }


    public static LoginAlertDialog createLoginAlertDialog(Context context, String title, String content, String cancelText, String okText, DialogOnClickListener callBack) {
        return DialogUtil.createLoginAlertDialog(context, title, content, cancelText, okText, callBack, true);
    }

    public static LoginAlertDialog createLoginAlertDialog(Context context, String title, String content, String cancelText, String okText, DialogOnClickListener callBack, boolean OutSideCancel) {
        LoginAlertDialog dialog4 = new LoginAlertDialog.Builder(context)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText(title)
                .setTitleTextColor(R.color.colorAccent)
                .setContentText(content)
                .setContentTextColor(R.color.black_text)
                .setLeftButtonText(cancelText)
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText(okText)
                .setRightButtonTextColor(R.color.white)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setCanceledOnTouchOutside(OutSideCancel)
                .setOnclickListener(callBack)
                .build();
        return dialog4;
    }

    /**
     * @param context
     * @param requestCode   请求码
     * @return
     */
    public static LoginAlertDialog createLoginAlertForResultDialog(final Activity context, final int contentRes, final int requestCode) {
        return createLoginAlertForResultDialog(context, "登录风生水账号", context.getResources().getString(contentRes), "取消", "登录", requestCode, new DialogOnClickListener() {
            @Override
            public void clickLeftButton(View view) {
                ((LoginAlertDialog) view.getTag()).dismiss();
            }

            @Override
            public void clickRightButton(View view) {
                GotoUtil.GoToActivityForResult(context,LoginActivity.class,requestCode);
            }
        }, true);
    }



    public static LoginAlertDialog createLoginAlertForResultDialog(final Activity context, String title, final String content, String cancelText, String okText, final int requestCode,DialogOnClickListener callBack, boolean OutSideCancel) {
        LoginAlertDialog dialog4 = new LoginAlertDialog.Builder(context)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText(title)
                .setTitleTextColor(R.color.colorAccent)
                .setContentText(content)
                .setContentTextColor(R.color.black_text)
                .setLeftButtonText(cancelText)
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText(okText)
                .setRightButtonTextColor(R.color.white)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setCanceledOnTouchOutside(OutSideCancel)
                .build();
        return dialog4;
    }




}
