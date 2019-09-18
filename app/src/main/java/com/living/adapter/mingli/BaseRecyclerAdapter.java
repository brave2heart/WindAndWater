package com.living.adapter.mingli;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weihao on 2018/3/24.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

    /*
    * 初始化控件
    * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            setLayoutID(itemView);
        }
    }
    /*
    * 初始化控件
    * */
    protected abstract void setLayoutID(View itemView);

    /*
     * 注入布局
     * */
    @Override
    public BaseRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(setLayout(), parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /*
    * 设置Item布局
    * */
    public abstract int setLayout();

    /*
    * 数据赋值
    * */
    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.ViewHolder holder, int position) {
        setData(holder,position);
    }
    /*
    * 数据赋值
    * */
    protected abstract void setData(ViewHolder holder, int position);

    /*
    * Item条目总数
    * */
    @Override
    public int getItemCount() {
        return setItemCount();
    }
    /*
    * Item条目总数
    * */
    protected abstract int setItemCount();
}
