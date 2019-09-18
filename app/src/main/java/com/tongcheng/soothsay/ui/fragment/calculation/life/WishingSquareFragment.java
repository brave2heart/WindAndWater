package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.WishingSquareAdapter;
import com.tongcheng.soothsay.base.BaseFragment;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.WishingSquare;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.ui.activity.calculate.life.MyWishingActivity;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquareConstant;
import com.tongcheng.soothsay.ui.fragment.calculation.life.wishingsquare.WishingSquarePresenter;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.DividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.umeng.socialize.utils.Log.TAG;

/**
 * Created by ALing on 2016/12/6 0006.
 */

public class WishingSquareFragment extends BaseTitleFragment implements WishingSquareConstant.View {
    @BindView(R.id.rc_wishing_square)
    RecyclerView rcWishingSquare;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    private View view;
    private WishingSquareAdapter adapter;
    private WishingSquareConstant.Presenter mPresenter;
    private int type;
    private HashMap<String, String> map;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBusUtil.register(this);
        type = getArguments().getInt("type");
        new WishingSquarePresenter(this);
        map = new HashMap<>();
        map.put("type", String.valueOf(type));
        mPresenter.getWishingSquare(map);
    }

    public static WishingSquareFragment getInstance(int type) {
        WishingSquareFragment fragment = new WishingSquareFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_wishing_square, container, false);

    }

    @Override
    public void showWishingSquare(List<WishingSquare> beans) {
        Log.e(TAG, "showWishingSquare: " + beans.size());
        adapter = new WishingSquareAdapter(getActivity(), beans, R.layout.item_rc_wishing_square);
        rcWishingSquare.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcWishingSquare.setAdapter(adapter);
        rcWishingSquare.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL, 20, getResources().getColor(R.color.divide_line)));
    }

    @Override
    public void showNetError() {
        getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getWishingSquare(map);
                getBaseEmptyView().hideEmptyView();
            }
        });
        ToastUtil.showShort(getContext(),getResources().getString(R.string.net_error));
    }

    @Override
    public void showError(String info) {
        getBaseLoadingView().hideLoading();
        getBaseEmptyView().showEmptyView(R.drawable.loadfailed,info);
    }

    @Override
    public void showEmpty() {
        getBaseEmptyView().showEmptyView(R.drawable.nodata,"暂无数据");
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading();
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {
        getBaseEmptyView().hideEmptyView();
        mPresenter.getWishingSquare(map);
    }

    @Override
    public void setPresenter(WishingSquareConstant.Presenter presenter) {
        this.mPresenter = presenter;
    }

    //网络监听
    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            //有网络且没加载成功数据时，重新请求数据
            mPresenter.getWishingSquare(map);
            getBaseEmptyView().hideEmptyView();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBusUtil.unregister(this);
    }
}
