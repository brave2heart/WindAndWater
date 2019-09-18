package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.ui.activity.calculate.life.TestQQActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/4.
 * qq号测凶吉测试开始界面
 */

public class TestQQFirstFragment extends BaseTitleFragment {
    @BindView(R.id.et_testnumber)
    BootstrapEditText mEt;
    @BindView(R.id.btn_testnumber)
    Button mBtn;

    private OnBtnClickListener onBtnClick;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_testqq_first, container, false);
        return view;
    }

    @OnClick(R.id.btn_testnumber)
    public void onClick() {
        if (ClickUtil.isFastClick()) {
            return;
        }
        String qqNumber = mEt.getText().toString();
        String regex = "[1-9][0-9]{4,14}";
        boolean flag = qqNumber.matches(regex);
        if ("".equals(qqNumber)) {
            ToastUtil.showShort(getActivity(), "QQ号不能为空");
        } else if (flag) {
            onBtnClick.onClick(mBtn);
            ((TestQQActivity) getActivity()).setQqnumber(qqNumber);
        } else {
            ToastUtil.showShort(getActivity(), "QQ号不正确");
        }
    }

    public OnBtnClickListener getOnBtnClick() {
        return onBtnClick;
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClick) {
        this.onBtnClick = onBtnClick;
    }

    public interface OnBtnClickListener {
        void onClick(View view);
    }
}
