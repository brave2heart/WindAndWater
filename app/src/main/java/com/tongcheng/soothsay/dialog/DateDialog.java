package com.tongcheng.soothsay.dialog;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.lang.reflect.Field;
import java.util.Calendar;

public class DateDialog{

    private OnDateSelectorListener listener;
    private DatePickerDialog dialog;
    private Context context;

    public DateDialog(Context context) {
        this.context=context;
        initView();
    }

    public void initView() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        dialog = new DatePickerDialog(context,new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                if(listener!=null){
                    listener.onDateChanged(view, year, monthOfYear+1, dayOfMonth);
                }
                dialog.dismiss();
            }
        }, year, monthOfYear, dayOfMonth);
        setDatePickerDividerColor(dialog.getDatePicker());
    }


    public void show(){
        dialog.show();
    }


    /**
     * 时间选择器监听
     */
    public interface OnDateSelectorListener{
        public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth);
    }

    public OnDateSelectorListener getListener() {
        return listener;
    }

    public void setOnDateSelectorListener(OnDateSelectorListener listener) {
        this.listener = listener;
    }


    private void setDatePickerDividerColor(DatePicker datePicker){
        // Divider changing:

        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.colorAccent)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(pf.getName().equals("mSelectionDividerHeight")){
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, WindowUtil.dip2px(context, 1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
