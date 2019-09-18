package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;

/**
 * @description: 命理推算-祈福转运的测试实体类，到时要以实际接口为准
 * @author: lijuan
 * @date: 2016-10-25
 * @time: 15:56
 */
public class CliffordBean implements Serializable {
    private int id;
    private String name;
    private String content;

    public CliffordBean(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public CliffordBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
