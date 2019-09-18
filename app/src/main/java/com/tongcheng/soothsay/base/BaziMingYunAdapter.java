package com.tongcheng.soothsay.base;

import android.content.Context;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziSzshishenBean;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/12.
 */

public class BaziMingYunAdapter extends AbsBaseAdapter<BaziSzshishenBean> {


    public BaziMingYunAdapter(Context context, List<BaziSzshishenBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, BaziSzshishenBean baziSzshishenBean, int position) {
        holder.setText(R.id.item_bazi_mingyun_title,baziSzshishenBean.getSiZhu());
        holder.setText(R.id.item_bazi_mingyun_icon,baziSzshishenBean.getShiShen());
        holder.setText(R.id.item_bazi_mingyun_content,baziSzshishenBean.getJieLun());
    }
}
