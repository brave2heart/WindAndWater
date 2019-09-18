package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.NameTestBean;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/17.
 */

public class NewTestNameRecycleViewAdapter extends RecyclerView.Adapter<NewTestNameRecycleViewAdapter.MyViewHolder> {
    private List<NameTestBean.WuGeBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public NewTestNameRecycleViewAdapter(Context context, List<NameTestBean.WuGeBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_testname_recycleview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvWg.setText(mDatas.get(position).getType());
        holder.tvwx.setText(mDatas.get(position).getWuXing());
        holder.tvjx.setText(mDatas.get(position).getJiXiong());
        holder.tvfz.setText(mDatas.get(position).getScore());
        holder.tvjiex.setText(mDatas.get(position).getJieXi());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvWg;
        TextView tvwx;
        TextView tvjx;
        TextView tvfz;
        TextView tvjiex;

        public MyViewHolder(View view) {
            super(view);
            tvWg = (TextView) view.findViewById(R.id.tv_testname_item_wg);
            tvwx = (TextView) view.findViewById(R.id.tv_testname_item_wx);
            tvjx = (TextView) view.findViewById(R.id.tv_testname_item_jx);
            tvfz = (TextView) view.findViewById(R.id.tv_testname_item_fz);
            tvjiex = (TextView) view.findViewById(R.id.tv_testname_item_jiex);
        }

    }
}
