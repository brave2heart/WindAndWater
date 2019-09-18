package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.base.BaseRecyclerAdapter;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.helper.ImageHelper;

import java.util.List;

/**
 * Created by 宋家任 on 2016/10/26.
 */

public class ClassRoomRecycleViewAdapter extends AbsBaseRecycleAdapter<ClassRoomBean.StoresBean> {
    /**
     * 构造器
     *
     * @param context
     * @param mDatas
     * @param itemLayoutId
     */
    public ClassRoomRecycleViewAdapter(Context context, List<ClassRoomBean.StoresBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void bindData(AbsBaseRecycleViewHolder holder, ClassRoomBean.StoresBean bean) {
        ImageView iv = holder.getView(R.id.iv_classroom_shop);
//        ImageHelper.getInstance().display(bean.getFacePic(), iv);
        ImageHelper.getInstance().display(bean.getFacePic(), R.drawable.placeholder_sc_340, iv);
        TextView tvTitle = holder.getView(R.id.tv_classroom_shop_title);
        tvTitle.setText(bean.getName());
        TextView tvContent = holder.getView(R.id.tv_classroom_shop_content);
        tvContent.setText(bean.getYinyu());
    }


}
