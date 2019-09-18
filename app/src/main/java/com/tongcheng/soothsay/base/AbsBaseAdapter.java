package com.tongcheng.soothsay.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 把适配器进行封装，体现一种面向对象的思想,要弄懂弄透
 *
 * @param <T> 每次重写适配器都要重写四个方法，而且三个方法是没用的，都是重复的动作，所以可以把这个重复的动作进行封装,把三个没用的方法封装在一起，
 *            然后下车写适配器的时候就直接继承这个方法然后就重写一个方法就可以了
 *            <p/>
 *            所有操作数据源的操作都放到适配器来操作
 *
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Context context;
    private int itemLayoutId;
    private List<T> datas;	//数据源

    public AbsBaseAdapter(Context context,List<T> datas,int itemLayoutId){
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
        this.itemLayoutId = itemLayoutId;
    }


    /**
     * 填充数据
     * @param holder 装有View控件的holder
     * @param t	对应position的数据对象
     */
    public abstract void bindData(ViewHolder holder, T t, int position);


    /**
     * 更新数据源
     */
    public void notifyChangeData(List<T> datas){
        if(datas == null)	return ;

        this.datas = datas;
        notifyDataSetChanged();
    }


    /**
     * 加载更多数据
     */
    public void changeData(List<T> data){
        if(data == null)  return ;

        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    public void insetData(T t){
        if(t == null)	return ;

        datas.add(t);
        notifyDataSetChanged();
    }

    public List<T> getDatas(){
        return datas;
    }


    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? 0 :datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbsBaseAdapter.ViewHolder holder;
        if(convertView == null){
            convertView =mInflater.inflate(itemLayoutId, parent, false);
            holder = new AbsBaseAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (AbsBaseAdapter.ViewHolder) convertView.getTag();
        }
        bindData(holder,datas.get(position),position);
        return convertView;
    }



    public static class ViewHolder{
        /**
         * 这个一般不需要用到  只要直接在view里用  view.getTag(R.id.tag_first)就能拿到
         * @return
         */
        public Object getTag() {
          return getTag(R.id.tag_first);
        }

        public Object getTag(@IdRes int key){
            return itemView.getTag(key);
        }

        public ViewHolder setTag(Object object){
            return setTag(R.id.tag_first, object);
        }

        public ViewHolder setTag(@IdRes int key,Object object) {
            itemView.setTag(key,object);
            return this;
        }

        private SparseArray<View> viewMap;
        private View itemView;

        public ViewHolder(View rootView){
            viewMap = new SparseArray<>();
            this.itemView = rootView;
        }


        /**
         * 根据id获取view
         * 可以试试v2版  不需要再强转
         * @param viewId
         * @return
         */
        public View getView(int viewId){
            View view = viewMap.get(viewId);
            if(view == null){
                view = itemView.findViewById(viewId);
                viewMap.put(viewId, view);
            }
            return view;
        }


        /**
         * 根据id获取view
         * @param viewId
         * @param <S>
         * @return 返回View
         */
        public <S extends View> S getViewV2(int viewId){
            View view = viewMap.get(viewId);
            if(view == null){
                view = itemView.findViewById(viewId);
                viewMap.put(viewId, view);
            }
            return (S) view;
        }

        /**
         * 返回所有的Item
         * @return
         * @param card_view
         */
        public List<View> getAllView(int card_view){
            ArrayList<View> views = new ArrayList<>();
            for (int i = 0; i < viewMap.size(); i++) {
                View view = viewMap.valueAt(i);
                views.add(view);
            }
            return views;
        }

        public View getItemView(){
            return itemView;
        }


        public ViewHolder setText(@IdRes int resId, String str){
            ((TextView)getViewV2(resId)).setText(str);
            return this;
        }

        public ViewHolder setImage(@IdRes int resId,int imageId){
            ((ImageView)getViewV2(resId)).setImageResource(imageId);
            return this;
        }



    }

}
