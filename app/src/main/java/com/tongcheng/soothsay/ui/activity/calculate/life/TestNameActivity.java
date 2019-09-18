package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestNameFirstFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestNameResultFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestQQFirstFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestQQResultFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.WindowUtil;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/16.
 * 姓名测算
 */

public class TestNameActivity extends BaseTitleActivity {

    @BindView(R.id.fl_testname)
    FrameLayout flTestname;
    private TestNameFirstFragment firstFragment;
    private TestNameResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_testname);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("姓名测算");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TestNameActivity.this.finish();
            }
        });
        firstFragment = new TestNameFirstFragment();
        resultFragment = new TestNameResultFragment();

        switchFragment(firstFragment, TestQQFirstFragment.class.getSimpleName(), R.id.fl_testname);

        firstFragment.setOnBtnClickListener(new TestNameFirstFragment.OnBtnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ClickUtil.isFastClick()) return;

                switchFragment(resultFragment, TestNameResultFragment.class.getSimpleName(), R.id.fl_testname);
                WindowUtil.closeInputMethod(TestNameActivity.this);
            }
        });
    }

    //传值
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
