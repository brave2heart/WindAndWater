package com.tongcheng.soothsay.ui.fragment.classroom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.classroom.CircletListAdapter;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.classroom.ClassRoomBean;
import com.tongcheng.soothsay.data.user.UserManager;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.living.utils.GotoUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 共修圈子
 * Created by Gubr on 2016/12/30.
 */
public class CircleListActivity extends BaseTitleActivity {

    @BindView(R.id.listview)
    ListView mListview;
    private CircletListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_listview);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        getBaseHeadView().showTitle("共修圈子");
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        getBaseLoadingView().showLoading();
        AllApi.getInstance().getCircleTypeList().enqueue
                (new ApiCallBack<ApiResponseBean<List<ClassRoomBean.CirclesBean>>>
                        (new BaseCallBack<List<ClassRoomBean.CirclesBean>>() {
                            @Override
                            public void onSuccess(List<ClassRoomBean.CirclesBean> data) {
                                if (data.size() == 0) {
                                    getBaseEmptyView().showEmptyContent();
                                } else {
                                    mAdapter=new CircletListAdapter(CircleListActivity.this,data,R.layout.item_circlet_listview);
                                    mListview.setAdapter(mAdapter);
                                    getBaseLoadingView().hideLoading();
                                }
                            }

                            @Override
                            public void onError(String errorCode, String message) {
                                ToastUtil.showShort(CircleListActivity.this, message);
                            }
                        }));
    }

    @Override
    public void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassRoomBean.CirclesBean bean = (ClassRoomBean.CirclesBean) view.getTag(R.id.tag_first);
                String url=bean.getRedirectUrl();
                if (UserManager.getInstance().isLogin()){
                    url=url+UserManager.getInstance().getToken();
                }
                GotoUtil.GoToWebViewActivity(CircleListActivity.this,bean.getName(),url);
            }
        });
    }
}
