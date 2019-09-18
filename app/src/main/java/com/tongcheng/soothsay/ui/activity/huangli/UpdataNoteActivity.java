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
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

//修改记事
public class UpdataNoteActivity extends BaseTitleActivity implements View.OnClickListener, TimeDialog.OnTimeSetListener {


    @BindView(R.id.tv_add_note_date)        TextView        tvDate;
    @BindView(R.id.tv_updata_time)          TextView        tvTime;
    @BindView(R.id.tv_add_note_time)        TextView        tvWranTime;
    @BindView(R.id.edit_add_title)          EditText        editTitle;
    @BindView(R.id.edit_add_content)        EditText        editContent;
    @BindView(R.id.edit_add_warn)           EditText        editContentWran;
    @BindView(R.id.rl_add_note_time)        RelativeLayout  rlTime;

    @BindView(R.id.ll_add_note)             LinearLayout    layoutNote;        //记事布局
    @BindView(R.id.ll_add_warn)             LinearLayout    layoutWarn;        //提醒布局


    private int noteFlag ;          //1 记事  2提醒
    private int hour;
    private int min;
    private int month;
    private int day;
    private int dayOfWeek;

    private String time;
    private String days;
    private String months;

    private String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    private TimeDialog dialog;
    private Calendar cal;
    private CalendarNoteBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_note);

        bean = (CalendarNoteBean) getIntent().getSerializableExtra(OldAlmanacConstant.INTENT_DATE);
        initView();
    }

    @Override
    public void initView() {
        cal = Calendar.getInstance();
        getBaseHeadView().showBackButton(this);
        getBaseHeadView().showHeadRightButton("保存",this);

        if(bean != null){

            noteFlag = bean.getNoteFlag();
            //记事
            if(noteFlag == 1){
                getBaseHeadView().showTitle("修改记事");
                layoutNote.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.VISIBLE);

                editTitle.setText(bean.getTitle());
                editContent.setText(bean.getContent());

                cal.setTime(new Date(bean.getTime()));
                hour = cal.get(Calendar.HOUR_OF_DAY);
                min = cal.get(Calendar.MINUTE);
                String hours = hour < 10 ? "0" + hour : hour + "";
                String mins = min < 10 ? "0" + min : min + "";
                String time = bean.getDate() + " " + hours + ":" + mins;
                tvTime.setText(time);


            //提醒
            }else{
                dialog = new TimeDialog(this);
                getBaseHeadView().showTitle("修改提醒");
                layoutWarn.setVisibility(View.VISIBLE);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                try {
                    cal = Calendar.getInstance();
                    cal.setTime(sdf.parse(bean.getDate()));
                    month = cal.get(Calendar.MONTH) + 1;
                    day = cal.get(Calendar.DAY_OF_MONTH);
                    months = month < 10 ? "0" + month : "" + month;
                    days = day < 10 ? "0" + day : "" + day;
                    dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
                    String date = months+ "月"+days+"日"+"  "+ weekDays[dayOfWeek];

                    cal.setTime(new Date(bean.getTime()));
                    hour = cal.get(Calendar.HOUR_OF_DAY);
                    min = cal.get(Calendar.MINUTE);
                    time = hour + ":" + min;
                    String hours = hour < 10 ? "0" + hour : hour + "";
                    String mins = min < 10 ? "0" + min : min + "";
                    String times = hours + ":" + mins;
                    dialog = new TimeDialog(this,hour,min);
                    dialog.setOnTimeSetListener(this);

                    tvDate.setText(date);
                    tvWranTime.setText(times);
                    editContentWran.setText(bean.getTitle());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }


        }
    }

    @OnClick(R.id.rl_add_note_time)
    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;

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
                dialog.show();
                break;
        }
    }

    private void save(){
        if(noteFlag == 1){
            saveNote();

        }else{
            saveWran();

        }
    }

    private void saveNote(){
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

        bean.setContent(content);
        bean.setTitle(title);

        String d = cal.get(Calendar.YEAR) + "." + cal.get(Calendar.MONTH) + 1 + "." + cal.get(Calendar.DAY_OF_MONTH);
        EventBusUtil.post(new EventNoteBean(d,2,title,noteFlag));

        Intent intent = new Intent();
        intent.putExtra(OldAlmanacConstant.INTENT_DATE,bean);
        setResult(OldAlmanacConstant.INTENT_UPDATA_RESULT,intent);
        finish();

    }

    private void saveWran(){
        String content = editContentWran.getText().toString();
        if(TextUtils.isEmpty(content)){
            ToastUtil.showShort(this,"内容不能为空");
            return ;
        }

        String temp = bean.getDate() + " " + time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        try {
            cal.setTime(sdf.parse(temp));
            bean.setTime(cal.getTime().getTime());
            bean.setTitle(content);

            String d = cal.get(Calendar.YEAR) + "." + cal.get(Calendar.MONTH) + 1 + "." + cal.get(Calendar.DAY_OF_MONTH);
            EventBusUtil.post(new EventNoteBean(d,2,content,noteFlag));

            Intent intent = new Intent();
            intent.putExtra(OldAlmanacConstant.INTENT_DATE,bean);
            setResult(OldAlmanacConstant.INTENT_UPDATA_RESULT,intent);
            finish();

        } catch (ParseException e) {
            e.printStackTrace();
            ToastUtil.showShort(this,"发生未知错误");
            finish();
        }

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hours = hourOfDay < 10 ? "0" + hourOfDay : hourOfDay + "";
        String mins = minute < 10 ? "0" + minute : minute + "";
        time = hours + ":" + mins;
        tvWranTime.setText(time);
    }
}
