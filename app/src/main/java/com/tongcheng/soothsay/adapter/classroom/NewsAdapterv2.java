package com.tongcheng.soothsay.adapter.classroom;

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
import com.tongcheng.soothsay.bean.calculation.BaziPaipanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubr on 2016/12/13.
 */

public class NewsAdapterv2 extends BaseAdapter {


    private final Context mContext;
    private final List<BaziPaipanBean.LiuNianBean> mDatas;
    private final int mHeadItemlayout;
    private final int mItemslayout;

    public NewsAdapterv2(Context context, List<BaziPaipanBean.LiuNianBean> datas, int headItemlayout, int itemslayout) {

        mContext = context;
        mDatas = datas;
        mHeadItemlayout = headItemlayout;
        mItemslayout = itemslayout;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case 1:
                return getheadItem(mDatas.get(position), convertView, parent);
            case 0:
            default:
                return getNormalItem(mDatas.get(position), convertView, parent);
        }
    }

    private View getNormalItem(BaziPaipanBean.LiuNianBean bean, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView= LayoutInflater.from(mContext).inflate(mItemslayout,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.setText(R.id.tv_liunian_year,(bean.getYear()+"年"))
                .setText(R.id.tv_liunian_ganzhi,bean.getGanZhi())
                .setText(R.id.tv_liunian_shensha,bean.getShenSha());
        return convertView;
    }

    private View getheadItem(BaziPaipanBean.LiuNianBean bean, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView= LayoutInflater.from(mContext).inflate(mHeadItemlayout,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.setText(R.id.tv_liunian_tenyear,bean.getTenYearStr())
                .setText(R.id.tv_liunian_year,(bean.getYear()+"年"))
                .setText(R.id.tv_liunian_ganzhi,bean.getGanZhi())
                .setText(R.id.tv_liunian_shensha,bean.getShenSha());
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 10 == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    public static class ViewHolder {
        private SparseArray<View> viewMap;
        private View itemView;

        public ViewHolder(View rootView) {
            viewMap = new SparseArray<>();
            this.itemView = rootView;
        }


        public <S extends View> S getView(int viewId) {
            View view = viewMap.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                viewMap.put(viewId, view);
            }
            return (S) view;
        }

        /**
         * 返回所有的Item
         *
         * @return
         */
        public List<View> getAllView() {
            ArrayList<View> views = new ArrayList<View>();
            for (int i = 0; i < viewMap.size(); i++) {
                View view = viewMap.valueAt(i);
                views.add(view);
            }
            return views;
        }

        public View getItemView() {
            return itemView;
        }


        public ViewHolder setText(@IdRes int resId, String str) {
            ((TextView) getView(resId)).setText(str);
            return this;
        }

        public ViewHolder setImage(@IdRes int resId, int imageId) {
            ((ImageView) getView(resId)).setImageResource(imageId);
            return this;
        }


    }
}
