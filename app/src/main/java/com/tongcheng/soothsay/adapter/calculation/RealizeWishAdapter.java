package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.utils.DateUtil;
import com.tongcheng.soothsay.utils.ResUtil;

import java.util.List;

/**
 * 现求愿望 适配器
 */

public class RealizeWishAdapter extends AbsBaseRecycleAdapter<QfDxBean> {

    private final ResUtil mResUtil;

    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */


    public RealizeWishAdapter(Context context, List<QfDxBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        mResUtil = ResUtil.newInstance();
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, QfDxBean godBean) {
        ImageView dxImage = holder.getView(R.id.iv_qifu_item_dx);
        TextView date = holder.getView(R.id.tv_qifu_item_date);
        TextView cxz = holder.getView(R.id.tv_qifu_item_cxz);
        TextView totalTimes = holder.getView(R.id.tv_qifu_item_totaltimes);
        TextView content = holder.getView(R.id.tv_qifu_content_text);


        dxImage.setImageResource(getDxImageResId(godBean));
        date.setText(DateUtil.formatTime(Long.valueOf(godBean.getQfDate()),"yyyy年MM月dd日"));
        cxz.setText(godBean.getQfCxz());
        totalTimes.setText("累计祈福 "+godBean.getQfTotalTimes()+"天");
        content.setText(godBean.getQfContent());

    }


    /**
     * 获取大仙的图片
     * @param godBean
     * @return
     */
//    这里只能通个大仙的名字获取图片。
    private int getDxImageResId(QfDxBean godBean){
        String qfDx = godBean.getQfDx();
//        return R.drawable.god_1;
        return mResUtil.getGodImageRes(qfDx);
    }


}
