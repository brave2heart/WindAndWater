package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.HuangDaXianBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;


/**
 * 黄大仙求签介绍
 */

public class HuangDaXianJieQianActivity extends BaseTitleActivity {


    @BindView(R.id.tv_hdx_dijiqian)
    TextView mTvHdxDijiqian;
    @BindView(R.id.tv_hdx_subtitle)
    TextView mTvHdxSubtitle;
    @BindView(R.id.tv_hdx_title)
    TextView mTvHdxTitle;
    @BindView(R.id.tv_hdx_qianci_1)
    TextView mTvHdxQianci1;
    @BindView(R.id.tv_hdx_qianci_2)
    TextView mTvHdxQianci2;
    @BindView(R.id.tv_hdx_qianci_3)
    TextView mTvHdxQianci3;
    @BindView(R.id.tv_hdx_qianci_4)
    TextView mTvHdxQianci4;
    @BindView(R.id.tv_hdx_jiexi)
    TextView mTvHdxJiexi;
    @BindView(R.id.tv_hdx_liunian)
    TextView mTvHdxLiunian;
    @BindView(R.id.tv_hdx_shiye)
    TextView mTvHdxShiye;
    @BindView(R.id.tv_hdx_caifu)
    TextView mTvHdxCaifu;
    @BindView(R.id.tv_hdx_zishen)
    TextView mTvHdxZishen;
    @BindView(R.id.tv_hdx_jiating)
    TextView mTvHdxJiating;
    @BindView(R.id.tv_hdx_hunyin)
    TextView mTvHdxHunyin;
    @BindView(R.id.tv_hdx_yiju)
    TextView mTvHdxYiju;
    @BindView(R.id.tv_hdx_mingyu)
    TextView mTvHdxMingyu;
    @BindView(R.id.tv_hdx_jiankang)
    TextView mTvHdxJiankang;
    @BindView(R.id.tv_hdx_youyi)
    TextView mTvHdxYouyi;
    private HuangDaXianBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_huangdaxian_jieqian);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.hdxlq_app_name));
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
        HuangDaXianBean mBean = new HuangDaXianBean(integer);

         mTvHdxDijiqian.setText(mBean.getdijiqian());

         mTvHdxSubtitle.setText(mBean.getyunshi());

         mTvHdxTitle.setText(mBean.gettitle());
        String[] qianCiArray = mBean.getQianCiArray();

        mTvHdxQianci1.setText(qianCiArray[0]);

         mTvHdxQianci2.setText(qianCiArray[1]);

         mTvHdxQianci3.setText(qianCiArray[2]);

         mTvHdxQianci4.setText(qianCiArray[3]);

         mTvHdxJiexi.setText(mBean.getqianjie());

         mTvHdxLiunian.setText(mBean.getliunian());

         mTvHdxShiye.setText(mBean.getshiye());

         mTvHdxCaifu.setText(mBean.getcaifu());

         mTvHdxZishen.setText(mBean.getzishen());

         mTvHdxJiating.setText(mBean.getjiating());

         mTvHdxHunyin.setText(mBean.gethunyin());

         mTvHdxYiju.setText(mBean.getyiju());

         mTvHdxMingyu.setText(mBean.getmingyu());

         mTvHdxJiankang.setText(mBean.getjiating());

         mTvHdxYouyi.setText(mBean.getyouyi());


    }


}
