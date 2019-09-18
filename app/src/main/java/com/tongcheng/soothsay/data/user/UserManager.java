package com.tongcheng.soothsay.data.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tongcheng.soothsay.bean.dao.UserBean;
import com.tongcheng.soothsay.bean.dao.UserBeanDao;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.helper.GreenDaoHelper;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.SpUtil;

/**
 * Created by Steven on 16/11/9.
 * 用户信息管理类
 */

public class UserManager {

    private static UserManager manager;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (manager == null) {
            manager = new UserManager();
        }
        return manager;
    }

    public long saveUser(UserBean userBean) {
        if (getUser() != null) {
            deleteUser();
        }
        UserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getUserBeanDao();
        return dao.insert(userBean);
    }

    public UserBean getUser() {
        UserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getUserBeanDao();
        if (dao.queryBuilder().unique() == null) {
            return null;
        } else
            return dao.queryBuilder().unique();
    }

    public String getToken() {
        UserBean user = getUser();
        if (user != null) {
            return user.getToken();
        }
        return null;
    }


    /**
     * 获取融云token
     *
     * @return
     */
    public String getRyToken() {
        UserBean user = getUser();
        if (user != null) {
            return user.getRyToken();
        }
        return null;
    }

    public void setRyToken(String token) {
        UserBean user = getUser();
        if (user != null) {
            user.setRyToken(token);
            saveUser(user);
        }
    }

    public void deleteUser() {
        UserBeanDao dao = GreenDaoHelper.getInstance().getSeeion().getUserBeanDao();
        dao.delete(UserManager.getInstance().getUser());
    }

    public String getUserName(Context context) {
        String name = SpUtil.getString(context, "name", "");
        return name;
    }

    public void setUserName(Context context, String name) {
        SpUtil.putString(context, "name", name);
    }

    public String getUserPhotoUrl(Context context) {
        String photo = SpUtil.getString(context, "img", "");
        return photo;
    }

    public void setUserDate(Context context, String userDate) {
        SpUtil.putString(context, "userdate", userDate);
    }

    public void setUserPhone(Context context, String phone){
        SpUtil.putString(context, "userphone", phone);
    }
    public String getUserPhone(Context context) {
        String phone = SpUtil.getString(context, "userphone", "");
        return phone;
    }

    /**
     * @return yyyy.MM.dd
     */
    public String getUserDate(Context context) {
        String userDate = SpUtil.getString(context, "userdate", "");
        return userDate;
    }

    public void setUserPhotoUrl(Context context, String photoUrl) {
        SpUtil.putString(context, "img", photoUrl);
    }


    public void setUserJf(Context context, String userJF) {
        SpUtil.putString(context, "jf", userJF);
    }

    public String getUserJf(Context context) {
        String jf = SpUtil.getString(context, "jf", "0");
        return jf;
    }

    public void changeUserJF(Context context, int count) {
        String userJf = getUserJf(context);
        Integer integer = Integer.valueOf(userJf);
        int sum = integer + count;
        setUserJf(context, String.valueOf(sum));
    }


    /**
     * 是否登录
     *
     * @return true : false ? 已登录 : 未登录
     */
    public boolean isLogin() {
        if (getUser() != null) {
            return true;
        }

        return false;
    }


    /**
     * 如果没有登录  可以跳到登录界面
     *
     * @param activity
     * @return
     */
    public boolean isLogin(Activity activity) {
        if (getUser() != null) {
            return true;
        }
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, Constant.RequestCode.LOGIN);
        return false;
    }
}
