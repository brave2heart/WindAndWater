package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.ZeriBean;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/22.
 * 宜忌选择适合器
 */

public class ZeriSelectEventAdapter extends AbsBaseAdapter<ZeriBean> {

    public ZeriSelectEventAdapter(Context context, List<ZeriBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, final ZeriBean zeriBean, final int position) {
        holder.setText(R.id.zeri_yiji_textView_item,zeriBean.getTitle())
                .setText(R.id.zeri_yiji_analysis_textView_item,zeriBean.getContent());
        ((CheckBox) holder.getViewV2(R.id.zeri_shixiang_checkBox_item)).setChecked(zeriBean.isCheck());


    }


}
