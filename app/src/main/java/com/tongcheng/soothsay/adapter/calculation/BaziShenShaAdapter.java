package com.tongcheng.soothsay.adapter.calculation;

import android.content.Context;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.bazi.BaziShenShaBean;

import java.util.List;

/**
 * Created by Gubr on 2016/12/29.
 * 神煞介绍适配器
 */

public class BaziShenShaAdapter extends AbsBaseAdapter<BaziShenShaBean> {
    public BaziShenShaAdapter(Context context, List<BaziShenShaBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, BaziShenShaBean baziShenShaBean, int position) {
        holder.setText(R.id.bazi_shensha_title,baziShenShaBean.getShenSha())
                .setText(R.id.bazi_shensha_content,baziShenShaBean.getJieXi());
    }
}
