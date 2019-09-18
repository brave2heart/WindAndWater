package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.event.EventJiFenBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.utils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bozhihuatong on 2016/11/30.
 */

public class CreditsObtainIntroFragment extends BaseTitleFragment {


    TextView mTvCreditsObtainIntroMine;
    TextView mTvCreditsCount;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_credits_obtain_intro, container, false);
        mTvCreditsCount=(TextView)view.findViewById(R.id.tv_credits_count);
        String jifenstr = String.format(Locale.CANADA, "积分 %s", getjifeng());
        mTvCreditsCount.setText(jifenstr);
        mTvCreditsObtainIntroMine= (TextView)view.findViewById(R.id.tv_credits_obtain_intro_mine);
        String introStr = String.format(Locale.CANADA, "100积分奖励  %s/1", getIsFinishIntro());
        mTvCreditsObtainIntroMine.setText(introStr);
        return view;
    }

    public String getjifeng(){
        return UserManager.getInstance().getUserJf(getActivity());
    }


    public String getIsFinishIntro() {
        return "1";
    }



    @Subscribe
    public void setJifen (EventJiFenBean bean){
       String jifen= bean.getJiFen();
        mTvCreditsCount.setText("积分 "+jifen);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBusUtil.register(this);
    }


    @Override
    public void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();

    }
}
