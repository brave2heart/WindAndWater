package com.tongcheng.soothsay.ui.activity.calculate.life;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.CalulationRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;
import com.tongcheng.soothsay.ui.fragment.calculation.life.zeri.ZeriInputViewActivity;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/4.
 * 命里推算-生活指南
 */

public class LifeGuideActivity extends BaseTitleActivity {
    @BindView(R.id.rv_clifford)
    RecyclerView mRecycleView;
    private List<CliffordBean> mDatas;
    private CalulationRecycleAdapter mAdapter;
    String[] title = new String[]{};
    String[] content = new String[]{};
    private int[] img = {
            R.drawable.xyd_icon, R.drawable.shzn_icon_zr, R.drawable.sj_icon, R.drawable.shzn_icon_cp,
            R.drawable.shzn_icon_qq, R.drawable.shzn_icon_sfz, R.drawable.shzn_icon_xm, R.drawable.god_7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_clifford);
        getBaseHeadView().showTitle("生活指南");
        initData();
        initRecycleView();
    }


    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        title = getResources().getStringArray(R.array.life_title);
        content = getResources().getStringArray(R.array.life_intro);
        for (int i = 0; i < title.length; i++) {
            CliffordBean bean = new CliffordBean(img[i], title[i], content[i]);
            mDatas.add(bean);
        }
//        mTv.setText(getResources().getString(R.string.txt_life));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void initRecycleView() {
        mAdapter = new CalulationRecycleAdapter(LifeGuideActivity.this, mDatas, R.layout.item_clifford);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        //分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //item点击监听
        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    //许愿灯
                    case 0:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, WishingLightActivity.class);
                        break;
                    //择日
                    case 1:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, ZeriInputViewActivity.class);
                        break;
                    //手机号码测算
                    case 2:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, TestPhoneNumberActivity.class);
                        break;
                    //车牌号码测算
                    case 3:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, TestLicensePlateNumberActivity.class);
                        break;
                    //qq号测凶吉
                    case 4:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, TestQQActivity.class);
                        break;
                    //身份证号测算
                    case 5:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, TestIDCardActivity.class);
                        break;
                    //姓名测算
                    case 6:
                        GotoUtil.GoToActivity(LifeGuideActivity.this, TestNameActivity.class);
                        break;
                }
            }
        });
    }
}
