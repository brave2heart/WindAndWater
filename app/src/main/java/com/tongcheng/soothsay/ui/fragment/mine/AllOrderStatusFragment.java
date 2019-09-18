package com.tongcheng.soothsay.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.mine.AllOrderAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.bean.event.EventPayResultBean;
import com.tongcheng.soothsay.bean.mine.AllOrder;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.ui.activity.mine.MineOrderActivity;
import com.tongcheng.soothsay.ui.activity.pay.PayConstant;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/25.
 * 所有订单状态fragment
 */

public class AllOrderStatusFragment extends BaseTitleFragment implements BaseUserView{


    @BindView(R.id.allorder_list)
    PullToRefreshListView allorderList;
    private int status;
    private String token;
    private HashMap<String,String> map;
    private int start = 0;//翻一页就 +15
    private AllOrderAdapter adapter;
    private List<AllOrder> list;

    public static AllOrderStatusFragment getInstance(int status) {
        AllOrderStatusFragment fragment = new AllOrderStatusFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_all_order_status, container, false);

    }

    @Override
    public void initListener() {
        super.initListener();
        allorderList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                start += 15;
                getAllOrder();
            }
        });
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.base_empty_error, allorderList, false);
        View viewById = inflate.findViewById(R.id.base_reload);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                重新加载数据
                getAllOrder();
            }
        });
        allorderList.setEmptyView(inflate);
    }

    @Override
    public void initData() {
        EventBusUtil.register(this);
        status = getArguments().getInt("status");
        token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)){
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }
        Log.e("tag", "initData: " + token + "..." + status);

        getAllOrder();
    }

    public void getAllOrder() {
        if(adapter == null){
            showLoad();
        }
        map = new HashMap<>();
        map.put("token", token);
        map.put("status", String.valueOf(status));
        map.put("start", String.valueOf(start));
        AllApi.getInstance().getAllOrder(map).enqueue(new ApiCallBack<ApiResponseBean<List<AllOrder>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                list = (List<AllOrder>) data;
                if (list.size() == 0) {
                    allorderList.onRefreshComplete();
                    ToastUtil.showShort(getActivity(), "没有更多数据");
                }
                if (list != null &&  list.size() == 0) {
                    getBaseEmptyView().hideEmptyView();
                    showEmpty();
                    return;
                }

                if(adapter == null){
                    showData(list);

                    //加载更多
                }else if(adapter != null ){
                    if(list.size() != 0){
                        adapter.changeDatas(list);
                    }
                    else{
                        allorderList.onRefreshComplete();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                showLoadFinish();
                if (Constant.ErrorCode.TOKEN_ERROR_10001.equals(errorCode)){
                    showTokenOverdue();
                }else {
                    ErrorCodeUtil.handleErr(AllOrderStatusFragment.this,errorCode,message);
                }

//                ErrorCodeUtil.showHaveTokenError(getActivity(), errorCode);
            }
        }));
    }

    private void showData(List<AllOrder> list) {
        adapter = new AllOrderAdapter(getActivity(),list,status,new int[]{R.layout.item_my_order,R.layout.item_my_order});
        allorderList.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBusUtil.unregister(this);
    }

    @Override
    public void showTokenOverdue() {
        GotoUtil.GoToActivity(getActivity(), LoginActivity.class);
    }

    @Override
    public void showNetError() {
        if(adapter == null){
            getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getBaseEmptyView().hideEmptyView();
                    getAllOrder();
                }
            });
        }else{
            ToastUtil.showShort(getContext(),getString(R.string.showNeterr));
        }
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(getContext(),info);
    }

    @Override
    public void showEmpty() {
        if(adapter == null){
            getBaseEmptyView().showEmptyContent();
        }
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
        allorderList.onRefreshComplete();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(Object presenter) {

    }
    /**
     * 支付回调
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPayResult(EventPayResultBean bean) {
        int code = bean.status;
        switch (code) {
            case PayConstant.ERR_CODE_PAY_FINISH:
                ToastUtil.showShort(getActivity(),getResources().getString(R.string.pay_result_success));
                adapter = null;
                getAllOrder();
                break;

            case PayConstant.ERR_CODE_PAY_CANCEL:
                Intent intent1 = new Intent(getActivity(), MineOrderActivity.class);
                intent1.putExtra(Constant.INTENT_DATA,1);
                getActivity().startActivity(intent1);
                break;
        }
    }

    //网络监听
    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            //有网络且没加载成功数据时，重新请求数据
            getAllOrder();
            getBaseEmptyView().hideEmptyView();
        }
    }

}
