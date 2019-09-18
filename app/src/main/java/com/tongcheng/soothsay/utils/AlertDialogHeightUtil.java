package com.tongcheng.soothsay.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ALing on 2017/1/10 0010.
 */

public class AlertDialogHeightUtil {
    public static void halfHeght(AlertDialog dialog,Activity context){
        WindowManager manager =context.getWindowManager();
        Display d = manager.getDefaultDisplay();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.5);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        dialog.getWindow().setAttributes(params);
    }
}
