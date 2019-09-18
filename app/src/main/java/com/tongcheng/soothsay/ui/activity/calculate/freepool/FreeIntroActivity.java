package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import android.os.Bundle;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.utils.ClickUtil;

/**
 * 放生池简介
 */
public class FreeIntroActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_intro);

        getBaseHeadView().showTitle(getString(R.string.title_free_intro));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
