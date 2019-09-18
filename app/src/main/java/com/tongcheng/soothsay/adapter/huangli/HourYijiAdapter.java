package com.tongcheng.soothsay.adapter.huangli;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.huangli.HousYijiBean;

import java.util.List;

/**
 * Created by Steven on 16/11/14.
 */

public class HourYijiAdapter extends AbsBaseAdapter<HousYijiBean.ResultBean> {

    private String [] hours = {"丑","寅","卯","辰","巳","午","未","申","酉","戌","亥","子"};

    private String [] times ={"01:00-02:59","03:00-04:59","05:00-06:59","07:00-08:59","09:00-10:59","11:00-12:59","13:00-14:59","15:00-16:59","17:00-18:59","19:00-20:59","21:00-22:59","23:00-00:59"};

    public HourYijiAdapter(Context context, List<HousYijiBean.ResultBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, HousYijiBean.ResultBean resultBean, int position) {
        TextView tvGanzhi = (TextView) holder.getView(R.id.tv_hour_ganzhi);
        TextView tvTime = (TextView) holder.getView(R.id.tv_hour_time);
        TextView tvChongsha = (TextView) holder.getView(R.id.tv_hour_chongsha);
        TextView tvYi = (TextView) holder.getView(R.id.tv_hour_yi);
        TextView tvJi = (TextView) holder.getView(R.id.tv_hour_ji);

        String ganzhi = hours[position];
        String time = times[position];
        String chongsha [] = resultBean.getDes().trim().split(" ");
        String yi = resultBean.getYi();
        String ji = resultBean.getJi();

        yi = TextUtils.isEmpty(yi) ? "无" : yi;
        ji = "-".equals(ji) ? "无" : ji;

        tvGanzhi.setText(ganzhi + "时");
        tvYi.setText(yi);
        tvJi.setText(ji);
        tvChongsha.setText(chongsha[0] + " " + chongsha[1]);
        tvTime.setText(time);

    }
}
