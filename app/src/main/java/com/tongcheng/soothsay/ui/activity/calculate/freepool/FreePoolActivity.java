package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.FishBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.popwindow.FreefishPop;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.FreePoolView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 放生池
 */
public class FreePoolActivity extends BaseTitleActivity implements FreePoolConstant.View, View.OnClickListener {

    @BindView(R.id.freePoolView)        FreePoolView    freePoolView;
    @BindView(R.id.btn_free_market)     TextView        btnMarket;
    @BindView(R.id.btn_free_jilu)       TextView        btnJilu;
    @BindView(R.id.btn_free_bang)       TextView        btnBang;
    @BindView(R.id.btn_free_shuoming)   ImageView       btnShuoming;
    @BindView(R.id.headArea)            LinearLayout    headArea;
    @BindView(R.id.headBackButton)      ImageButton     headBackButton;

    private FreefishPop mPop;

    private FreePoolConstant.Presenter mPresenter;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_pool);

        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        headBackButton.setVisibility(View.VISIBLE);
        setActionBarTopPadding(headArea);
    }

    @Override
    public void initListener() {
        freePoolView.setOnFishClickListener(new FreePoolView.OnFishClickListener() {
            @Override
            public void onFishClick(View v, FishBean bean) {
                showPop(bean);
            }
        });
    }

    @OnClick({R.id.headBackButton,
            R.id.btn_free_market,
            R.id.btn_free_jilu,
            R.id.btn_free_bang,
            R.id.btn_free_shuoming})
    public void onClick(View v){
        if (ClickUtil.isFastClick()) {
            return;
        }
        switch (v.getId()){
            case R.id.headBackButton:
                finish();
                break;

            //说明
            case R.id.btn_free_shuoming:
                GotoUtil.GoToActivity(this,FreeIntroActivity.class);
                break;

            //功德榜
            case R.id.btn_free_bang:
                gotoBang();
                break;

            //放生记录
            case R.id.btn_free_jilu:
                gotojilu();
                break;

            //商城
            case R.id.btn_free_market:
                GotoUtil.GoToActivity(this,FreeMarketActivity.class);
                break;

        }
    }

    private void gotoBang(){
        GotoUtil.GoToActivity(this,FreeListActivity.class);
    }

    private void gotojilu(){
        if (!UserManager.getInstance().isLogin(this)) {
            return ;
        }
        GotoUtil.GoToActivity(this,FreeRecordActivity.class);
    }


    @Override
    public void initData() {
        new FreePoolPresenter(this);
        mPresenter.getFishList();
    }


    private int index = 0;
    @Override
    public void showFishList(final List<FishBean> beans) {
        subscription = Observable.interval(700,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        index++;
                        if(index < beans.size()){
                            freePoolView.addFish(beans.get(index));
                        }else{
                            subscription.unsubscribe();
                        }
                    }
                });

    }


    private void showPop(FishBean bean){
        if(mPop == null){
            mPop = new FreefishPop(this,R.layout.dialog_free_fish);
        }
        mPop.showFishInfo(bean);
        mPop.showPopOnRootView(this);
    }


    @Override
    public void showNetError() {
        ToastUtil.showShort(this,getString(R.string.showNeterr));
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this,info);
    }

    @Override
    public void showEmpty() {
        ToastUtil.showShort(this,"池子暂时没有鱼，施主快来放生吧");
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading(true);
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(FreePoolConstant.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    protected void onResume() {
        super.onResume();
        freePoolView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        freePoolView.onPause();
    }

    @Override
    protected void onDestroy() {
        if(subscription != null){
            subscription.unsubscribe();
        }
        super.onDestroy();
    }


}
