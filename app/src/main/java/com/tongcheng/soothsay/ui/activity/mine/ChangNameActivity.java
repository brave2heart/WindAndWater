package com.tongcheng.soothsay.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/23.
 * 修改昵称
 */

public class ChangNameActivity extends BaseTitleActivity {

    @BindView(R.id.et_change_name)
    TextInputEditText etName;
    @BindView(R.id.btn_change_name_save)
    BootstrapButton btnSave;

    String newName;
    String oldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_change_name);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        getBaseHeadView().showTitle("修改昵称");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangNameActivity.this.finish();
            }
        });
        etName.setText(UserManager.getInstance().getUserName(ChangNameActivity.this));
        btnSave.setEnabled(false);
        etName.setSelection(etName.getText().length());
        etName.addTextChangedListener(new TextWatcher() {

            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.toString().equals(oldName)) {
                    btnSave.setEnabled(false);
                } else {
                    newName = temp.toString();
                    btnSave.setEnabled(true);
                }
            }
        });
    }


    @OnClick(R.id.btn_change_name_save)
    public void onClick() {
        if (newName.length() <= 0 || newName.equals(oldName)) {
            ToastUtil.showShort(this, "请输入新昵称");
            return;
        }
        AllApi.getInstance().changeNane(newName, UserManager.getInstance().getUser().getToken())
                .enqueue(new ApiCallBack<ApiResponseBean<Object>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtil.showShort(ChangNameActivity.this, "修改成功");
                        UserManager.getInstance().setUserName(ChangNameActivity.this, newName);
                        Intent intent = new Intent();
                        intent.putExtra("name", newName);
                        setResult(1000, intent);
                        finish();
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        boolean b = ErrorCodeUtil.showHaveTokenError(ChangNameActivity.this, errorCode);
                        if (b==false) {
                            ToastUtil.showShort(ChangNameActivity.this, message);
                        }
                    }
                }));
    }
}
