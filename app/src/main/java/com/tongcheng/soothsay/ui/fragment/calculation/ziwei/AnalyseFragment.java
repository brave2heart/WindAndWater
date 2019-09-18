package com.tongcheng.soothsay.ui.fragment.calculation.ziwei;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseLazyFragment;
import com.tongcheng.soothsay.bean.calculation.ZiweiAnalyseBean;
import com.tongcheng.soothsay.bean.calculation.ZiweiAnalyseBean.GongExplainBean;
import com.tongcheng.soothsay.bean.calculation.ZiweiAnalyseBean.GongExplainBean.ContentBean;
import com.tongcheng.soothsay.bean.calculation.ZiweiAnalyseBean.SfszListBean;
import com.tongcheng.soothsay.bean.calculation.ZiweiAnalyseBean.XingXiangInfoBean;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.chuantongApi;
import com.tongcheng.soothsay.ui.activity.calculate.chuantong.ziwei.ZiweiInputConstant;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Steven on 16/11/25.
 * 运程分析
 */
public class AnalyseFragment extends BaseLazyFragment implements View.OnClickListener {

    @BindView(R.id.tv_analyse_gongwei_info)             TextView        tvGongwei;
    @BindView(R.id.tv_analyse_zhuxing_info)             TextView        tvZhuxing;
    @BindView(R.id.tv_analyse_xingxiang_info)           TextView        tvXingxiang;
    @BindView(R.id.tv_analyse_remindInfo)               TextView        tvRemind;
    @BindView(R.id.ll_analyse)                          LinearLayout    ll;
    @BindView(R.id.ll_analyse_sfsz)                     LinearLayout    hScroll;

    //主星
    private String zx [] = {"紫微","廉贞","七杀","武曲","破军","太阳","太阴","天府","天相","天同","天机","天梁","巨门","贪狼"};

    private ZiweiAnalyseBean bean;

    private ZiweiUserBean inputBean;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ll.setVisibility(View.INVISIBLE);
        getBaseLoadingView().showLoading();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_analyse, container, false);
        return view;
    }


    @Override
    protected void lazyLoad() {
        if (bean == null) {
            String temp[] = inputBean.getDate().split("\\.");
            String date = temp[0] + "-" + temp[1] + "-" + temp[2] + " " + temp[3];
            String name = inputBean.getName();
            String sex = inputBean.getSex().equals("男") ? "1" : "2";
            String gongWZIndex = inputBean.getGongWZIndex();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("bornDate", date);
            map.put("name", name);
            map.put("sex", sex);
            map.put("gongWZIndex", gongWZIndex);
            chuantongApi.getInstance().getZiweiAnalyse(map).enqueue(new ApiCallBack<ApiResponseBean<ZiweiAnalyseBean>>(new BaseCallBack() {
                @Override
                public void onSuccess(Object data) {
                    bean = (ZiweiAnalyseBean) data;
                    getBaseLoadingView().hideLoading();
                    fillData(bean);
                }

                @Override
                public void onError(String errorCode, String message) {
                    getBaseLoadingView().hideLoading();
                    if (errorCode == ApiCallBack.NET_ERROR) {
                        getBaseEmptyView().showNetWorkView(R.drawable.nonetwork, R.string.showNeterr, R.string.showReload, AnalyseFragment.this);
                    }else{
                        getBaseEmptyView().showEmptyContent();
                    }
                }
            }));

        } else {
            fillData(bean);
        }
    }


    //填充数据
    private void fillData(ZiweiAnalyseBean bean) {
        if (bean == null) {
            getBaseEmptyView().showEmptyContent();
            return;
        }

        //三方四重
        List<SfszListBean> sfszBeans = bean.getSfszList();
        for(int i = 0; i< sfszBeans.size(); i++){
            SfszListBean sBean = sfszBeans.get(i);
            View v = View.inflate(getContext(),R.layout.item_horscroll_sfsz,null);
            LinearLayout llTop = (LinearLayout) v.findViewById(R.id.item_ziwei_content_ll);
            LinearLayout rlTop = (LinearLayout) v.findViewById(R.id.item_ziwei_content_rl);
            View bg = v.findViewById(R.id.rl_analyse_bg);

            if(i == 0)
                bg.setBackgroundResource(R.drawable.ziwei_no_bg);
            else if(i == 1)
                bg.setBackgroundResource(R.drawable.ziwei_zhao_bg);
            else
                bg.setBackgroundResource(R.drawable.ziwei_hui_bg);


            //上部星
            List<String> topYs = sBean.getXingMzYS();
            List<String> topZs = sBean.getXingMzZS();
            fillTopXing(topZs,llTop);
            fillTopXing(topYs,rlTop);


            //中部星
            TextView tvYouzhong = (TextView) v.findViewById(R.id.youzhongxing);
            TextView tvZuozhong = (TextView) v.findViewById(R.id.zuozhongxing);
            TextView tvXiaoxian = (TextView) v.findViewById(R.id.xiaoxian);
            TextView tvGz = (TextView) v.findViewById(R.id.ganzhi);
            tvYouzhong.setText(sBean.getXingMzYZ().get(0));
            tvZuozhong.setText(sBean.getXingMzZZ().get(0));
            tvXiaoxian.setText(sBean.getDaXian());
            tvGz.setText(sBean.getGongGz());

            //下部星
            TextView tvYouxia = (TextView) v.findViewById(R.id.youxia);
            TextView tvZuoxia = (TextView) v.findViewById(R.id.zuoxia);
            TextView tvMz = (TextView) v.findViewById(R.id.minggong);
            tvYouxia.setText(sBean.getXingMzYX().get(0));
            tvZuoxia.setText(sBean.getXingMzZX().get(0));
            tvMz.setText(sBean.getGongMz());

            if(hScroll != null){
                hScroll.addView(v);
            }
        }


        String gw = bean.getGongWeiInfo();
        String zx = bean.getZhuXingInfo();

        //星象
        XingXiangInfoBean xx = bean.getXingXiangInfo();
        if (xx != null) {
            StringBuffer sb = new StringBuffer();
            sb.append(xx.getMJiXing());
            sb.append("\n");
            sb.append(xx.getMShaXing());
            sb.append("\n");
            sb.append(xx.getMJXScale());
            tvXingxiang.setText(sb.toString());
        }
        tvGongwei.setText(gw);
        tvZhuxing.setText(zx);

        //宫位解释
        GongExplainBean expBean = bean.getGongExplain();
        if(expBean == null)
            return ;
        String remind = expBean.getRemind();
        tvRemind.setText(remind);

        //命宫解释
        for(ContentBean cBean : expBean.getContent()){
            View view = View.inflate(getContext(),R.layout.item_list_analyse,ll);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_analyse_childTitle);
            TextView tvContent = (TextView) view.findViewById(R.id.tv_analyse_childContent);
            float size = getResources().getDimension(R.dimen.textSize_m);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);

            tvTitle.setText(cBean.getChildTitle());
            tvContent.setText(cBean.getChildContent());

        }

        //填充完所有数据在显示界面
        ll.setVisibility(View.VISIBLE);
    }

    //将上部星填充到ViewGroup里面
    private void fillTopXing(List<String> tops , LinearLayout viewGruop){
        if(viewGruop.getChildCount() != 0)
            viewGruop.removeAllViews();

        for(String s : tops){
            String topX = s;
            SpannableStringBuilder builder = new SpannableStringBuilder(topX);
            if(isMainStar(topX)){
                ForegroundColorSpan forSpan = new ForegroundColorSpan(getResources().getColor(R.color.ziwei_topxing_red));
                builder.setSpan(forSpan,0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if(topX.length() >= 4){
                BackgroundColorSpan bgSpan = new BackgroundColorSpan(getResources().getColor(R.color.ziwei));
                ForegroundColorSpan fSpan = new ForegroundColorSpan(getResources().getColor(R.color.white));
                try{
                    builder.setSpan(bgSpan,4,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(fSpan,4,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }catch (Exception e){
                    builder.setSpan(bgSpan,3,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(fSpan,3,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            int dp = WindowUtil.dip2px(getContext(),2);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            param.leftMargin = dp / 2;
            param.rightMargin = dp / 2;
            TextView tv = new TextView(getContext());
            tv.setEms(1);
            tv.setLineSpacing(-dp,1.0f);
            tv.setTextSize(12);
            tv.setTextColor(getResources().getColor(R.color.ziwei_topxing_gray));
            tv.setText(builder);
            tv.setLayoutParams(param);

            viewGruop.addView(tv);
        }
    }


    //判断是否是主星
    private boolean isMainStar(String star){
        for(String s : zx){
            if(star.contains(s)){
                return true;
            }

        }
        return false;
    }

    @Override
    protected void onGetBundle(Bundle bundle) {
        inputBean = (ZiweiUserBean) bundle.getSerializable(ZiweiInputConstant.INTENT_INPUT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void onClick(View v) {
        if (ClickUtil.isFastClick()) {
            return;
        }
        if (v.getId() == R.id.base_reload) {
            lazyLoad();
        }
    }

}
