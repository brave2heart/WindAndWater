package com.tongcheng.soothsay.dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.WindowUtil;
import java.lang.reflect.Field;
import java.util.Calendar;

public class TimeDialog implements android.app.TimePickerDialog.OnTimeSetListener {

    private Context  context;
    private TimePickerDialog dialog;
    private OnTimeSetListener listener;


    public TimeDialog(Context context,int hour,int min) {
        this.context = context;
        initView(hour,min);
    }

    public TimeDialog(Context context) {
        this.context = context;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        initView(hour,min);
    }

    private void initView(int hour,int min) {

        dialog = new TimePickerDialog(context,android.R.attr.timePickerDialogTheme,this, hour, min, true);
        try {
            setDatePickerDividerColor(dialog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public interface OnTimeSetListener{

        void onTimeSet(TimePicker view, int hourOfDay, int minute);

    }

    public OnTimeSetListener getListener() {
        return listener;
    }

    public void setOnTimeSetListener(OnTimeSetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(listener == null) return ;
        listener.onTimeSet(view, hourOfDay, minute);
        dialog.dismiss();
    }

    public void show(){
        dialog.show();
    }



    //修改分隔线颜色
    private void setDatePickerDividerColor(TimePickerDialog dialog) throws IllegalAccessException, IllegalArgumentException{
        // Divider changing:
        Field[] timeFields = TimePickerDialog.class.getDeclaredFields();
        TimePicker timePicker = null;
        for(Field pf : timeFields){
            if (pf.getName().equals("mTimePicker")) {
                pf.setAccessible(true);
                timePicker = (TimePicker) pf.get(dialog);
            }
        }

        Field[] numFields = TimePicker.class.getDeclaredFields();
        for (Field pf : numFields) {
            NumberPicker picker = null;

            if (pf.getName().endsWith("Spinner")) {
                pf.setAccessible(true);
                picker = (NumberPicker) pf.get(timePicker);
                Field[] pickerFields = NumberPicker.class.getDeclaredFields();
                for (Field pf1 : pickerFields) {
                    if (pf1.getName().equals("mSelectionDivider")) {
                        pf1.setAccessible(true);
                        try {
                            pf1.set(picker, new ColorDrawable(context.getResources().getColor(R.color.colorAccent)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(pf1.getName().equals("mSelectionDividerHeight")){
                        pf1.setAccessible(true);
                        try {
                            pf1.set(picker, WindowUtil.dip2px(context, 1));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

}
