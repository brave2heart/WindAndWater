package com.tongcheng.soothsay.ui.fragment.classroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.bazi.ClassRoomGridViewBeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weihao on 2017/12/9.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {


    private List<ClassRoomGridViewBeans> mGridViewBeans = new ArrayList<>();

    public EntryAdapter(List<ClassRoomGridViewBeans> gridViewBeans) {
        mGridViewBeans = gridViewBeans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculation_classification, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassRoomGridViewBeans classRoomGridViewBeans = mGridViewBeans.get(position);
        holder.mImageView.setImageResource(classRoomGridViewBeans.getImage());
        holder.mTextView.setText(classRoomGridViewBeans.getImageName());
    }
    @Override
    public int getItemCount() {
        return mGridViewBeans.size();
    }


}
