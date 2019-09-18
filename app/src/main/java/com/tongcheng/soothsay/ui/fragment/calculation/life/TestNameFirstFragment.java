package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.ui.activity.calculate.life.TestNameActivity;
import com.tongcheng.soothsay.ui.activity.calculate.life.TestQQActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/4.
 * 姓名测试输入界面
 */

public class TestNameFirstFragment extends BaseTitleFragment {
    @BindView(R.id.et_testname)
    BootstrapEditText mEt;
    @BindView(R.id.btn_testname)
    Button mBtn;

    private OnBtnClickListener onBtnClick;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_testname_first, container, false);
        return view;
    }

    @OnClick(R.id.btn_testname)
    public void onClick() {
        String name = mEt.getText().toString();
        boolean iscn = checkNameChinese(name);
        if (iscn && name.length() > 1) {
            onBtnClick.onClick(mBtn);
            ((TestNameActivity) getActivity()).setName(name);
        } else {
            ToastUtil.showShort(getContext(), "请输入中文姓名");
        }
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */

    public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;

        }

        return false;

    }


    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */

    public boolean checkNameChinese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {

            if (!isChinese(cTemp[i])) {

                res = false;

                break;

            }
        }
        return res;

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
