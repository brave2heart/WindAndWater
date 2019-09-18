package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.bean.calculation.TarotBean;

/**
 * Created by Steven on 16/12/9.
 */

public class TarotPop extends BasePopupWindow {

    private ImageView       img;
    private TextView        tvName;
    private TextView        tvPaiwei;
    private TextView        tvPaiyi;
    private TextView        tvDes;
    private Button          btn;

    public TarotPop(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public void init(View layoutView) {
        img = (ImageView) layoutView.findViewById(R.id.img_tarot_pop);
        tvName = (TextView) layoutView.findViewById(R.id.tv_tarot_pop_name);
        tvPaiwei = (TextView) layoutView.findViewById(R.id.tv_tarot_pop_paiwei);
        tvPaiyi = (TextView) layoutView.findViewById(R.id.tv_tarot_pop_paiyi);
        tvDes = (TextView) layoutView.findViewById(R.id.tv_tarot_pop_des);
        btn = (Button) layoutView.findViewById(R.id.btn_tarot_intro);
    }

    @Override
    public void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePop();
            }
        });
    }

    public void setTarotBean(TarotBean bean){
        img.setImageResource(bean.getImageId());
        tvName.setText("牌名： " + bean.getCardName());
        tvPaiyi.setText("牌义： " + bean.getPaiyi());

        if(bean.isZhengwei()){
            tvPaiwei.setText("牌位： 正位");
        }else{
            tvPaiwei.setText("牌位： 逆位");
        }
        tvDes.setText("解语： " + bean.getJieyu());

    }

}

