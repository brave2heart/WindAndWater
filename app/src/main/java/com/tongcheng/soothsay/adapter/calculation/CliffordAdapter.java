package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.CliffordBean;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.PrayingStationActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;

import java.util.List;

/**
 * @description: 祈福转运的适配器
 * @author: lijuan
 * @date: 2016-10-25
 * @time: 16:19
 */
public class CliffordAdapter extends AbsBaseAdapter<CliffordBean> {
    private Activity mContext;

    public CliffordAdapter(Context context, List<CliffordBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        this.mContext = (Activity) context;
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, CliffordBean cliffordBean, int position) {
        ((TextView) holder.getView(R.id.tv_name)).setText(cliffordBean.getName());
        ((TextView) holder.getView(R.id.tv_content)).setText(cliffordBean.getContent());

        TextView mBtnOpen = (TextView) holder.getView(R.id.tv_open);
        mBtnOpen.setOnClickListener(new open(cliffordBean.getId()));
    }

    public class open implements View.OnClickListener {
        private int id;

        public open(int id) {
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            if (ClickUtil.isFastClick()) return;

            GotoUtil.GoToActivity(mContext, PrayingStationActivity.class);
        }
    }
}
