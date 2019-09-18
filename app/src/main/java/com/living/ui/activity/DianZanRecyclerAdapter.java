package com.living.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.living.bean.xuetang.LikeImage;
import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/3/3.
 */

public class DianZanRecyclerAdapter extends RecyclerView.Adapter<DianZanRecyclerAdapter.ViewHolder> {
    private List<LikeImage> LikeImages = new ArrayList<>();

    public DianZanRecyclerAdapter(List<LikeImage> LikeImages) {
        this.LikeImages = LikeImages;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.dianzan_image);
        }
    }

    @Override
    public DianZanRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.life_dianzan_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DianZanRecyclerAdapter.ViewHolder holder, int position) {
        LikeImage likeImage = LikeImages.get(position);
        holder.mImageView.setImageResource(likeImage.getImage());
    }

    @Override
    public int getItemCount() {
        return 7;
    }


}
