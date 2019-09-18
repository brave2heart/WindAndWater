package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.EventQianXianBean;
import com.tongcheng.soothsay.data.calculate.zhuanyun.qifutai.QiFuTaiTempData;
import com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun.TaoismImmortalFragment;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 宋家任 on 2016/11/7.
 * 神仙二级详情页
 */

public class ImmortalDetailsActivity extends BaseTitleActivity {

    @BindView(R.id.tv_immortal_zk)
    TextView tvImmortalZk;
    @BindView(R.id.tv_immortal_content)
    TextView tvImmortalContent;
    @BindView(R.id.ll_immortal_zk)
    LinearLayout llImmortalZk;
    @BindView(R.id.iv_immortal_zk)
    ImageView ivImmortalZk;
    @BindView(R.id.btn_immortal)
    BootstrapButton btn;
    @BindView(R.id.iv_immortal)
    ImageView ivImmortal;
    @BindView(R.id.tv_immortal_name)
    TextView tvName;
    @BindView(R.id.tv_immrotal_type)
    TextView tvType;
    @BindView(R.id.qifutai_ispraying)
    ImageView mIspraying;
//    这个用来保存临时的 已经请仙状态
    private QiFuTaiTempData mQiFuTaiTempData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_immortal_detail);
        mQiFuTaiTempData = QiFuTaiTempData.newInstaince();
        EventBusUtil.register(this);
        initBaseHeadView();
        initData();
    }

    private void initBaseHeadView() {
        getBaseHeadView().showTitle("大仙介绍");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImmortalDetailsActivity.this.finish();
            }
        });
    }

    //是道家  还是佛家
    int godType;

    int imgId;
    String name;
    String[] strs = new String[]{};

    @Override
    public void initData() {
        Intent intent = getIntent();

        //设置神像
        imgId = intent.getIntExtra("img", -1);
        if (imgId != -1)
            ivImmortal.setImageResource(imgId);

        //设置姓名
        name = intent.getStringExtra("name");
        if (!TextUtils.isEmpty(name))
            tvName.setText(name);


        if (mQiFuTaiTempData.isGodPraying(name)) {
            mIspraying.setVisibility(View.VISIBLE);
            btn.setClickable(false);
            btn.setBootstrapBrand(DefaultBootstrapBrand.REGULAR);

        }
        //设置大仙类型
        String type = intent.getStringExtra("type");
        if (!TextUtils.isEmpty(type))
            tvType.setText("祈福类型：" + type);
        int position = intent.getIntExtra("position", -1);
        String flag = intent.getStringExtra("flag");
        if ("dj".equals(flag)) {
            strs = getResources().getStringArray(R.array.daxian);
            tvImmortalContent.setText(strs[position]);
            godType = 1;
        } else if ("ps".equals(flag)) {
            strs = getResources().getStringArray(R.array.pusa);
            tvImmortalContent.setText(strs[position]);
            godType = 2;
        }

    }

    Boolean szflag = true;

    @OnClick({R.id.ll_immortal_zk, R.id.btn_immortal})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_immortal_zk://展开或收缩文字
                if (szflag) {
                    szflag = false;
                    tvImmortalZk.setText("收起");
                    ivImmortalZk.setImageResource(R.drawable.qifu_intro_collapse);
                    tvImmortalContent.setEllipsize(null);
                    tvImmortalContent.setSingleLine(szflag);
                } else {
                    szflag = true;
                    tvImmortalZk.setText("展开");
                    ivImmortalZk.setImageResource(R.drawable.qifu_intro_spread);
                    tvImmortalContent.setLines(3);
                }
                break;
            case R.id.btn_immortal:
                if (ClickUtil.isFastClick())return;
                Intent intent = new Intent(this, MyWishActivity.class);
                intent.putExtra("img", imgId);
                intent.putExtra("name", name);
                intent.putExtra("godType", godType);
                startActivity(intent);
//                ImmortalDetailsActivity.this.finish();
                break;
        }
    }


    @Subscribe
    public void QingXianSuccessful(EventQianXianBean bean){
        if (bean.getEvent()){
            ImmortalDetailsActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }
}
