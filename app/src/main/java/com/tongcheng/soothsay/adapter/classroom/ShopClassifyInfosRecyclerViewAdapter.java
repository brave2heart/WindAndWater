package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.classroom.ShopClassifyInfosBean;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.ui.activity.classroom.ShopClassifyInfosActivity;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/30.
 * 商城列表适配器
 */

public class ShopClassifyInfosRecyclerViewAdapter extends AbsBaseAdapter<ShopClassifyInfosBean.StoresBean> {


    public ShopClassifyInfosRecyclerViewAdapter(Context context, List<ShopClassifyInfosBean.StoresBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, final ShopClassifyInfosBean.StoresBean storesBean, int position) {
        ImageView iv = (ImageView) holder.getView(R.id.iv_classroom_shop);
//        ImageHelper.getInstance().display(storesBean.getFacePic(), iv);
        ImageHelper.getInstance().display(storesBean.getFacePic(), R.drawable.placeholder_sc_340, iv);
        TextView tvTitle = (TextView) holder.getView(R.id.tv_classroom_shop_title);
        tvTitle.setText(storesBean.getName());
        TextView tvContent = (TextView) holder.getView(R.id.tv_classroom_shop_content);
        tvContent.setText(storesBean.getYinyu());
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String merchandiseId = storesBean.getMerchandiseId();//商品id
                Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", storesBean.getRedirectUrl());
                getContext().startActivity(intent);
            }
        });
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String merchandiseId = storesBean.getMerchandiseId();//商品id
                Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", storesBean.getRedirectUrl());
                getContext().startActivity(intent);
            }
        });
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String merchandiseId = storesBean.getMerchandiseId();//商品id
                Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", storesBean.getRedirectUrl());
                getContext().startActivity(intent);
            }
        });

    }

//    @Override
//    public void bindData(AbsBaseRecycleViewHolder holder, ShopClassifyInfosBean.StoresBean storesBean) {
//        ImageView iv = holder.getView(R.id.iv_classroom_shop);
////        ImageHelper.getInstance().display(storesBean.getFacePic(), iv);
//        ImageHelper.getInstance().display(storesBean.getFacePic(), R.drawable.placeholder_sc_340, iv);
//        TextView tvTitle = holder.getView(R.id.tv_classroom_shop_title);
//        tvTitle.setText(storesBean.getName());
//        TextView tvContent = holder.getView(R.id.tv_classroom_shop_content);
//        tvContent.setText(storesBean.getYinyu());
//    }


}
