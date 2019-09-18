package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestIDCardFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.SystemTools;

import butterknife.BindView;

/**
 * Created by ALing on 2016/11/24 0024.
 * 身份证测算
 */

public class TestIDCardActivity extends BaseTitleActivity {
    @BindView(R.id.fl_test_fragment)
    FrameLayout flTestFragment;
    private TestIDCardFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_test);
        initData();

    }

    public void initData() {
        super.initView();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_test_idcard));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        firstFragment = new TestIDCardFragment();

        switchFragment(firstFragment, TestIDCardFragment.class.getSimpleName(), R.id.fl_test_fragment);

    }
}
