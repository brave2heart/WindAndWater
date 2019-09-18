package com.tongcheng.soothsay.ui.activity.calculate.zhuanyun;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipan.BaziPaipanInputConstant;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.bazi.BaziLiuNianActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.UMShareUtils;
import com.tongcheng.soothsay.utils.WuxingUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bozhihuatong on 2016/11/24.
 * 八字排盘
 */

public class BaziPaipanActivity extends BaseTitleActivity {


    @BindView(R.id.tv_bazipaipan_name)
    TextView mTvBazipaipanName;

    @BindView(R.id.tv_bazipaipan_sex)
    TextView mTvBazipaipanSex;

    @BindView(R.id.tv_bazipaipan_birthday)
    TextView mTvBazipaipanBirthday;

    @BindView(R.id.tv_bazipaipan_nongLi)
    TextView mTvBazipaipanNongLi;

    @BindView(R.id.iv_shengxiao)
    ImageView mIvShengxiao;
    @BindView(R.id.tv_shengxiao)
    TextView mTvShengxiao;


    /**
     * 十神
     */
    @BindView(R.id.tv_sishishen1)
    TextView mTvSishishen1;
    @BindView(R.id.tv_sishishen2)
    TextView mTvSishishen2;
    @BindView(R.id.tv_sishishen3)
    TextView mTvSishishen3;
    @BindView(R.id.tv_sishishen4)
    TextView mTvSishishen4;


    /**
     * 八字
     */
    @BindView(R.id.tv_bazi1)
    TextView mTvBazi1;
    @BindView(R.id.tv_bazi2)
    TextView mTvBazi2;
    @BindView(R.id.tv_bazi3)
    TextView mTvBazi3;
    @BindView(R.id.tv_bazi4)
    TextView mTvBazi4;

    /**
     * 藏干
     */
    @BindView(R.id.tvzangGan1)
    TextView mTvzangGan1;
    @BindView(R.id.tvzangGan2)
    TextView mTvzangGan2;
    @BindView(R.id.tvzangGan3)
    TextView mTvzangGan3;
    @BindView(R.id.tvzangGan4)
    TextView mTvzangGan4;

    /**
     * 纳音
     */
    @BindView(R.id.tv_nayin1)
    TextView mTvNayin1;
    @BindView(R.id.tv_nayin2)
    TextView mTvNayin2;
    @BindView(R.id.tv_nayin3)
    TextView mTvNayin3;
    @BindView(R.id.tv_nayin4)
    TextView mTvNayin4;

    /**
     * 四柱吉凶神煞
     */
    @BindView(R.id.tv_sizhushensha)
    TextView mTvSizhushensha;

    /**
     * 几年后起运
     */
    @BindView(R.id.tv_age)
    TextView mTvAge;

    /**
     * 大运
     */

    @BindView(R.id.tv_dayun1)
    TextView mTvDayun1;
    @BindView(R.id.tv_dayun2)
    TextView mTvDayun2;
    @BindView(R.id.tv_dayun3)
    TextView mTvDayun3;
    @BindView(R.id.tv_dayun4)
    TextView mTvDayun4;
    @BindView(R.id.tv_dayun5)
    TextView mTvDayun5;
    @BindView(R.id.tv_dayun6)
    TextView mTvDayun6;
    @BindView(R.id.tv_dayun7)
    TextView mTvDayun7;
    @BindView(R.id.tv_dayun8)
    TextView mTvDayun8;


    /**
     * 大运时间是多少年起运后  每个加十年
     */
    @BindView(R.id.tv_dayun_year1)
    TextView mTvDayunYear1;
    @BindView(R.id.tv_dayun_year2)
    TextView mTvDayunYear2;
    @BindView(R.id.tv_dayun_year3)
    TextView mTvDayunYear3;
    @BindView(R.id.tv_dayun_year4)
    TextView mTvDayunYear4;
    @BindView(R.id.tv_dayun_year5)
    TextView mTvDayunYear5;
    @BindView(R.id.tv_dayun_year6)
    TextView mTvDayunYear6;
    @BindView(R.id.tv_dayun_year7)
    TextView mTvDayunYear7;
    @BindView(R.id.tv_dayun_year8)
    TextView mTvDayunYear8;


    /**
     * 表格 十神
     */
    @BindView(R.id.tv_shishen1)
    TextView mTvShishen1;
    @BindView(R.id.tv_shishen2)
    TextView mTvShishen2;
    @BindView(R.id.tv_shishen3)
    TextView mTvShishen3;
    @BindView(R.id.tv_shishen4)
    TextView mTvShishen4;
    @BindView(R.id.tv_shishen5)
    TextView mTvShishen5;
    @BindView(R.id.tv_shishen6)
    TextView mTvShishen6;
    @BindView(R.id.tv_shishen7)
    TextView mTvShishen7;
    @BindView(R.id.tv_shishen8)
    TextView mTvShishen8;
    @BindView(R.id.tv_ruowan)
    TextView mTvRuowan;
    @BindView(R.id.tv_xiyong)
    TextView mTvXiyong;
    private BaziUserBean inputBean;
    private BaziPaipanBean mBean;
    private WuxingUtil mWuxingUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.eightcharacters_bazi_xiantian_mingpan_graphic_fragment_layout2);
        mWuxingUtil = WuxingUtil.newInstace();
        initData();
        setBaziPaipan(mBean, inputBean);


    }


    @Override
    public void initData() {
        super.initData();
        inputBean = (BaziUserBean) getIntent().getSerializableExtra(BaziPaipanInputConstant.INTENT_INPUT);
        mBean = (BaziPaipanBean) getIntent().getSerializableExtra(Constant.INTENT_DATA);
        getBaseHeadView().showTitle("八字排盘");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getBaseHeadView().showHeadRightImageButton(R.drawable.icon_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UMShareUtils(BaziPaipanActivity.this).shareDefault(R.drawable.share_bazipaipan,"八字排盘",
                        getResources().getString(R.string.txt_share_bzpp),Constant.Url.SHARE_APP);
            }
        });
    }


    /**
     * @param bean
     */
    private void setBaziPaipan(BaziPaipanBean bean, BaziUserBean userBean) {
        mTvBazipaipanName.setText(userBean.getName());
        mTvBazipaipanSex.setText(userBean.getSex());
        mTvBazipaipanBirthday.setText(userBean.getDate());


        mTvBazipaipanNongLi.setText(bean.getNongLi());
        mTvShengxiao.setText(bean.getShengXiao());
        mIvShengxiao.setImageResource(mWuxingUtil.getShengXiaoImage(bean.getShengXiao()));

        mTvSishishen1.setText(bean.getSiShiShen().get(0));
        mTvSishishen2.setText(bean.getSiShiShen().get(1));
        mTvSishishen3.setText(bean.getSiShiShen().get(2));
        mTvSishishen4.setText(bean.getSiShiShen().get(3));

        String[] bazis = bean.getBaZi().split(",");
//       这里等等要对八字进行富文本修改
        setSpanText(mTvBazi1, bazis[0]);
        setSpanText(mTvBazi2, bazis[1]);
        setSpanText(mTvBazi3, bazis[2]);
        setSpanText(mTvBazi4, bazis[3]);


        checkAndSetZangGan(mTvzangGan1, bean.getZangGan().get(0));
        checkAndSetZangGan(mTvzangGan2, bean.getZangGan().get(1));
        checkAndSetZangGan(mTvzangGan3, bean.getZangGan().get(2));
        checkAndSetZangGan(mTvzangGan4, bean.getZangGan().get(3));


        mTvNayin1.setText(bean.getNaYin().get(0));
        mTvNayin2.setText(bean.getNaYin().get(1));
        mTvNayin3.setText(bean.getNaYin().get(2));
        mTvNayin4.setText(bean.getNaYin().get(3));


        StringBuilder sizhushensha = new StringBuilder();
        for (String s : bean.getSiZhuShenSha()) {
            sizhushensha.append(s + "\n");
        }
        sizhushensha.deleteCharAt(sizhushensha.length() - 2);
        mTvSizhushensha.setText(sizhushensha.toString());

        mTvRuowan.setText(bean.getWangRuo());

        mTvXiyong.setText(bean.getXiYong());


        mTvAge.setText("出生后" + bean.getAge() + "年交上大运");
        String date = inputBean.getDate();
        String[] split = date.split("年");
        String s = split[0];
        int dayunyear = Integer.valueOf(s) + Integer.valueOf(bean.getAge());
        mTvShishen1.setText(bean.getShiShen().get(0));
        mTvShishen2.setText(bean.getShiShen().get(1));
        mTvShishen3.setText(bean.getShiShen().get(2));
        mTvShishen4.setText(bean.getShiShen().get(3));
        mTvShishen5.setText(bean.getShiShen().get(4));
        mTvShishen6.setText(bean.getShiShen().get(5));
        mTvShishen7.setText(bean.getShiShen().get(6));
        mTvShishen8.setText(bean.getShiShen().get(7));

        mTvDayun1.setText(bean.getDaYun().get(0));
        mTvDayun2.setText(bean.getDaYun().get(1));
        mTvDayun3.setText(bean.getDaYun().get(2));
        mTvDayun4.setText(bean.getDaYun().get(3));
        mTvDayun5.setText(bean.getDaYun().get(4));
        mTvDayun6.setText(bean.getDaYun().get(5));
        mTvDayun7.setText(bean.getDaYun().get(6));
        mTvDayun8.setText(bean.getDaYun().get(7));


        mTvDayunYear1.setText(String.valueOf(dayunyear));
        mTvDayunYear2.setText(String.valueOf(dayunyear + 10));
        mTvDayunYear3.setText(String.valueOf(dayunyear + 20));
        mTvDayunYear4.setText(String.valueOf(dayunyear + 30));
        mTvDayunYear5.setText(String.valueOf(dayunyear + 40));
        mTvDayunYear6.setText(String.valueOf(dayunyear + 50));
        mTvDayunYear7.setText(String.valueOf(dayunyear + 60));
        mTvDayunYear8.setText(String.valueOf(dayunyear + 70));
    }

    private void checkAndSetZangGan(TextView textView, String s) {
        String newStr = s.replace(" null", "");
        textView.setText(newStr);
    }

    private void setSpanText(TextView textView, String str) {
        String tmep = str;
//        这只是暂时的 到时候  还需要ui给图片来修改
        if (str == null || str.length() < 2) return;
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.insert(1, " \n");
        stringBuilder.append("B");
        str = stringBuilder.toString();
        char[] chars = tmep.toCharArray();
        System.out.println(str);
        Drawable wuxing1 = getWuxing(String.valueOf(chars[0]));
        Drawable wuxing2 = getWuxing(String.valueOf(chars[1]));
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ImageSpan(wuxing1), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ImageSpan(wuxing2), str.length() - 1, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

    }


    private Drawable getWuxing(String name) {
        return mWuxingUtil.getWuxingImag(name);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @OnClick(R.id.btn_liaojiedayuanliunian)
    public void onClick() {
        if (ClickUtil.isFastClick()) {
            return;
        }
        GotoUtil.GoToActivityWithBean(this, BaziLiuNianActivity.class,mBean);
    }


//    public void Click(){
//        List<BaziPaipanBean.LiuNianBean> liuNian = mBean.getLiuNian();
//        new Bundle().putSerializable("LiuNian",mBean);
//    }
}
