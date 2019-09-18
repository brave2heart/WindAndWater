package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/8.
 *周公解梦  结果  适配器
 */

public class OneiromancyDetalisAdapter extends AbsBaseAdapter<String> {


    public OneiromancyDetalisAdapter(Context context, List<String> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, String resultBean, int position) {
        TextView title= (TextView) holder.getView(R.id.tv_content);
        title.setText("    "+resultBean);
    }


}
