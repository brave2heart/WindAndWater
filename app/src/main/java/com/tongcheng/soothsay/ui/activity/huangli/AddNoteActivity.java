package com.tongcheng.soothsay.ui.activity.huangli;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.dao.CalendarNoteBean;
import com.tongcheng.soothsay.bean.event.EventNoteBean;
import com.tongcheng.soothsay.dialog.TimeDialog;
import com.tongcheng.soothsay.ui.fragment.huangli.OldAlmanacConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

//添加记事
public class AddNoteActivity extends BaseTitleActivity implements View.OnClickListener, TimeDialog.OnTimeSetListener {

    public static final String BOUNDLE_KEY_DATA = "data";

    @BindView(R.id.tv_add_note_date)        TextView        tvDate;
    @BindView(R.id.tv_add_note_time)        TextView        tvTime;
    @BindView(R.id.edit_add_title)          EditText        editTitle;
    @BindView(R.id.edit_add_content)        EditText        editContent;
    @BindView(R.id.edit_add_warn)           EditText        editContentWran;
    @BindView(R.id.rl_add_note_time)        RelativeLayout  rlTime;

    @BindView(R.id.ll_add_note)             LinearLayout    layoutNote;        //记事布局
    @BindView(R.id.ll_add_warn)             LinearLayout    layoutWarn;        //提醒布局

    private int noteFlag;

    private String date;
    private String months;
    private String days;
    private String time = "06:00";
    private int day;
    private int month;

    private String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    private Calendar cal;

    private TimeDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_note);

        initView();
        initData();
    }

    @Override
    public void initView() {
        getBaseHeadView().showBackButton(this);
        getBaseHeadView().showHeadRightButton("保存",this);

        dialog = new TimeDialog(this);
        dialog.setOnTimeSetListener(this);
    }

    @Override
    public void initData() {
        noteFlag = getIntent().getIntExtra(OldAlmanacConstant.INTENT_KEY,1);
        date = getIntent().getStringExtra(OldAlmanacConstant.INTENT_DATE);

        if(noteFlag == 1){
            getBaseHeadView().showTitle("添加记事");
            layoutNote.setVisibility(View.VISIBLE);
        }else{
            getBaseHeadView().showTitle("添加提醒");
            layoutWarn.setVisibility(View.VISIBLE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            try {
                cal = Calendar.getInstance();
                cal.setTime(sdf.parse(date));
                month = cal.get(Calendar.MONTH) + 1;
                day = cal.get(Calendar.DAY_OF_MONTH);
                months = month < 10 ? "0" + month : "" + month;
                days = day < 10 ? "0" + day : "" + day;
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
                String date = months+ "月"+days+"日"+"  "+ weekDays[dayOfWeek];
                tvDate.setText(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    @OnClick({R.id.rl_add_note_time})
    @Override
    public void onClick(View v) {

        WindowUtil.closeInputMethod(this);
        switch (v.getId()){
            case R.id.headBackButton:
                finish();
                break;

            //保存
            case R.id.headRightButton:
                save();
                break;

            //选择时间
            case R.id.rl_add_note_time:
                if (ClickUtil.isFastClick()) return;
                dialog.show();
                break;
        }
    }

    private void save() {
       if(noteFlag == 1){
            saveNote();
       }else{
            saveWarn();
       }
    }

    //保存记事
    private void saveNote(){
        CalendarNoteBean bean = new CalendarNoteBean();

        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();

        if(TextUtils.isEmpty(title)){
            ToastUtil.showShort(this,"标题不能为空");
            return ;
        }

        if(TextUtils.isEmpty(content)){
            ToastUtil.showShort(this,"内容不能为空");
            return ;
        }

        cal = Calendar.getInstance();

        bean.setTitle(title);
        bean.setContent(content);
        bean.setDate(date);
        bean.setTime(cal.getTime().getTime());
        bean.setNoteFlag(noteFlag);

        EventBusUtil.post(new EventNoteBean(date,1,title,noteFlag));


        Intent intent = new Intent();
        intent.putExtra(BOUNDLE_KEY_DATA,bean);
        setResult(OldAlmanacConstant.INTENT_RESULT,intent);
        finish();
    }

    //保存提醒
    private void saveWarn(){
        CalendarNoteBean bean = new CalendarNoteBean();

        String content = editContentWran.getText().toString();
        if(TextUtils.isEmpty(content)){
            ToastUtil.showShort(this,"内容不能为空");
            return ;
        }

        String temp = date + " " + time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        try {
            cal.setTime(sdf.parse(temp));
            bean.setTime(cal.getTime().getTime());
            bean.setTitle(content);
            bean.setDate(date);
            bean.setNoteFlag(noteFlag);
            int alarmId = createAlarmId();
            bean.setAlarm(alarmId);

            String d = cal.get(Calendar.YEAR) + "." + cal.get(Calendar.MONTH) + 1 + "." + cal.get(Calendar.DAY_OF_MONTH);
            EventBusUtil.post(new EventNoteBean(d,1,content,noteFlag));

            Intent intent = new Intent();
            intent.putExtra(BOUNDLE_KEY_DATA,bean);
            setResult(OldAlmanacConstant.INTENT_RESULT,intent);
            finish();

        } catch (ParseException e) {
            e.printStackTrace();
            ToastUtil.showShort(this,"发生未知错误");
            finish();
        }

    }

    //根据日期创建闹钟id
    private int createAlarmId (){
        String temp []  = date.split("\\.");
        int year = Integer.valueOf(temp[0]);
        int month = Integer.valueOf(temp[1]);
        int day = Integer.valueOf(temp[2]);
        int hour = Integer.valueOf(time.split(":")[0]);
        int m = Integer.valueOf(time.split(":")[1]);

        return year + month + day + hour + m;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hours = hourOfDay < 10 ? "0" + hourOfDay : hourOfDay + "";
        String mins = minute < 10 ? "0" + minute : minute + "";
        time = hours + ":" + mins;
        tvTime.setText(time);
    }
}
