package com.tongcheng.soothsay.ui.activity.classroom;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.MoreMasterRecyclerviewAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.classroom.MoreMasterBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.utils.ClickUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;
import com.tongcheng.soothsay.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 宋家任 on 2016/11/3.
 * 更多大师列表
 */

public class MoreMasterActivity extends BaseTitleActivity {

    @BindView(R.id.rv_classroom_more_master)
    RecyclerView mRecycleView;
    private MoreMasterRecyclerviewAdapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_more_master);

        initData();

    }

    @Override
    public void initData() {
        getBaseHeadView().showTitle("大师亲算");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MoreMasterActivity.this.finish();
            }
        });
        getBaseLoadingView().showLoading();
        AllApi.getInstance().getmoreMaster("").enqueue(new ApiCallBack<ApiResponseBean<List<MoreMasterBean>>>(new BaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                getBaseLoadingView().hideLoading();
                List<MoreMasterBean> list = (List<MoreMasterBean>) data;
                initRecycleView(list);

            }

            @Override
            public void onError(String errorCode, String message) {
                getBaseLoadingView().hideLoading();
                if (errorCode == ApiCallBack.NET_ERROR) {
                    getBaseEmptyView().showNetWorkView(R.drawable.nonetwork, R.string.showReload, R.string.touch_again, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            initData();
                            getBaseEmptyView().hideEmptyView();
                        }
                    });
                } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {//服务器系统错误
                    getBaseEmptyView().showEmptyView(R.drawable.nonetwork, R.string.server_error, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            initData();
                            getBaseEmptyView().hideEmptyView();
                        }
                    });
                } else {
                    getBaseEmptyView().showEmptyContent();
                    ToastUtil.showShort(MoreMasterActivity.this, message);
                }
            }
        }));


    }

    private void initRecycleView(List<MoreMasterBean> list) {

        mRecycleAdapter = new MoreMasterRecyclerviewAdapter(this, list,
                R.layout.item_more_master);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mRecycleAdapter);

        mRecycleView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
}
