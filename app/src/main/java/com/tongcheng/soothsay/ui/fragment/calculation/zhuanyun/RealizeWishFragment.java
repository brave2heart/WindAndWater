package com.tongcheng.soothsay.ui.fragment.calculation.zhuanyun;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.RealizeWishAdapter;
import com.tongcheng.soothsay.base.AbsBaseRecycleAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.QfDxBean;
import com.tongcheng.soothsay.bean.event.ChangeWishBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.PrayingApi;
import com.tongcheng.soothsay.ui.activity.calculate.zhuanyun.DxDetailActivity;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by bozhihuatong on 2016/12/2.
 * 现求愿望
 */

public class RealizeWishFragment extends BaseTitleFragment {
    @BindView(android.R.id.list)
    RecyclerView mList;
    @BindView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    private List<QfDxBean> mDatas;
    private RealizeWishAdapter mRealizeWishAdapter;
    private QfDxBean mQfDxBean;
    private int mPosition;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_swiperefresh, container, false);
        return view;
    }

    @Override
    public void initData() {
        EventBusUtil.register(this);

        mRealizeWishAdapter = new RealizeWishAdapter(getActivity(), mDatas, R.layout.qifu_wishunfinished_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(mRealizeWishAdapter);
        mRealizeWishAdapter.setOnItemClickListener(new AbsBaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                mQfDxBean = mRealizeWishAdapter.getDatas().get(position);
                mPosition = position;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("DxDetail", mQfDxBean.getQfDx());
                GotoUtil.GoToActivityWithData(getActivity(), DxDetailActivity.class, hashMap);
            }
        });
        getDxList();
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshWidget.setRefreshing(false);
                Toast.makeText(getActivity(), "更新完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Subscribe()
    public void showEventBus(ChangeWishBean wishBean) {
        if (mQfDxBean != null) {
            mQfDxBean.setQfContent(wishBean.Wish);
            mRealizeWishAdapter.getDatas().set(mPosition, mQfDxBean);
            mRealizeWishAdapter.notifyDataSetChanged();
        }
//        mQiFuWishContent.setText(wishBean.Wish);
//        mDetailAdapter.notifyDataSetChanged();
    }


    private void getDxList() {
        PrayingApi.getInstance().qfDxList(UserManager.getInstance().getUser().getToken())
                .enqueue(new ApiCallBack<ApiResponseBean<List<QfDxBean>>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {

                        mRealizeWishAdapter.setDatas((List<QfDxBean>) data);
                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    public void onDestroy() {
        EventBusUtil.unregister(this);
        super.onDestroy();
    }
}
