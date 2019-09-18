package com.tongcheng.soothsay.bean.calculation;

import java.util.List;

/**
 * Created by Steven on 16/12/21.
 */

public class FreeTopBean {

    private String title;

    private List<String> topList;

    public List<String> getTopList() {
        return topList;
    }

    public void setTopList(List<String> topList) {
        this.topList = topList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
