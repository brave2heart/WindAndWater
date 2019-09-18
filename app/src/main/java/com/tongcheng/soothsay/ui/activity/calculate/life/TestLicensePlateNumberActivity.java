package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.TestLicensePlateFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.SystemTools;

import butterknife.BindView;

/**
 * Created by ALing on 2016/11/24 0024.
 * 车牌号码测算
 */

public class TestLicensePlateNumberActivity extends BaseTitleActivity {
    @BindView(R.id.fl_test_fragment)
    FrameLayout flTestFragment;

    private TestLicensePlateFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_test);
        initData();
    }

    @Override
    public void initData() {
        super.initView();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_test_license));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        firstFragment = new TestLicensePlateFragment();
        switchFragment(firstFragment, TestLicensePlateFragment.class.getSimpleName(), R.id.fl_test_fragment);

    }

}
