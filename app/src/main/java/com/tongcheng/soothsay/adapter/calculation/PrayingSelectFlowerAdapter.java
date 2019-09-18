package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.FlowerBean;

import java.util.List;

/**
 * @description:
 * @author: linjinyan
 * @date: 2016-11-08
 * @time: 10:09
 * 供花 适配器
 */
public class PrayingSelectFlowerAdapter extends AbsBaseAdapter<FlowerBean>  {

    private onPopFlowerListener popFlowerListener;

    public PrayingSelectFlowerAdapter(Context context, List<FlowerBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, final FlowerBean flowerBean, int position) {
        ImageView ivFlowerFirst = (ImageView) holder.getView(R.id.first);
        TextView tvFlowerFirstName = (TextView) holder.getView(R.id.first_name);
        TextView tvFlowerFirstPrice = (TextView) holder.getView(R.id.first_price);
        ImageView ivFlowerSecond = (ImageView) holder.getView(R.id.second);
        TextView tvFlowerSecondName = (TextView) holder.getView(R.id.second_name);
        TextView tvFlowerSecondPrice = (TextView) holder.getView(R.id.second_price);
        ImageView ivFlowerThird = (ImageView) holder.getView(R.id.third);
        TextView tvFlowerThirdName = (TextView) holder.getView(R.id.third_name);
        TextView tvFlowerThirdPrice = (TextView) holder.getView(R.id.third_price);
        //获取数据
        if (flowerBean.getFirst_name() != null) {
            ivFlowerFirst.setImageResource(flowerBean.getFirst_img());
            tvFlowerFirstName.setText(flowerBean.getFirst_name());
            tvFlowerFirstPrice.setText(flowerBean.getFirst_price());
        }
        if (flowerBean.getSecond_name() != null) {
            ivFlowerSecond.setImageResource(flowerBean.getSecond_img());
            tvFlowerSecondName.setText(flowerBean.getSecond_name());
            tvFlowerSecondPrice.setText(flowerBean.getSecond_price());
        }
        if (flowerBean.getThird_name() != null) {
            ivFlowerThird.setImageResource(flowerBean.getThird_img());
            tvFlowerThirdName.setText(flowerBean.getThird_name());
            tvFlowerThirdPrice.setText(flowerBean.getThird_price());
        }
        //点击事件
        ivFlowerFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popFlowerListener != null)
                    popFlowerListener.onPopFlowerClick(flowerBean.getFirst_img());
            }
        });
        ivFlowerSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popFlowerListener != null)
                    popFlowerListener.onPopFlowerClick(flowerBean.getSecond_img());
            }
        });
        ivFlowerThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popFlowerListener != null)
                    popFlowerListener.onPopFlowerClick(flowerBean.getThird_img()        );
            }
        });
    }


    public interface onPopFlowerListener {
        void onPopFlowerClick(int i);
    }

    public void setPopFlowerListener(onPopFlowerListener popFlowerListener) {
        this.popFlowerListener = popFlowerListener;
    }
}
