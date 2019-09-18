package com.tongcheng.soothsay.adapter.calculation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.bean.calculation.CalculationBannerBean;
import com.tongcheng.soothsay.bean.calculation.MingLiBean;
import com.tongcheng.soothsay.helper.ImageHelper;

/**
 * @description: 命理推算中广告条的适配器
 * @author: lijuan
 * @date: 2016-10-24
 * @time: 16:55
 */
public class BannerAdapter implements Holder<MingLiBean.AdsBean> {
    private ImageView imageView;
//    private TextView tv_title;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        View view = View.inflate(context, R.layout.banner_layout, null);
        imageView = (ImageView) view.findViewById(R.id.img);
//        tv_title = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, MingLiBean.AdsBean data) {
        ImageHelper.getInstance().display(data.getCover(), imageView);

    }

//    @Override
//    public void UpdateUI(Context context, int position, CalculationBannerBean data) {
//        tv_title.setText(data.getContent());
//        if (context != null && context instanceof Activity) {
//            Activity activity = (Activity) context;
//            if (!activity.isFinishing()) {
//            }
//        }
//    }
}
