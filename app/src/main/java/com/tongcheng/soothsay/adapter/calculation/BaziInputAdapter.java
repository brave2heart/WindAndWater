package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.dao.BaziUserBean;
import com.tongcheng.soothsay.widget.RoundImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/11/28.
 * 八字输入界面 listview的适配器
 */

public class BaziInputAdapter extends BaseAdapter {

    private final Context mContext;
    private final int mItemLayoutId;
    private List<BaziUserBean> datas;

    public BaziInputAdapter(Context context, List<BaziUserBean> datas, int itemLayoutId) {
        mContext = context;
        this.datas = datas;
        mItemLayoutId = itemLayoutId;
    }



    /**
     * 填充数据
     * @param holder 装有View控件的holder
     * @param baziUserBean	对应position的数据对象
     */
    public  void bindData(ViewHolder holder, BaziUserBean baziUserBean, int position){
        TextView tvName = (TextView) holder.getView(R.id.tv_ziwei_input_name);
        TextView tvSex = (TextView) holder.getView(R.id.tv_ziwei_input_sex);
        TextView tvBirthday = (TextView) holder.getView(R.id.tv_ziwei_input_birthday);
        RoundImageView img = (RoundImageView) holder.getView(R.id.img_ziwei_input_icon);

        String name = baziUserBean.getName();
        String sex = baziUserBean.getSex();
        String day = baziUserBean.getDate();
        String temp [] = day.split("\\.");
        int icon = baziUserBean.getIcon();

        img.setImageResource(icon);
        tvName.setText("姓名: " + name);
        tvSex.setText("性别: " + sex);
        tvBirthday.setText("生辰: " + temp[0] + "年" + temp[1] + "月" + temp[2] + "日" + temp[3] + "时");
    }


    /**
     * 更新数据源
     */
    public void notifyChangeData(List<BaziUserBean> datas){
        if(datas == null)	return ;

        this.datas = datas;
        notifyDataSetChanged();
    }


    /**
     * 加载更多数据
     */
    public void changeData(List<BaziUserBean> data){
        if(data == null)  return ;

        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    public void insetData(BaziUserBean t){
        if(t == null)	return ;

        datas.add(t);
        notifyDataSetChanged();
    }

    public List<BaziUserBean> getDatas(){
        return datas;
    }


    public void setDatas(List<BaziUserBean> datas) {
        this.datas = datas;
    }

    public Context getContext(){
        return mContext;
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
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        bindData(holder,datas.get(position),position);
        return convertView;
    }



    public class ViewHolder{

        private ArrayMap<Integer, View> viewMap;
        private View itemView;

        public ViewHolder(View rootView){
            viewMap = new ArrayMap<Integer, View>();
            this.itemView = rootView;
        }

        /*
         * 从集合中获取view，没有则重新findId
         */
        public View getView(int viewId){
            View view = viewMap.get(viewId);
            if(view == null){
                view = itemView.findViewById(viewId);
                viewMap.put(viewId, view);
            }
            return view;
        }

        //返回所有的Item
        public List<View> getAllView(){
            ArrayList<View> views = new ArrayList<View>();
            Iterator<Integer> it = viewMap.keySet().iterator();
            while(it.hasNext()){
                int id = it.next();
                views.add(viewMap.get(id));
            }
            return views;
        }

        public View getItemView(){
            return itemView;
        }


    }
}
