package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.CalculationGridViewBean;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.helper.ImageHelper;

import java.util.List;

/**
 * Created by Gubr on 2016/12/29.
 * 热门测算  适合器
 */

public class HotTestAdapter extends BaseRecyclerAdapter<CalculationGridViewBean> {
    /**
     * 构造器
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public HotTestAdapter(Context context, List<CalculationGridViewBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, int position, CalculationGridViewBean bean) {
        holder.setImage(R.id.imageView,bean.iconRes);
    }





}
