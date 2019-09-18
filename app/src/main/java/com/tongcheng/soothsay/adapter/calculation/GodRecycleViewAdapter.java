package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.GodBean;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/4.
 */

public class GodRecycleViewAdapter extends AbsBaseRecycleAdapter<GodBean> {
    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public GodRecycleViewAdapter(Context context, List<GodBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, GodBean godBean) {
        ImageView iv = (ImageView) holder.getView(R.id.iv_request);
        iv.setImageResource(godBean.getIconRes());
        TextView tv1 = (TextView) holder.getView(R.id.tv_request_1);
        tv1.setText(godBean.getName1());
        TextView tv2 = (TextView) holder.getView(R.id.tv_request_2);
        tv2.setText(godBean.getName2());
    }



}
