package com.tongcheng.soothsay.ui.activity.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.classroom.AddressBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.dialog.ProvinceCityAreaDialog;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.StoreApi;
import com.tongcheng.soothsay.ui.activity.classroom.goodsConfirm.GoodsConfirmConstant;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ALing on 2016/12/26 0026.
 * 添加收获地址
 */
public class AddAddressActivity extends BaseTitleActivity implements View.OnClickListener,BaseUserView, ProvinceCityAreaDialog.OnCitySelectListener {

    @BindView(R.id.et_name)             EditText        etName;
    @BindView(R.id.et_phone)            EditText        etPhone;
    @BindView(R.id.et_address)          EditText        etAddress;
    @BindView(R.id.tv_area)             TextView        tvArea;
    @BindView(R.id.tv_set_default)      TextView        tvSetDefault;
    @BindView(R.id.sw_address_default)  Switch          swDefault;
    @BindView(R.id.ll_msg)              LinearLayout    llMsg;
    @BindView(R.id.rl_add_address)      RelativeLayout  rl;


    private String province;
    private String city;
    private String area;
    private String type = "1";      //保存类型 1：新增，2：修改
    private String defult = "0";
    private boolean isDefult = false;

    private ProvinceCityAreaDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_address);
        initData();
        initListener();
    }

    @Override
    public void initListener() {
        getBaseHeadView().showTitle(getString(R.string.title_add_address));
        getBaseHeadView().showBackButton(this);
        getBaseHeadView().showHeadRightButton("保存", this);
    }
    @OnClick({R.id.tv_area,
            R.id.sw_address_default,
            R.id.rl_add_address})
    public void onClick(View view){

        WindowUtil.closeInputMethod(this);
        switch (view.getId()){
            case R.id.headBackButton:
                finish();
                break;

            //保存
            case R.id.headRightButton:
                saveAddress();
                break;

            //选择城市
            case R.id.tv_area:
                showChooseProvinceCityArea();
                break;

            //是否是默认地址
            case R.id.sw_address_default:
                isDefult = swDefault.isChecked();
                defult = isDefult ? "1":"0";
                break;
        }
    }


    //保存地址
    private void saveAddress() {
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            GotoUtil.GoToActivity(this,LoginActivity.class);
            return ;
        }


        String addr = etAddress.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ToastUtil.showShort(this,getString(R.string.add_address_name_tip));
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort(this,getString(R.string.add_address_phone_tip));
            return;
        }

        if (TextUtils.isEmpty(addr)) {
            ToastUtil.showShort(this,getString(R.string.add_address_intro_tip));
            return;
        }

        if (addr != null && addr.length() < 5) {
            ToastUtil.showShort(this,getString(R.string.add_address_length_tip));
            return;
        }

        if (TextUtils.isEmpty(province)) {
            ToastUtil.showShort(this,getString(R.string.add_address_province_tip));
            return;
        }

        if (TextUtils.isEmpty(city)) {
            ToastUtil.showShort(this,getString(R.string.add_address_city_tip));
            return;
        }


        final AddressBean bean = new AddressBean(token,name,phone,province,city,addr,defult,type,area);
        HashMap<String,String > map = new HashMap<String,String>();
        map.put("token",token);
        map.put("type",type);
        map.put("name",name);
        map.put("phone",phone);
        map.put("isdefault",defult);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("address",addr);

        showLoad();
        StoreApi.getInstance().addAddress(map).enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                ToastUtil.showShort(AddAddressActivity.this,"添加地址成功");
                Intent intent = new Intent();
                intent.putExtra(Constant.INTENT_DATA,bean);
                setResult(Constant.ResultCode.ADD_ADDRESS,intent);
                finish();
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(AddAddressActivity.this,errorCode,message);
            }
        }));
    }

    @Override
    public void initData() {
        isDefult = getIntent().getBooleanExtra(GoodsConfirmConstant.INTENT_ISDEFULT,false);
        defult = isDefult ? "1":"0";
    }

    private void showChooseProvinceCityArea() {
        if(dialog == null){
            dialog = new ProvinceCityAreaDialog(this);
            dialog.setOnCitySelectListener(this);
        }
        dialog.show();

    }

    @Override
    public void showTokenOverdue() {
        GotoUtil.GoToActivity(this, LoginActivity.class);
    }

    @Override
    public void showNetError() {
        ToastUtil.showShort(this,getString(R.string.showNeterr));
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this,info);
    }

    @Override
    public void showEmpty() {}

    @Override
    public void showTitle(String title) {}

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading(true);
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {}

    @Override
    public void setPresenter(Object presenter) {}


    @Override
    public void onCitySelect(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = TextUtils.isEmpty(area) ? "": area;
        tvArea.setText(province + " " + city + " " + this.area);
    }
}
