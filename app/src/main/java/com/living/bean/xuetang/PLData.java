package com.living.bean.xuetang;

/**
 * Created by weihao on 2018/3/5.
 */

public class PLData {

    private int userIcon;
    private String userName;
    private String location;
    private String commentTime;
    private String comment;

    public PLData(int userIcon, String userName, String location, String commentTime, String comment) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.location = location;
        this.commentTime = commentTime;
        this.comment = comment;
    }

    public int getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(int userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
