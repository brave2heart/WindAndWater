package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.CalulationRecycleAdapter;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.widget.DividerItemDecoration;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;
import com.living.utils.GotoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 婚姻恋爱
 */

public class MarriageLoveActivity extends BaseTitleActivity {
    @BindView(R.id.rv_clifford)
    RecyclerView mRecycleView;
    private List<CliffordBean> mDatas;
    private CalulationRecycleAdapter mAdapter;
    String[] title = new String[]{};
    String[] content = new String[]{};
    //item 图片
    private int[] img = {
            R.drawable.hyal_icon_bmhh, R.drawable.hyal_icon_lchh, R.drawable.hyal_icon_sxpd, R.drawable.hyal_icon_xxpd};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_clifford);
        getBaseHeadView().showTitle(getResources().getString(R.string.title_marriage_love));
        initData();
        initRecycleView();
    }


    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        title = getResources().getStringArray(R.array.marray_love_title);
        content = getResources().getStringArray(R.array.marray_love_intro);
        for (int i = 0; i < title.length; i++) {
            CliffordBean bean = new CliffordBean(img[i], title[i],content[i]);
            mDatas.add(bean);
        }
//        mTitle.setText(getResources().getString(R.string.txt_marrage_love));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void initRecycleView() {
        mAdapter = new CalulationRecycleAdapter(MarriageLoveActivity.this, mDatas, R.layout.item_clifford);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);
        //分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //item点击监听
        mAdapter.setOnReItemOnClickListener(new BaseRecyclerAdapter.OnReItemOnClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    //本命挂合婚
                    case 0:
                        GotoUtil.GoToActivity(MarriageLoveActivity.this, BenMingMarriageActivity.class);
                        break;
                    //吕才合婚
                    case 1:
                        GotoUtil.GoToActivity(MarriageLoveActivity.this, LvCaiMarriageActivity.class);
                        break;
//                    case 2:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"八字合婚");
//                        break;
//                    case 3:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"姓名配对");
//                        break;
//                    生肖配对
                    case 2:
                        GotoUtil.GoToActivity(MarriageLoveActivity.this, ZodiacPairingActivity.class);
                        break;
//                    case 5:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"八字测姻缘");
//                        break;
//                    血型配对
                    case 3:
                        GotoUtil.GoToActivity(MarriageLoveActivity.this, BloodTypeActivity.class);
                        break;
//                    case 7:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"单身桃花运势");
//                        break;
//                    case 8:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"姓名恋爱配对");
//                        break;
//                    case 9:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"个人姻缘");
//                        break;
//                    case 10:
//                        ToastUtil.showShort(MarriageLoveActivity.this,"透视未来婚姻");
//                        break;
                }

            }
        });
    }
}
