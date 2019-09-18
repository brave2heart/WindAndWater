package com.tongcheng.soothsay.base;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Steven on 16/10/10.
 */

/**
 * 抽象recyclerView 适配器基类
 *
 * @param <T>
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {
    private Context context;
    private int itemLayoutId;
    private List<T> list;
    private OnReItemOnClickListener itemListener;
    private OnReItemOnLongClickListener longListener;

    public BaseRecyclerAdapter(Context context, List<T> list, int itemLayoutId) {
        this.context = context;
        this.list = list;
        this.itemLayoutId = itemLayoutId;
    }

    /**
     * 将数据与view视图绑定
     *
     * @param holder
     * @param position
     * @param t
     */
    public abstract void bindData(ViewHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.ViewHolder arg0, int arg1) {
        arg0.getItemView().setTag(arg1);
        bindData(arg0, arg1, list.get(arg1));
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(context).inflate(itemLayoutId, null, false);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longListener != null) {
                    longListener.onItemLongClick(v, (Integer) v.getTag());
                }
                return false;
            }
        });
        return new ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ArrayMap<Integer, View> viewMap;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            viewMap = new ArrayMap<Integer, View>();
        }

        public View getView(int viewId) {
            View view = viewMap.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                viewMap.put(viewId, view);
            }

            return view;
        }

        /**
         * 查找view  用这个方法不需要强转  内部已经帮转好了。
         *
         * @param viewId
         * @param <T>    view的子类
         * @return 查找到的view
         */
        public <T extends View> T getViewV2(int viewId) {
            View view = viewMap.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                viewMap.put(viewId, view);
            }
            return (T) view;
        }


        /**
         * 设置文字
         *
         * @param viewId 要设置文字的 ImageView ID
         * @param str    文字的资源ID
         * @return
         */
        public ViewHolder setText(int viewId, String str) {
            ((TextView) getViewV2(viewId)).setText(str);
            return this;
        }

        /**
         * 设置图片
         *
         * @param viewId 要设置图片的 ImageView ID
         * @param resId  图片的资源ID
         * @return
         */
        public ViewHolder setImage(int viewId, int resId) {
            ((ImageView) getViewV2(viewId)).setImageResource(resId);
            return this;
        }

        /**
         * 设置点击事件
         *
         * @param viewId 要设置的控件的id
         * @param l      点击事件
         * @return
         */
        public ViewHolder setOnClick(int viewId, View.OnClickListener l) {
            getView(viewId).setOnClickListener(l);
            return this;
        }

        public View getItemView() {
            return itemView;
        }

    }


    public interface OnReItemOnClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnReItemOnLongClickListener {
        void onItemLongClick(View v, int position);
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setOnReItemOnClickListener(OnReItemOnClickListener listener) {
        this.itemListener = listener;
    }


    public void setOnReItemOnLongClickListener(OnReItemOnLongClickListener longListener) {
        this.longListener = longListener;
    }

    public Context getContext() {
        return context;
    }

    public void addData(int position, T t) {
        list.add(position, t);
        notifyItemInserted(position);
    }

    public void addData(T t) {
        list.add(t);
        notifyItemInserted(list.size() - 1);
    }


    public void removeData(ViewHolder holder, int position) {
        list.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
    }
}
