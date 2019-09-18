package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.DadeBean;

import java.util.List;


/**
 * Created by 宋家任 on 2016/11/9.
 * 大德符运listview适配器
 */

public class DadeListViewAdapter extends AbsBaseAdapter<DadeBean> {

    private OnQingFuClickListener onQingFuClick;
    private List<DadeBean> datas;

    public DadeListViewAdapter(Context context, List<DadeBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        this.datas = datas;
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, final DadeBean dadeBean, final int position) {
//        TextView tvLeft = (TextView) holder.getView(R.id.tv_dadefuyun_item_box_name_left);
//        tvLeft.setText(dadeBean.getTvLeft());
//
//        TextView tvCentre = (TextView) holder.getView(R.id.tv_dadefuyun_item_box_name_centre);
//        tvCentre.setText(dadeBean.getTvCenter());
//        TextView tvRight = (TextView) holder.getView(R.id.tv_dadefuyun_item_box_name_right);
//        tvRight.setText(dadeBean.getTvRight());

//        左中右请符
//        TextView qfLeft = (TextView) holder.getView(R.id.tv_dadefuyun_item_qing_left_text);
//        TextView qfCentre = (TextView) holder.getView(R.id.tv_dadefuyun_item_qing_centre_text);
//        TextView qfRight = (TextView) holder.getView(R.id.tv_dadefuyun_item_qing_right_text);
        //中右盒子
//        ImageView ivCenter = (ImageView) holder.getView(R.id.iv_dadefuyun_item_box_centre);
//        ImageView ivRight = (ImageView) holder.getView(R.id.iv_dadefuyun_item_box_right);
//        if (!"".equals(dadeBean.getTvCenter())) {
//            qfCentre.setVisibility(InputView.VISIBLE);
//            ivCenter.setVisibility(InputView.VISIBLE);
//        } else {
//            qfCentre.setVisibility(InputView.INVISIBLE);
//            ivCenter.setVisibility(InputView.INVISIBLE);
//        }
//        if (!"".equals(dadeBean.getTvRight())) {
//            qfRight.setVisibility(InputView.VISIBLE);
//            ivRight.setVisibility(InputView.VISIBLE);
//        } else {
//            qfRight.setVisibility(InputView.INVISIBLE);
//            ivRight.setVisibility(InputView.INVISIBLE);
//        }
//
//        qfLeft.setOnClickListener(new InputView.OnClickListener() {
//            @Override
//            public void onClick(InputView view) {
//                onQingFuClick.onLeftClick(view, dadeBean.getTvLeft(),
//                        dadeBean.getTvDescLeft(), dadeBean.getTvGxLeft());
//            }
//        });
//        qfCentre.setOnClickListener(new InputView.OnClickListener() {
//            @Override
//            public void onClick(InputView view) {
//                onQingFuClick.onCenterClick(view, dadeBean.getTvCenter(),
//                        dadeBean.getTvDescCenter(), dadeBean.getTvGxCenter());
//            }
//        });
//        qfRight.setOnClickListener(new InputView.OnClickListener() {
//            @Override
//            public void onClick(InputView view) {
//                onQingFuClick.onRightClick(view, dadeBean.getTvRight(),
//                        dadeBean.getTvDescRight(), dadeBean.getTvGxRight());
//            }
//        });
    }

    public void setOnQingFuClickListener(OnQingFuClickListener onQingFuClick) {
        this.onQingFuClick = onQingFuClick;
    }

    public OnQingFuClickListener getOnQingFuClick() {
        return onQingFuClick;
    }


    public interface OnQingFuClickListener {
        void onLeftClick(View view, String name, String desc, String gx);

        void onCenterClick(View view, String name, String desc, String gx);

        void onRightClick(View view, String name, String desc, String gx);
    }

}
