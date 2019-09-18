package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.widget.ImageView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbTypeBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.NewsBean;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.utils.DateUtil;

import java.util.List;


/**
 * 资讯适配器
 */

public class NewsListAdapter extends AbTypeBaseAdapter<NewsBean> {
    public NewsListAdapter(Context context, List<NewsBean> datas, int... itemLayoutId) {
        super(context, datas, itemLayoutId);
    }



    @Override
    public int getItemType(int position) {
        return getDatas().get(position).getIntShowtype();
    }

    @Override
    public void bindData(ViewHolder holder, int type, NewsBean bean) {
        switch (type) {
            case 1:
                ImageHelper.getInstance().display(bean.getCover(),(ImageView) holder.getViewV2(R.id.img_news_cover));
                holder.setText(R.id.tv_news_title, bean.getTitle())
                        .setText(R.id.tv_news_date, DateUtil.formatTime(Long.valueOf(bean.getDate()), "yyyy-MM-dd"))
                        .setTag(bean);
                break;
            case 0:
                holder.setText(R.id.tv_news_title, bean.getTitle())
                        .setText(R.id.tv_news_date, DateUtil.formatTime(Long.valueOf(bean.getDate()), "yyyy-MM-dd"))
                        .setTag(bean);
                break;
        }
    }
}
