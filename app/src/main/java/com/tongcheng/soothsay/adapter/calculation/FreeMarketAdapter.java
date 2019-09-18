package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.FreeGoodsBean;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.WindowUtil;

import java.util.List;

/**
 * Created by Steven on 16/12/1.
 */
public class FreeMarketAdapter extends AbsBaseAdapter<FreeGoodsBean> {

    private int width;
    private int height;

    public FreeMarketAdapter(Context context, List<FreeGoodsBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        width = WindowUtil.getScreenWidth(context) / 3;
        height = width;
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder,FreeGoodsBean bean, int position) {
        ImageView imgBg = (ImageView) holder.getView(R.id.img_free_market_bg);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgBg.getLayoutParams();
        params.width = width;
        params.height = height;

        ImageView img = (ImageView) holder.getView(R.id.img_free_market);
        ImageView imgJian = (ImageView) holder.getView(R.id.img_free_market_jian);
        ImageView imgJia = (ImageView) holder.getView(R.id.img_free_market_jia);
        TextView tvName = (TextView) holder.getView(R.id.tv_free_market_name);
        TextView tvMoney = (TextView) holder.getView(R.id.tv_free_market_money);
        final TextView tvCount = (TextView) holder.getView(R.id.tv_free_market_num);

        ImageHelper.getInstance().display(bean.getFacePic(),img);
        tvName.setText(bean.getName());
        tvMoney.setText("¥" + bean.getPrice());

        imgJian.setTag(position);
        imgJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = (int) v.getTag();
                int count = getDatas().get(index).getPayCount();
                if(count == 0){
                    return;
                }
                getDatas().get(index).setPayCount(--count);
                tvCount.setText(count+"");
            }
        });

        imgJia.setTag(position);
        imgJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = (int) v.getTag();
                int count = getDatas().get(index).getPayCount();
                getDatas().get(index).setPayCount(++count);
                tvCount.setText(count+"");
            }
        });

    }


}
