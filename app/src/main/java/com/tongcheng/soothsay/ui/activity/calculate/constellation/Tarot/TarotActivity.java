package com.tongcheng.soothsay.ui.activity.calculate.constellation.Tarot;

import android.os.Bundle;
import android.view.View;

import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.TarotBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.other.tarot.TarotCardView;
import com.tongcheng.soothsay.other.tarot.TarotManager;
import com.tongcheng.soothsay.popwindow.TarotIntroPop;
import com.tongcheng.soothsay.popwindow.TarotPop;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.widget.TarotView;

import butterknife.BindView;

/**
 * 塔罗占卜
 */
public class TarotActivity extends BaseTitleActivity implements TarotConstant.View, TarotView.OnTarotFlowListener {

    @BindView(R.id.TarotView)       TarotView tarotView;

    private int type;

    private TarotPop mPop;
    private TarotIntroPop introPop;
    private TarotManager tarotManager;

    private TarotConstant.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot);

        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_tarot));

        mPop = new TarotPop(this,R.layout.pop_tarot_explain);
        introPop = new TarotIntroPop(this,R.layout.pop_tarot_intro,false);
    }

    @Override
    public void initListener() {
        tarotView.setOnTarotFlowListener(this);
        tarotView.setOnTarotClickListener(new TarotView.OnTarotClickListener() {
            @Override
            public void onTarotClick(TarotCardView card) {
                TarotBean bean = card.getTarotBean();
                if(!card.isOpen()){
                    if(!bean.isZhengwei()){
                        card.setRotation(180);
                    }
                    card.switchBg();
                }
                String jieyu = tarotManager.getjieYu(bean.getIndex(),type,bean.isZhengwei());
                bean.setJieyu(jieyu);
                mPop.setTarotBean(bean);
                mPop.showPopOnRootView(TarotActivity.this);
            }
        });

        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        introPop.setOnIntroClickListener(new TarotIntroPop.OnIntroClickListener() {
            @Override
            public void introClick(View v) {

                int flag = tarotView.getSelectFlag();
                switch (flag){
                    case 0:
                        tarotView.startShuffleAnim();
                        break;

                    case 1:
                        tarotView.dealCard();
                        break;
                }

            }
        });
    }


    //洗牌完成
    @Override
    public void ShuffleFinish() {
        introPop.setIntro(getString(R.string.tarot_choupai));
        introPop.showPopOnRootView(this);
    }

    //发牌完成
    @Override
    public void DealFinish() {}

    @Override
    public void initData() {
        new TarotPresenter(this);
        tarotView.postDelayed(new Runnable() {
            @Override
            public void run() {
                introPop.setIntro(getString(R.string.tarot_xipai));
                introPop.showPopOnRootView(TarotActivity.this);
            }
        },100);

        tarotManager = new TarotManager(MyApplicationLike.getInstance());

        type = getIntent().getIntExtra(Constant.INTENT_DATA,1);
    }


    @Override
    public void showNetError() {

    }

    @Override
    public void showError(String info) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {

    }

    @Override
    public void showLoadFinish() {

    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(TarotConstant.Presenter presenter) {
        mPresenter = presenter;
    }


}
