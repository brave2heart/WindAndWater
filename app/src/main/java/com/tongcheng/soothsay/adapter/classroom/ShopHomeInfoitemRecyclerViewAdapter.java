package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.helper.ImageHelper;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/30.
 * 商城首页里面的商品条目适配器
 */

public class ShopHomeInfoitemRecyclerViewAdapter extends AbsBaseRecycleAdapter<ShopHomeBean.StoreInfosBean.StoresBean> {
    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public ShopHomeInfoitemRecyclerViewAdapter(Context context, List<ShopHomeBean.StoreInfosBean.StoresBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, ShopHomeBean.StoreInfosBean.StoresBean storesBean) {
        ImageView iv = holder.getView(R.id.iv_classroom_shop);
        ImageHelper.getInstance().display(storesBean.getFacePic(), iv);
        ImageHelper.getInstance().display(storesBean.getFacePic(), R.drawable.placeholder_sc_340, iv);
        TextView tvTitle = holder.getView(R.id.tv_classroom_shop_title);
        tvTitle.setText(storesBean.getName());
        TextView tvContent = holder.getView(R.id.tv_classroom_shop_content);
        tvContent.setText(storesBean.getYinyu());
    }
}
