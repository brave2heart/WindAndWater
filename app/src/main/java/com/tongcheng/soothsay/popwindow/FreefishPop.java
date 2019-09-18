package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.helper.ImageHelper;
import com.tongcheng.soothsay.bean.calculation.FishBean;
import com.tongcheng.soothsay.utils.ClickUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Steven on 16/12/2.
 */
public class FreefishPop extends BasePopupWindow implements View.OnClickListener {

    private ImageView   dlogClose;
    private ImageView   imgFish;
    private TextView    tvName;
    private TextView    tvDate;
    private TextView    tvContent;

    public FreefishPop(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public void init(View layoutView) {
        dlogClose = (ImageView) layoutView.findViewById(R.id.img_free_diloag_close);
        imgFish = (ImageView) layoutView.findViewById(R.id.img_free_diloag_fish);
        tvName = (TextView) layoutView.findViewById(R.id.img_free_diloag_name);
        tvDate = (TextView) layoutView.findViewById(R.id.img_free_diloag_date);
        tvContent = (TextView) layoutView.findViewById(R.id.img_free_diloag_info);

        dlogClose.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    //显示放生者信息
    public void showFishInfo(FishBean bean){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(Long.valueOf(bean.getFsTime())));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        tvName.setText("放生人: " + bean.getUsername());
        tvDate.setText("时间: " + year + "." + month + "." +day);
        tvContent.setText(bean.getDescription());
        ImageHelper.getInstance().display(bean.getFacePic(),imgFish);

    }


    @Override
    public void onClick(View v) {

        closePop();
    }
}
