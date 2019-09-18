package com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.ZiweiAbsBean;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean;
import com.tongcheng.soothsay.bean.calculation.ZiweipaipanBean.*;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.BaziPaipanActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.UMShareUtils;
import com.tongcheng.soothsay.utils.WindowUtil;
import com.tongcheng.soothsay.widget.MingpanView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 紫微排盘
 * ["紫微","廉贞","七杀","武曲","破军","太阳","太阴","天府","天相","天同","天机","天梁","巨门","贪狼"]
 */
public class ZiweiPPActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.item_ziwei_content)
    MingpanView mpView;
    @BindView(R.id.ziwei_paipan)
    ViewGroup ppView;
    @BindView(R.id.ziwei_info)
    ViewGroup infoView;
    @BindView(R.id.item_ziwei_content_zuozhong)
    TextView ppZz;
    @BindView(R.id.item_ziwei_content_youzhong)
    TextView ppYz;
    @BindView(R.id.item_ziwei_content_daxian)
    TextView ppDaxian;
    @BindView(R.id.item_ziwei_content_xiaoxian)
    TextView ppXiaoxian;
    @BindView(R.id.item_ziwei_content_gz)
    TextView ppGz;
    @BindView(R.id.item_ziwei_content_zuoxia)
    TextView ppZx;
    @BindView(R.id.item_ziwei_content_youxia)
    TextView ppYx;
    @BindView(R.id.item_ziwei_content_minggong)
    TextView ppMg;
    @BindView(R.id.item_ziwei_content_ll)
    LinearLayout ppTopll;
    @BindView(R.id.item_ziwei_content_rl)
    LinearLayout ppToprl;
    @BindView(R.id.btn_ziwei_fenxi)
    TextView btnFenxi;
    @BindView(R.id.btn_ziwei_fenxi1)
    TextView btnFenxi1;


    //主星
    private String zx[] = {"紫微", "廉贞", "七杀", "武曲", "破军", "太阳", "太阴", "天府", "天相", "天同", "天机", "天梁", "巨门", "贪狼"};

    private List<View> views;
    private SparseArray<ZiweiAbsBean> beanMap;
    private ZiweipaipanBean mBean;
    private ZiweiUserBean inputBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziwei_pp);

        initView();
        initListener();
    }


    @Override
    public void initListener() {
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        getBaseHeadView().showHeadRightImageButton(R.drawable.icon_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UMShareUtils(ZiweiPPActivity.this).shareDefault(R.drawable.share_ziwei, "紫薇命盘",
                        getResources().getString(R.string.txt_share_zwmp), Constant.Url.SHARE_APP);
            }
        });
    }

    @OnClick({R.id.item_ziwei_content,
            R.id.btn_ziwei_fenxi1,
            R.id.btn_ziwei_fenxi})
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.item_ziwei_content) {
            mpView.switchView(MingpanView.VIEW_INFO);
            return;
        }

        //命盘分析
        if (v.getId() == R.id.btn_ziwei_fenxi || v.getId() == R.id.btn_ziwei_fenxi1) {
            Intent intent = new Intent(this, ZiweiAnalyseActivity.class);
            intent.putExtra(ZiweiInputConstant.INTENT_INPUT, inputBean);
            startActivity(intent);
            return;
        }

        changeBg();
        v.setSelected(true);

        int pois[] = computePoi((Integer) v.getTag());
        mpView.setOrder(pois[0], pois[1], pois[2], pois[3]);
        mpView.switchView(MingpanView.VIEW_PAIPAN);
        ShowZiweiPaiPanData(beanMap.get((Integer) v.getTag()));
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getResources().getString(R.string.title_ziwei_mingpan));
        pullAwayBean();     //由于服务器给的bean对象是12个不同的对象，不能用于循环填充，所以需要抽离数据放到一个相同的bean里面

        views = new ArrayList<View>();
        //填充12个命宫数据
        for (int i = 1; i <= 12; i++) {
            int id = getResources().getIdentifier("item_ziwei_" + i, "id", getPackageName());
            View v = findViewById(id);
            ZiweiAbsBean bean = beanMap.get(i);
            fillMinggong(v, bean);
            v.setTag(i);
            v.setOnClickListener(this);
            views.add(v);
        }

        initInfoView();

    }


    //初始化用户信息
    private void initInfoView() {
        BaseInfoBean infoBean = mBean.getBaseInfo();
        TextView tvName = (TextView) infoView.findViewById(R.id.item_ziwei_content_name);
        TextView tvAnimal = (TextView) infoView.findViewById(R.id.item_ziwei_content_animal);
        TextView tvSex = (TextView) infoView.findViewById(R.id.item_ziwei_content_sex);
        TextView tvDate = (TextView) infoView.findViewById(R.id.item_ziwei_content_date);
        TextView tvOldDate = (TextView) infoView.findViewById(R.id.item_ziwei_content_oldDate);
        TextView tvMg = (TextView) infoView.findViewById(R.id.item_ziwei_info_minggong);
        TextView tvMz = (TextView) infoView.findViewById(R.id.item_ziwei_info_mingzhu);
        TextView tvSg = (TextView) infoView.findViewById(R.id.item_ziwei_info_shengong);
        TextView tvSz = (TextView) infoView.findViewById(R.id.item_ziwei_info_shenzhu);
        TextView tvWuxing = (TextView) infoView.findViewById(R.id.item_ziwei_info_wuxing);

        tvName.setText("姓名:  " + infoBean.getName());
        tvAnimal.setText("生肖:  " + infoBean.getAnimals());
        tvSex.setText("性别:  " + infoBean.getSexYY());
        tvDate.setText("阳历:  " + infoBean.getSolarDate());
        tvOldDate.setText("阴历:  " + infoBean.getLunarDate());
        tvMg.setText(infoBean.getMingGong());
        tvMz.setText(infoBean.getMingZhu());
        tvSg.setText(infoBean.getShengGong());
        tvSz.setText(infoBean.getShenZhu());
        tvWuxing.setText(infoBean.getWuXingJu());
    }


    //将12命宫数据显示到中间
    private void ShowZiweiPaiPanData(ZiweiAbsBean bean) {
        if (ppTopll.getChildCount() != 0) {
            ppTopll.removeAllViews();
        }

        //上部星
        List<String> topYs = bean.getXingMzYS();
        List<String> topZs = bean.getXingMzZS();
        fillTopXing(topZs, ppTopll);
        fillTopXing(topYs, ppToprl);

        //中部星
        ppDaxian.setText(bean.getXiaoXian());
        ppXiaoxian.setText(bean.getDaXian());
        ppZz.setText(bean.getXingMzZZ().get(0));
        ppYz.setText(bean.getXingMzYZ().get(0));
        ppZx.setText(bean.getXingMzZX().get(0));
        ppYx.setText(bean.getXingMzYX().get(0));
        ppGz.setText(bean.getGongGz());
        ppMg.setText(bean.getGongMz());

    }

    //将上部星填充到ViewGroup里面
    private void fillTopXing(List<String> tops, LinearLayout viewGruop) {
        if (viewGruop.getChildCount() != 0)
            viewGruop.removeAllViews();

        for (String s : tops) {
            String topX = s;
            SpannableStringBuilder builder = new SpannableStringBuilder(topX);
            if (isMainStar(topX)) {
                ForegroundColorSpan forSpan = new ForegroundColorSpan(getResources().getColor(R.color.ziwei_topxing_red));
                builder.setSpan(forSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (topX.length() >= 4) {
                BackgroundColorSpan bgSpan = new BackgroundColorSpan(getResources().getColor(R.color.ziwei));
                ForegroundColorSpan fSpan = new ForegroundColorSpan(getResources().getColor(R.color.white));
                try {
                    builder.setSpan(bgSpan, 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(fSpan, 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (Exception e) {
                    builder.setSpan(bgSpan, 3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(fSpan, 3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            int dp = WindowUtil.dip2px(this, 2);
            LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            param.leftMargin = dp / 2;
            param.rightMargin = dp / 2;
            TextView tv = new TextView(this);
            tv.setEms(1);
            tv.setLineSpacing(-dp, 1.0f);
            tv.setTextSize(12);
            tv.setTextColor(getResources().getColor(R.color.ziwei_topxing_gray));
            tv.setText(builder);
            tv.setLayoutParams(param);

            viewGruop.addView(tv);
        }
    }


    //填充12个命宫数据
    private void fillMinggong(View v, ZiweiAbsBean bean) {
        //上部星
        LinearLayout llTop = (LinearLayout) v.findViewById(R.id.ll_topxing);
        List<String> topYs = bean.getXingMzYS();
        List<String> topZs = bean.getXingMzZS();
        List<String> tops = new ArrayList<String>();
        if (topYs != null) {
            tops.addAll(topYs);
        }
        if (topZs != null) {
            tops.addAll(topZs);
        }
        for (int i = 0; i < llTop.getChildCount(); i++) {
            TextView tv = (TextView) llTop.getChildAt(i);
            if (i >= tops.size()) {
                break;
            }
            String topX = tops.get(i);
            SpannableStringBuilder builder = new SpannableStringBuilder(topX);
            if (isMainStar(topX)) {
                ForegroundColorSpan forSpan = new ForegroundColorSpan(getResources().getColor(R.color.ziwei_topxing_red));
                builder.setSpan(forSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (topX.length() >= 4) {
                BackgroundColorSpan bgSpan = new BackgroundColorSpan(getResources().getColor(R.color.ziwei));
                ForegroundColorSpan fSpan = new ForegroundColorSpan(getResources().getColor(R.color.white));
                try {
                    builder.setSpan(bgSpan, 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(fSpan, 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (Exception e) {
                    builder.setSpan(bgSpan, 3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(fSpan, 3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(builder);


        }

        //中部星
        TextView tvYouzhong = (TextView) v.findViewById(R.id.youzhongxing);
        TextView tvZuozhong = (TextView) v.findViewById(R.id.zuozhongxing);
        TextView tvXiaoxian = (TextView) v.findViewById(R.id.xiaoxian);
        TextView tvGz = (TextView) v.findViewById(R.id.ganzhi);
        tvYouzhong.setText(bean.getXingMzYZ().get(0));
        tvZuozhong.setText(bean.getXingMzZZ().get(0));
        tvXiaoxian.setText(bean.getDaXian());
        tvGz.setText(bean.getGongGz());

        //下部星
        TextView tvYouxia = (TextView) v.findViewById(R.id.youxia);
        TextView tvZuoxia = (TextView) v.findViewById(R.id.zuoxia);
        TextView tvMz = (TextView) v.findViewById(R.id.minggong);
        tvYouxia.setText(bean.getXingMzYX().get(0));
        tvZuoxia.setText(bean.getXingMzZX().get(0));
        tvMz.setText(bean.getGongMz());
    }

    //判断是否是主星
    private boolean isMainStar(String star) {
        for (String s : zx) {
            if (star.contains(s)) {
                return true;
            }

        }
        return false;
    }

    //由于服务器给的bean对象是12个不同的对象，不能用于循环填充，所以需要抽离数据放到一个相同的bean里面
    private void pullAwayBean() {
        inputBean = (ZiweiUserBean) getIntent().getSerializableExtra(ZiweiInputConstant.INTENT_INPUT);
        mBean = (ZiweipaipanBean) getIntent().getSerializableExtra(Constant.INTENT_DATA);
        beanMap = new SparseArray<ZiweiAbsBean>();
        beanMap.put(1, mBean.getGongWeiSi());
        beanMap.put(2, mBean.getGongWeiWu());
        beanMap.put(3, mBean.getGongWeiWei());
        beanMap.put(4, mBean.getGongWeiShen());
        beanMap.put(5, mBean.getGongWeiYou());
        beanMap.put(6, mBean.getGongWeiXu());
        beanMap.put(7, mBean.getGongWeiHai());
        beanMap.put(8, mBean.getGongWeiZi());
        beanMap.put(9, mBean.getGongWeiChou());
        beanMap.put(10, mBean.getGongWeiYin());
        beanMap.put(11, mBean.getGongWeiMao());
        beanMap.put(12, mBean.getGongWeiChen());
    }


    //计算连线view的位置
    private int[] computePoi(int currTag) {
        int pois[] = new int[4];
        pois[0] = currTag;
        pois[1] = (int) nextView(currTag).getTag();
        pois[2] = (int) nextView(pois[1]).getTag();
        pois[3] = (int) oppositeView(currTag).getTag();
        return pois;
    }

    //下一个view
    private View nextView(int nextPoi) {
        int temp = nextPoi + 4;
        View view = null;
        if (temp > 12) {
            view = views.get(temp - 12 - 1);
        } else {
            view = views.get(temp - 1);
        }

        return view;
    }

    //对角view
    private View oppositeView(int currTag) {
        View view = null;
        int temp = currTag + 6;
        if (temp > 12) {
            view = views.get(temp - 12 - 1);
        } else {
            view = views.get(temp - 1);
        }

        return view;
    }

    private void changeBg() {
        for (View v : views) {
            if (v.isSelected()) {
                v.setSelected(false);
            }
        }
    }
}
