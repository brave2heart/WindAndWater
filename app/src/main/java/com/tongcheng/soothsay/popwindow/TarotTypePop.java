package com.tongcheng.soothsay.popwindow;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BasePopupWindow;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Steven on 16/12/9.
 */
public class TarotTypePop extends BasePopupWindow {

    public static final int LOVE = 0;
    public static final int SHIYE = 1;
    public static final int MONEY = 2;
    public static final int HEALTH = 3;


    @BindView(R.id.tv_tarot_type_love)      TextView tvLove;
    @BindView(R.id.tv_tarot_type_shiye)     TextView tvShiye;
    @BindView(R.id.tv_tarot_type_money)     TextView tvMoney;
    @BindView(R.id.tv_tarot_type_health)    TextView tvHealth;
    @BindView(R.id.btn_tarot_type)          TextView   btn;

    private int type = -1;

    private String [] types = {"爱情","事业","财富","健康"};

    private OnTypeSelectListener listener;

    public TarotTypePop(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public void init(View layoutView) {
        ButterKnife.bind(this,layoutView);
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.tv_tarot_type_love,
            R.id.tv_tarot_type_shiye,
            R.id.tv_tarot_type_money,
            R.id.tv_tarot_type_health,
            R.id.btn_tarot_type})
    public void onClick(View v){

        switch (v.getId()){
            case R.id.tv_tarot_type_love:
                changeType(LOVE);
                type = LOVE;
                if(listener != null)
                    listener.onTypeSelect(types[type]);
                break;

            case R.id.tv_tarot_type_shiye:
                changeType(SHIYE);
                type = SHIYE;
                if(listener != null)
                    listener.onTypeSelect(types[type]);
                break;

            case R.id.tv_tarot_type_money:
                changeType(MONEY);
                type = MONEY;
                if(listener != null)
                    listener.onTypeSelect(types[type]);
                break;

            case R.id.tv_tarot_type_health:
                changeType(HEALTH);
                type = HEALTH;
                if(listener != null)
                    listener.onTypeSelect(types[type]);
                break;

            default:
                closePop();
        }
    }

    private void changeType(int flag){
        tvLove.setSelected(flag == LOVE);
        tvShiye.setSelected(flag == SHIYE);
        tvMoney.setSelected(flag == MONEY);
        tvHealth.setSelected(flag == HEALTH);
    }

    public int getType() {
        return type;
    }


    public interface OnTypeSelectListener{
        void onTypeSelect(String type);
    }

    public void setOnTypeSelectListener(OnTypeSelectListener listener) {
        this.listener = listener;
    }
}
