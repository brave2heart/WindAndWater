package com.tongcheng.soothsay.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.event.MusicEventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by bozhihuatong on 2016/12/14.
 */

public class MusicService extends Service {


    public static MediaPlayer mp=new MediaPlayer();


    public final IBinder binder = new MyBinder();
    public class MyBinder extends Binder {
       public  MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        mp = MediaPlayer.create(MusicService.this, R.raw.qifu_taijiyun);
        playOrPause();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                Intent intent = new Intent();
//                intent.setAction("MusicFinish");
//                sendBroadcast(intent);
                playOrPause();
                MusicEventBean musicEventBean = new MusicEventBean(MusicEventBean.STOP_KEY);
                EventBus.getDefault().post(musicEventBean);
            }
        });
    }


    @Subscribe
    public void musicEvent(MusicEventBean bean){
        switch (bean.getEvent()) {
            case MusicEventBean.PLAY_KEY:
                playOrPause();
                break;
            case MusicEventBean.BYSTOP_KEY:
                stop();
                break;
        }
    }


    public void setMusic(Context context,int resId){
        mp=MediaPlayer.create(context,resId);
    }

    public void play(){
        if (mp==null)return;
        if (mp.isPlaying())return;
        mp.start();
    }

    public void playOrPause() {
        if (mp==null)return;
        if(mp.isPlaying()){
            mp.pause();
        } else {
            mp.start();
        }
    }
    public void stop() {
        if(mp != null) {
            mp.stop();
            try {
                mp.prepare();
                mp.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @Override
    public void onDestroy() {
        stop();
        if (mp!=null) {
            mp=null;
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

