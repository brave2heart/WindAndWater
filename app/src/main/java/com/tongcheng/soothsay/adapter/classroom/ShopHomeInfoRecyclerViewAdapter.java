package com.tongcheng.soothsay.adapter.classroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.ui.activity.classroom.GoodsDetailsActivity;
import com.tongcheng.soothsay.ui.activity.classroom.ShopClassifyInfosActivity;
import com.tongcheng.soothsay.utils.WindowUtil;
import com.tongcheng.soothsay.widget.RecyclerSpace;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/30.
 * 商品信息适配器
 */

public class ShopHomeInfoRecyclerViewAdapter extends AbsBaseRecycleAdapter<ShopHomeBean.StoreInfosBean> {
    private Context mContext;
    private Activity mActivity;
    ShopHomeInfoitemRecyclerViewAdapter mAdapter;


    public ShopHomeInfoRecyclerViewAdapter(Activity activity, Context context, List<ShopHomeBean.StoreInfosBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.mActivity = activity;
        this.mContext = context;
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, final ShopHomeBean.StoreInfosBean storeInfosBean) {
        TextView tvTitle = holder.getView(R.id.tv_classroom_shop_item);
//        TextView tvOldPrice = holder.getView(R.id.tv_classroom_shop_oldprice);
//        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvTitle.setText(storeInfosBean.getSortName());
//        tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        RelativeLayout rlTitle = holder.getView(R.id.rl_shop_item_title);
        rlTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ShopClassifyInfosActivity.class);
                intent.putExtra("sortId", storeInfosBean.getSortId());
                intent.putExtra("sortName", storeInfosBean.getSortName());
                mActivity.startActivity(intent);
            }
        });
        final List<ShopHomeBean.StoreInfosBean.StoresBean> stores = storeInfosBean.getStores();
        mAdapter = new ShopHomeInfoitemRecyclerViewAdapter(mContext, stores,
                R.layout.item_shop_content_item_recyclerview);
        RecyclerView rv = holder.getView(R.id.rv_item_shop_content);
        rv.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv.setAdapter(mAdapter);

        rv.addItemDecoration(new RecyclerSpace(0,Color.WHITE));
        //item点击监听
        mAdapter.setOnItemClickListener(new AbsBaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int posotion) {
                String merchandiseId = stores.get(posotion).getMerchandiseId();//商品id
                Intent intent = new Intent(mActivity, GoodsDetailsActivity.class);
                intent.putExtra("goodsid", merchandiseId);
                intent.putExtra("redirectUrl", stores.get(posotion).getRedirectUrl());
                mActivity.startActivity(intent);
            }
        });
    }

    /**
     * 给recycleview设置间隔
     */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        /**
         * @param space 传入的值，其单位视为dp
         */
        public SpaceItemDecoration(int space) {
            this.mSpace = WindowUtil.px2dip(mActivity, space);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int itemCount = mAdapter.getItemCount();
            int pos = parent.getChildAdapterPosition(view);
            outRect.left = 0;
            outRect.top = 0;
            outRect.bottom = 0;


            if (pos != (itemCount - 1) && pos != 1 & pos != 3) {
                outRect.right = mSpace;
            } else {
                outRect.right = 0;
            }
        }
    }
}
