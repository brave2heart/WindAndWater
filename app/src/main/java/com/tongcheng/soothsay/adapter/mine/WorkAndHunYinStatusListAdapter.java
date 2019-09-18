package com.tongcheng.soothsay.adapter.mine;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.mine.WorkAndHunYinListBean;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/23.
 * 工作和婚姻状态列表适配器
 */

public class WorkAndHunYinStatusListAdapter extends AbsBaseAdapter<WorkAndHunYinListBean> {

    public WorkAndHunYinStatusListAdapter(Context context, List<WorkAndHunYinListBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, WorkAndHunYinListBean workAndHunYinListBean, int position) {
        TextView textView = (TextView) holder.getView(R.id.tv_dialog_mine_content);
        textView.setText(workAndHunYinListBean.getItem());
    }
}
