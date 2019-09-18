package com.tongcheng.soothsay.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseTitleFragment extends Fragment {
    final public String TAG = this.getClass().getSimpleName();

    private View baseRootView;

    @BindView(R.id.view_stub_base_head)
    ViewStub viewStubBaseHead;
    @BindView(R.id.view_stub_base_loading)
    ViewStub viewStubBaseLoading;
    @BindView(R.id.view_stub_base_empty)
    ViewStub viewStubBaseEmpty;

    @BindView(R.id.contentArea)
    FrameLayout contentArea;
    @BindView(R.id.parentArea)
    LinearLayout parentArea;

    private LoadingView baseLoadingView;
    private EmptyView baseEmptyView;
    private HeadView baseHeadView;


    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        baseRootView = inflater.inflate(R.layout.base_head_activity, container, false);
        contentArea = (FrameLayout) baseRootView.findViewById(R.id.contentArea);
        setContentView(initView(inflater, container));
        onGetBundle(getArguments());
        initListener();
        initData();
        return baseRootView;
    }

    private View setContentView(View view) {
        contentArea.addView(view, 0);
        unbinder = ButterKnife.bind(this, baseRootView);
        return baseRootView;
    }

    /**
     * onCreatView 中调用
     *
     * @param inflater
     * @param container
     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    public void initListener() {
    }

    public void initData() {
    }


    /**
     * onCreateView中调用
     * 可以获取上一个Fragment传过来的数据
     */
    protected void onGetBundle(Bundle bundle) {
    }


    public LoadingView getBaseLoadingView() {
        if (baseLoadingView == null) {
            baseLoadingView = new LoadingView(viewStubBaseLoading.inflate());
        }
        return baseLoadingView;
    }

    public EmptyView getBaseEmptyView() {
        if (baseEmptyView == null) {
            baseEmptyView = new EmptyView(viewStubBaseEmpty.inflate());
        }
        return baseEmptyView;
    }

    public HeadView getBaseHeadView() {
        if (baseHeadView == null) {
            baseHeadView = new HeadView(viewStubBaseHead.inflate());
            setActionBarTopPadding(baseHeadView.getParentView());
        }
        return baseHeadView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //官方文档只是解除了对 fragment 的绑定
        unbinder.unbind();
    }

    protected void setActionBarTopPadding(View v) {
        if (v != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int stataHeight = getStatusBarHeight();
                v.getLayoutParams().height = v.getLayoutParams().height + stataHeight;
                v.setPadding(v.getPaddingLeft(),
                        stataHeight,
                        v.getPaddingRight(),
                        v.getPaddingBottom());

                //要加阴影所以..
                if (baseHeadView != null) {
                    baseHeadView.getRootHeadView().getLayoutParams().height += stataHeight;
                }
            }
        }
    }

    //状态栏高度
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        Window window;
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public View getBaseRootView() {
        return baseRootView;
    }

    public FrameLayout getContentArea() {
        return contentArea;
    }
}
