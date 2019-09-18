package com.tongcheng.soothsay.ui.activity.calculate.divination;

import android.os.Bundle;
import android.view.KeyEvent;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.data.calculate.divination.OneiromancySourceImpl;
import com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy.OneiromancyContract;
import com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy.OneiromancyFragmentDetails;
import com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy.OneiromancyFragmentInput;
import com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy.OneiromancyPresenter;
import com.tongcheng.soothsay.utils.ActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by bozhihuatong on 2016/12/7.
 * 周公解梦
 */

public class OneiromancyActivity extends BaseTitleActivity {
    private OneiromancyFragmentDetails mOneiromancyFragmentDetails;
    private OneiromancyFragmentInput mOneiromancyFragmentInput;
    private OneiromancySourceImpl mOneiromancySource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneiromancy);
        EventBus.getDefault().register(this);
        mOneiromancyFragmentDetails = (OneiromancyFragmentDetails) getSupportFragmentManager().findFragmentByTag(OneiromancyFragmentDetails.TAG);

        if (mOneiromancyFragmentDetails == null) {
            mOneiromancyFragmentDetails = OneiromancyFragmentDetails.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mOneiromancyFragmentDetails,
                    R.id.contentFrame,OneiromancyFragmentDetails.TAG);
            getSupportFragmentManager().beginTransaction().hide(mOneiromancyFragmentDetails).commit();
        }

        mOneiromancyFragmentInput = (OneiromancyFragmentInput) getSupportFragmentManager()
                .findFragmentByTag(OneiromancyFragmentInput.TAG);

        if (mOneiromancyFragmentInput == null) {
            mOneiromancyFragmentInput = OneiromancyFragmentInput.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mOneiromancyFragmentInput,
                    R.id.contentFrame,OneiromancyFragmentInput.TAG);
        }

        if (mOneiromancySource==null) {
            mOneiromancySource = new OneiromancySourceImpl();
        }
        new OneiromancyPresenter("InputPresenter",mOneiromancySource,mOneiromancyFragmentInput);
        new OneiromancyPresenter("Detailspresenter",mOneiromancySource,mOneiromancyFragmentDetails);

    }


    @Subscribe
    public void changeView(String showTag) {
        switch (showTag) {
            case OneiromancyContract.DetailView.TAG:
                getSupportFragmentManager().beginTransaction().hide(mOneiromancyFragmentInput).show(mOneiromancyFragmentDetails).commit();
                break;
            case OneiromancyContract.InputView.TAG:
                getSupportFragmentManager().beginTransaction().hide(mOneiromancyFragmentDetails).show(mOneiromancyFragmentInput).commit();
                break;
            default:
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK &&mOneiromancyFragmentDetails.isVisible()) {
            changeView(OneiromancyContract.InputView.TAG);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
