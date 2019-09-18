package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.classroom.ShopHomeHeadBean;
import com.tongcheng.soothsay.helper.ImageHelper;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/30.
 * 商城首页八个头部数据的适配器
 */

public class ShopHomeHeadRecyleViewAdapter extends AbsBaseRecycleAdapter<ShopHomeHeadBean> {
    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public ShopHomeHeadRecyleViewAdapter(Context context, List<ShopHomeHeadBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, ShopHomeHeadBean shopHomeHeadBean) {
        ImageView iv = holder.getView(R.id.iv_shop_head);
        iv.setImageResource(shopHomeHeadBean.getIv());
        TextView tvTitle = holder.getView(R.id.tv_shop_head);
        tvTitle.setText(shopHomeHeadBean.getTitle());
    }
}
