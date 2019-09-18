package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.ChangeWishBean;
import com.tongcheng.soothsay.bean.event.EventQianXianBean;
import com.tongcheng.soothsay.data.calculate.zhuanyun.qifutai.QiFuTaiTempData;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ResUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.wevey.selector.dialog.MDAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/7.
 * 请仙-我的愿望
 */
public class MyWishActivity extends BaseTitleActivity {
    static int i;

    @BindView(R.id.iv_wish)
    ImageView ivWish;
    @BindView(R.id.tv_wish_name)
    TextView tvWishName;
    @BindView(R.id.et_wish)
    EditText etWish;

    public static String TYPE_WISH = "wish";

    private MDAlertDialog dialog;

    int godType;
    int img;
    String name;
    /**
     * 判断是不是来自修改请求
     */
    private boolean mIsFromChange;
    private String mWish;
    private String mDxName;
    private QiFuTaiTempData mQiFuTaiTempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_my_wish);
        mQiFuTaiTempData = QiFuTaiTempData.newInstaince();
        //       在这里我要拿到是不是调用用来改愿望的。   还需要原来的愿望内容  大仙的名字。
        mIsFromChange = getIntent().getBooleanExtra("isFromChange", false);
        if (mIsFromChange) {
            mWish = getIntent().getStringExtra("Wish");
            mDxName = getIntent().getStringExtra("dxName");

            etWish.setText(mWish);
        }
        initBaseHeadView();
        initData();


    }


    LinkedHashMap<String, View.OnClickListener> contentMap;

    private void initBaseHeadView() {
        getBaseHeadView().showTitle("我的愿望");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyWishActivity.this.finish();

            }
        });
        contentMap = new LinkedHashMap<>();
        getBaseHeadView().showHeadRightButton("下一步", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) return;

                final String s = etWish.getText().toString();
                String cotent = "";
                if (TextUtils.isEmpty(s)) {
                    cotent = getResources().getString(R.string.txt_qx_null);
                } else {
                    cotent = getResources().getString(R.string.txt_qx_sure);
                }
                if (mIsFromChange) {
                    if (s.equals(mWish)) {
                        Toast.makeText(MyWishActivity.this, "心愿没有修改", Toast.LENGTH_SHORT).show();
                    } else {
//                       这里请求服务器 修改数据
                        PrayingApi.getInstance().changeQfContent(UserManager.getInstance().getUser().getToken(), mDxName, s).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {


                            @Override
                            public void onSuccess(Object data) {
                                EventBus.getDefault().post(new ChangeWishBean(s));
                                finish();
                            }

                            @Override
                            public void onError(String errorCode, String message) {
                                finish();
                            }
                        }));

                    }
                } else {
                    String content;
                    if (TextUtils.isEmpty(s)) {
                        content = getResources().getString(R.string.txt_qx_null);
                    } else {
                        content = getResources().getString(R.string.txt_qx_sure);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyWishActivity.this);
                    builder.setTitle("祈福许愿");
                    builder.setMessage(content);

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (ClickUtil.isFastClick()) return;

                            showDialog((Dialog) dialog);
                        }
                    });
                    builder.show();
//                    dialog = DialogUtil.createDialog(MyWishActivity.this, "祈福许愿", "", "", cotent,
//                            new DialogOnClickListener() {
//
//                                @Override
//                                public void clickLeftButton(View view) {
//                                    dialog.dismiss();
//                                    dialog = null;
//                                }
//
//                                @Override
//                                public void clickRightButton(View view) {
//
//                                    PrayingApi.getInstance().setisQingXianSuccessful(UserManager.getInstance().getUser().getToken(), name, etWish.getText().toString()).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
//                                        @Override
//                                        public void onSuccess(Object data) {
//                                            mQiFuTaiTempData.addGod(name);
//                                            GotoUtil.GoToActivity(MyWishActivity.this, PrayingStationActivity.class);
//                                            dialog.dismiss();
//                                            dialog = null;
//                                            MyWishActivity.this.finish();
//                                        }
//
//                                        @Override
//                                        public void onError(String errorCode, String message) {
//                                            dialog.dismiss();
//                                            dialog = null;
//
//                                        }
//                                    }));
////                                    Intent intent = new Intent(MyWishActivity.this, PrayingStationActivity.class);
////                                    intent.putExtra("img", img);
////                                    intent.putExtra("name", name);
////                                    String wish = etWish.getText().toString();
////                                    intent.putExtra("wish", wish);
////                                    intent.putExtra("type", TYPE_WISH);
////                                    startActivity(intent);
////                                在这里把神仙数据写到数据库跟服务器上。
//
////                                    GodBean godBean = new GodBean(Long.valueOf(name.hashCode()), godType, name, img, "TestTest", "TestTest", true);
////                                    GreenDaoHelper.getInstance().getSeeion().getGodBeanDao().insert(godBean);
//                                    askimmortalscessful();
////                                    dialog.dismiss();
////                                    dialog = null;
//                                }
//                            });
//
//                    dialog.show();
//
//                    if (TextUtils.isEmpty(s)) {
//                        contentMap.put(getResources().getString(R.string.txt_qx_null), null);
//                    } else {
//                        contentMap.put(getResources().getString(R.string.txt_qx_sure), null);
//                    }
                }
            }
        });
    }

    private void showDialog(final Dialog dialog) {
//"去请仙");
        PrayingApi.getInstance().qfDx(UserManager.getInstance().getUser().getToken(), name, etWish.getText().toString()).enqueue(new ApiCallBack<ApiResponseBean<Void>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                mQiFuTaiTempData.addGod(name);
                mQiFuTaiTempData.setisQingXianSuccessful(true,name);
                EventBusUtil.post(new EventQianXianBean(true));
//                GotoUtil.GoToActivityForResult(MyWishActivity.this, PrayingStationActivity.class,3);
                dialog.dismiss();
                startActivity(new Intent(MyWishActivity.this,PrayingStationActivity.class));
                MyWishActivity.this.finish();
            }

            @Override
            public void onError(String errorCode, String message) {
                ToastUtil.showShort(MyWishActivity.this,"出错了");
                dialog.dismiss();
            }
        }));
    }

    private void askimmortalscessful() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        img = intent.getIntExtra("img", -1);
        if (img != -1)
            ivWish.setImageResource(img);
        name = intent.getStringExtra("name");
        godType = intent.getIntExtra("godType", 1);
        if (!TextUtils.isEmpty(name))
            tvWishName.setText("诚心许下愿望，向【" + name + "】祈愿助您愿望实现!");
        int godImageRes = ResUtil.newInstance().getGodImageRes(name);
        if (godImageRes!=-1){
            ivWish.setImageResource(godImageRes);
        }
    }
}
