package com.tongcheng.soothsay.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.mine.MyMessageAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.event.EventNetWorkChangeBean;
import com.tongcheng.soothsay.bean.mine.MyMessage;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.common.WebViewActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.EventBusUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ALing on 2017/1/3 0003.
 */

public class MyMessageActivity extends BaseTitleActivity implements BaseUserView, AdapterView.OnItemClickListener {
    @BindView(R.id.list_my_message)
    PullToRefreshListView listView;
    private String token;
    private int start = 0;
    private HashMap<String, String> map;
    private List<MyMessage.MyMyssageListBean> list;
    private MyMessage bean;
    private MyMessageAdapter adapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_my_message);
        EventBusUtil.register(this);
        initData();
        initListener();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(R.string.title_my_message);
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                start += 15;
                getData();
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        token = UserManager.getInstance().getToken();
        Log.e("token", "initData: token" + token);
        getData();
    }

    private void getData() {
        if (adapter == null) {
            showLoad();
        }
        map = new HashMap<>();
        map.put("start", start + "");
        map.put("token", token);
        Log.e(TAG, "getData: " + token + ",,," + start);
        AllApi.getInstance().getMyMessageList(map).enqueue(new ApiCallBack<ApiResponseBean<MyMessage>>(new BaseCallBack() {

            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                bean = (MyMessage) data;

                if (bean == null) {
                    getBaseEmptyView().hideEmptyView();
                    showEmpty();
                    return;
                }else if (adapter == null && bean.getCommunList().size() == 0){
                    showEmpty();
                }

                list = bean.getCommunList();
                if (adapter == null) {
                    showData(list);

                    //加载更多
                } else if (adapter != null) {
                    if (list.size() != 0) {
                        adapter.changeData(list);
                        listView.onRefreshComplete();
                    }
                    if (list.size() == 0) {
                        listView.onRefreshComplete();
                        ToastUtil.showShort(MyMessageActivity.this, "没有更多数据");
                        listView.onRefreshComplete();
                    } else {
                        listView.onRefreshComplete();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                showLoadFinish();
                ErrorCodeUtil.handleErr(MyMessageActivity.this, errorCode, message);
            }
        }));
    }

    private void showData(List<MyMessage.MyMyssageListBean> list) {
//        设置适配器
        adapter = new MyMessageAdapter(this, list, R.layout.item_my_messag);
        listView.setAdapter(adapter);
    }

    @Override
    public void showTokenOverdue() {
        GotoUtil.GoToActivity(this, LoginActivity.class);
    }

    @Override
    public void showNetError() {
        if (adapter == null) {
            getBaseEmptyView().showNetWorkView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getBaseEmptyView().hideEmptyView();
                    getData();
                }
            });
        } else {
            ToastUtil.showShort(this, getString(R.string.showNeterr));
        }
    }

    @Override
    public void showError(String info) {
        ToastUtil.showShort(this, info);
    }

    @Override
    public void showEmpty() {
        if (adapter == null) {
            getBaseEmptyView().showEmptyView(R.drawable.nodata,"暂无消息");
        }
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showLoad() {
        getBaseLoadingView().showLoading();
        listView.onRefreshComplete();
    }

    @Override
    public void showLoadFinish() {
        getBaseLoadingView().hideLoading();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.LOGIN) {
            getData();
        }
    }

    //      item点击跳转WebView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemClick: !!!!!! "+position );
        if (position == 1){
            index = 0;
        }else{
            index = position - 1;
        }
        if (listView.isRefreshing()){
            Log.i(TAG, "onItemClick: "+listView.isRefreshing()+"......"+ adapter.getDatas().size() );
        }else {

            Log.i(TAG, "onItemClick: +position======"+position );
            List<MyMessage.MyMyssageListBean> messageList = adapter.getDatas();
            String url = messageList.get(index).getRedirectUrl();
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("web_url", url + token);
            intent.putExtra("web_title", "消息详情");
            startActivity(intent);
        }
    }
    //网络监听
    @Subscribe
    public void onEvent(EventNetWorkChangeBean bean) {
        if (bean.getEvent() == EventNetWorkChangeBean.NETWORK_CONNECTED) {
            //有网络且没加载成功数据时，重新请求数据
            getData();
            getBaseEmptyView().hideEmptyView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}
