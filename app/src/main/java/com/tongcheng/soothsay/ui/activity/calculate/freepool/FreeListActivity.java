package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tongcheng.soothsay.BaseUiView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.FreeListAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.FreeTopBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.FreePoolApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import butterknife.BindView;

/**
 * 功德榜
 */
public class FreeListActivity extends BaseTitleActivity implements BaseUiView{

    @BindView(R.id.lv_free_list)            PullToRefreshListView   listView;
    @BindView(R.id.tv_free_list)            TextView                tvTitle;

    private FreeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_list);

        initView();
        initData();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_free_list));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    @Override
    public void initData() {
        showLoad();
        requestData();
    }



    private void requestData(){
        String token = UserManager.getInstance().getToken();
        FreePoolApi.getInstance().getFreeTopList(token).enqueue(new ApiCallBack<ApiResponseBean<FreeTopBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                FreeTopBean bean = (FreeTopBean) data;
                if(bean != null && bean.getTopList().size() != 0){
                    adapter = new FreeListAdapter(FreeListActivity.this,bean.getTopList(),R.layout.item_list_free_bang);
                    listView.setAdapter(adapter);
                    tvTitle.setText(bean.getTitle());
                }
            }

            @Override
            public void onError(String errorCode, String message) {
                showLoadFinish();
                if(ApiCallBack.NET_ERROR.equals(errorCode)){
                    showNetError();
                }else{
                    showError(message);
                }
            }
        }));
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

    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
