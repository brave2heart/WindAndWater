package com.tongcheng.soothsay.ui.fragment.calculation.divination.oneiromancy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;

import com.tongcheng.soothsay.bean.calculation.ZGJMDetailBean;
import com.tongcheng.soothsay.data.calculate.divination.IOneiromancySource;
import com.tongcheng.soothsay.utils.RegexUtils;
import com.tongcheng.soothsay.utils.SystemTools;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gubr on 2016/12/7.
 */

public class OneiromancyPresenter implements OneiromancyContract.Presenter {


    private String mTaskId;
    private final IOneiromancySource mOneiromancySource;
    private OneiromancyContract.InputView mOneiromancyInputView;
    private OneiromancyContract.DetailView mOneiromancyDetailView;

    public OneiromancyPresenter(@Nullable String taskId, @NonNull IOneiromancySource
            oneiromancySource,
                                @NonNull OneiromancyContract.InputView oneiromancyInputView) {
        mTaskId = taskId;
        mOneiromancySource = oneiromancySource;
        mOneiromancyInputView = oneiromancyInputView;
        mOneiromancyInputView.setPresenter(this);
    }

    public OneiromancyPresenter(@Nullable String taskId, @NonNull IOneiromancySource
            oneiromancySource,
                                @NonNull OneiromancyContract.DetailView oneiromancyDetailView) {
        mTaskId = taskId;
        mOneiromancySource = oneiromancySource;
        mOneiromancyDetailView = oneiromancyDetailView;
        mOneiromancyDetailView.setPresenter(this);
    }


    @Override
    public void getDetail(int position) {
        ZGJMDetailBean.ResultBean details = mOneiromancySource.getDetails(position);
//        mOneiromancyDetailView.showDetail(details);
        EventBus.getDefault().post(details);
        EventBus.getDefault().post(OneiromancyContract.DetailView.TAG);
    }

    @Override
    public void showInput() {
        EventBus.getDefault().post(OneiromancyContract.InputView.TAG);
    }


    @Override
    public void query(String str) {

        if (!RegexUtils.isZh(str)) {
            mOneiromancyInputView.showMustHanZi();
            return;
        }
        if (mOneiromancyInputView == null) return;
//        mOneiromancySource.getOneiromancyList(str, new IOneiromancySource.OnGetQueryBean() {
//            @Override
//            public void getQueryBean(List<ZGJMDetailBean.ResultBean> bean) {
//
//                if (bean.size() == 0) {
//                    mOneiromancyInputView.showEmptyToast();
//                    return;
//                }
//                mOneiromancyInputView.showResult(bean);
//            }
//
//
//        });
        mOneiromancyInputView.showLoad();
        mOneiromancySource.getOneiromancyList(str, new Callback<ZGJMDetailBean>() {
            @Override
            public void onResponse(Call<ZGJMDetailBean> call, Response<ZGJMDetailBean> response) {
                if (response.body() == null||response.body().getResult().size()==0) {
                    mOneiromancyInputView.showLoadFinish();
                    mOneiromancyInputView.showEmptyToast();
                    return;

                }
                List<ZGJMDetailBean.ResultBean> result = response.body().getResult();
                mOneiromancySource.setResult(result);
                mOneiromancyInputView.showResult(result);
            }

            @Override
            public void onFailure(Call<ZGJMDetailBean> call, Throwable t) {
                mOneiromancyInputView.showLoadFinish();
                mOneiromancyInputView.showError("网络出错");
            }
        });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void removeDetailView() {

    }

    @Override
    public void start() {

    }
}
