package com.tongcheng.soothsay.ui.fragment.calculation.life;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.adapter.calculation.TestNameRecycleViewAdapter;
import com.tongcheng.soothsay.base.BaseTitleFragment;
import com.tongcheng.soothsay.bean.calculation.NameTestBean;
import com.tongcheng.soothsay.constant.Constant;
import com.tongcheng.soothsay.http.ApiCallBack;
import com.tongcheng.soothsay.http.ApiResponseBean;
import com.tongcheng.soothsay.http.BaseCallBack;
import com.tongcheng.soothsay.http.service.AllApi;
import com.tongcheng.soothsay.ui.activity.calculate.life.TestNameActivity;
import com.tongcheng.soothsay.utils.ErrorCodeUtil;
import com.tongcheng.soothsay.utils.LogUtil;
import com.tongcheng.soothsay.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;


/**
 * Created by 宋家任 on 2016/11/4.
 * 姓名测算结果页
 */

public class TestNameResultFragment extends BaseTitleFragment {


    @BindView(R.id.tv_testname_name)
    TextView tvName;
    @BindView(R.id.tv_testname_wx)
    TextView tvWx;
    @BindView(R.id.tv_testname_bh)
    TextView tvBh;
    @BindView(R.id.tv_testname_tbh)
    TextView tvTbh;
    @BindView(R.id.rv_testname)
    RecyclerView mRecycleView;
    @BindView(R.id.tv_testname_cs)
    TextView tvCs;
    @BindView(R.id.tv_testname_xg)
    TextView tvXg;
    String name;
    private List<NameTestBean.WuGeBean> mDatas;
    private TestNameRecycleViewAdapter mAdapter;
    //    private NewTestNameRecycleViewAdapter mAdapter;
    private TestNameFirstFragment firstFragment;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_testname_result, container, false);
        firstFragment = new TestNameFirstFragment();
        return view;
    }

    @Override
    public void initData() {
        name = ((TestNameActivity) getActivity()).getName();
        if (!"".equals(name)) {

            getNameMsg();
        }
    }

    /**
     * 根据传过来的姓名测算
     */
    private void getNameMsg() {
        AllApi.getInstance().getNameMsg(name).
                enqueue(new ApiCallBack<ApiResponseBean<NameTestBean>>(new BaseCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        NameTestBean bean = (NameTestBean) data;
                        tvName.setText("姓名:" + name);
                        tvWx.setText("五行 ：" + bean.getWuXing());
                        tvBh.setText("笔画：" + bean.getBiHua());
                        tvTbh.setText("总笔画：" + bean.getTBiHua());
                        mDatas = bean.getWuGe();
                        if (mDatas.size() != 0)
                            initRecycleView();
                        tvCs.setText(bean.getJieLun().get(1));
                        tvXg.setText(bean.getJieLun().get(2));

                    }

                    @Override
                    public void onError(String errorCode, String message) {
                        //网络错误
                        if (errorCode == ApiCallBack.NET_ERROR) {
                            ToastUtil.showShort(getContext(), getContext().getResources().getString(R.string.net_error));

                            //服务器系统错误
                        } else if (Constant.ErrorCode.SERVER_ERROR_99999.equals(errorCode)) {
                            ToastUtil.showShort(getContext(), "服务器系统错误");
                        } else
                            ToastUtil.showShort(getContext(), message);
                    }
                }));

    }

    private void initRecycleView() {
        mAdapter = new TestNameRecycleViewAdapter(getContext(), mDatas, R.layout.item_testname_recycleview);
//        mAdapter = new NewTestNameRecycleViewAdapter(getContext(), mDatas);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mRecycleView.setAdapter(mAdapter);

    }

}
