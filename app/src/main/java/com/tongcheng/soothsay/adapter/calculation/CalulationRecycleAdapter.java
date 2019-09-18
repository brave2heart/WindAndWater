package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/4.
 * 命里推算测算二级菜单适配器
 */

public class CalulationRecycleAdapter extends BaseRecyclerAdapter<CliffordBean> {
    private Activity mContext;

    /**
     * }
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public CalulationRecycleAdapter(Context context, List<CliffordBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.mContext = (Activity) context;
    }

    @Override
    public void bindData(ViewHolder holder, int position, CliffordBean cliffordBean) {
//        ImageView iv = (ImageView) holder.getView(R.id.iv_url);
//        iv.setImageResource(cliffordBean.getId());
        holder.setImage(R.id.iv_url, cliffordBean.getId());
        ((TextView) holder.getView(R.id.tv_name)).setText(cliffordBean.getName());
        ((TextView) holder.getView(R.id.tv_content)).setText(cliffordBean.getContent());
    }
}