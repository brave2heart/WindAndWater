package com.tongcheng.soothsay.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.widget.CustomDialog;


/**
 * Created by 宋家任 on 2016/12/12.
 * 登录界面对话框
 */

public class CusDialogUtil {
    private Activity mActivity;

    public CusDialogUtil(Activity activity) {
        this.mActivity = activity;
    }


    /**
     * 系统默认对话框（只是弹出提醒用户而言..）
     *
     * @param title
     * @param content
     * @param isSingle 是否有取消按钮
     */
    public void showDefaultDialog(String title, String content, boolean isSingle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(content);
        if (isSingle) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 默认对话框（确定进行操作）
     *
     * @param title
     * @param content
     * @param onClickListener 确认按钮监听
     */
    public void showCusDefaultDialog(String title, String content, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("确定", onClickListener);
        builder.show();
    }
    public void showCusDefaultDialog(String title, String content,DialogInterface.OnClickListener onNeListener, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("取消",onNeListener);

        builder.setPositiveButton("确定", onClickListener);
        builder.show();
    }

    /**
     * 没有标题对话框
     *
     * @param content
     * @param onClickListener
     */
    public void showCusNoTitleDialog(String content, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage(content);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("确定", onClickListener);
        builder.show();
    }

    /**
     * 使用系统默认布局的对话框跳转登录界面
     *
     * @param contentRes
     * @param requestCode
     */
    public void showDefaultLoginDialog(int contentRes, final int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("登录风生水账号");
        builder.setMessage(mActivity.getResources().getString(contentRes));

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivityForResult(mActivity, LoginActivity.class, requestCode);
            }
        });
        builder.show();
    }

    /**
     * 使用系统默认布局的对话框跳转登录界面
     *
     * @param contentRes
     */
    public void showDefaultLoginDialog(int contentRes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("登录风生水账号");
        builder.setMessage(mActivity.getResources().getString(contentRes));

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivity(mActivity, LoginActivity.class);
            }
        });
        builder.show();
    }

    /**
     * 使用自定义布局的对话框
     *
     * @param contentRes
     * @param requestCode
     */
    public void showCusDialog(int contentRes, final int requestCode) {
        CustomDialog.Builder builder = new CustomDialog.Builder(mActivity);
        builder.setTitle("登录风生水账号");
        builder.setMessage(mActivity.getResources().getString(contentRes));
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivityForResult(mActivity, LoginActivity.class, requestCode);
            }
        });


        builder.create().show();
    }

    /**
     * 使用自定义布局的对话框
     *
     * @param title       标题
     * @param cancelText  取消按钮文字
     * @param sureText    确定按钮文字
     * @param contentRes  内容文字的id
     * @param requestCode 请求码
     */
    public void showCusDialog(String title, String cancelText, String sureText, int contentRes, final int requestCode) {
        CustomDialog.Builder builder = new CustomDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(mActivity.getResources().getString(contentRes));
        builder.setNegativeButton(cancelText,
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton(sureText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivityForResult(mActivity, LoginActivity.class, requestCode);
            }
        });
        builder.create().show();
    }

    /**
     * 登录后不需要刷新当前界面的对话框
     *
     * @param contentRes 对话框内容
     */
    public void showLoginDialogNoRefresh(int contentRes) {
        CustomDialog.Builder builder = new CustomDialog.Builder(mActivity);
        builder.setTitle("登录风生水账号");
        builder.setMessage(mActivity.getResources().getString(contentRes));
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivity(mActivity, LoginActivity.class);
            }
        });
        builder.create().show();
    }

    public CustomDialog showLoginDialg(int contentRes, final int requestCode) {
        return showLoginDialg(contentRes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                UserManager.getInstance().deleteUser();
                UserManager.getInstance().setUserPhotoUrl(mActivity, "");
                UserManager.getInstance().setUserName(mActivity, "");
                UserManager.getInstance().setUserDate(mActivity, "");
                GoodsIdUtils.getInstance().deleteGoodsList();
                EventBusUtil.post(new EventLoginBean(EventLoginBean.OUT_LOGIN));
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserManager.getInstance().deleteUser();
                UserManager.getInstance().setUserPhotoUrl(mActivity, "");
                UserManager.getInstance().setUserName(mActivity, "");
                UserManager.getInstance().setUserDate(mActivity, "");
                GoodsIdUtils.getInstance().deleteGoodsList();
                EventBusUtil.post(new EventLoginBean(EventLoginBean.OUT_LOGIN));
                GotoUtil.GoToActivityForResult(mActivity, LoginActivity.class, requestCode);
                dialog.dismiss();
            }
        });
    }

    public CustomDialog showLoginDialg(int contentRes, final int requestCode, DialogInterface.OnClickListener cancelListener) {
        return showLoginDialg(contentRes, cancelListener, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivityForResult(mActivity, LoginActivity.class, requestCode);
            }
        });
    }


    public CustomDialog showLoginDialg(int contentRes, DialogInterface.OnClickListener cancelListener, DialogInterface.OnClickListener sureListener) {
        return showDialog("登录风生水账号", "取消", "登录", contentRes, cancelListener, sureListener, false);
    }


    public CustomDialog showDialog(String title, String cancelText, String sureText, int contentRes, DialogInterface.OnClickListener cancelListener, DialogInterface.OnClickListener sureListener, boolean flag) {
        CustomDialog.Builder builder = new CustomDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(mActivity.getResources().getString(contentRes));
        builder.setNegativeButton(cancelText, cancelListener
             /*   new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }*/);
        builder.setPositiveButton(sureText, sureListener /*new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                GotoUtil.GoToActivityForResult(mActivity, LoginActivity.class, requestCode);
            }
        }*/);
        CustomDialog customDialog = builder.create();
        customDialog.setCancelable(flag);
        customDialog.show();
        return customDialog;
    }
}


