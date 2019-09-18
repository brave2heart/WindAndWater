package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseActivity;
import com.tongcheng.soothsay.base.PayBean;
import com.tongcheng.soothsay.bean.calculation.OneLingFuBean;
import com.tongcheng.soothsay.bean.event.RefreshLingFuBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.bean.pay.OrdersResultBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.dialog.DateTimePickDialogUtil;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.http.service.PayApi;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.ui.fragment.mine.MineFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ResUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 宋家任 on 2016/11/10.
 * 灵符
 */

public class LingfuActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_qingfu)
    ImageView ivQingfu;
    @BindView(R.id.tv_fu_name)
    TextView tvFuName;
    @BindView(R.id.tv_lf_title)
    TextView tvTitle;
    @BindView(R.id.rl_lingfu)
    RelativeLayout rlLingfu;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.iv_lingfu_light_open)
    ImageView ivLightOpen;
    //    @BindView(R.id.rl_open)
//    RelativeLayout rlOpen;
    @BindView(R.id.iv_qianming)
    ImageView ivQianming;
    @BindView(R.id.rl_lingfu_user)
    RelativeLayout rlUser;
    @BindView(R.id.iv_kaiguang)
    ImageView ivKaiguang;
    @BindView(R.id.iv_jiachi)
    ImageView ivJiachi;
    @BindView(R.id.tv_lingfu_box_use_name)
    TextView tvUseName;
    @BindView(R.id.tv_lingfu_box_use_time)
    TextView tvUseTime;
    @BindView(R.id.tv_lingfu_box_use_addre)
    TextView tvUseAddre;
    @BindView(R.id.tv_yz)
    TextView tvYz;
    @BindView(R.id.iv_lingfu_fu)
    ImageView ivLingfuFu;
    @BindView(R.id.rl_lingfu_scale)
    RelativeLayout rlScale;
    @BindView(R.id.rl_iv_cover)
    RelativeLayout rlIvCover;

    private final String transType = "LF";  //订单类型

    private Animation inAnimation;//初始进场动画
    private Animation boxOutYiBanAnim;//初始箱子出场动画(一半)
    private Animation boxin;//没请过符时返回时箱子进场动画
    private Animation boxinAll;//请过符时返回时箱子进场动画
    private Animation boxoutAll;//支付成功后箱子退场动画
    private Animation fuFangda;//放大符的布局
    private Animation fuSuoxiao;//缩小符布局

    private Handler mHandler = new Handler();

    String[] descs = new String[]{};//符介绍
    String[] gx = new String[]{};//符功效
    int fuId;//灵符对应的array id
    String id;//符对应大组的id
    String fuImgId;//符对应的大组的图片的id
    String status;//灵符最新状态 1：默认，2：请符成功，3：开光成功,4:加持成功
    String isFree;//是否免费，0：不免费，1，免费
    OneLingFuBean bean;//灵符实体
    String username;//灵符使用人
    String bazi;//灵符使用人八字
    String address;//使用人地址
    String orderNo;//订单号

    private long touchTime = 0;//点击时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingfu);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        descs = intent.getStringArrayExtra("desc");
        gx = intent.getStringArrayExtra("gongxiao");
        bean = (OneLingFuBean) intent.getSerializableExtra("bean");
        id = intent.getStringExtra("zu_id");
        fuImgId = bean.getLfId();
        initData();
        initAnimation();
        initListener();
    }


    @Override
    public void initData() {
        EventBusUtil.register(this);
//        fuId = ResUtil.getFuMingIdByName(bean.getName(), this);
        fuId = ResUtil.newInstance().getFuMingIdByNmaeV2(bean.getName());
        username = bean.getUsername();
        bazi = bean.getBazi();
        address = bean.getAddress();
        tvTitle.setText(bean.getName());
        tvFuName.setText(bean.getName());
        status = bean.getStatus();
        isFree = bean.getIsfree();
        orderNo = bean.getOrderNo();
        ivLingfuFu.setImageResource(getResources().getIdentifier("fy_lingfu_" + fuImgId, "drawable", getPackageName()));
        if ("".equals(username) && "".equals(bazi)
                && "".equals(address) && !"1".equals(status)) {//请过符但是没填写用符人信息
            rlUser.setVisibility(View.VISIBLE);
        } else {
            if (!"1".equals(status)) {
                rlUser.setVisibility(View.VISIBLE);
                tvUseName.setText(getResources().getString(R.string.txt_qianming_username) + username);
                tvUseTime.setText(getResources().getString(R.string.txt_qianming_shenggeng) + bazi);
                tvUseAddre.setText(getResources().getString(R.string.txt_qianming_address) + address);
                tvYz.setText("缘主：" + username);
                ivQianming.setVisibility(View.GONE);
            }
        }
        if ("1".equals(status)) {
            ivQingfu.setVisibility(View.VISIBLE);
        } else if ("2".equals(status)) {
            ivKaiguang.setVisibility(View.VISIBLE);
        } else if ("3".equals(status)) {
            ivJiachi.setVisibility(View.VISIBLE);
        } else if ("4".equals(status)) {
//            ivJiachi.setVisibility(View.VISIBLE);
        }
    }


    private void initAnimation() {
        this.inAnimation = AnimationUtils.loadAnimation(this, R.anim.lingfu_bottom_in);//初始进场动画
        this.boxOutYiBanAnim = AnimationUtils.loadAnimation(this, R.anim.box_out_ban);//初始箱子出场动画(一半)
        this.boxin = AnimationUtils.loadAnimation(this, R.anim.box_in);//没请过符时返回时箱子进场动画
        this.boxinAll = AnimationUtils.loadAnimation(this, R.anim.box_in_all);//请过符时返回时箱子进场动画
        this.boxoutAll = AnimationUtils.loadAnimation(this, R.anim.box_out_all);//支付成功后箱子对话
        this.fuFangda = AnimationUtils.loadAnimation(this, R.anim.fangda_fu);//放大符动画
        this.fuSuoxiao = AnimationUtils.loadAnimation(this, R.anim.suoxiao_fu);//缩小符动画

        rlRoot.startAnimation(inAnimation);//初始进场动画
        //进场动画结束之后的箱子动画
        inAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLightOpen.setVisibility(View.VISIBLE);
                if ("1".equals(status)) {//默认状态
                    rlIvCover.startAnimation(boxOutYiBanAnim);
//                    showLightAnim(boxOutYiBanAnim);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ivLightOpen.setVisibility(View.GONE);
                                }
                            });
                        }
                    }, 1000);
                } else {//除了默认状态其他状态灵符都要可以点击放大缩小
                    rlIvCover.startAnimation(boxoutAll);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ivLightOpen.setVisibility(View.GONE);
                                }
                            });
                        }
                    }, 1000);
                    lingFuListener();
//                    showLightAnim(boxoutAll);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 开场后光的动画
     */
//    private void showLightAnim(Animation animation) {
//        ivLightOpen.setVisibility(View.VISIBLE);
//        rlOpen.startAnimation(animation);
//        animation.setFillAfter(true);
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ivLightOpen.setVisibility(View.GONE);
//                    }
//                });
//            }
//        }, 1000);
//    }

    //下面是所有对话框用到的控件
    private View inflate;
    private Dialog dialog;

    //下面是请符对话框控件
    private TextView tvDesc;
    private Button btnPay;
    private Button btnCancel;

    /**
     * 请符对话框
     */
    private void showPayDialog() {
        dialog = new Dialog(this, R.style.Dialog);
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_lingfu_pay, null);

        tvDesc = (TextView) inflate.findViewById(R.id.tv_lf_dialog_title);
        btnPay = (Button) inflate.findViewById(R.id.btn_lf_pay);
        btnCancel = (Button) inflate.findViewById(R.id.btn_lf_cancel);

        btnPay.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        if ("1".equals(status)) {//请符介绍
            tvDesc.setText(descs[fuId]);
        }
        if ("2".equals(status)) {//开光介绍
            tvDesc.setText(getResources().getString(R.string.txt_kaiguagn));
        }
        if ("3".equals(status)) {//加持介绍
            tvDesc.setText(getResources().getString(R.string.txt_jiachi));
        }
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }


    //下面是功效对话框控件
    private TextView tvGx;

    /**
     * 功效对话框
     */
    private void showGongxiaoDialog() {
        dialog = new Dialog(this, R.style.Dialog);
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_lingfu_gongxiao, null);
        tvGx = (TextView) inflate.findViewById(R.id.tv_lingfu_gx);
        tvGx.setText(gx[fuId]);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();

    }

    /**
     * 获取缘主填写的信息
     */
    private void getUserMsg() {
        tvUseName.setText(getResources().getString(R.string.txt_qianming_username) + etUserName.getText().toString());
        tvUseTime.setText("生庚" + tvUserTime.getText().toString());
        tvUseAddre.setText("地址" + etUserAddr.getText().toString());
        tvYz.setText("缘主：" + etUserName.getText().toString());
        ivQianming.setVisibility(View.GONE);
    }

    //下面是用符者对话框
    private EditText etUserName;
    private TextView tvUserTime;
    private EditText etUserAddr;
    private Button btnQianmingCancel;
    private Button btnQianmingSure;

    /**
     * 用符者对话框
     */
    private void showUseFuDialog() {
        dialog = new Dialog(this, R.style.Dialog);
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_use_fu, null);

        etUserName = (EditText) inflate.findViewById(R.id.et_linfu_use_name);
        tvUserTime = (TextView) inflate.findViewById(R.id.tv_lingfu_user_time);
        etUserAddr = (EditText) inflate.findViewById(R.id.et_lingfu_user_addr);
        btnQianmingCancel = (Button) inflate.findViewById(R.id.btn_lingfu_dialog_cancel);
        btnQianmingSure = (Button) inflate.findViewById(R.id.btn_lingfu_dialog_sure);

        btnQianmingCancel.setOnClickListener(this);
        btnQianmingSure.setOnClickListener(this);
        tvUserTime.setOnClickListener(this);

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) return;

        switch (v.getId()) {
            case R.id.tv_back://返回
                if (isda == true && "2".equals(status)) {//请过符
                    rlScale.startAnimation(fuSuoxiao);
                    ivCover.setVisibility(View.VISIBLE);
                    ivKaiguang.setVisibility(View.VISIBLE);
                    isda = false;
                    return;
                } else if (isda == true && "3".equals(status)) {//开过光
                    rlScale.startAnimation(fuSuoxiao);
                    ivCover.setVisibility(View.VISIBLE);
                    ivJiachi.setVisibility(View.VISIBLE);
                    isda = false;
                    return;
                } else if (isda == true && "4".equals(status)) {//加持过
                    rlScale.startAnimation(fuSuoxiao);
                    ivCover.setVisibility(View.VISIBLE);
                    isda = false;
                    return;
                }
                if (System.currentTimeMillis() - touchTime > 2000) {
                    touchTime = System.currentTimeMillis();
                    backAnim();
                }

                break;
            case R.id.tv_right://功效
                showGongxiaoDialog();
                break;
            //下面是请符的操作
            case R.id.iv_qingfu://请符
                showPayDialog();
                break;
            case R.id.btn_lf_cancel://请符对话框取消
                dialog.dismiss();
                break;
            case R.id.btn_lf_pay://请符对话框支付
                dialog.dismiss();
                if ("1".equals(status)) {//请符
                    ivQingfu.setVisibility(View.GONE);
                }
                if ("2".equals(status)) {//开光
                    ivKaiguang.setVisibility(View.GONE);
                }
                if ("3".equals(status)) {//加持介绍
                    ivJiachi.setVisibility(View.GONE);
                }
                if ("1".equals(isFree)) {//免费请符
                    if ("1".equals(status)) {
                        payFree();
                    } else payNow(status);
                } else if ("0".equals(isFree)) {//不免费
                    payNow(status);

                }
                break;
            //下面是开光的操作
            case R.id.iv_kaiguang://开光
                showPayDialog();
                break;

            //下面是加持的操作
            case R.id.iv_jiachi://加持
                showPayDialog();
                break;

            //下面是给灵符签名的操作
            case R.id.iv_qianming:
                showUseFuDialog();
                break;
            case R.id.tv_lingfu_user_time:
                showTimeChoiceDialog();
                break;
            case R.id.btn_lingfu_dialog_cancel:
                showBtnLingfuDialogCancelDialog();
                break;
            case R.id.btn_lingfu_dialog_sure:
                showBtnLingfuDialogSureDialog();

                break;

        }
    }

    /**
     * 免费请符
     */
    private void payFree() {
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            new CusDialogUtil(this).showDefaultLoginDialog(R.string.login_now, 666);
            return;
        }

        JSONObject jObj = new JSONObject();
        try {
//            将商品列表转成json数组
            jObj.put("lfName", bean.getName());
            jObj.put("amount", "1");
            jObj.put("buyType", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("transType", transType);
        map.put("payType", "WX");
        map.put("totalPrice", 0 + "");
        map.put("extra", jObj.toString());

        PayApi.getInstance().getOrders(map).enqueue(new ApiCallBack<ApiResponseBean<OrdersResultBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                ivLightOpen.setVisibility(View.VISIBLE);
                rlIvCover.startAnimation(boxoutAll);
                lingFuListener();
                boxoutAll.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ivLightOpen.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                getLingfuInfo(bean.getName());
                EventBus.getDefault().post(new RefreshLingFuBean(id));
            }

            @Override
            public void onError(String errorCode, String message) {
                if ("1".equals(status)) {//请符
                    ivQingfu.setVisibility(View.VISIBLE);
                }
                if ("2".equals(status)) {//开光
                    ivKaiguang.setVisibility(View.VISIBLE);
                }
                if ("3".equals(status)) {//加持介绍
                    ivJiachi.setVisibility(View.VISIBLE);
                }
//                LogUtil.printD("errco:" + errorCode);
//                ErrorCodeUtil.showHaveTokenError(LingfuActivity.this, errorCode);
                boolean b = ErrorCodeUtil.showHaveTokenError(LingfuActivity.this, errorCode);
                if (b == false) {
                    ToastUtil.showShort(LingfuActivity.this, message);
                }
            }
        }));
    }

    /**
     * 跳转支付页
     * buyType 1：请符，2：开光，3：加持
     */
    private void payNow(String buyType) {
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            new CusDialogUtil(this).showDefaultLoginDialog(R.string.login_now, 666);
            return;
        }
        double money = 0.0d;
        if ("1".equals(buyType)) {//请符价格
            money = Double.parseDouble(bean.getPrice());

        } else if ("2".equals(buyType)) {//开光价格
            money = Double.parseDouble(bean.getKgprice());
        } else if ("3".equals(buyType)) {
            money = Double.parseDouble(bean.getJcprice());
        }
        JSONObject jObj = new JSONObject();
        try {
//            将商品列表转成json数组
            jObj.put("lfName", bean.getName());
            jObj.put("amount", "1");
            jObj.put("buyType", buyType);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        OrdersRequestBean requestBean = new OrdersRequestBean();
        requestBean.setTotalPrice(money * 100 + "");
        requestBean.setToken(token);
        requestBean.setExtra(jObj.toString());
        requestBean.setTransType(transType);
        requestBean.setGoodsName(getString(R.string.goods_lf_name));

        Intent intent = new Intent(LingfuActivity.this, PayActivity.class);
        intent.putExtra(Constant.INTENT_DATA, requestBean);
        startActivityForResult(intent, Constant.RequestCode.LF_PAY);
    }

    //灵符是否放大
    boolean isda = false;

    /**
     * 支付成功后回调的接口
     */
    private void showLingfu(final String status) {
        if ("2".equals(status)) {
            rlIvCover.startAnimation(boxin);
        } else {
            rlIvCover.startAnimation(boxinAll);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        rlIvCover.startAnimation(boxoutAll);
                        if ("2".equals(status)) {//请符后
                            rlUser.setVisibility(View.VISIBLE);
                            ivQingfu.setVisibility(View.GONE);
                            ivQingfu.setVisibility(View.GONE);
                            ivKaiguang.setVisibility(View.VISIBLE);
                        }
                        if ("3".equals(status)) {//开光后
                            ivQingfu.setVisibility(View.GONE);
                            ivKaiguang.setVisibility(View.GONE);
                            ivJiachi.setVisibility(View.VISIBLE);
                        }
                        if ("4".equals(status)) {//加持后
                            ivQingfu.setVisibility(View.GONE);
                            ivKaiguang.setVisibility(View.GONE);
                            ivJiachi.setVisibility(View.GONE);
                        }

                    }
                });
            }
        }, 1000);
        boxoutAll.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLightOpen.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lingFuListener();

    }

    /**
     * 灵符点击监听
     */
    private void lingFuListener() {
        ivLingfuFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                if (isda == false && "2".equals(status)) {//请符后放大图片
                    rlScale.startAnimation(fuFangda);
                    ivCover.setVisibility(View.INVISIBLE);
                    ivKaiguang.setVisibility(View.GONE);
                    isda = true;
                    return;
                }
                if (isda == true && "2".equals(status)) {//请符后还原图片
                    ivCover.setVisibility(View.VISIBLE);
                    ivKaiguang.setVisibility(View.VISIBLE);
                    rlScale.startAnimation(fuSuoxiao);
                    isda = false;
                    return;
                }
                if (isda == false && "3".equals(status)) {//开光后放大图片
                    rlScale.startAnimation(fuFangda);
                    ivCover.setVisibility(View.INVISIBLE);
                    ivKaiguang.setVisibility(View.GONE);
                    ivJiachi.setVisibility(View.GONE);
                    isda = true;
                    return;
                }
                if (isda == true && "3".equals(status)) {//开光后还原图片
                    rlScale.startAnimation(fuSuoxiao);
                    ivCover.setVisibility(View.VISIBLE);
                    ivKaiguang.setVisibility(View.GONE);
                    ivJiachi.setVisibility(View.VISIBLE);
                    isda = false;
                    return;
                }
                if (isda == false && "4".equals(status)) {//加持后放大图片
                    rlScale.startAnimation(fuFangda);
                    ivCover.setVisibility(View.INVISIBLE);
                    ivKaiguang.setVisibility(View.GONE);
                    ivJiachi.setVisibility(View.GONE);
                    isda = true;
                    return;
                }
                if (isda == true && "4".equals(status)) {//加持后还原图片
                    rlScale.startAnimation(fuSuoxiao);
                    ivCover.setVisibility(View.VISIBLE);
                    ivKaiguang.setVisibility(View.GONE);
                    ivJiachi.setVisibility(View.INVISIBLE);
                    isda = false;
                    return;
                }
            }
        });
    }

    /**
     * 请符签名确定对话框
     */
    private void showBtnLingfuDialogSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LingfuActivity.this);
        builder.setTitle("提示");
        builder.setMessage(getResources().getString(R.string.txt_qianming_notice_3));

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AllApi.getInstance().getUserLingFuMsg(UserManager.getInstance().getToken(),
                        orderNo, etUserName.getText().toString(), tvUserTime.getText().toString(),
                        etUserAddr.getText().toString()).
                        enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                getUserMsg();
                            }

                            @Override
                            public void onError(String errorCode, String message) {
//                                ErrorCodeUtil.showHaveTokenError(LingfuActivity.this, errorCode);
                                boolean b = ErrorCodeUtil.showHaveTokenError(LingfuActivity.this, errorCode);
                                if (b == false) {
                                    ToastUtil.showShort(LingfuActivity.this, message);
                                }
                            }
                        }));
                dialog.dismiss();
                LingfuActivity.this.dialog.dismiss();


            }
        });
        builder.show();

    }

    /**
     * 请符签名取消的对话框
     */
    private void showBtnLingfuDialogCancelDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LingfuActivity.this);
        builder.setTitle("提示");
        builder.setMessage(getResources().getString(R.string.txt_qianming_notice_2));

        builder.setNegativeButton("再等等", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                LingfuActivity.this.dialog.dismiss();
            }
        });
        builder.show();
    }

    //格式化时间
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");
    Date curDate = new Date(System.currentTimeMillis());

    private String initEndDateTime = formatter.format(curDate); // 初始化时间"2016年11月14日 17:18"

    /**
     * 选择用福者生辰八字
     */
    private void showTimeChoiceDialog() {
        DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                LingfuActivity.this, initEndDateTime);
        dateTimePicKDialog.dateTimePicKDialog(tvUserTime);
    }

    @Override
    public void initListener() {
        tvBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivQingfu.setOnClickListener(this);
        ivQianming.setOnClickListener(this);
        ivKaiguang.setOnClickListener(this);
        ivJiachi.setOnClickListener(this);
    }

    /**
     * 返回时动画
     */
    private void backAnim() {
        if ("1".equals(status)) {
            rlIvCover.startAnimation(boxin);
        } else {
            rlIvCover.startAnimation(boxinAll);
        }
        boxin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        boxinAll.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.LF_PAY && resultCode == PayConstant.PAY_RESULT) {
            int result = data.getIntExtra(Constant.INTENT_DATA, -1);
            if (result == PayConstant.ERR_CODE_PAY_FINISH) {//购买成功,重新刷新数据,还有Eventbus把信息发出去,请符界面接收订阅

                ivLightOpen.setVisibility(View.VISIBLE);
//                rlIvCover.startAnimation(boxoutAll);
                lingFuListener();
//                boxoutAll.setAnimationListener(new Animation.AnimationListener() {
//
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        ivLightOpen.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
                getLingfuInfo(bean.getName());
                EventBus.getDefault().post(new RefreshLingFuBean(id));
            }
        }
    }

    /**
     * 购买后重新获取灵符信息
     */
    private void getLingfuInfo(String name) {
        AllApi.getInstance().getoneLingFuInfo(UserManager.getInstance().getToken(), name).
                enqueue(new ApiCallBack<ApiResponseBean<List<OneLingFuBean>>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        List<OneLingFuBean> beans = (List<OneLingFuBean>) data;
                        bean = beans.get(0);
                        initData();
                        showLingfu(status);

                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        boolean b = ErrorCodeUtil.showHaveTokenError(LingfuActivity.this, errorCode);
                        if (b==false) {
                            ToastUtil.showShort(LingfuActivity.this, message);
                        }
                    }
                }));
    }

    /**
     * 购买成功与否
     *
     * @param bean
     */
    @Subscribe
    public void onEvent(PayBean bean) {
        if (!bean.isPaySucc) {//支付失败
            if ("1".equals(status)) {//请符
                ivQingfu.setVisibility(View.VISIBLE);
            }
            if ("2".equals(status)) {//开光
                ivKaiguang.setVisibility(View.VISIBLE);
            }
            if ("3".equals(status)) {//加持介绍
                ivJiachi.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in_from_left,
                R.anim.activity_out_of_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (isda == true && "2".equals(status)) {//请过符
                rlScale.startAnimation(fuSuoxiao);
                ivCover.setVisibility(View.VISIBLE);
                ivKaiguang.setVisibility(View.VISIBLE);
                isda = false;
                return true;
            } else if (isda == true && "3".equals(status)) {//开过光
                rlScale.startAnimation(fuSuoxiao);
                ivCover.setVisibility(View.VISIBLE);
                ivJiachi.setVisibility(View.VISIBLE);
                isda = false;
                return true;
            } else if (isda == true && "4".equals(status)) {//加持过
                rlScale.startAnimation(fuSuoxiao);
                ivCover.setVisibility(View.VISIBLE);
                ivJiachi.setVisibility(View.INVISIBLE);
                isda = false;
                return true;
            }
            if (System.currentTimeMillis() - touchTime > 2000) {
                touchTime = System.currentTimeMillis();
                backAnim();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
