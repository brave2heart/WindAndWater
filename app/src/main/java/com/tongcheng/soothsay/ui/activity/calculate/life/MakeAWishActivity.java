package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.XYDPriceBean;
import com.tongcheng.soothsay.bean.pay.OrderRePayBean;
import com.tongcheng.soothsay.bean.pay.OrdersRequestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.WishingSquareApi;
import com.tongcheng.soothsay.ui.activity.pay.PayActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquareConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;
import com.tongcheng.soothsay.widget.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ALing on 2016/12/6 0006.
 * 我要许愿点灯
 */

public class MakeAWishActivity extends BaseTitleActivity implements TimePickerView.OnTimeSelectListener{
    @BindView(R.id.iv_light_type)
    ImageView ivLightType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.rb_boy)
    RadioButton rbBoy;
    @BindView(R.id.rb_girl)
    RadioButton rbGirl;
    @BindView(R.id.rg_choose_sex)
    RadioGroup rgChooseSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.et_wish)
    EditText etWish;
    @BindView(R.id.choose_public)
    TextView choosePublic;
    @BindView(R.id.rb_public)
    RadioButton rbPublic;
    @BindView(R.id.rb_not_public)
    RadioButton rbNotPublic;
    @BindView(R.id.rg_choose_public)
    RadioGroup rgChoosePublic;
    @BindView(R.id.choose_deadline)
    TextView chooseDeadline;
    @BindView(R.id.rb_money1)
    RadioButton rbMoney1;
    @BindView(R.id.rb_money2)
    RadioButton rbMoney2;
    @BindView(R.id.rg_choose_deadline)
    RadioGroup rgChooseDeadline;
    @BindView(R.id.btn_light_up)
    Button btnLightUp;
    @BindView(R.id.tv_title_type)
    TextView tvTitleType;
    @BindView(R.id.tv_light_type)
    TextView tvLightType;

    private String sexType = "男";
    private String isPublic = "不公开";
    private String deadLine = "90天";
    private String sex = "1";//默认是男
    private String  expiryType = "1";
    private String isOpen = "1";
    private final String transType = "XYD";  //订单类型
    private double money = 0.0d; //默认价格
    private TimePickerView mTimePicker;
    private String time = "1900.08.08";
    private String[] temp;
    String bornDate;
    String userName;
    String content;

    private String index;
    private XYDPriceBean xydPriceBean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_make_wish);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_wishing_light));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        rgChooseSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View checkView = rgChooseSex.findViewById(checkedId);
                if (!checkView.isPressed()){
                    return;
                }
                switch (checkedId){
                    case R.id.rb_boy:
                        sexType = rbBoy.getText().toString();
                        sex = "1";
                        break;
                    case R.id.rb_girl:
                        sexType = rbGirl.getText().toString();
                        sex = "2";
                        break;
                }
            }
        });
        rgChooseDeadline.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_money1:
                        deadLine = rbMoney1.getText().toString();
                        expiryType = "0";
                        money = Double.valueOf(xydPriceBean.getValue0());
                        break;
                    case R.id.rb_money2:
                        deadLine = rbMoney2.getText().toString();
                        expiryType = "1";
                        money = Double.valueOf(xydPriceBean.getValue1());
                        break;
                }
            }
        });
        rgChoosePublic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_public:
                        isPublic = rbPublic.getText().toString();
                        isOpen = "0";
                        break;
                    case R.id.rb_not_public:
                        isPublic = rbNotPublic.getText().toString();
                        isOpen = "1";
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();

//        从上一个页面传来的灯类型以及灯图片的index
        index = getIntent().getStringExtra("index");
        Log.e(TAG, "initData: index==="+index );
        getWishingLightPrice();
        String[] lightType = getResources().getStringArray(R.array.light_typeArr);
        tvLightType.setText(lightType[Integer.valueOf(index)]);
        ivLightType.setImageResource(WishingSquareConstant.xyddArr[Integer.valueOf(index)]);



    }

    @OnClick({R.id.tv_date,R.id.btn_light_up})
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_date:
                showTimePicker();
                break;
            case R.id.btn_light_up:
                if (ClickUtil.isFastClick(view)) return;
                if (TextUtils.isEmpty(etName.getText().toString())){
                    ToastUtil.showShort(this,"请输入姓名");
                    return;
                }else if (TextUtils.isEmpty(tvDate.getText().toString())){
                    ToastUtil.showShort(this,"请选择日期");
                    return;
                }else if (TextUtils.isEmpty(etWish.getText().toString())){
                    ToastUtil.showShort(this,"请输入愿望");
                    return;
                }

                    payNow();

                break;
        }
        WindowUtil.closeInputMethod(this);
    }

    //显示时间控件
    private void showTimePicker() {
        if (mTimePicker == null) {
            mTimePicker = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            mTimePicker.setCyclic(false);
            mTimePicker.setTime(new Date());
            mTimePicker.setOnTimeSelectListener(this);
        }
        mTimePicker.show();
    }

    @Override
    public void onTimeSelect(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        time = sdf.format(date);
        temp = time.split("\\.");
        tvDate.setText(temp[0] + "-" + temp[1] + "-" + temp[2]);
    }

    private void payNow() {
        if (!UserManager.getInstance().isLogin()) {
            CustomDialog customDialog = new CusDialogUtil(this).showLoginDialg(R.string.txt_login_first, 2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();

                }
            });
            return;
        }
        if (0.0d == money){
            money = Double.valueOf(xydPriceBean.getValue1());
        }

        String token = UserManager.getInstance().getToken();

        JSONObject jsonObject = new JSONObject();
        String xydName = tvLightType.getText().toString();
        String bornDate = tvDate.getText().toString(); //日期
        String userName = etName.getText().toString();
        String content = etWish.getText().toString();

        try {

            jsonObject.put("xydName", xydName);
            jsonObject.put("sex", sex);
            jsonObject.put("bornDate", bornDate);
            jsonObject.put("username", userName);
            jsonObject.put("content", content);
            jsonObject.put("expiryType",expiryType );
            jsonObject.put("isOpen", isOpen);
            LogUtil.e(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("money",money+"");
        OrdersRequestBean requestBean = new OrdersRequestBean();
        requestBean.setTotalPrice(money * 100 + "");
        requestBean.setToken(token);
        requestBean.setExtra(jsonObject.toString());
        requestBean.setTransType(transType);
        requestBean.setGoodsName(getString(R.string.goods_xyd));

        Intent intent = new Intent(MakeAWishActivity.this, PayActivity.class);
        intent.putExtra(Constant.INTENT_DATA, requestBean);
        startActivityForResult(intent, 3);

}
    /**
     * 获取许愿价格
     */
    public void getWishingLightPrice() {
        WishingSquareApi.getInstance().getXYDPrice().enqueue(new ApiCallBack<ApiResponseBean<XYDPriceBean>>(new BaseCallBack() {

            @Override
            public void onSuccess(Object data) {
                xydPriceBean = (XYDPriceBean)data;
                xydPriceBean.setValue0(xydPriceBean.getValue0());
                xydPriceBean.setValue1(xydPriceBean.getValue1());
                rbMoney1.setText("90天—¥"+xydPriceBean.getValue0());
                rbMoney2.setText("180天—¥"+xydPriceBean.getValue1());
            }

            @Override
            public void onError(String errorCode, String message) {
                boolean b = ErrorCodeUtil.shownetWorkAndServerError(MakeAWishActivity.this, errorCode);
                if (b == false){
                    ToastUtil.showShort(MakeAWishActivity.this,message);
                }
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Constant.ResultCode.LOGIN){
            payNow();
        }

        if(resultCode == PayConstant.PAY_RESULT){
            int result = data.getIntExtra(Constant.INTENT_DATA,-1);
            if(result == PayConstant.ERR_CODE_PAY_FINISH){
                ToastUtil.showShort(this,getString(R.string.light_pay_finish));
                finish();
            }

        }
    }

}


