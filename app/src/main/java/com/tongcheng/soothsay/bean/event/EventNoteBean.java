package com.tongcheng.soothsay.bean.event;

/**
 * Created by Steven on 17/1/6.
 */
public class EventNoteBean {

    public static final int FLAG_DEL = 0;   //0为删除记事  1为增加记事  2修改记事
    public static final int FLAG_ADD = 1;   //0为删除记事  1为增加记事  2修改记事
    public static final int FLAG_UPDATA = 2;   //0为删除记事  1为增加记事  2修改记事
    public String date ;   //yyyy.mm.dd
    public int flag = 0;

    private int type ;   //1为记事  2为提醒

    private String title;

    public EventNoteBean(String date, int flag, String title, int type) {
        this.date = date;
        this.flag = flag;
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
