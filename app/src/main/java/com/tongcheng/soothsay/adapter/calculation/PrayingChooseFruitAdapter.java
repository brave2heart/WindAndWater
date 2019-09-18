package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.FruitBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import java.util.List;

/**
 * @description:
 * @author: linjinyan
 * @date: 2016-11-08
 * @time: 10:09
 *
 * 供果 适配器
 */
public class PrayingChooseFruitAdapter extends AbsBaseAdapter<FruitBean> {

    private onPopFruitListener popFruitListener;

    public PrayingChooseFruitAdapter(Context context, List<FruitBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, final FruitBean fruitBean, int position) {
        ImageView ivFruitFirst = (ImageView) holder.getView(R.id.first);
        TextView tvFruitFirstName = (TextView) holder.getView(R.id.first_name);
        TextView tvFruitFirstPrice = (TextView) holder.getView(R.id.first_price);
        ImageView ivFruitSecond = (ImageView) holder.getView(R.id.second);
        TextView tvFruitSecondName = (TextView) holder.getView(R.id.second_name);
        TextView tvFruitSecondPrice = (TextView) holder.getView(R.id.second_price);
        ImageView ivFruitThird = (ImageView) holder.getView(R.id.third);
        TextView tvFruitThirdName = (TextView) holder.getView(R.id.third_name);
        TextView tvFruitThirdPrice = (TextView) holder.getView(R.id.third_price);
        //获取数据
        if (fruitBean.getFirst_name() != null) {
            ivFruitFirst.setImageResource(fruitBean.getFirst_img());
            tvFruitFirstName.setText(fruitBean.getFirst_name());
            tvFruitFirstPrice.setText(fruitBean.getFirst_price());
        }
        if (fruitBean.getSecond_name() != null) {
            ivFruitSecond.setImageResource(fruitBean.getSecond_img());
            tvFruitSecondName.setText(fruitBean.getSecond_name());
            tvFruitSecondPrice.setText(fruitBean.getSecond_price());
        }
        if (fruitBean.getThird_name() != null) {
            ivFruitThird.setImageResource(fruitBean.getThird_img());
            tvFruitThirdName.setText(fruitBean.getThird_name());
            tvFruitThirdPrice.setText(fruitBean.getThird_price());
        }
        tvFruitFirstName.setText(fruitBean.getFirst_name());
        tvFruitFirstPrice.setText(fruitBean.getFirst_price());
        tvFruitSecondName.setText(fruitBean.getSecond_name());
        tvFruitSecondPrice.setText(fruitBean.getSecond_price());
        tvFruitThirdName.setText(fruitBean.getThird_name());
        tvFruitThirdPrice.setText(fruitBean.getThird_price());
        //点击事件
        ivFruitFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popFruitListener.onPopFruitClick(fruitBean.getFirst_img());
            }
        });
        ivFruitSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFruitListener.onPopFruitClick(fruitBean.getSecond_img());
            }
        });
        ivFruitThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFruitListener.onPopFruitClick(fruitBean.getThird_img());
            }
        });
    }

    public interface onPopFruitListener {
        void onPopFruitClick(int i);
    }

    public void setPopFruitListener(onPopFruitListener popListener) {
        this.popFruitListener = popListener;
    }
}
