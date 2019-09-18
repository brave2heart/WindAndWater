package com.living.adapter.mingli;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;

/**
 * Created by weihao on 2018/3/24.
 */

public class MingLiRecyclerAdapter extends BaseRecyclerAdapter {

    @Override
    protected void setLayoutID(View itemView) {
        ImageView image = itemView.findViewById(R.id.headitem_image);
        TextView text = itemView.findViewById(R.id.headitem_name);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_mingli_item_headitem;
    }

    @Override
    protected void setData(ViewHolder holder, int position) {

    }

    @Override
    protected int setItemCount() {
        return 0;
    }

}
