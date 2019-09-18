package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.dao.ZiweiUserBean;
import com.tongcheng.soothsay.widget.RoundImageView;

import java.util.List;

/**
 * Created by Steven on 16/11/16.
 */

public class ZiweiInputAdapter extends AbsBaseAdapter<ZiweiUserBean> {


    public ZiweiInputAdapter(Context context, List<ZiweiUserBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, ZiweiUserBean bean, int position) {
        TextView tvName = (TextView) holder.getView(R.id.tv_ziwei_input_name);
        TextView tvSex = (TextView) holder.getView(R.id.tv_ziwei_input_sex);
        TextView tvBirthday = (TextView) holder.getView(R.id.tv_ziwei_input_birthday);
        RoundImageView img = (RoundImageView) holder.getView(R.id.img_ziwei_input_icon);

        String name = bean.getName();
        String sex = bean.getSex();
        String day = bean.getDate();
        String temp [] = day.split("\\.");
        int icon = bean.getIcon();

        img.setImageResource(icon);
        tvName.setText("姓名: " + name);
        tvSex.setText("性别: " + sex);
        tvBirthday.setText("生辰: " + temp[0] + "年" + temp[1] + "月" + temp[2] + "日" + temp[3] + "时");
    }
}
