package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.MaZuBean;

import butterknife.BindView;


/**
 * Created by ALing on 2016/12/13 0013.
 * 妈祖求签介绍
 */

public class MazuJieQianActivity extends BaseTitleActivity {


    @BindView(R.id.tv_mazu_dijiqian)
    TextView mTvMazuDijiqian;
    @BindView(R.id.tv_mazu_subtitle)
    TextView mTvMazuSubtitle;
    @BindView(R.id.tv_mazu_title)
    TextView mTvMazuTitle;
    @BindView(R.id.tv_mazu_qianci_1)
    TextView mTvMazuQianci1;
    @BindView(R.id.tv_mazu_qianci_2)
    TextView mTvMazuQianci2;
    @BindView(R.id.tv_mazu_qianci_3)
    TextView mTvMazuQianci3;
    @BindView(R.id.tv_mazu_qianci_4)
    TextView mTvMazuQianci4;
    @BindView(R.id.tv_mazu_jiexi)
    TextView mTvMazuJiexi;
    private MaZuBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mazu_jieqian);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_mazusign));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    @Override
    public void initData() {
        String position = getIntent().getStringExtra("position");
        Integer integer = Integer.valueOf(position);
        MaZuBean mBean = new MaZuBean(integer);
        if (mBean!=null) {
            mTvMazuDijiqian.setText(mBean.getdiJiQian());
            mTvMazuSubtitle.setText(mBean.getsubTitle());
            mTvMazuJiexi.setText(mBean.getJieXi());
            mTvMazuTitle.setText(mBean.getTitle());
            String[] qianCiArray = mBean.getQianCiArray();
            mTvMazuQianci1.setText(qianCiArray[0]);
            mTvMazuQianci2.setText(qianCiArray[1]);
            mTvMazuQianci3.setText(qianCiArray[2]);
            mTvMazuQianci4.setText(qianCiArray[3]);
        }
    }


}
