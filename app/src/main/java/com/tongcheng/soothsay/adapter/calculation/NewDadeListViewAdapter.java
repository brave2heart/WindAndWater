package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.bean.calculation.LingFuBean;
import com.tongcheng.soothsay.bean.calculation.OneLingFuBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.LingfuActivity;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.utils.CusDialogUtil;

import java.util.List;


/**
 * Created by 宋家任 on 2016/11/9.
 * 大德符运listview适配器
 */

public class NewDadeListViewAdapter extends AbsBaseAdapter<List<LingFuBean>> {

    String[] desc = new String[]{};
    String[] gx = new String[]{};
    private Activity mActivity;
    String id;

    /**
     * @param context
     * @param id           符所在大组的id
     * @param datas
     * @param itemLayoutId
     * @param desc         介绍
     * @param gx           功效
     * @param activity
     */
    public NewDadeListViewAdapter(Context context, String id, List<List<LingFuBean>> datas, int itemLayoutId, String[] desc, String[] gx, Activity activity) {
        super(context, datas, itemLayoutId);
        this.desc = desc;
        this.gx = gx;
        this.mActivity = activity;
        this.id = id;
    }

    @Override
    public void bindData(AbsBaseAdapter.ViewHolder holder, final List<LingFuBean> lingFuBeen, int position) {
        for (int i = 0; i < lingFuBeen.size(); i++) {
            final LingFuBean bean = lingFuBeen.get(i);
            int id = getContext().getResources().getIdentifier("tv_dadefuyun_item_box_name_left" + i, "id", getContext().getPackageName());
            TextView tvfm = (TextView) holder.getView(id);
            tvfm.setText(bean.getName());//符名

            int id1 = getContext().getResources().getIdentifier("tv_dadefuyun_item_qing_text" + i, "id", getContext().getPackageName());
            TextView tvqf = (TextView) holder.getView(id1);
            tvqf.setVisibility(View.VISIBLE);

            tvqf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) return;

                    if (UserManager.getInstance().isLogin()) {
                        getLingfuInfo(bean.getName());
                    } else {
                        new CusDialogUtil(mActivity).showCusDialog(R.string.txt_dade_login, Constant.RequestCode.DADE_LOGIN);
                    }
                }
            });
            int id2 = getContext().getResources().getIdentifier("iv_dadefuyun_item_box" + i, "id", getContext().getPackageName());
            ImageView ivbox = (ImageView) holder.getView(id2);
            String status = bean.getStatus();
            ivbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickUtil.isFastClick()) return;

                    if (UserManager.getInstance().isLogin()) {
                        getLingfuInfo(bean.getName());
                    } else {
                        new CusDialogUtil(mActivity).showCusDialog(R.string.txt_dade_login, Constant.RequestCode.DADE_LOGIN);
                    }
                }
            });
            int id3 = getContext().getResources().getIdentifier("iv_dadefuyun_marks" + i, "id", getContext().getPackageName());
            ImageView ivMark = (ImageView) holder.getView(id3);

            if ("1".equals(status)) {//默认状态
                ivbox.setVisibility(View.VISIBLE);
            } else if ("2".equals(status)) {//已经请符过，等待开光
                ivbox.setImageResource(R.drawable.fy_dade_box_status_qing);
                tvqf.setText("开光");
                ivbox.setVisibility(View.VISIBLE);
            } else if ("3".equals(status)) {//已经开光，等待加持
                ivbox.setImageResource(R.drawable.fy_dade_box_status_kai);
                ivbox.setVisibility(View.VISIBLE);
                tvqf.setText("加持");
            } else if ("4".equals(status)) {
                ivbox.setImageResource(R.drawable.fy_dade_box_status_jia);
                ivbox.setVisibility(View.VISIBLE);
                tvqf.setText("已加持");
            }
            if ("1".equals(bean.getIsfree())&&"1".equals(status)) {//免费请符
                ivMark.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * 获取点击的灵符信息
     */
    private void getLingfuInfo(String name) {
        AllApi.getInstance().getoneLingFuInfo(UserManager.getInstance().getToken(), name).
                enqueue(new ApiCallBack<ApiResponseBean<List<OneLingFuBean>>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        List<OneLingFuBean> beans = (List<OneLingFuBean>) data;
                        OneLingFuBean bean = beans.get(0);
                        Intent intent = new Intent(mActivity, LingfuActivity.class);
                        intent.putExtra("bean", bean);
                        intent.putExtra("desc", desc);
                        intent.putExtra("gongxiao", gx);
                        intent.putExtra("zu_id", id);
                        getContext().startActivity(intent);
                        mActivity.overridePendingTransition(R.anim.activity_in_from_right,
                                R.anim.activity_out_of_left);
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        if (Constant.ErrorCode.RESPONSE_ERROR_LOST_10007.equals(errorCode)) {//token过期
                            new CusDialogUtil(mActivity).showCusDialog(R.string.login_error, Constant.RequestCode.DADE_LOGIN);
                        }
                    }
                }));
    }


}
