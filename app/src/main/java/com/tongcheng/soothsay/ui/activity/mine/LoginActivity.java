package com.tongcheng.soothsay.ui.activity.mine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.dao.UserBean;
import com.tongcheng.soothsay.bean.event.DontLoginBean;
import com.tongcheng.soothsay.bean.event.EventLoginBean;
import com.tongcheng.soothsay.bean.mine.LoginBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.JPushHelper;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/20.
 * 登录注册界面 注：新浪和微信两个平台id和key没拿到，暂时搁置这两个
 * 2017/1/3
 * 一期无微博登录需求,其他两端idkey无问题
 */

public class LoginActivity extends BaseTitleActivity {

    @BindView(R.id.btn_login_register)
    BootstrapButton btnRegister;
    @BindView(R.id.btn_login_login)
    BootstrapButton btnLogin;
    @BindView(R.id.et_login_phone)
    TextInputEditText etPhone;
    @BindView(R.id.et_login_password)
    TextInputEditText etPassword;
    @BindView(R.id.ibtn_can_read_psd)
    ImageButton btnReadPsd;//密码是否可见
    @BindView(R.id.tv_longin_forget)
    TextView tvLonginForget;

    String mobile;
    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;
    @BindView(R.id.iv_login_weixin)
    ImageView ivLoginWeixin;
//    @BindView(R.id.iv_login_weibo)
//    ImageView ivLoginWeibo;

    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        etPhone.addTextChangedListener(new MobileTextWatcher());
        mDialog = new ProgressDialog(this);

    }


    @OnClick({R.id.btn_login_register, R.id.btn_login_login,
            R.id.ibtn_can_read_psd, R.id.tv_longin_forget,
            R.id.iv_login_qq, R.id.iv_login_weixin,/* R.id.iv_login_weibo*/})
    public void onClick(View view) {
        if (ClickUtil.isFastClick()) return;

        UMShareAPI mShareAPI = UMShareAPI.get(LoginActivity.this);
        switch (view.getId()) {
            case R.id.btn_login_register://注册
                Intent intent = new Intent(this, PhoneNumberRegisterActivity.class);
                intent.putExtra("isThird", "no");
                startActivityForResult(intent, Constant.RequestCode.REGISTER);
                break;
            case R.id.btn_login_login://登录
                if (etPhone.length() == 13 && etPassword.length() > 5
                        && etPassword.length() < 21) {
                    login();
                } else if (TextUtils.isEmpty(mobile) || etPhone.length() != 13) {
                    ToastUtil.showShort(this, "请输入正确的手机号");
                    return;
                } else if (TextUtils.isEmpty(etPassword.getText().toString()) || etPassword.length() <= 5) {
                    ToastUtil.showShort(this, "请输入正确的密码");
                }
                break;
            case R.id.ibtn_can_read_psd://密码是否可见
                canReadPsd();
                break;
            case R.id.tv_longin_forget:
                GotoUtil.GoToActivityForResult(this, ForgetPsdActivity.class, Constant.RequestCode.FORGET);
                break;
            case R.id.iv_login_qq://qq登录
                boolean qqAvilible = SystemTools.isQQClientAvailable(this);
                if (qqAvilible) {
                    mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mDialog.show();
                    mDialog.setMessage("正在努力授权登录，请等待..");
                }
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.iv_login_weixin://微信登录
                boolean weixinAvilible = SystemTools.isWeixinAvilible(this);
                if (weixinAvilible) {
                    mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mDialog.show();
                    mDialog.setMessage("正在努力授权登录，请等待..");
                }
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);

                break;
//            case R.id.iv_login_weibo://微博登录
//                ToastUtil.showShort(this, "微博登录");
////                mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.SINA, authListener);
//                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, authListener);
//                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.REGISTER && resultCode == 9999) {
            //手机注册成功 返回上一页面
            Intent intent = new Intent();
            setResult(9999, intent);
            finish();
        }
        if (requestCode == Constant.RequestCode.FORGET && resultCode == 9999) {
            //找回密码成功 返回上一页面
            Intent intent = new Intent();
            setResult(9999, intent);
            finish();
        }
        if (requestCode == Constant.RequestCode.QQLOGIN && resultCode == 9999) {
            //注册成功
            Intent intent = new Intent();
            setResult(9999, intent);
            finish();
        }
        if (requestCode == Constant.RequestCode.WEIXINLOGIN && resultCode == 9999) {
            //注册成功
            Intent intent = new Intent();
            setResult(9999, intent);
            finish();
        }
    }

    /**
     * 登录
     */
    private void login() {
        final String phone = mobile.replaceAll(" ", "");
        String psd = etPassword.getText().toString();
        long l = System.currentTimeMillis();//当前时间毫秒数
        String time = String.valueOf(l);
        AllApi.getInstance().login(phone, psd, time)
                .enqueue(new ApiCallBack<ApiResponseBean<LoginBean>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        LoginBean bean = (LoginBean) data;
                        String phone1 = bean.getPhone();
                        String token = bean.getToken();
                        if (token != null) {
                            UserBean userBean = new UserBean(token);
                            UserManager.getInstance().saveUser(userBean);
                        }
                        UserManager.getInstance().setUserPhotoUrl(LoginActivity.this, bean.getHeadPic());
                        UserManager.getInstance().setUserName(LoginActivity.this, bean.getName());
                        UserManager.getInstance().setUserJf(LoginActivity.this, bean.getJf());
                        UserManager.getInstance().setUserPhone(LoginActivity.this, bean.getPhone());
                        if (!"".equals(bean.getBornTime())) {
                            String s = DateUtil.stampToDate(bean.getBornTime());
                            UserManager.getInstance().setUserDate(LoginActivity.this, s);
                        }
                        ToastUtil.showShort(LoginActivity.this, "登录成功");
                        //极光推送
                        String alias = UserManager.getInstance().getUserPhone(LoginActivity.this);
                        JPushHelper.getInstance().setJPushAlias(LoginActivity.this, alias);
                        Log.e(TAG, "onSuccess: "+alias );
                        //登录成功 返回上一页面
                        EventBusUtil.post(new EventLoginBean(EventLoginBean.LOGIN));
                        Intent intent = new Intent();
                        setResult(Constant.ResultCode.LOGIN, intent);
                        finish();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        if ("10010".equals(errorCode)) {
                            ToastUtil.showShort(LoginActivity.this, "用户名或密码错误");
                        } else if (errorCode == ApiCallBack.NET_ERROR) { //网络错误
                            ToastUtil.showShort(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.net_error));

                            //服务器系统错误
                        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                            ToastUtil.showShort(LoginActivity.this, "服务器系统错误");
                        } else
                            ToastUtil.showShort(LoginActivity.this, message);
                    }
                }));
    }


    boolean canRead = true;

    /**
     * 密码是否可见
     */

    private void canReadPsd() {
        if (canRead) {
            btnReadPsd.setSelected(true);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            canRead = false;
        } else if (canRead == false) {
            btnReadPsd.setSelected(false);
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            canRead = true;
        }

    }

    /**
     * 登录监听
     */
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            if (platform == SHARE_MEDIA.QQ) {
                if (data != null)
                    qqLogin(data);
                else
                    ToastUtil.showShort(LoginActivity.this, "未知错误，请重新登录");

            } else if (platform == SHARE_MEDIA.WEIXIN) {
                if (data != null)
                    weixinLogin(data);
                else
                    ToastUtil.showShort(LoginActivity.this, "未知错误，请重新登录");
            }
//            else if (platform == SHARE_MEDIA.SINA) {
//                if (data != null) {
//                    weiboLogin(data);一期无微博登录需求
//                }
//            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtil.showShort(LoginActivity.this, "登录失败：" + t.getMessage());
            mDialog.hide();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            mDialog.hide();
        }
    };


    /**
     * qq登录
     */
    private void qqLogin(Map<String, String> data) {
        final String uid = data.get("uid");//用户id
        final String screenName = data.get("screen_name");//昵称
        final String profileImageUrl = data.get("profile_image_url");//头像
        final String gender = data.get("gender");//性别
        AllApi.getInstance().
                thirdPartylogin("1", uid).
                enqueue(new ApiCallBack<ApiResponseBean<LoginBean>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        LoginBean bean = (LoginBean) data;
                        String token = bean.getToken();
                        if (token != null) {
                            UserBean userBean = new UserBean(token);
                            if (UserManager.getInstance().getUser() != null) {
                                UserManager.getInstance().deleteUser();
                            }
                            UserManager.getInstance().saveUser(userBean);
                        }
                        UserManager.getInstance().setUserPhotoUrl(LoginActivity.this, bean.getHeadPic());
                        UserManager.getInstance().setUserName(LoginActivity.this, bean.getName());
                        mDialog.hide();
                        ToastUtil.showLong(LoginActivity.this, "登录成功");
                        //登录成功 返回上一页面
//                        EventBus.getDefault().post(new LoginRefreshBean());
                        //登录成功 返回上一页面
                        EventBusUtil.post(new EventLoginBean(EventLoginBean.LOGIN));
                        Intent intent = new Intent();
                        setResult(Constant.ResultCode.LOGIN, intent);
                        finish();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        if ("10006".equals(errorCode)) {//未进行手机注册，跳转手机注册页
                            Intent intent = new Intent(LoginActivity.this, PhoneNumberRegisterActivity.class);
                            intent.putExtra("uid", uid);
                            intent.putExtra("screenName", screenName);
                            intent.putExtra("profileImageUrl", profileImageUrl);
                            if (!"".equals(gender))
                                intent.putExtra("gender", gender);
                            intent.putExtra("isThird", "yes");
                            intent.putExtra("thirdtype", "1");
                            startActivityForResult(intent, Constant.RequestCode.QQLOGIN);
                            ToastUtil.showLong(LoginActivity.this, "绑定QQ成功，请绑定手机号");
                        } else {
                            //网络错误
                            if (errorCode == ApiCallBack.NET_ERROR) {
                                ToastUtil.showShort(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.net_error));

                                //服务器系统错误
                            } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                                ToastUtil.showShort(LoginActivity.this, "服务器系统错误");
                            } else
                                ToastUtil.showShort(LoginActivity.this, message);
                        }

                    }
                }));
    }

    /**
     * 微信登录
     *
     * @param data
     */
    private void weixinLogin(Map<String, String> data) {
        final String unionid = data.get("unionid");
        final String screen_name = data.get("screen_name");
        final String profile_image_url = data.get("profile_image_url");
        AllApi.getInstance().
                thirdPartylogin("2", unionid).
                enqueue(new ApiCallBack<ApiResponseBean<LoginBean>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {

                        LoginBean bean = (LoginBean) data;
                        String token = bean.getToken();
                        if (token != null) {
                            UserBean userBean = new UserBean(token);
                            UserManager.getInstance().saveUser(userBean);
                        }
                        UserManager.getInstance().setUserPhotoUrl(LoginActivity.this, bean.getHeadPic());
                        UserManager.getInstance().setUserName(LoginActivity.this, bean.getName());
                        UserManager.getInstance().setUserPhone(LoginActivity.this, bean.getPhone());
                        mDialog.hide();
                        ToastUtil.showLong(LoginActivity.this, "登录成功");
                        //登录成功 返回上一页面
//                        EventBus.getDefault().post(new LoginRefreshBean());
                        //登录成功 返回上一页面
                        EventBusUtil.post(new EventLoginBean(EventLoginBean.LOGIN));
                        Intent intent = new Intent();
                        setResult(Constant.ResultCode.LOGIN, intent);
                        finish();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        if ("10006".equals(errorCode)) {//未进行手机注册，跳转手机注册页
                            Intent intent = new Intent(LoginActivity.this, PhoneNumberRegisterActivity.class);
                            intent.putExtra("openid", unionid);
                            intent.putExtra("screenName", screen_name);
                            intent.putExtra("profileImageUrl", profile_image_url);
                            intent.putExtra("isThird", "yes");
                            intent.putExtra("thirdtype", "2");
                            startActivityForResult(intent, Constant.RequestCode.WEIXINLOGIN);
                            ToastUtil.showLong(LoginActivity.this, "绑定微信成功，请绑定手机号");
                        } else {
                            //网络错误
                            if (errorCode == ApiCallBack.NET_ERROR) {
                                ToastUtil.showShort(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.net_error));

                                //服务器系统错误
                            } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                                ToastUtil.showShort(LoginActivity.this, "服务器系统错误");
                            } else
                                ToastUtil.showShort(LoginActivity.this, message);
                        }
                    }
                }));

    }

    /**
     * 微博登录
     * 一期无微博登录需求
     *
     * @param data
     */
//    private void weiboLogin(Map<String, String> data) {
//        final String uid = data.get("uid");//用户id
//        final String screenName = data.get("screen_name");//昵称
//        final String profileImageUrl = data.get("profile_image_url");//头像
//        final String gender = data.get("gender");//性别
//        AllApi.getInstance().
//                thirdPartylogin("3", uid).
//                enqueue(new ApiCallBack<ApiResponseBean<LoginBean>>(new BaseCallBack() {
//                    @Override
//                    public void onSuccess(Object data) {
//                        LoginBean bean = (LoginBean) data;
//                        String token = bean.getToken();
//                        if (token != null) {
//                            UserBean userBean = new UserBean(token);
//                            UserManager.getInstance().saveUser(userBean);
//                        }
//                        UserManager.getInstance().setUserPhotoUrl(LoginActivity.this, bean.getHeadPic());
//                        UserManager.getInstance().setUserName(LoginActivity.this, bean.getName());
//                        ToastUtil.showLong(LoginActivity.this, "登录成功");
//                        //登录成功 返回上一页面
//                        EventBus.getDefault().post(new LoginRefreshBean());
//                        Intent intent = new Intent();
//                        setResult(Constant.ResultCode.LOGIN, intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onError(String errorCode, String message) {
//                        if ("10006".equals(errorCode)) {//未进行手机注册，跳转手机注册页
//                            Intent intent = new Intent(LoginActivity.this, PhoneNumberRegisterActivity.class);
//                            intent.putExtra("uid", uid);
//                            intent.putExtra("screenName", screenName);
//                            intent.putExtra("profileImageUrl", profileImageUrl);
//                            if (!"".equals(gender))
//                                intent.putExtra("gender", gender);
//                            intent.putExtra("isThird", "yes");
//                            intent.putExtra("thirdtype", "1");
//                            startActivityForResult(intent, Constant.RequestCode.QQLOGIN);
//                            ToastUtil.showLong(LoginActivity.this, "绑定QQ成功，请绑定手机号");
//                        } else
//                            ErrorCodeUtil.shownetWorkAndServerError(LoginActivity.this, errorCode);
//                    }
//                }));
//    }

    /**
     * 手机号输入监听
     */
    class MobileTextWatcher implements TextWatcher {
        private CharSequence temp;
        private String addString = " ";
        public boolean isTel = true;
        private boolean isRun = false;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isRun) {//这几句要加，不然每输入一个值都会执行两次onTextChanged()，导致堆栈溢出，原因不明
                isRun = false;
                return;
            }
            isRun = true;
            if (isTel) {
                String finalString = "";
                int index = 0;
                String telString = s.toString().replace(" ", "");
                if ((index + 3) < telString.length()) {
                    finalString += (telString.substring(index, index + 3) + addString);
                    index += 3;
                }
                while ((index + 4) < telString.length()) {
                    finalString += (telString.substring(index, index + 4) + addString);
                    index += 4;
                }
                finalString += telString.substring(index, telString.length());
                temp = finalString;
                etPhone.setText(finalString);
                //此语句不可少，否则输入的光标会出现在最左边，不会随输入的值往右移动
                etPhone.setSelection(finalString.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() == 13) {
                mobile = temp.toString();
                etPassword.setEnabled(true);
                etPassword.setFocusable(true);
                etPassword.setFocusableInTouchMode(true);
                etPassword.requestFocus();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            EventBus.getDefault().post(new DontLoginBean());
            if (UserManager.getInstance().isLogin()) {
                UserManager.getInstance().deleteUser();
                UserManager.getInstance().setUserPhotoUrl(this, "");
                UserManager.getInstance().setUserName(this, "");
                UserManager.getInstance().setUserDate(this, "");
            }
            this.finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
