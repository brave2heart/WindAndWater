package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestQQFirstFragment;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestQQResultFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.SystemTools;
import com.tongcheng.soothsay.utils.WindowUtil;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/4.
 * qq号测凶吉
 */

public class TestQQActivity extends BaseTitleActivity {

    @BindView(R.id.fl_testqq)
    FrameLayout mFragment;

    private TestQQFirstFragment firstFragment;
    private TestQQResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_testqq);
        initData();
    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("Q号测算");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TestQQActivity.this.finish();
            }
        });
        firstFragment = new TestQQFirstFragment();
        resultFragment = new TestQQResultFragment();

        switchFragment(firstFragment, TestQQFirstFragment.class.getSimpleName(),R.id.fl_testqq);

        firstFragment.setOnBtnClickListener(new TestQQFirstFragment.OnBtnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ClickUtil.isFastClick()) return;
                switchFragment(resultFragment, TestQQResultFragment.class.getSimpleName(),R.id.fl_testqq);
                WindowUtil.closeInputMethod(TestQQActivity.this);
            }
        });
    }

    //传值
    private String qqnumber;

    public String getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
    }
}
