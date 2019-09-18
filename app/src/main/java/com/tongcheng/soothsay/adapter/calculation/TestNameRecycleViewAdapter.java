package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.calculation.NameTestBean;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/16.
 * 姓名测试recycleview适配器
 */

public class TestNameRecycleViewAdapter extends AbsBaseRecycleAdapter<NameTestBean.WuGeBean> {
    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public TestNameRecycleViewAdapter(Context context, List<NameTestBean.WuGeBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, NameTestBean.WuGeBean wuGeBean) {
        //五格
        TextView tvWg = (TextView) holder.getView(R.id.tv_testname_item_wg);
        tvWg.setText(wuGeBean.getType());
        //五行
        TextView tvwx = (TextView) holder.getView(R.id.tv_testname_item_wx);
        tvwx.setText("五行：" + wuGeBean.getWuXing());
        //吉凶
        TextView tvjx = (TextView) holder.getView(R.id.tv_testname_item_jx);
        tvjx.setText("吉凶：" + wuGeBean.getJiXiong());
        //分值
        TextView tvfz = (TextView) holder.getView(R.id.tv_testname_item_fz);
        tvfz.setText("分值：" + wuGeBean.getScore());
        //解析
        TextView tvjiex = (TextView) holder.getView(R.id.tv_testname_item_jiex);
        tvjiex.setText("解析：" + wuGeBean.getJieXi());
    }




}
