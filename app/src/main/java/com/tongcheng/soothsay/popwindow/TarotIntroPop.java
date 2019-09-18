package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;

/**
 * Created by Steven on 16/12/10.
 * 塔罗牌介绍pop
 */
public class TarotIntroPop extends BasePopupWindow implements View.OnClickListener {

    private TextView    tvIntro;
    private Button      btn;

    private OnIntroClickListener listener;

    public TarotIntroPop(Activity activity, int resId) {
        super(activity, resId);
    }

    public TarotIntroPop(Activity activity, int resId, boolean outSide) {
        super(activity, resId,outSide);
    }

    @Override
    public void init(View layoutView) {
        tvIntro = (TextView) layoutView.findViewById(R.id.tv_tarot_intro);
        btn = (Button) layoutView.findViewById(R.id.btn_tarot_intro);
    }

    @Override
    public void initListener() {
        btn.setOnClickListener(this);
    }

    public void setIntro(String text){
        tvIntro.setText(text);
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.introClick(v);

        closePop();
    }

    public interface OnIntroClickListener{

        void introClick(View v);
    }

    public void setOnIntroClickListener(OnIntroClickListener listener) {
        this.listener = listener;
    }
}
