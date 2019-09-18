package com.tongcheng.soothsay.bean.dao;

import android.view.View;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * 黄历记事
 * Created by Steven on 16/11/4.
 */

@Entity
public class CalendarNoteBean implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id(autoincrement = true)
    private Long id;

    private String date;        //日期

    private String title;

    private String content;     //内容

    private Long time;          //提醒时间Or记事时间

    private int noteFlag;       //0 是日期相等 1是小于 (跳转记事)  2是大于 （跳转提醒）

    private int alarm;          //闹钟id 用户取消闹钟


    @Generated(hash = 392352981)
    public CalendarNoteBean(Long id, String date, String title, String content,
            Long time, int noteFlag, int alarm) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.time = time;
        this.noteFlag = noteFlag;
        this.alarm = alarm;
    }

    @Generated(hash = 1743054525)
    public CalendarNoteBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNoteFlag() {
        return this.noteFlag;
    }

    public void setNoteFlag(int noteFlag) {
        this.noteFlag = noteFlag;
    }

    public int getAlarm() {
        return this.alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CalendarNoteBean){
            CalendarNoteBean bean = (CalendarNoteBean) obj;
            return this.id ==  bean.getId();
        }
        return false;
    }
}
