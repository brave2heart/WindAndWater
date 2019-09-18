package com.tongcheng.soothsay.bean.mine;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.tongcheng.soothsay.data.user.UserManager;

/**
 * Created by bozhihuatong on 2016/12/21.
 */

public class SignInBean {

    /**
     * jf : 266105
     */

    @SerializedName("jf")
    private String jf;

    public String getJf(){
        return jf;
    }

    public String getJf(Context context) {
        if (jf==null||jf.isEmpty())
            return UserManager.getInstance().getUserJf(context);
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }
}
