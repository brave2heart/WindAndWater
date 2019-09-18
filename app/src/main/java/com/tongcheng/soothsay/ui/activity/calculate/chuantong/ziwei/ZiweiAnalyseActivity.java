package com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.ZiweiPagerAdapter;
import com.tongcheng.soothsay.base.BaseLazyFragment;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.ui.fragment.calculation.ziwei.AnalyseFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.MPagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 紫微命盘分析
 */
public class ZiweiAnalyseActivity extends BaseTitleActivity {


    @BindView(R.id.pst_tab_analyse)     MPagerSlidingTabStrip pstTabAnalyse;
    @BindView(R.id.vp_analyse)          ViewPager vpAnalyse;

    private String [] titles = {"自身状况","兄弟关系","婚姻感情","子女状况","财运状况","健康状况","迁移状况","人际关系","事业状况","产业状况","精神德行","父母情况"};
    private int [] icons = {R.drawable.selector_ziwei_tab_me,R.drawable.selector_ziwei_tab_xiongdi,
            R.drawable.selector_ziwei_tab_love,R.drawable.selector_ziwei_tab_zinv,
            R.drawable.selector_ziwei_tab_money,R.drawable.selector_ziwei_tab_health,
            R.drawable.selector_ziwei_tab_qianyi,R.drawable.selector_ziwei_tab_friend,
            R.drawable.selector_ziwei_tab_shiye,R.drawable.selector_ziwei_tab_chanye,
            R.drawable.selector_ziwei_tab_jingshen,R.drawable.selector_ziwei_tab_family,};

    private List<BaseLazyFragment> fragments ;

    private ZiweiUserBean inputBean;

    private ZiweiPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziwei_analyse);
        ButterKnife.bind(this);

        inputBean = (ZiweiUserBean) getIntent().getSerializableExtra(ZiweiInputConstant.INTENT_INPUT);
        initView();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getResources().getString(R.string.title_ziwei_analyse));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        fragments = new ArrayList<BaseLazyFragment>();
        for(int i=0;i<titles.length;i++){
            AnalyseFragment fragment = new AnalyseFragment();
            fragments.add(fragment);
        }

        pagerAdapter = new ZiweiPagerAdapter(getSupportFragmentManager(),fragments,titles,icons,inputBean);
        vpAnalyse.setAdapter(pagerAdapter);
        pstTabAnalyse.setViewPager(vpAnalyse);

    }
}
