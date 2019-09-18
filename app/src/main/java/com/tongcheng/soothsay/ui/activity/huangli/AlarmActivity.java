package com.tongcheng.soothsay.ui.activity.huangli;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.MainActivity;
import com.tongcheng.soothsay.utils.AlarmManagerUtil;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 闹钟界面 当记事提醒时间到了跳转到这里
 */
public class AlarmActivity extends BaseTitleActivity {

    public final int DURATION_TIME = 1000*10;

    @BindView(R.id.tv_alarm_title)      TextView    tvTitle;
    @BindView(R.id.tv_alarm_time)       TextView    tvTime;
    @BindView(R.id.btn_alarm_look)      Button      btnLook;
    @BindView(R.id.btn_alarm_cancel)    Button      btnCancel;

    private Ringtone r;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_alarm);

        initView();
    }

    @Override
    public void initView() {
        String title = getIntent().getStringExtra(AlarmManagerUtil.ALARM_MSG);
        String time = getIntent().getStringExtra(AlarmManagerUtil.ALARM_TIME).split(" ")[1];
        tvTitle.setText(title);
        tvTime.setText(time);

        //2表示声音和震动都执行，1表示只有铃声提醒，0表示只有震动提醒
        int soundOrVa = getIntent().getIntExtra(AlarmManagerUtil.ALARM_SOUND,2);
        playSoundOrVibrate(soundOrVa);
    }

    @OnClick({R.id.btn_alarm_look,
            R.id.btn_alarm_cancel})
    public void onClick(View view){
        if (ClickUtil.isFastClick()) return;

        switch (view.getId()){
            case R.id.btn_alarm_look:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btn_alarm_cancel:
                finish();
                break;
        }
        r.stop();
        vibrator.cancel();

    }

    //播放声音或者震动
    public void playSoundOrVibrate(int flag){
        switch (flag){
            case 0:
                playVibrate();
                break;
            case 1:
                playSound();
                break;
            case 2:
                playVibrate();
                playSound();
                break;
        }

    }

    //播放系统提示音 android
    public void playSound(){
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }

    //播放震动
    public void playVibrate(){
        vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
        //数组参数意义：第一个参数为等待指定时间后开始震动，震动时间为第二个参数。后边的参数依次为等待震动和震动的时间
        //第二个参数为重复次数，-1为不重复，0为一直震动
        vibrator.vibrate(new long[]{100, 10, 100, 600}, -1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.KEYCODE_BACK){
            return true;
        }
        if(event.getAction() == KeyEvent.KEYCODE_HOME){
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
