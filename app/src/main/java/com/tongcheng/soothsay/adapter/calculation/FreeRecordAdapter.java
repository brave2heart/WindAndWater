package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.FreeRecordBean;

import java.util.List;

/**
 * Created by Steven on 16/12/21.
 */

public class FreeRecordAdapter extends AbsBaseAdapter<FreeRecordBean.RecordListBean>{


    public FreeRecordAdapter(Context context, List<FreeRecordBean.RecordListBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }


    @Override
    public void bindData(ViewHolder holder, FreeRecordBean.RecordListBean bean, int position) {
        TextView tvDate = holder.getViewV2(R.id.tv_free_record_date);
        TextView tvTime = holder.getViewV2(R.id.tv_free_record_time);
        TextView tvContent = holder.getViewV2(R.id.tv_free_record_content);

        tvDate.setText(bean.getBuyDate());
        tvTime.setText(bean.getBuyTime());
        tvContent.setText(bean.getInfo());
    }
}
