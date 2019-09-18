package com.tongcheng.soothsay.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;

/**
 * Created by 宋家任 on 2016/12/13.
 * 错误码工具类(处理公共的错误码)
 */

public class ErrorCodeUtil {
    /**
     * 需要token的错误码工具方法(网络错误在这个方法只是打toast，无网络需要showEmptyView的界面不适合这个方法)
     *
     * @param errorCode 错误码
     * @param activity
     */
    public static boolean showHaveTokenError(Activity activity, String errorCode) {
        //网络错误
        if (errorCode == ApiCallBack.NET_ERROR) {
            ToastUtil.showShort(activity, activity.getResources().getString(R.string.net_error));
            return true;
            //token过期,弹出登录对话框
        } else if (Constant.ErrorCode.TOKEN_ERROR_10001.equals(errorCode)) {
            new CusDialogUtil(activity).showLoginDialogNoRefresh(R.string.token_stale);
            return true;
            //查不到用户
        } else if (Constant.ErrorCode.RESPONSE_ERROR_LOST_10007.equals(errorCode)) {
            new CusDialogUtil(activity).showLoginDialogNoRefresh(R.string.login_error);
            return true;
            //服务器系统错误
        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
            ToastUtil.showShort(activity, "服务器系统错误");
            return true;
            //服务器繁忙
        } else if (Constant.ErrorCode.SERVER_BUSY.equals(errorCode)) {
            ToastUtil.showShort(activity, "服务器开小差了，请稍等");
            return true;
        } else return false;
    }

    /**
     * 网络错误、系统错误、重新加载 其他错误不需请求码（不需刷新数据）
     *
     * @param activity
     * @param errorCode
     * @param onlick
     */
    public static void showEmptyView(BaseTitleActivity activity, String errorCode, View.OnClickListener onlick) {
        //网络错误
        if (errorCode == ApiCallBack.NET_ERROR) {
            activity.getBaseEmptyView().showNetWorkView(onlick);
            activity.getBaseLoadingView().hideLoading();
            ToastUtil.showShort(activity, activity.getResources().getString(R.string.net_error));

            //服务器系统错误
        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
            activity.getBaseEmptyView().showEmptyView(R.drawable.load_error, "服务器系统错误", "重试", onlick);
            activity.getBaseLoadingView().hideLoading();
            ToastUtil.showShort(activity, "服务器系统错误");

            //token过期,弹出登录对话框
        } else if (Constant.ErrorCode.TOKEN_ERROR_10001.equals(errorCode)) {
            new CusDialogUtil(activity).showLoginDialogNoRefresh(R.string.token_stale);

            //多设备登录
        } else if (Constant.ErrorCode.RESPONSE_ERROR_LOST_10007.equals(errorCode)) {
            new CusDialogUtil(activity).showLoginDialogNoRefresh(R.string.login_error);


        } else if (Constant.ErrorCode.SERVER_BUSY.equals(errorCode)) {
            ToastUtil.showShort(activity, "服务器开小差了，请稍等");
        }
    }

    /**
     * 网络错误、系统错误、重新加载 其他错误需请求码（需刷新数据）
     *
     * @param activity
     * @param errorCode
     * @param onlick
     * @param requestCode
     */
    public static void showEmptyView(BaseTitleActivity activity, String errorCode, View.OnClickListener onlick, int requestCode) {
        if (errorCode == ApiCallBack.NET_ERROR) {
            activity.getBaseEmptyView().showNetWorkView(onlick);
            activity.getBaseLoadingView().hideLoading();
            ToastUtil.showShort(activity, activity.getResources().getString(R.string.net_error));

            //服务器系统错误
        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
            activity.getBaseEmptyView().showEmptyView(R.drawable.load_error, "服务器系统错误", "重试", onlick);
            activity.getBaseLoadingView().hideLoading();
            ToastUtil.showShort(activity, "服务器系统错误");

            //token过期,弹出登录对话框
        } else if (Constant.ErrorCode.TOKEN_ERROR_10001.equals(errorCode)) {
            new CusDialogUtil(activity).showLoginDialg(R.string.token_stale, requestCode);

            //查不到用户
        } else if (Constant.ErrorCode.RESPONSE_ERROR_LOST_10007.equals(errorCode)) {
            new CusDialogUtil(activity).showLoginDialg(R.string.login_error, requestCode);
        }
    }

    /**
     * @param errorCode 错误码
     * @param view      view层接口
     */
    public static void handleErr(BaseUiView view, String errorCode, String msg) {
        view.showLoadFinish();
        //网络错误
        if (errorCode == ApiCallBack.NET_ERROR) {
            view.showNetError();

            //服务器系统错误
        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
            view.showError("服务器繁忙");

            //其他错误
        } else {
            view.showError(msg);
        }
    }

    /**
     * 用于有token验证的情况
     *
     * @param errorCode 错误码
     * @param view      view层接口
     */
    public static void handleErr(BaseUserView view, String errorCode, String msg) {
        view.showLoadFinish();
        //网络错误
        if (errorCode == ApiCallBack.NET_ERROR) {
            view.showNetError();

            //token过期,弹出登录对话框
        } else if (Constant.ErrorCode.TOKEN_ERROR_10001.equals(errorCode)) {
            view.showTokenOverdue();

            //除了登录 注册 界面外   其他界面查不到用户全都是多端登录
        } else if (Constant.ErrorCode.RESPONSE_ERROR_LOST_10007.equals(errorCode)) {
            view.showTokenOverdue();

            //服务器系统错误
        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
            view.showError("服务器繁忙");

            //其他错误
        } else {
            view.showError(msg);

        }
    }

    /**
     * 没网络和服务器系统错误
     *
     * @param errorCode
     */
    public static boolean shownetWorkAndServerError(Context context, String errorCode) {
        //网络错误
        if (errorCode == ApiCallBack.NET_ERROR) {
            ToastUtil.showShort(context, context.getResources().getString(R.string.net_error));
            return true;
            //服务器系统错误
        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
            ToastUtil.showShort(context, "服务器系统错误");
            return true;
        } else return false;
    }
}
