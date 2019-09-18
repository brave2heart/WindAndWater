package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.calculation.QfDetailBean;
import com.tongcheng.soothsay.utils.DateUtil;

import java.util.List;

/**
 */

public class DxDetailAdapter extends AbsBaseRecycleAdapter<QfDetailBean> {
    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */


    public DxDetailAdapter(Context context, List<QfDetailBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, QfDetailBean godBean) {

        holder.setText(R.id.tv_qifu_recor_date,DateUtil.formatTime(Long.valueOf(godBean.getQfDate()),"MM·dd"))
                .setText(R.id.tv_qifu_recor_gp,godBean.getQfGp());
//        TextView date = holder.getView(R.id.tv_qifu_recor_date);
//        TextView gp = holder.getView(R.id.tv_qifu_recor_gp);
//        date.setText(DateUtil.formatTime(Long.valueOf(godBean.getQfDate()),"MM·dd"));
//        gp.setText(godBean.getQfGp());


    }





}
