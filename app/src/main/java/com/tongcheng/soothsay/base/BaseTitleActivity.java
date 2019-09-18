package com.tongcheng.soothsay.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.tongcheng.soothsay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseTitleActivity extends BaseActivity {

    @BindView(R.id.view_stub_base_head)         ViewStub        viewStubBaseHead;           //标题栏
    @BindView(R.id.view_stub_base_loading)      ViewStub        viewStubBaseLoading;        //正在加载
    @BindView(R.id.view_stub_base_empty)        ViewStub        viewStubBaseEmpty;          //无数据

    @BindView(R.id.contentArea)                 FrameLayout     contentArea;
    @BindView(R.id.parentArea)                  LinearLayout    parentArea;

    private LoadingView baseLoadingView;
    private EmptyView baseEmptyView;
    private HeadView baseHeadView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_head_activity);
        contentArea = (FrameLayout) findViewById(R.id.contentArea);
    }


    @Override
    public void setContentView(int layoutResID) {
        View v = getLayoutInflater().inflate(layoutResID, contentArea, false);
        setContentView(v);

    }
    @Override
    public void setContentView(View view) {
        setContentView(view, view.getLayoutParams());
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentArea.addView(view, 0, params);
        ButterKnife.bind(this);
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


    protected void setActionBarTopPadding(View v){
        if (v != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int stataHeight = getStatusBarHeight();
                v.getLayoutParams().height = v.getLayoutParams().height + stataHeight;
                v.setPadding(v.getPaddingLeft(),
                        stataHeight,
                        v.getPaddingRight(),
                        v.getPaddingBottom());

                //要加阴影所以..
                if(baseHeadView != null){
                    baseHeadView.getRootHeadView().getLayoutParams().height += stataHeight;
                }
            }
        }
    }

    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        Window window;
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

}
