package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.classroom.MoreMasterBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.living.utils.GotoUtil;

import java.util.List;

/**
 * Created by 宋家任 on 2016/12/2.
 * 大师列表适配器
 */

public class MoreMasterRecyclerviewAdapter extends AbsBaseRecycleAdapter<MoreMasterBean> {
    private Context mContext;

    /**
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public MoreMasterRecyclerviewAdapter(Context context, List<MoreMasterBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.mContext = context;
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, final MoreMasterBean moreMasterBean) {
        ImageView iv = holder.getView(R.id.riv_master);
        ImageHelper.getInstance().display(moreMasterBean.getTouXiang(), iv);

        TextView tvName = holder.getView(R.id.tv_master_name);
        tvName.setText(moreMasterBean.getName());

        TextView tvChenghao = holder.getView(R.id.tv_master_chenghao);
        tvChenghao.setText("称号：" + moreMasterBean.getChengHao());

        TextView tvJianjie = holder.getView(R.id.tv_master_jianjie);
        tvJianjie.setText("简介：" + moreMasterBean.getJianJie());

        TextView tvSanchang = holder.getView(R.id.tv_master_sanchang);
        tvSanchang.setText("擅长：" + moreMasterBean.getShanChang());

        BootstrapButton btn = holder.getView(R.id.btn_master_yuyue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserManager.getInstance().isLogin())
                    GotoUtil.GoToWebViewActivity(mContext, "大师详情", moreMasterBean.getUrl() + UserManager.getInstance().getToken());
                else
                    GotoUtil.GoToWebViewActivity(mContext, "大师详情", moreMasterBean.getUrl());
            }
        });

//        TextView tvMore = holder.getView(R.id.tv_master_more);
//        tvMore.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        tvMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳转H5页面
//                if (ClickUtil.isFastClick()) return;
//                ToastUtil.showShort(mContext, "大师详情");
//            }
//        });

    }
}
