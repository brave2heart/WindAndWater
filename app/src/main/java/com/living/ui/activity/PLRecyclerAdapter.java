package com.living.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.living.bean.xuetang.PLData;
import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2018/3/5.
 */

public class PLRecyclerAdapter extends RecyclerView.Adapter<PLRecyclerAdapter.ViewHolder> {


    private List<PLData> plDatas = new ArrayList<>();

    public PLRecyclerAdapter(List<PLData> plDatas) {
        this.plDatas = plDatas;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mName;
        private final TextView mLocation;
        private final TextView mSendTime;
        private final TextView mComment;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.life_details_use_icon);
            mName = itemView.findViewById(R.id.life_details_use_name);
            mLocation = itemView.findViewById(R.id.life_details_location);
            mSendTime = itemView.findViewById(R.id.life_details_send_time);
            mComment = itemView.findViewById(R.id.life_details_comment);


        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.life_details_pl, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PLData plData = plDatas.get(position);
        holder.mImage.setImageResource(plData.getUserIcon());
        holder.mName.setText(plData.getUserName());
        holder.mLocation.setText(plData.getLocation());
        holder.mSendTime.setText(plData.getCommentTime());
        holder.mComment.setText(plData.getComment());
    }

    @Override
    public int getItemCount() {
        return plDatas.size();
    }

}
