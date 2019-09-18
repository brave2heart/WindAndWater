package com.tongcheng.soothsay.bean.event;

/**
 * Created by 宋家任 on 2016/12/27.
 */

public class ChangePricebean {
    public int status;//是否全选标志，只要用户点击了条目一个就改成false

    public ChangePricebean(int status) {
        this.status = status;
    }

    public ChangePricebean() {
    }
}
