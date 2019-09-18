package com.tongcheng.soothsay.ui.activity.huangli.almanac;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.huangli.HourYijiAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 时辰宜忌
 */
public class HourYijiActivity extends BaseTitleActivity {

    @BindView(R.id.lv_hour_yiji)
    ListView  listView;

    private ArrayList<HousYijiBean.ResultBean> hourBeans;

    private HourYijiAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_hour_yiji);

        initView();
        initData();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getResources().getString(R.string.title_hour_yiji));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    @Override
    public void initData() {
        hourBeans = (ArrayList<HousYijiBean.ResultBean>) getIntent().getSerializableExtra("data");
        adapter = new HourYijiAdapter(this,hourBeans,R.layout.item_hour_list_yiji);
        listView.setAdapter(adapter);
    }
}
