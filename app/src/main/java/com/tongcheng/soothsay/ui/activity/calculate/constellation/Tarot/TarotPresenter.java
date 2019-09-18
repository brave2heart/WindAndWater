package com.tongcheng.soothsay.ui.activity.calculate.constellation.Tarot;

import com.tongcheng.soothsay.data.calculate.constellation.ITarot;
import com.tongcheng.soothsay.data.calculate.constellation.TarotImp;

/**
 * Created by Steven on 16/12/6.
 */

public class TarotPresenter implements TarotConstant.Presenter{

    private TarotConstant.View mView;
    private ITarot iData;

    public TarotPresenter(TarotConstant.View mView) {
        this.mView = mView;
        iData = new TarotImp();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


}
