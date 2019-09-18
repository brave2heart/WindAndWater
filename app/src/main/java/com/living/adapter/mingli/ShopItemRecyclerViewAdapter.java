package com.living.adapter.mingli;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by weihao on 2018/1/18.
 */

public class ShopItemRecyclerViewAdapter extends RecyclerView.Adapter<ShopItemRecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private List<ShopHomeBean.StoreInfosBean.StoresBean> stores = new ArrayList<>();

    public ShopItemRecyclerViewAdapter(List<ShopHomeBean.StoreInfosBean.StoresBean> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mShop_ItemView;
        private ImageView mShop_iamge;
        private TextView mShop_title;
        private TextView mShop_content;
        private TextView mShop_newprice;
        private TextView mShop_oldprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mShop_ItemView = itemView;
            mShop_iamge = itemView.findViewById(R.id.shop_item_image);
            mShop_title = itemView.findViewById(R.id.shop_item_title);
            mShop_content = itemView.findViewById(R.id.shop_item_content);
            mShop_newprice = itemView.findViewById(R.id.shop_item_newprice);
            mShop_oldprice = itemView.findViewById(R.id.shop_item_oldprice);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mingli_item_shop, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShopHomeBean.StoreInfosBean.StoresBean storesBean = stores.get(position);
        Glide.with(context).load(storesBean.getFacePic()).into(holder.mShop_iamge);
        holder.mShop_title.setText(storesBean.getName());
        holder.mShop_content.setText(storesBean.getYinyu());
        holder.mShop_newprice.setText("￥" + storesBean.getPrice());

        holder.mShop_ItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String merchandiseId = storesBean.getMerchandiseId();//商品id
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", storesBean.getRedirectUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }


}
