package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.AbsBaseAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleViewHolder;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.helper.ImageHelper;

import java.util.List;

/**
 * Created by Gubr on 2016/12/30.
 * 共修圈子适配器
 */

public class CircletListAdapter extends AbsBaseAdapter<ClassRoomBean.CirclesBean> {


    public CircletListAdapter(Context context, List<ClassRoomBean.CirclesBean> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, ClassRoomBean.CirclesBean circlesBean, int position) {
        ImageHelper.getInstance().display(circlesBean.getFacePic(),(ImageView) holder.getView(R.id.iv_item_circletlist_facepic));
        holder.setText(R.id.tv_item_circletlist_name, circlesBean.getName())
                .setText(R.id.tv_item_circlelist_count, "话题:"+circlesBean.getCount())
                .setTag(circlesBean);
    }




}
