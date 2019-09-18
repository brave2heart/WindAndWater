package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.classroom.GoodsListBean;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.widget.BigRoundImageView;

import java.util.List;

/**
 * Created by Steven on 17/1/3.
 */

public class GoodsListAdapter extends AbsBaseAdapter<GoodsListBean.StoresBean> {

    public GoodsListAdapter(Context context, List<GoodsListBean.StoresBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, GoodsListBean.StoresBean bean, int position) {
        BigRoundImageView img = holder.getViewV2(R.id.iv_classroom_shop);
        TextView tvTitle = holder.getViewV2(R.id.tv_classroom_shop_title);
        TextView tvContent = holder.getViewV2(R.id.tv_classroom_shop_content);

        String url = bean.getFacePic();
        String title = bean.getName();
        String content = bean.getYinyu();

        tvTitle.setText(title+"");
        tvContent.setText(content+"");
        ImageHelper.getInstance().display(url,img);
    }
}
