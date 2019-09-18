package com.tongcheng.soothsay.ui.activity.calculate.freepool;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tongcheng.soothsay.BaseUserView;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.FreeRecordAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.FreeRecordBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.FreePoolApi;
import com.tongcheng.soothsay.ui.activity.mine.LoginActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import butterknife.BindView;

/**
 * 放生记录
 */
public class FreeRecordActivity extends BaseTitleActivity implements View.OnClickListener,BaseUserView {


    @BindView(R.id.tv_month)                        TextView                tvMonth;
    @BindView(R.id.tv_all)                          TextView                tvAll;
    @BindView(R.id.tv_user)                         TextView                tvUser;
    @BindView(R.id.lv_record)                       PullToRefreshListView   listview;

    private int start = 0 ;   //翻一页就 +15

    private FreeRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_record);

        initView();
        initListener();
        initData();
    }


    @Override
    public void initView() {
        getBaseHeadView().showTitle(getString(R.string.title_free_record));
        getBaseHeadView().showBackButton(this);
    }

    @Override
    public void initListener() {
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                start += 15;
                loadMore(start);
            }
        });
    }

    @Override
    public void initData() {
        showLoad();
        requestData(start);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.headBackButton:
                finish();
                break;
        }
    }


    private void requestData(int start){
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            return;
        }

        FreePoolApi.getInstance().getFreeRecordList(token,start+"").enqueue(new ApiCallBack<ApiResponseBean<FreeRecordBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                FreeRecordBean bean = (FreeRecordBean) data;
                if(bean == null)
                    return ;

                showData(bean);

            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(FreeRecordActivity.this,errorCode,message);
            }
        }));
    }


    private void loadMore(int start){
        String token = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            return;
        }
        FreePoolApi.getInstance().getFreeRecordList(token,start+"").enqueue(new ApiCallBack<ApiResponseBean<FreeRecordBean>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                showLoadFinish();
                FreeRecordBean bean = (FreeRecordBean) data;
                if(bean == null)
                    return ;

                adapter.changeData(bean.getRecordList());
            }

            @Override
            public void onError(String errorCode, String message) {
                ErrorCodeUtil.handleErr(FreeRecordActivity.this,errorCode,message);
            }
        }));
    }

    private void showData(FreeRecordBean bean){
        adapter = new FreeRecordAdapter(FreeRecordActivity.this,bean.getRecordList(),R.layout.item_list_free_record);
        listview.setAdapter(adapter);

        String temp = "本月拯救" + bean.getMonthCount() + "个众生生命";
        SpannableStringBuilder sb = new SpannableStringBuilder(temp);
        ForegroundColorSpan fs = new ForegroundColorSpan(getResources().getColor(R.color.ziwei_topxing_red));
        sb.setSpan(fs,4,4 + bean.getMonthCount().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMonth.setText(sb);

        temp = "目前拯救" + bean.getTotalCount() + "个众生生命";
        sb.clear();
        sb.clearSpans();
        sb.append(temp);
        ForegroundColorSpan fs1 = new ForegroundColorSpan(getResources().getColor(R.color.ziwei_topxing_red));
        sb.setSpan(fs1,4,4 + bean.getTotalCount().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAll.setText(sb);

        if(!"0".equals(bean.getTotalCount())){
            String name = UserManager.getInstance().getUserName(this);
            tvUser.setText(name + "，您于");

        }
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
        listview.onRefreshComplete();
    }

    @Override
    public void showReLoad() {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void showTokenOverdue() {
        ToastUtil.showShort(this,getString(R.string.token_overdue));
        GotoUtil.GoToActivity(this, LoginActivity.class);
    }
}
