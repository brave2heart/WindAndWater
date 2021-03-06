package com.tongcheng.soothsay.adapter.classroom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.bean.classroom.ShopHomeBean;
import com.tongcheng.soothsay.helper.ImageHelper;

/**
 * Created by 宋家任 on 2016/10/25.
 * 商城轮播图适配器
 */
public class ShopHomeBannerAdapter implements Holder<ShopHomeBean.AdsBean> {
    private ImageView iv;
    private View view;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        view = View.inflate(context, R.layout.banner_layout_classroom, null);
        iv = (ImageView) view.findViewById(R.id.iv_classroom_banner);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, ShopHomeBean.AdsBean data) {
//        ImageHelper.getInstance().display(data.getCover(), iv);
        ImageHelper.getInstance().display(data.getCover(), R.drawable.place_holder_banner_1, iv);
    }
}
