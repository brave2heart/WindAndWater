package com.tongcheng.soothsay.ui.activity.classroom;

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
import com.tongcheng.soothsay.bean.event.EventUpdataAddrBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.dialog.ProvinceCityAreaDialog;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.StoreApi;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.WindowUtil;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改地址
 */
public class UpdataAddressActivity extends BaseTitleActivity implements View.OnClickListener,ProvinceCityAreaDialog.OnCitySelectListener,BaseUserView {

    @BindView(R.id.et_name)                     EditText        etName;
    @BindView(R.id.et_phone)                    EditText        etPhone;
    @BindView(R.id.et_address)                  EditText        etAddress;
    @BindView(R.id.tv_area)                     TextView        tvArea;
    @BindView(R.id.sw_address_default)          Switch          swAddressDefault;
    @BindView(R.id.ll_msg)                      LinearLayout    llMsg;
    @BindView(R.id.rl_add_address)              RelativeLayout  rlAddAddress;

    private String type = "2";      //保存类型  1：新增，2：修改

    private String province;
    private String city;
    private String area;

    private boolean defult = false;

    private AddressBean bean;

    private ProvinceCityAreaDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_address);

        bean = (AddressBean) getIntent().getSerializableExtra(Constant.INTENT_DATA);

        initView();
        initListener();
    }


    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_updata_address));

        if(bean == null)
            return ;

        String name = bean.getName();
        String phone = bean.getPhone();
        String province = bean.getProvince();
        String city = bean.getCity();
        String area = bean.getArea();
        String address = bean.getAddress();
        String isDefult = bean.getIsdefault();

        String temp = province + " " + city ;
        temp = TextUtils.isEmpty(area) ? "" : temp + " " + area;

        etName.setHint(name);
        etPhone.setHint(phone);
        tvArea.setText(temp);
        etAddress.setHint(address);

        defult = "1".equals(isDefult) ? true : false;
        swAddressDefault.setChecked(defult);
    }

    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(this);
        getBaseHeadView().showHeadRightButton("保存",this);
    }

    @OnClick({R.id.tv_area,
            R.id.sw_address_default,
            R.id.rl_add_address})
    @Override
    public void onClick(View v) {
        WindowUtil.closeInputMethod(this);
        switch (v.getId()){
            case R.id.headBackButton:
                finish();
                break;

            //选择城市
            case R.id.tv_area:
                showCityPopwindow();
                break;

            //保存
            case R.id.headRightButton:
                saveUpdata();
                break;
        }
    }


    //保存修改
    private void saveUpdata(){
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            GotoUtil.GoToActivity(this,LoginActivity.class);
            return ;
        }

        String name = etName.getText().toString();
        name = TextUtils.isEmpty(name) ? bean.getName() : name;

        String phone = etPhone.getText().toString();
        phone = TextUtils.isEmpty(phone) ? bean.getPhone() : phone;

        String address = etAddress.getText().toString();
        address = TextUtils.isEmpty(address) ? bean.getAddress() : address;

        province = TextUtils.isEmpty(province) ? bean.getProvince() : province;
        city = TextUtils.isEmpty(city) ? bean.getCity() : city;
        area = TextUtils.isEmpty(area) ? bean.getArea(): area;

        String isDefult = swAddressDefault.isChecked() ? "1" : "0";


        bean.setName(name);
        bean.setPhone(phone);
        bean.setProvince(province);
        bean.setCity(city);
        bean.setArea(area);
        bean.setIsdefault(isDefult);

        HashMap<String,String> map = new HashMap<String,String>();
        map.put("token",token);
        map.put("addressId",bean.getId());
        map.put("type",type);
        map.put("name",name);
        map.put("phone",phone);
        map.put("isdefault",isDefult);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("address",address);

        showLoad();
        StoreApi.getInstance().addAddress(map).enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                ToastUtil.showShort(UpdataAddressActivity.this,"修改成功");
                EventBusUtil.post(new EventUpdataAddrBean(bean));
                finish();
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(UpdataAddressActivity.this,errorCode,message);
            }
        }));

    }

    private void showCityPopwindow() {
        if(dialog == null){
            dialog = new ProvinceCityAreaDialog(this);
            dialog.setOnCitySelectListener(this);
        }
        dialog.show();
    }


    @Override
    public void onCitySelect(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = TextUtils.isEmpty(area) ? "": area;
        tvArea.setText(province + " " + city + " " + this.area);
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
}
