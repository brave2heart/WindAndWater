package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.CalculationGridViewBean;

import java.util.List;

/**
 * Created by Gubr on 2016/12/29.
 * 命理 八个主功能的 适合器
 */

public class ClassificationAdapter extends BaseRecyclerAdapter<CalculationGridViewBean> {
    /**
     * 构造器
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public ClassificationAdapter(Context context, List<CalculationGridViewBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, int position, CalculationGridViewBean bean) {
        holder.setText(R.id.textView, bean.getName())
                .setImage(R.id.imageView,bean.iconRes);
    }





}
