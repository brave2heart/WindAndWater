package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.GuanYinBean;
import com.tongcheng.soothsay.bean.calculation.HuangDaXianBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;


/**
 * 黄大仙求签介绍
 */

public class GuanYinJieQianActivity extends BaseTitleActivity {


    @BindView(R.id.tv_guanyin_dijiqian)
    TextView mTvGuanyinDijiqian;
    @BindView(R.id.tv_guanyin_subtitle)
    TextView mTvGuanyinSubtitle;
    @BindView(R.id.tv_guanyin_title)
    TextView mTvGuanyinTitle;
    @BindView(R.id.tv_guanyin_qianci_1)
    TextView mTvGuanyinQianci1;
    @BindView(R.id.tv_guanyin_qianci_2)
    TextView mTvGuanyinQianci2;
    @BindView(R.id.tv_guanyin_qianci_3)
    TextView mTvGuanyinQianci3;
    @BindView(R.id.tv_guanyin_qianci_4)
    TextView mTvGuanyinQianci4;
    @BindView(R.id.tv_guanyin_jieyue)
    TextView mTvGuanyinJieyue;
    @BindView(R.id.tv_guanyin_jiazhai)
    TextView mTvGuanyinJiazhai;
    @BindView(R.id.tv_guanyin_zishen)
    TextView mTvGuanyinZishen;
    @BindView(R.id.tv_guanyin_qiuchai)
    TextView mTvGuanyinQiuchai;
    @BindView(R.id.tv_guanyin_jibing)
    TextView mTvGuanyinJibing;
    @BindView(R.id.tv_guanyin_hunyin)
    TextView mTvGuanyinHunyin;
    @BindView(R.id.tv_guanyin_liujia)
    TextView mTvGuanyinLiujia;
    @BindView(R.id.tv_guanyin_xingren)
    TextView mTvGuanyinXingren;
    @BindView(R.id.tv_guanyin_tiancan)
    TextView mTvGuanyinTiancan;
    @BindView(R.id.tv_guanyin_liuchu)
    TextView mTvGuanyinLiuchu;
    @BindView(R.id.tv_guanyin_xunren)
    TextView mTvGuanyinXunren;
    @BindView(R.id.tv_guanyin_gongsong)
    TextView mTvGuanyinGongsong;
    @BindView(R.id.tv_guanyin_yiqian)
    TextView mTvGuanyinYiqian;
    @BindView(R.id.tv_guanyin_shiwu)
    TextView mTvGuanyinShiwu;
    @BindView(R.id.tv_guanyin_jiaoyi)
    TextView mTvGuanyinJiaoyi;
    @BindView(R.id.tv_guanyin_shanfen)
    TextView mTvGuanyinShanfen;
    @BindView(R.id.tv_guanyin_diangu)
    TextView mTvGuanyinDiangu;
    private GuanYinBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_guanyin_jieqian);
        initListener();
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.gylq_app_name));
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
         mBean = new GuanYinBean(integer);


         mTvGuanyinDijiqian.setText(mBean.getDijiqian());

         mTvGuanyinSubtitle.setText(mBean.getYunshi());

         mTvGuanyinTitle.setText(mBean.getTitle());

        String[] qianCiArray = mBean.getQianCiArray();
        mTvGuanyinQianci1.setText(qianCiArray[0]);

         mTvGuanyinQianci2.setText(qianCiArray[1]);

         mTvGuanyinQianci3.setText(qianCiArray[2]);

         mTvGuanyinQianci4.setText(qianCiArray[3]);

         mTvGuanyinJieyue.setText("     "+mBean.getJieyue());

         mTvGuanyinJiazhai.setText(mBean.getJiazhai());

         mTvGuanyinZishen.setText(mBean.getZishen());

         mTvGuanyinQiuchai.setText(mBean.getQiuchai());

         mTvGuanyinJibing.setText(mBean.getJibing());

         mTvGuanyinHunyin.setText(mBean.getHunyin());

         mTvGuanyinLiujia.setText(mBean.getLiujia());

         mTvGuanyinXingren.setText(mBean.getXingren());

         mTvGuanyinTiancan.setText(mBean.getTiancan());

         mTvGuanyinLiuchu.setText(mBean.getLiuchu());

         mTvGuanyinXunren.setText(mBean.getXunren());

         mTvGuanyinGongsong.setText(mBean.getGongsong());

         mTvGuanyinYiqian.setText(mBean.getYiqian());

         mTvGuanyinShiwu.setText(mBean.getShiwu());

         mTvGuanyinJiaoyi.setText(mBean.getJiaoyi());

         mTvGuanyinShanfen.setText(mBean.getShanfen());

         mTvGuanyinDiangu.setText(mBean.getDiangu());
    }


}
